package com.example.appenglish.models;

public class CardModel {

    String txtCard_1, txtCard_2;
    String user_email;  // use for database

    public String getUser_email() {
        return user_email;
    }

    public CardModel(String txtCard_1, String txtCard_2, String user_email) {
        this.txtCard_1 = txtCard_1;
        this.txtCard_2 = txtCard_2;
        this.user_email = user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public CardModel() {

    }

    public CardModel(String txtCard_1, String txtCard_2) {
        this.txtCard_1 = txtCard_1;
        this.txtCard_2 = txtCard_2;
    }

    public String getTxtCard_1() {
        return txtCard_1;
    }

    public void setTxtCard_1(String txtCard_1) {
        this.txtCard_1 = txtCard_1;
    }

    public String getTxtCard_2() {
        return txtCard_2;
    }

    public void setTxtCard_2(String txtCard_2) {
        this.txtCard_2 = txtCard_2;
    }
}
