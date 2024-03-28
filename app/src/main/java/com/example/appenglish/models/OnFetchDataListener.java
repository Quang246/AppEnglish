package com.example.appenglish.models;

public interface OnFetchDataListener {
    void onFetchData(MeanModel meanModel, String message);
    void onError(String message);
}
