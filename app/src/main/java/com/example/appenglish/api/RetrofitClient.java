package com.example.appenglish.api;

import android.content.Context;
import android.widget.Toast;

import com.example.appenglish.SearchActivity;
import com.example.appenglish.models.MeanModel;
import com.example.appenglish.models.OnFetchDataListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Context context;
    private static final String BASE_URL = "https://api.dictionaryapi.dev/api/v2/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
//              converse from JSON to GSON and contrast
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RetrofitClient(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word) {
        DictionaryApi dictionaryApi = retrofit.create(DictionaryApi.class);
        Call<List<MeanModel>> call = dictionaryApi.getWord(word);

        try {
            call.enqueue(new Callback<List<MeanModel>>() {
                @Override
                public void onResponse(Call<List<MeanModel>> call, Response<List<MeanModel>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error !!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<MeanModel>> call, Throwable throwable) {
                    listener.onError("Request Error !!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "An Error Orrcured !!", Toast.LENGTH_SHORT).show();
        }

    }

}
