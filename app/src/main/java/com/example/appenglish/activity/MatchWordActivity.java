package com.example.appenglish.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.R;
import com.example.appenglish.adapters.PracticeWordAdapter;
import com.example.appenglish.models.CardModel;
import com.example.appenglish.models.CardWord;

import java.util.ArrayList;
import java.util.Collections;

public class MatchWordActivity extends AppCompatActivity {
    RecyclerView rvMatchWord;
    ArrayList<CardWord> arWords = new ArrayList<>();
    CreateDatabase db = new CreateDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_word);
        rvMatchWord = findViewById(R.id.rvMatchWord);
        setInit();
    }

    public void setInit() {
        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");
        for (CardModel cm: db.getAllCards(userEmail)) {
            CardWord w1 = new CardWord(cm.getTxtCard_1());
            CardWord w2 = new CardWord(cm.getTxtCard_2());
            arWords.add(w1);
            arWords.add(w2);
        }

//      shuffer aray
        Collections.shuffle(arWords);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMatchWord.setLayoutManager(gridLayoutManager);

        PracticeWordAdapter practiceWordAdapter = new PracticeWordAdapter(this, arWords);
        rvMatchWord.setAdapter(practiceWordAdapter);
    }

}