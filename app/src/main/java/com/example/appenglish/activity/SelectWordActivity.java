package com.example.appenglish.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.R;
import com.example.appenglish.adapters.SelectWordAdapter;
import com.example.appenglish.models.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SelectWordActivity extends AppCompatActivity {
    CreateDatabase db = new CreateDatabase(this);
    RecyclerView rvSelectWord;
    TextView txtSw, pages;
    ArrayList<CardModel> setWordIndex = new ArrayList<>();
    ArrayList<CardModel> arWords = new ArrayList<>();
    ArrayList<CardModel> subArWords = new ArrayList<>();
    Random random = new Random();
    ArrayList<Integer> set = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_word);
        rvSelectWord = findViewById(R.id.rvSelectWord);
        txtSw = findViewById(R.id.txtSw);
        pages = findViewById(R.id.pages);

        setInit();

    }

    public void setInit() {
        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");

        ArrayList<CardModel> cardLists = new ArrayList<>(db.getAllCards(userEmail));
        arWords.addAll(cardLists);
        setWordIndex.addAll(cardLists);

//        for (CardModel cm: db.getAllCards(userEmail)) {
//            CardModel w = new CardModel(cm.getTxtCard_1(), cm.getTxtCard_2());
//            arWords.add(w);
//        }

//        for (CardModel cm: db.getAllCards(userEmail)) {
//            CardModel w = new CardModel(cm.getTxtCard_1(), cm.getTxtCard_2());
//            setWordIndex.add(w);
//        }

        Collections.shuffle(arWords);

//        for (CardModel cm: arWords) {
//            subArWords.add(cm);
//            if (subArWords.size() == 4) {
//                break;
//            }
//        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rvSelectWord.setLayoutManager(mLayoutManager);

//      set ngau nhieu cho txtSw
        int r = random.nextInt(arWords.size());
        String w = setWordIndex.get(r).getTxtCard_1();
        txtSw.setText(w);

//      check trung
        updateSubArWords();
        pages.setText(set.size() + "/" + arWords.size());

        // Tạo và thiết lập Adapter cho RecyclerView
        SelectWordAdapter selectWordAdapter = new SelectWordAdapter(subArWords, new SelectWordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                CardModel cm = subArWords.get(position);
                String d = cm.getTxtCard_2();

                if ((db.checkWordDes(txtSw.getText().toString(), d) == true) || (db.checkWordDes(d, txtSw.getText().toString()) == true)) {
                    Toast.makeText(SelectWordActivity.this, "Correct !!", Toast.LENGTH_SHORT).show();
                    updateSubArWords();

                } else {
                    Toast.makeText(SelectWordActivity.this, "Incorrect !!", Toast.LENGTH_SHORT).show();
                }

                pages.setText(set.size() + "/" + arWords.size());

            }
        });
        rvSelectWord.setAdapter(selectWordAdapter);

    }

//  set dialog
    public void showFullDialog() {
        final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
            dialog.setContentView(R.layout.dialog_complete);

            Window window = dialog.getWindow();
            if (window == null) {
                return;
            }
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams windowAttribute = window.getAttributes();
            windowAttribute.gravity = Gravity.CENTER;

            window.setAttributes(windowAttribute);

//              ấn ra khoảng trống sẽ tắt dialog
            dialog.setCancelable(true);
            dialog.findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(SelectWordActivity.this, SelectWordActivity.class);
//                    startActivity(i);
//                    dialog.dismiss();

                    // Khởi tạo Intent để khởi động lại SelectWordActivity
                    Intent intent = getIntent();
                    finish(); // Kết thúc Activity hiện tại
//
//                    // Khởi động lại SelectWordActivity bằng Intent
                    startActivity(intent);
                    dialog.dismiss();
//                    recreate();
                    set.clear();
                }
            });
            dialog.show();
    }

    public void updateSubArWords() {

        int randomIndex;
        String randomWord;

        subArWords.clear();
        Collections.shuffle(arWords);

        for (CardModel cm: arWords) {
            subArWords.add(cm);
            if (subArWords.size() == 4) {
                break;
            }
        }

//        SelectWordAdapter selectWordAdapter = (SelectWordAdapter) rvSelectWord.getAdapter();
//        if (selectWordAdapter != null) {
//            selectWordAdapter.notifyDataSetChanged();
//        }

        // Cập nhật lại adapter của RecyclerView
        if (rvSelectWord.getAdapter() != null) {
            rvSelectWord.getAdapter().notifyDataSetChanged();
        }

        randomIndex = random.nextInt(setWordIndex.size());
        randomWord = setWordIndex.get(randomIndex).getTxtCard_1();
        txtSw.setText(randomWord);

//        String s = txtSw.getText().toString();

        if (set.size() != arWords.size()) {
            while ((checkWord() == false) || (checkSet(randomIndex) == false)) {
                randomIndex = random.nextInt(setWordIndex.size());
                randomWord = setWordIndex.get(randomIndex).getTxtCard_1();
                txtSw.setText(randomWord);
            }
            set.add(randomIndex);
        } else {
            showFullDialog();
        }

//        while ((checkWord() == false) || (checkSet(randomIndex) == false)) {
//            randomIndex = random.nextInt(setWordIndex.size());
//            randomWord = setWordIndex.get(randomIndex).getTxtCard_1();
//            txtSw.setText(randomWord);
//        }
//        set.add(randomIndex);

    }

    public boolean checkWord() {
        for (CardModel cm: subArWords) {
            if ((db.checkWordDes(txtSw.getText().toString(), cm.getTxtCard_2()) == true) || (db.checkWordDes(cm.getTxtCard_2(), txtSw.getText().toString()) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSet(int r) {
        if (set.contains(r)) {
            return false;
        }
        return true;
    }

}