package com.example.appenglish.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.Home;
import com.example.appenglish.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Login extends AppCompatActivity {
    Button btn_Login;
    TextView txtSign_up;
    EditText edtEmail,edtPass;
    ImageView btnGoogle;
    CreateDatabase dtb;

//  after login, sharedPreferences = email
    SharedPreferences sharedPreferences, sharedPreferencesUsername;

//  sign in with Google
    GoogleSignInOptions gso;
    GoogleSignInClient gsi;
    private static final int RC_SIGN_IN = 123;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_app);

        setUp();

        dtb = new CreateDatabase(this);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckDT();
            }
        });
//                v -> CheckDT()
        txtSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

//      login with google
//      call API to authentication and signin with Google acc -> provide interface user-friendly
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
//
        gsi = GoogleSignIn.getClient(Login.this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String googleEmail = account.getEmail();
                Toast.makeText(Login.this, "Updating...!!!", Toast.LENGTH_SHORT).show();
//                signIn();

//              tìm email trong database
//              my code ....

            }
        });
    }


    void setUp () {
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btn_Login = findViewById(R.id.btn_Login);
        txtSign_up = findViewById(R.id.txtSign_up);
        btnGoogle = findViewById(R.id.btnGoogle);
    }
    void CheckDT() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();

        if (email.isEmpty()){
            edtEmail.setError("Vui lòng nhập Email!");
        } else if (pass.isEmpty()){
            edtPass.setError("Vui lòng nhập Password!");
        } else {
            boolean kiemtra = dtb.KTLogin(email, pass);
            if (kiemtra) {
//              save email for word
                sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorEmail = sharedPreferences.edit();
                sharedPreferencesUsername = getSharedPreferences("username", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorUsername = sharedPreferencesUsername.edit();
                editorEmail.putString("email", email);
                editorEmail.clear();
                editorEmail.apply();
//                ...
                String un = dtb.getUsername(email, pass);
                editorUsername.putString("username", un);
                editorUsername.clear();
                editorUsername.apply();

                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void signIn() {
        Intent signInIntent = gsi.getSignInIntent();
        startActivity(signInIntent);
    }



}
