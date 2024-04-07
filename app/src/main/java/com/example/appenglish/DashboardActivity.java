package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.adapters.CardAdapter;
import com.example.appenglish.models.CardModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView rvCard;
    Button btnAddCard;
    TextView txtSaveWord;
    ImageView btnPrev, btnFilter, btnBaCham;
    CreateDatabase db = new CreateDatabase(this);
//    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnPrev = findViewById(R.id.btnPrev);
        btnFilter = findViewById(R.id.btnFilter);
        btnBaCham = findViewById(R.id.btnBaCham);
        rvCard = findViewById(R.id.rvSaveWord);
        btnAddCard = findViewById(R.id.btnAddCard);
        txtSaveWord = findViewById(R.id.txtSaveWord);

//      get email from login
        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");

//        Toast.makeText(this, account.getEmail(), Toast.LENGTH_SHORT).show();

        getInit(userEmail);

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
                dialog.setContentView(R.layout.dialog_add_flashcard);

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

                EditText editWord = dialog.findViewById(R.id.editWord);
                EditText editDescription = dialog.findViewById(R.id.editDescription);
                Button btnCancle = dialog.findViewById(R.id.btnCancel);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);

                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String valueEditWord = editWord.getText().toString();
                        String valueDescription = editDescription.getText().toString();

                        CardModel cardModel = new CardModel();
                        cardModel.setTxtCard_1(valueEditWord);
                        cardModel.setTxtCard_2(valueDescription);
                        cardModel.setUser_email(userEmail);

                        Toast.makeText(DashboardActivity.this, userEmail, Toast.LENGTH_SHORT).show();

//                      insert database
                        db.insertWord(cardModel);
                        getInit(userEmail);
                        dialog.dismiss();
                    }
                });
//              show dialog
                dialog.show();
            }
        });
    }

    public void getInit(String email) {
        ArrayList<CardModel> arCards = db.getAllCards(email);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvCard.setLayoutManager(mLayoutManager);

        CardAdapter cardAdapter = new CardAdapter(this, arCards);
        rvCard.setAdapter(cardAdapter);

        txtSaveWord.setText("ĐÃ GHI NHỚ: " + arCards.size());

    }
}