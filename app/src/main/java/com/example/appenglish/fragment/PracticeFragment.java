package com.example.appenglish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appenglish.R;
import com.example.appenglish.activity.MatchWordActivity;
import com.example.appenglish.activity.SelectWordActivity;

public class PracticeFragment extends Fragment {
    Button btnMeanWord, btnMatchWord;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_practice,container,false);
        setUp();
        onClick();
        return view;
    }

    void setUp() {
        btnMatchWord = view.findViewById(R.id.btnMatchWord);
        btnMeanWord = view.findViewById(R.id.btnMeanWord);
    }

    void onClick() {
        btnMatchWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MatchWordActivity.class);
                startActivity(i);
            }
        });

        btnMeanWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SelectWordActivity.class);
                startActivity(i);
            }
        });
    }

}