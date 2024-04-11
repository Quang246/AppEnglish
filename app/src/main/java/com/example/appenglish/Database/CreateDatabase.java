package com.example.appenglish.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appenglish.models.CardModel;
import com.example.appenglish.models.User;

import java.util.ArrayList;

public class CreateDatabase extends SQLiteOpenHelper {
    private Context context;

    public CreateDatabase(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME, null, DataBaseConstant.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseConstant.CREATE_USER);
        db.execSQL(DataBaseConstant.CREATE_CARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstant.TABLE_NAME_CARD);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstant.TABLE_NAME_USER);
        onCreate(db);
    }

//  register
    public void insertTK(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COL_EMAIL_USER,user.getEmail());
        values.put(DataBaseConstant.COL_USERNAME,user.getUsername());
        values.put(DataBaseConstant.COL_PASSWORD,user.getPassword());
        values.put(DataBaseConstant.COL_RE_PASSWORD,user.getRepass());

        db.insert(DataBaseConstant.TABLE_NAME_USER, null, values);
        db.close();
    }

//  check login
    public boolean KTLogin(String email, String pass_word) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + DataBaseConstant.TABLE_NAME_USER + " WHERE email = ? AND password = ?";
        String[] selectionArgs = {email, pass_word};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

//  insert word to database
//  table_name: FlashCard
    public void insertWord(CardModel cardModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseConstant.COL_WORD, cardModel.getTxtCard_1());
        values.put(DataBaseConstant.COL_DESCRIPTION, cardModel.getTxtCard_2());
        values.put(DataBaseConstant.COL_EMAIL_CARD, cardModel.getUser_email());

        db.insert(DataBaseConstant.TABLE_NAME_CARD, null, values);
        db.close();
    }

//  delete word
//  table_name: FlashCard
    public void deleteWord(String w, String des) {
        SQLiteDatabase db = this.getWritableDatabase();
        String table = DataBaseConstant.TABLE_NAME_CARD;
        String query = DataBaseConstant.COL_WORD + " = ? and " + DataBaseConstant.COL_DESCRIPTION + " = ?";
        String[] whereArgs = {w, des};
        db.delete(table, query, whereArgs);
    }

//  update words
public void updateWord(String oldWord, String oldDescription, String newWord, String newDescription) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(DataBaseConstant.COL_WORD, newWord);
    values.put(DataBaseConstant.COL_DESCRIPTION, newDescription);

    String table = DataBaseConstant.TABLE_NAME_CARD;
    String whereClause = DataBaseConstant.COL_WORD + " = ? AND " + DataBaseConstant.COL_DESCRIPTION + " = ?";
    String[] whereArgs = {oldWord, oldDescription};

    db.update(table, values, whereClause, whereArgs);
}

//  get all card in database
    public ArrayList<CardModel> getAllCards(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CardModel> arCards = new ArrayList<>();
        String query = "SELECT * FROM " + DataBaseConstant.TABLE_NAME_CARD + " WHERE " +
                DataBaseConstant.COL_EMAIL_CARD + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int _ID = cursor.getInt(0);
            String cursorWord = cursor.getString(1);
            String cursorDes = cursor.getString(2);
            String cursorEmail = cursor.getString(3);
            CardModel cm = new CardModel(cursorWord, cursorDes, cursorEmail);
            arCards.add(cm);
            cursor.moveToNext();
        }
        cursor.close();
        return arCards;
    }

//  check email google existsed in database
    public boolean checkEmailGoogleExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM WHERE " + DataBaseConstant.COL_EMAIL_USER + " = " + email;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

//  check word and des cùng 1 hàng không
    public boolean checkWordDes (String w1, String w2) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DataBaseConstant.TABLE_NAME_CARD + " WHERE " + DataBaseConstant.COL_WORD + " = ? and " +
                DataBaseConstant.COL_DESCRIPTION + " = ?";
        String[] selectionArgs = {w1, w2};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }
//  get username
    public String getUsername(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + DataBaseConstant.COL_USERNAME + " FROM " + DataBaseConstant.TABLE_NAME_USER +
                " WHERE " + DataBaseConstant.COL_EMAIL_USER + " = ? AND " + DataBaseConstant.COL_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        String username = "";
        if (cursor != null && cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex("username");
            if (usernameIndex != -1) {
                username = cursor.getString(usernameIndex);
            }
            cursor.close(); // Đóng Cursor sau khi sử dụng xong
        }

        return username;
    }

//  QUANG
    public boolean KTPass(String email,String pass_word) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + DataBaseConstant.TABLE_NAME_USER + " WHERE email=? AND password = ?";
        String[] selectionArgs = {email, pass_word};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }
//  update user
    public void updateData(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseConstant.COL_USERNAME, username);
        values.put(DataBaseConstant.COL_PASSWORD, password);
        values.put(DataBaseConstant.COL_RE_PASSWORD, password);
        db.update(DataBaseConstant.TABLE_NAME_USER, values, "email = ?", new String[]{email});

        db.close();
    }
    public void deleteData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataBaseConstant.TABLE_NAME_USER, "email = ?", new String[]{email});
        db.close();
    }

}
