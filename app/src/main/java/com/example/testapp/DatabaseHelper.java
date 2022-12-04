package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    protected static final String ACTIVITY_NAME = "LoginActivity"; //debugging message

    //DB Name and Version
    public static final String DATABASE_NAME = "User.db";
    public static final int DATABASE_Version  = 1;

    //VARIABLES
    public final static String TABLE_NAME = "AccountReg";
    public final static String KEY_ID = "ID";
    public final static String KEY_USERNAME = "username";
    public final static String KEY_PASSWORD = "passWord";

    //creating the query
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + " (" + KEY_ID
            + " integer primary key autoincrement, " + KEY_USERNAME
            + " TEXT, " + KEY_PASSWORD
            + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }

    //insert data
    public Boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        readData();

        if(result == -1){
            return false;
        }
        else{

            return true;
        }
    }

    @SuppressLint("Range")
    public void readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorChats = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // moving our cursor to first position.
        if (cursorChats.moveToFirst()) {
            do {
                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursorChats.getString( cursorChats.getColumnIndex( KEY_USERNAME) ) );
            } while (cursorChats.moveToNext());
            // moving our cursor to next chat.
        }
        //Add Log.i() messages for each message that you retrieve from the Cursor object:
        cursorChats.close();
    }

    //checking if username exists
    public Boolean checkUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountReg where username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    //checking user's password
    public Boolean checkUserPassWord(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountReg where username = ? and password =?", new String[] {username,password});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean updatePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);

        long result = db.update(TABLE_NAME, contentValues, "username = ?", new String[] {username});
        readData();

        if(result == -1){
            return false;
        }
        else{

            return true;
        }

    }




}