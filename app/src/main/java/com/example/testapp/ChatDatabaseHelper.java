package com.example.testapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "User.db";
    private static int VERSION_NUM = 2;
    public static final String TABLE_Of_My_ITEMS = "MessageLog";
    public static String KEY_ID ="ID";
    public static final String KEY_MESSAGE = "MESSAGES";
    public static final String KEY_SENT_TO = "SENT_TO";
    public static final String KEY_SENT_BY = "SENT_BY";
    private static final String DATABASE_CREATE = "create table "
            + TABLE_Of_My_ITEMS + " ( " + KEY_ID
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null, "
            + KEY_SENT_TO+ " integer not null, "
            + KEY_SENT_BY+ " integer not null " +");";
    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + i + " newVersion=" + i1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_Of_My_ITEMS);
        onCreate(sqLiteDatabase);
    }
}