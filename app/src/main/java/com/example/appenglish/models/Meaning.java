package com.example.appenglish.models;

import java.util.ArrayList;

public class Meaning {
    public String partOfSpeech;
    public ArrayList<Definition> definitions;
    public ArrayList<String> synonyms;
    public ArrayList<String> antonyms;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }

    public String getSynonyms() {
        String tmp = "";
        for (String s: synonyms) {
            tmp = tmp + s  + ", ";
        }
        return tmp;
    }

    public String getAntonym() {
        String tmp = "";
        for (String s: antonyms) {
            tmp = tmp + s  + ", ";
        }
        return tmp;
    }
}
