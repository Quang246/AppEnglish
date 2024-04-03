package com.example.appenglish.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appenglish.models.CardModel;
import com.example.appenglish.models.User;

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

}
