package com.example.appenglish.models;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MeanModel {
    public String word;
    public ArrayList<Phonetic> phonetics;
    public ArrayList<Meaning> meanings;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Phonetic> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(ArrayList<Phonetic> phonetics) {
        this.phonetics = phonetics;
    }

    public ArrayList<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(ArrayList<Meaning> meanings) {
        this.meanings = meanings;
    }
}
