package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appenglish.models.CardModel;

import java.util.ArrayList;

import DTO_User.User;

public class CreateDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabaseUser.db";
    public static final int DATABASE_VERSION = 1;
    public static final String Table_name = "DuLieuTaiKhoan";
    public static final String COL_Email = "email";
    public static final String COL_Username = "username";
    public static final String COL_Pass = "password";
    public static final String COL_RePass = "repassword";
    private static final String Table_Create = "create table DuLieuTaiKhoan ("
            +"email TEXT PRIMARY KEY,"
            +"username TEXT,"
            +"password TEXT,"
            +"repassword TEXT"
            +")";
    public CreateDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_Create);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertTK(User user){
        db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreateDatabase.COL_Email,user.getEmail());
        values.put(CreateDatabase.COL_Username,user.getUsername());
        values.put(CreateDatabase.COL_Pass,user.getPassword());
        values.put(CreateDatabase.COL_RePass,user.getRepass());
        db.insert(CreateDatabase.Table_name, null, values);
        db.close();
    }
    public boolean KTUser(String user_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " +Table_name+ " WHERE username = ?";
        String[] selectionArgs = {user_name};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.getCount() ==0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean KTLogin(String email,String user_name, String pass_word) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " +Table_name+ " WHERE email=? AND username = ? AND password = ?";
        String[] selectionArgs = {email, user_name, pass_word};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean KTPass(String email,String pass_word) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " +Table_name+ " WHERE email=? AND password = ?";
        String[] selectionArgs = {email, pass_word};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }
    public void updateData(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("repassword", password);
        db.update(Table_name, values, "email = ?", new String[]{email});

        db.close();
    }
    public void deleteData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_name, "email = ?", new String[]{email});
        db.close();
    }


    //
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
}
