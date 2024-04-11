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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.appenglish.R;

public class SettingFragment extends Fragment {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switcher;

    TextView cskh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        view = inflater.inflate(R.layout.fragment_setting,container,false);
//        switcher = view.findViewById(R.id.switcher);
//        sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
//        nigthMODE = sharedPreferences.getBoolean("nigth", false);
//        if(nigthMODE){
//            switcher.setChecked(true);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                val editor = sharedPreferences.edit();
//                editor.putBoolean("nigth", isChecked);
//                editor.apply();
//
//                if (isChecked) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//
//        });
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        switcher = view.findViewById(R.id.switcher);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("night", false);

        switcher.setChecked(nightMode);

        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night", isChecked);
                editor.apply();

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
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