package com.example.appenglish.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.R;

public class EditAccountActivity extends AppCompatActivity {
    EditText edtMail, edtUser, edtPass, edtNewpass;
    Button btn_Update, btn_Delete;
    TextView nameProfile;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

//        get email from login
        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");

        SharedPreferences sharedPreferencesUn = getSharedPreferences("username", Context.MODE_PRIVATE);
        String userName = sharedPreferencesUn.getString("username", "");

        edtMail = findViewById(R.id.edtMail);
        edtUser = findViewById(R.id.edtUser);
        btn_Update = findViewById(R.id.btn_Update);
        btn_Delete = findViewById(R.id.btn_Delete);
        nameProfile = findViewById(R.id.nameProfile);

        nameProfile.setText(userName);
        edtMail.setText(userEmail);
        edtMail.setFocusable(false);
        edtMail.setFocusableInTouchMode(false);
        edtUser.setText(userName);

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDT();
            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDT();
            }
        });
    }
    CreateDatabase dtb = new CreateDatabase(this);
    public void UpdateDT(){
        edtNewpass = findViewById(R.id.edtNewpass);
        edtPass = findViewById(R.id.edtCurrentPass);
        String email = edtMail.getText().toString();
        String username = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        String newPass = edtNewpass.getText().toString();
        boolean kiemtra = dtb.KTPass(email,pass);
        if(pass.isEmpty()){
            edtPass.setError("Please enter your current password!");
        }else if(newPass.isEmpty()){
            edtNewpass.setError("Please enter your current password!");
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(EditAccountActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle("Are you sure to update the information?");
            builder.setMessage("Choose yes or no!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(kiemtra){
                        dtb.updateData(email, username, newPass);
                        Toast.makeText(getApplicationContext(), "Successfully Update!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Current passwword is not correct!", Toast.LENGTH_SHORT).show();
                    }
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(EditAccountActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                    builder2.setTitle("Please login again!");
                    builder2.setMessage("Choose yes!");
                    builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(EditAccountActivity.this, Login.class);
                            startActivity(i);
                        }
                    });
                    builder2.show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        }

    }
    public void DeleteDT(){
        String email = edtMail.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(EditAccountActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
        builder.setTitle("Are you sure you want to delete your account?");
        builder.setMessage("Choose yes or no!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dtb.deleteData(email);
                Intent i = new Intent(EditAccountActivity.this, Register.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}