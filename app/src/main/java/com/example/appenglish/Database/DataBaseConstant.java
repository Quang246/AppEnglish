package com.example.appenglish.Database;

public class DataBaseConstant {

    public static final String DATABASE_NAME = "AppEnglish.db";

    public static final int DATABASE_VERSION = 1;

    //  DuLieuTaiKhoan
    public static final String TABLE_NAME_USER = "DuLieuTaiKhoan";
    public static final String COL_EMAIL_USER = "email";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_RE_PASSWORD = "repassword";

    //  FlashCard
    public static final String TABLE_NAME_CARD = "FlashCard";
    public static final String COL_ID = "_id";
    public static final String COL_WORD = "word";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_EMAIL_CARD = "email";


    public static final String CREATE_USER = "CREATE TABLE " + TABLE_NAME_USER + " (" +
            COL_EMAIL_USER + " TEXT PRIMARY KEY, " +
            COL_USERNAME + " TEXT, " +
            COL_PASSWORD + " TEXT, " +
            COL_RE_PASSWORD + " TEXT);";

    public static final String CREATE_CARD = "CREATE TABLE " + TABLE_NAME_CARD + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_WORD + " TEXT, " +
            COL_DESCRIPTION + " TEXT, " +
            COL_EMAIL_USER + " TEXT NOT NULL, " +
            "FOREIGN KEY (" + COL_EMAIL_USER + ") REFERENCES " + TABLE_NAME_USER + " (" + COL_EMAIL_USER + "));";

}
