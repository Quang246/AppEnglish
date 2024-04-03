package com.example.appenglish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appenglish.models.User;
import com.example.appenglish.Database.CreateDatabase;

public class Register extends AppCompatActivity {
    CreateDatabase dtb = new CreateDatabase(this);
    Button btn_register;
    TextView txtSign_in;
    EditText edtEmail, edtUser,edtPass,edtRePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_app);

        btn_register = findViewById(R.id.btn_Register);
        txtSign_in = findViewById(R.id.txtSign_in);
        edtEmail = findViewById(R.id.edtEmail);
        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        edtRePass = findViewById(R.id.edtRePass);

        btn_register.setOnClickListener(v -> CheckDT());
        txtSign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    void CheckDT() {
        String email = edtEmail.getText().toString();
        String username = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        String repass = edtRePass.getText().toString();

        if(email.isEmpty()) {
            edtEmail.setError("Vui lòng nhập dữ liệu vào trường này!");
        } else if(username.isEmpty()) {
            edtUser.setError("Vui lòng nhập dữ liệu vào trường này!");
        } else if(pass.isEmpty()) {
            edtPass.setError("Vui lòng nhập dữ liệu vào trường này!");
        } else if(repass.isEmpty()) {
            edtRePass.setError("Vui lòng nhập dữ liệu vào trường này!");
        } else {
            if(repass.equals(pass)) {
                User user = new User();
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(pass);
                user.setRepass(repass);

                dtb.insertTK(user);

                Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Mật khẩu chưa trùng khớp!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
