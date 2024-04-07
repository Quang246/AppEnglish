package com.example.appenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    LinearLayout cardSearch, cardFlashCard, cardPractice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_app);

        cardSearch = findViewById(R.id.cardSearch);
        cardFlashCard = findViewById(R.id.cardFlashCard);
        cardPractice = findViewById(R.id.cardPractice);
        cardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, SearchActivity.class);
                startActivity(i);
            }
        });

        cardFlashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, DashboardActivity.class);
                startActivity(i);
            }
        });

        cardPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, PracticeActivity.class);
                startActivity(i);
            }
        });

    }
}
