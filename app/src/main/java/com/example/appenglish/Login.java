package com.example.appenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.CreateDatabase;

public class Login extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_app);
        Button btn_Login;
        btn_Login = findViewById(R.id.btn_Login);
        btn_Login.setOnClickListener(v -> CheckDT());
        TextView txtSign_up;
        txtSign_up = findViewById(R.id.txtSign_up);
        txtSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
    CreateDatabase dtb = new CreateDatabase(this);
    void CheckDT(){
        EditText edtUser,edtPass;
        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        String username = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        if(username.isEmpty()){
            edtUser.setError("Vui lòng nhập dữ liệu vào trường này!");
        }else if(pass.isEmpty()){
            edtPass.setError("Vui lòng nhập dữ liệu vào trường này!");
        }else{
            boolean kiemtra = dtb.KTLogin(username,pass);
            if(kiemtra){
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
