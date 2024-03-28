package com.example.appenglish.api;

import com.example.appenglish.models.MeanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;

public interface DictionaryApi {

    @GET("en/{word}")
    Call<List<MeanModel>> getWord(
            @Part("word") String word
    );
}
