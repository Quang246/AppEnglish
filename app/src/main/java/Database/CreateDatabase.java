package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
