package com.example.appenglish.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.Database.CreateDatabase;
import com.example.appenglish.R;
import com.example.appenglish.adapters.CardAdapter;
import com.example.appenglish.models.CardModel;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment {
    RecyclerView rvCard;
    Button btnAddCard;
    TextView txtSaveWord;
    ImageView btnPrev, btnFilter, btnBaCham;
    CreateDatabase db;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dash_board,container,false);
        db = new CreateDatabase(getContext());

//        get email from login
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("email", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");

        setUp();
        onClick(userEmail);
        getInit(userEmail);

        return view;
    }

    void setUp() {
//        btnPrev = view.findViewById(R.id.btnPrev);
//        btnFilter = view.findViewById(R.id.btnFilter);
//        btnBaCham = view.findViewById(R.id.btnBaCham);
        rvCard = view.findViewById(R.id.rvSaveWord);
        btnAddCard = view.findViewById(R.id.btnAddCard);
        txtSaveWord = view.findViewById(R.id.txtSaveWord);
    }

    void onClick(String userEmail) {
        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
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

                        Toast.makeText(getContext(), "Success !!", Toast.LENGTH_SHORT).show();

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

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCard.setLayoutManager(mLayoutManager);

        CardAdapter cardAdapter = new CardAdapter(getContext(), arCards);
        rvCard.setAdapter(cardAdapter);

        txtSaveWord.setText("ĐÃ GHI NHỚ: " + arCards.size());

    }

}