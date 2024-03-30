package com.example.appenglish;

import android.content.Intent;
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

public class Login extends AppCompatActivity {
    Button btn_Login;
    TextView txtSign_up;
    EditText edtUser,edtPass;
    CreateDatabase dtb;

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
        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btn_Login = findViewById(R.id.btn_Login);
        txtSign_up = findViewById(R.id.txtSign_up);
    }
    void CheckDT() {

        String username = edtUser.getText().toString();
        String pass = edtPass.getText().toString();

        if (username.isEmpty()){
            edtUser.setError("Vui lòng nhập dữ liệu vào trường này!");
        } else if (pass.isEmpty()){
            edtPass.setError("Vui lòng nhập dữ liệu vào trường này!");
        } else {
            boolean kiemtra = dtb.KTLogin(username,pass);
            if (kiemtra) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, SearchActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
