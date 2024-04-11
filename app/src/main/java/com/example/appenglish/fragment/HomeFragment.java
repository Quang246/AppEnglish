package com.example.appenglish.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.R;
import com.example.appenglish.adapters.MeaningAdapter;
import com.example.appenglish.adapters.PhoneticAdapter;
import com.example.appenglish.api.RetrofitClient;
import com.example.appenglish.models.MeanModel;
import com.example.appenglish.models.OnFetchDataListener;

public class HomeFragment extends Fragment {
    private DrawerLayout drawerLayout;
    TextView txtResult;
    SearchView editSearch;
    RecyclerView rvResultIPA, rvResult;
    MeaningAdapter meaningAdapter;
    PhoneticAdapter phoneticAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        setUp();
        eventListener();
        return view;
    }
    public void eventListener() {

        editSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                builder.setTitle("Đang tải ...");
//                builder.show();
                RetrofitClient retrofitClient = new RetrofitClient(HomeFragment.this.getContext());
                retrofitClient.getWordMeaning(listener, query);
//                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    void setUp () {
        txtResult = view.findViewById(R.id.txtResult);
        editSearch = view.findViewById(R.id.editSearch);
        rvResult = view.findViewById(R.id.rvResult);
        rvResultIPA = view.findViewById(R.id.rvResultIPA);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(MeanModel meanModel, String message) {
            if (meanModel == null) {
                Toast.makeText(HomeFragment.this.getContext(), "No data found !!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(meanModel);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(HomeFragment.this.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    void showData(MeanModel meanModel) {
        txtResult.setText(meanModel.getWord());

//      set layout : important
        RecyclerView.LayoutManager m1LayoutManager = new GridLayoutManager(HomeFragment.this.getContext(), 1);
        rvResult.setLayoutManager(m1LayoutManager);

        RecyclerView.LayoutManager m2LayoutManager = new GridLayoutManager(HomeFragment.this.getContext(), 1);
        rvResultIPA.setLayoutManager(m2LayoutManager);

//      in ra IPA và audio
        phoneticAdapter = new PhoneticAdapter(HomeFragment.this.getContext(), meanModel.getPhonetics());
        rvResultIPA.setAdapter(phoneticAdapter);
//        Toast.makeText(this, String.valueOf(phoneticAdapter.getAu()), Toast.LENGTH_SHORT).show();
//      in ra loại từ và nghĩa
        meaningAdapter = new MeaningAdapter(HomeFragment.this.getContext(), meanModel.getMeanings());
        rvResult.setAdapter(meaningAdapter);

    }

}