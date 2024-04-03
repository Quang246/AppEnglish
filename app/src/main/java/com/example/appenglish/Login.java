package com.example.appenglish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.models.User;

public class Login extends AppCompatActivity {
    Button btn_Login;
    TextView txtSign_up;
    EditText edtEmail,edtPass;
    CreateDatabase dtb;

//  after login, sharedPreferences = email
    SharedPreferences sharedPreferences;
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
                Intent intent = new Intent(Login.this, DashboardActivity.class);
                startActivity(intent);


            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
