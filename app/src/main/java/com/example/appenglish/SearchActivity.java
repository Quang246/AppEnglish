package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appenglish.adapters.DefinitionAdapter;
import com.example.appenglish.adapters.MeaningAdapter;
import com.example.appenglish.adapters.PhoneticAdapter;
import com.example.appenglish.api.RetrofitClient;
import com.example.appenglish.models.MeanModel;
import com.example.appenglish.models.Meaning;
import com.example.appenglish.models.OnFetchDataListener;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {
    TextView txtResult;
    SearchView editSearch;
    Button btnSearch;
    RecyclerView rvResultIPA, rvResult;
    MeaningAdapter meaningAdapter;
    PhoneticAdapter phoneticAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUp();
//        builder = new AlertDialog.Builder(this);
        RetrofitClient retrofitClient = new RetrofitClient(SearchActivity.this);
        retrofitClient.getWordMeaning(listener, "hello");
        eventListener();

    }

    public void eventListener() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                builder.setTitle("Đang tải ...");
//                builder.show();
                RetrofitClient retrofitClient = new RetrofitClient(SearchActivity.this);
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
        txtResult = findViewById(R.id.txtResult);
        editSearch = findViewById(R.id.editSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rvResult = findViewById(R.id.rvResult);
        rvResultIPA = findViewById(R.id.rvResultIPA);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(MeanModel meanModel, String message) {
            if (meanModel == null) {
                Toast.makeText(SearchActivity.this, "No data found !!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(meanModel);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    void showData(MeanModel meanModel) {
        txtResult.setText(meanModel.getWord());

//      set layout : important
        RecyclerView.LayoutManager m1LayoutManager = new GridLayoutManager(this, 1);
        rvResult.setLayoutManager(m1LayoutManager);

        RecyclerView.LayoutManager m2LayoutManager = new GridLayoutManager(this, 1);
        rvResultIPA.setLayoutManager(m2LayoutManager);

//      in ra IPA và audio
        phoneticAdapter = new PhoneticAdapter(this, meanModel.getPhonetics());
        rvResultIPA.setAdapter(phoneticAdapter);
//        Toast.makeText(this, String.valueOf(phoneticAdapter.getAu()), Toast.LENGTH_SHORT).show();
//      in ra loại từ và nghĩa
        meaningAdapter = new MeaningAdapter(this, meanModel.getMeanings());
        rvResult.setAdapter(meaningAdapter);

    }

}