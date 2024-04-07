package com.example.appenglish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.models.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class Login extends AppCompatActivity {
    Button btn_Login;
    TextView txtSign_up;
    EditText edtEmail,edtPass;
    ImageView btnGoogle;
    CreateDatabase dtb;

//  after login, sharedPreferences = email
    SharedPreferences sharedPreferences;

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
//                Toast.makeText(Login.this, googleEmail, Toast.LENGTH_SHORT).show();
//                signIn();

//              tìm email trong database
//              my code ....

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to research");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
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
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.clear();
                editor.apply();

                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void signIn() {
        Intent signInIntent = gsi.getSignInIntent();
        startActivity(signInIntent);
    }

//    @Override
//    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                finish();
//                Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Login.this, DashboardActivity.class);
//                startActivity(i);
//            } else {
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
