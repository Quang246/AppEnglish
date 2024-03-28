package com.example.appenglish.api;

import android.widget.Toast;

import com.example.appenglish.SearchActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//              converse from JSON to GSON and contrast
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitClient(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public DictionaryApi getApi() {
        return retrofit.create(DictionaryApi.class);
    }

}
