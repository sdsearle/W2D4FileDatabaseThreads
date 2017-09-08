package com.example.admin.w2d4filedatabasethreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by admin on 9/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "TAG";
    public static final String DATABASE_NAME = "MY_DATABASE";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Contacts";
    public static final String CONTACT_FIRST_NAME = "first_name";
    public static final String CONTACT_LAST_NAME = "last_name";
    public static final String CONTACT_EMAIL = "email";
    public static final String CONTACT_BLOOD_TYPE = "blood_type";
    public static final String CONTACT_FAVORITE_COLOR = "favorite_color";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                CONTACT_FIRST_NAME + " TEXT, " +
                CONTACT_LAST_NAME + " TEXT, " +
                CONTACT_EMAIL + " TEXT PRIMARY KEY, " +
                CONTACT_FAVORITE_COLOR + " TEXT, " +
                CONTACT_BLOOD_TYPE + " TEXT" +
                ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveContact(String fname, String lname, String email, String favColor, String btype){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_FIRST_NAME, fname);
        contentValues.put(CONTACT_LAST_NAME, lname);
        contentValues.put(CONTACT_EMAIL, email);
        contentValues.put(CONTACT_FAVORITE_COLOR, favColor);
        contentValues.put(CONTACT_BLOOD_TYPE, btype);
        long saved = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return saved;
    }

    public ArrayList<String[]> getContacts(){
        ArrayList<String[]> Selection = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                String[] tmp = {cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4)};
                Selection.add(tmp);
            }while (cursor.moveToNext());
        }
        return Selection;
    }


    public void updateContact(String fname, String lname, String email, String favColor, String btype) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_FIRST_NAME, fname);
        contentValues.put(CONTACT_LAST_NAME, lname);
        contentValues.put(CONTACT_EMAIL, email);
        contentValues.put(CONTACT_FAVORITE_COLOR, favColor);
        contentValues.put(CONTACT_BLOOD_TYPE, btype);
        String tmp = "'";
        tmp += email + "'";
        sqLiteDatabase.update(TABLE_NAME,contentValues,CONTACT_EMAIL + " = " + tmp,null);
    }

    public void deleteContact(String email) {
        Log.d(TAG, "deleteContact: " + email);
        String tmp = "'";
        tmp += email + "'";
        email = tmp;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,CONTACT_EMAIL + " = " + email,null);
        //sqLiteDatabase.delete(TABLE_NAME,null,CONTACT_EMAIL + " " + s);
    }
}
