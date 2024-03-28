package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appenglish.api.DictionaryApi;
import com.example.appenglish.api.RetrofitClient;
import com.example.appenglish.models.MeanModel;
import com.example.appenglish.models.OnFetchDataListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    DictionaryApi dictionaryApi;
    OnFetchDataListener listenerData;
    TextView txtResult, txtResultIPA;
    EditText editSearch;
    Button btnSearch;
    RecyclerView rvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUp();
        eventListener();


    }

    public void eventListener() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String resultSearch = editSearch.getText().toString();
//                getWord(resultSearch);
                init();
//                Toast.makeText(SearchActivity.this, "Success !", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void setUp () {
        txtResult = findViewById(R.id.txtResult);
        txtResultIPA = findViewById(R.id.txtResultIPA);
        editSearch = findViewById(R.id.editSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rvResult = findViewById(R.id.rvResult);

        dictionaryApi = RetrofitClient.getInstance().getApi();
    }

    public void init() {
        String word = editSearch.getText().toString();

        Call<List<MeanModel>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getWord(word);

//        Toast.makeText(this, String.valueOf(RetrofitClient.getInstance().getApi().getWord(word)), Toast.LENGTH_SHORT).show();

//        try {
//            call.enqueue(new Callback<List<MeanModel>>() {
//                @Override
//                public void onResponse(Call<List<MeanModel>> call, Response<List<MeanModel>> response) {
//                        if (!response.isSuccessful()) {
//                            Toast.makeText(SearchActivity.this, "Error !", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
////                    listenerData.onFetchData(response.body().get(0), response.message());
//                    Toast.makeText(SearchActivity.this, "Success !", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<List<MeanModel>> call, Throwable throwable) {
////                    listenerData.onError("Request Error !!");
//                    Toast.makeText(SearchActivity.this, "Request Failed !! !", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Failed !!", Toast.LENGTH_SHORT).show();
//        }

    }
}