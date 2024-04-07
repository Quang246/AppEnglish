package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeActivity extends AppCompatActivity {
    Button btnMeanWord, btnMatchWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        btnMatchWord = findViewById(R.id.btnMatchWord);
        btnMeanWord = findViewById(R.id.btnMeanWord);

        btnMatchWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PracticeActivity.this, MatchWordActivity.class);
                startActivity(i);
            }
        });

    }
}