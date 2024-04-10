package com.example.appenglish.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.appenglish.R;

public class SettingFragment extends Fragment {
    View view;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switcher;
    boolean nigthMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView cskh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_setting,container,false);
        switcher = view.findViewById(R.id.switcher);
        sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nigthMODE = sharedPreferences.getBoolean("nigth", false);
        if(nigthMODE){
            switcher.setChecked(true);
        }
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nigthMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });

        //cskh
        cskh = view.findViewById(R.id.cskh);
        cskh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = "0335026292";
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sdt));
                startActivity(i);
            }
        });
        return view;



    }
}
