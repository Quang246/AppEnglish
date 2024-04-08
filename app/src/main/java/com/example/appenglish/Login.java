package com.example.appenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DTO_User.UtilData;
import Database.CreateDatabase;

public class Login extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_app);
        Button btn_Login;
        btn_Login = findViewById(R.id.btn_Login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendDataToEditAcount();
                CheckDT();
            }

        });
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
        EditText edtEmail, edtUser,edtPass;
        edtEmail = findViewById(R.id.edtEmail);
        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        String email = edtEmail.getText().toString().trim();
        String username = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        if(email.isEmpty()) {
            edtEmail.setError("Vui lòng nhập dữ liệu vào trường này!");
        }else if(username.isEmpty()){
            edtUser.setError("Vui lòng nhập dữ liệu vào trường này!");
        }else if(pass.isEmpty()){
            edtPass.setError("Vui lòng nhập dữ liệu vào trường này!");
        }else{
            boolean kiemtra = dtb.KTLogin(email,username,pass);
            if(kiemtra){
                UtilData.mEmail = email;
                UtilData.mUsername =username;
                Intent intent = new Intent(Login.this, editAcount.class);
                startActivity(intent);
                Intent i = new Intent(Login.this, Home.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Email, Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
