package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    String UserID;

    String convoID;
    SQLiteDatabase BaseHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        UserID = Signin.test;
        RecyclerView recycleView = findViewById(R.id.UserMessages);

        ChatDatabaseHelper tempBase = new ChatDatabaseHelper(this);
        BaseHolder = tempBase.getWritableDatabase();
        ChatAdapter convos = new ChatAdapter(readConvos(BaseHolder));
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navigation_bar,ToolbarFragment.class,bundle)
                .commit();
    }
    public void onClickSearch(View view){
        Intent intent = new Intent(this, Search.class);

        startActivity(intent);
    }
    public void onClickSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void onClickConvo(View view){
        Intent intent = new Intent(this,PersonalChat.class);

        intent.putExtra("username",UserID);
        intent.putExtra("convoID",convoID);
        startActivity(intent);
    }
    @SuppressLint("Range")
    public ArrayList<Conversations> readConvos(SQLiteDatabase db){

        Cursor point = db.rawQuery("select * from "+ChatDatabaseHelper.TABLE_Of_My_ITEMS+" where "+ChatDatabaseHelper.KEY_CONVO_ID +" in( select distinct ChatDatabaseHelper.KEY_CONVO_ID from ChatDatabaseHelper.Table_Of_My_ITEMS)"
                ,new String[]{UserID,UserID});
        ArrayList<Conversations> convos = new ArrayList<Conversations>();
        if(point.getCount()>=0){
            point.moveToFirst();
            while(!point.isAfterLast()){
                String convoName;
                    if (point.getString(point.getColumnIndex(ChatDatabaseHelper.KEY_SENT_TO)).equals(UserID)) {
                        convoName = point.getString(point.getColumnIndex(ChatDatabaseHelper.KEY_SENT_BY));
                    }
                    else{
                        convoName = point.getString(point.getColumnIndex(ChatDatabaseHelper.KEY_SENT_TO));
                    }



                @SuppressLint("Range") String message = point.getString(point.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
                Conversations c = new Conversations(convoName,message);
                convos.add(c);
            }
        }

    return convos;
    }


}