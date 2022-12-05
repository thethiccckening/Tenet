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
    public static final String DATABASE_NAME = "tenetLogin.db";
    public static final int DATABASE_Version  = 1;

    //VARIABLES
    public final static String TABLE_NAME = "AccountReg";
    public final static String KEY_ID = "ID";
    public final static String KEY_USERNAME = "username";
    public final static String KEY_EMAIL = "email";
    public final static String KEY_PASSWORD = "passWord";

    //creating the query
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + " (" + KEY_ID
            + " integer primary key autoincrement, " + KEY_USERNAME
            + " TEXT, " + KEY_EMAIL
            + " TEXT, " + KEY_PASSWORD
            + " TEXT);";
    public static final String TABLE_Of_My_ITEMS = "MessageLog";
    public static String KEY_ID_MESSAGE ="MESSAGE_NUMBER";
    public static final String KEY_MESSAGE = "MESSAGES";
    public static final String KEY_SENT_TO = "SENT_TO";
    public static final String KEY_SENT_BY = "SENT_BY";
    public static final String KEY_CONVO_ID = "CONVERSATION_ID";
    private static final String DATABASE_CREATE2 = "create table "
            + TABLE_Of_My_ITEMS + " ( " + KEY_ID_MESSAGE
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null, "
            + KEY_SENT_TO+ " text not null, "
            + KEY_SENT_BY+ " text not null, "
            + KEY_CONVO_ID+ " integer not null "+");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);

    }

    //insert data
    public Boolean insertData(String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
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

    //update username and password
    public Boolean updateData(String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        
        Cursor cursor = db.rawQuery("Select * from AccountReg where email = ?", new String[] {email});
        if(cursor.getCount() >0) {


            long result = db.update(TABLE_NAME, contentValues, "name=?", new String[]{email});
            readData();

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    //delete username and password
    public Boolean deleteData(String email){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from AccountReg where email = ?", new String[] {email});
        if(cursor.getCount() >0) {
            long result = db.delete(TABLE_NAME,"name=?", new String[]{email});
            readData();

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    //get data
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from AccountReg", null);
        return cursor;
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
    public Boolean checkUserName(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountReg where email = ?", new String[] {email});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    @SuppressLint("Range")
    public String getUserEmail(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountReg where username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            return cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
        }
        else{
            return "";
        }
    }

    //checking user's username
    public Boolean checkUserExists(String email, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountReg where email = ? and username =?", new String[] {email,username});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    //checking user's password
    public Boolean checkUserPassWord(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountReg where email = ? and password =?", new String[] {email,password});
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
