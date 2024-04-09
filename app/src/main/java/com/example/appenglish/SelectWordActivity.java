package com.example.appenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.adapter.SelectWordAdapter;
import com.example.appenglish.models.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Database.CreateDatabase;

public class SelectWordActivity extends AppCompatActivity {
    CreateDatabase db = new CreateDatabase(this);
    RecyclerView rvSelectWord;
    TextView txtSw;
    ArrayList<CardModel> setWordIndex;
    ArrayList<CardModel> arWords = new ArrayList<>();
    ArrayList<CardModel> subArWords = new ArrayList<>();
    Random random = new Random();
    Set<Integer> set = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_word);
        rvSelectWord = findViewById(R.id.rvSelectWord);
        txtSw = findViewById(R.id.txtSw);

        setInit();

//      show dialog_complete
//        if (set.size() == arWords.size()) {
////            final Dialog dialog = new Dialog(this);
////            dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
////            dialog.setContentView(R.layout.dialog_complete);
////
////            Window window = dialog.getWindow();
////            if (window == null) {
////                return;
////            }
////            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
////            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////
////            WindowManager.LayoutParams windowAttribute = window.getAttributes();
////            windowAttribute.gravity = Gravity.CENTER;
////
////            window.setAttributes(windowAttribute);
////
//////              ấn ra khoảng trống sẽ tắt dialog
////            dialog.setCancelable(true);
////            dialog.findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent i = new Intent(SelectWordActivity.this, PracticeActivity.class);
////                    startActivity(i);
////                }
////            });
////            dialog.show();
//            Toast.makeText(this, String.valueOf(set.size()), Toast.LENGTH_SHORT).show();
//        }

    }

    public void setInit() {
        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");

        setWordIndex = new ArrayList<>(db.getAllCards(userEmail));

        for (CardModel cm: db.getAllCards(userEmail)) {
            CardModel w = new CardModel(cm.getTxtCard_1(), cm.getTxtCard_2());
            arWords.add(w);
        }

        Collections.shuffle(arWords);

        for (CardModel cm: arWords) {
            subArWords.add(cm);
            if (subArWords.size() == 4) {
                break;
            }
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rvSelectWord.setLayoutManager(mLayoutManager);

//      set ngau nhieu cho txtSw
        int r = random.nextInt(arWords.size());
        String w = arWords.get(r).getTxtCard_1();
        txtSw.setText(w);

//      check subArWords truoc khi setAdapter
//        while (checkWord(txtSw.getText().toString(), subArWords) == false) {
//            subArWords.clear();
//            Collections.shuffle(arWords);
//            for (CardModel cm: arWords) {
//                subArWords.add(cm);
//                if (subArWords.size() == 4) {
//                    break;
//                }
//            }
//        }

        // Tạo và thiết lập Adapter cho RecyclerView
        SelectWordAdapter selectWordAdapter = new SelectWordAdapter(subArWords, new SelectWordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CardModel cm = subArWords.get(position);
//                String w = setWordIndex.get(i).getTxtCard_1();
                String d = cm.getTxtCard_2();
//                txtSw.setText(w);

                if ((db.checkWordDes(txtSw.getText().toString(), d) == true) || (db.checkWordDes(d, txtSw.getText().toString()) == true)) {
                    Toast.makeText(SelectWordActivity.this, "Correct !!", Toast.LENGTH_SHORT).show();
                    updateSubArWords();
                } else {
                    Toast.makeText(SelectWordActivity.this, "Incorrect !!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        rvSelectWord.setAdapter(selectWordAdapter);
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

        SelectWordAdapter selectWordAdapter = (SelectWordAdapter) rvSelectWord.getAdapter();
        if (selectWordAdapter != null) {
            selectWordAdapter.notifyDataSetChanged();
        }

        randomIndex = random.nextInt(arWords.size());
        randomWord = arWords.get(randomIndex).getTxtCard_1();
        txtSw.setText(randomWord);

//        String s = txtSw.getText().toString();
        while (checkWord() == false) {
            randomIndex = random.nextInt(arWords.size());
            randomWord = arWords.get(randomIndex).getTxtCard_1();
            txtSw.setText(randomWord);
        }
    }

    public boolean checkWord() {
        for (CardModel cm: subArWords) {
            if ((db.checkWordDes(txtSw.getText().toString(), cm.getTxtCard_2()) == true) || (db.checkWordDes(cm.getTxtCard_2(), txtSw.getText().toString()) == true)) {
                return true;
            }
        }
        return false;
    }

//    public boolean checkWord(String s, ArrayList<CardModel> subArWords) {
//        for (CardModel cm: subArWords) {
//            if ((db.checkWordDes(s, cm.getTxtCard_2()) == true) || (db.checkWordDes(cm.getTxtCard_2(), s) == true)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean checkWord(String s, ArrayList<CardModel> subArWords) {
//        for (CardModel cm: subArWords) {
//            if ((db.checkWordDes(s, cm.getTxtCard_2()) == true) || (db.checkWordDes(cm.getTxtCard_2(), s) == true)) {
//                return true;
//            }
//        }
//        return false;
//    }

}