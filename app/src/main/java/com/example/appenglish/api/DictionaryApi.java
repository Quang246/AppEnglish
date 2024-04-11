package com.example.appenglish.api;

import com.example.appenglish.models.MeanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DictionaryApi {
    @GET("entries/en/{word}")
    Call<List<MeanModel>> getWord(
            @Path("word") String word
    );
}
