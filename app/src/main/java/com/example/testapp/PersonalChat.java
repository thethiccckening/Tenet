package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonalChat extends AppCompatActivity {
    private ArrayList<String> chatHistory = new ArrayList<String>();
    SQLiteDatabase BaseHolder;
    String[] allItems = new String[]{String.valueOf(DatabaseHelper.KEY_ID), DatabaseHelper.KEY_MESSAGE};
    Cursor pointer;
    String UserID;
    String OtherID;
    private ArrayList<String> sendHistory = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        DatabaseHelper tempBase = new DatabaseHelper(this);
        BaseHolder = tempBase.getWritableDatabase();
        ListView listView = findViewById(R.id.ChatList);
        EditText chatField = findViewById(R.id.search_messages);
        Intent i = getIntent();
        UserID = i.getStringExtra("username");
        OtherID = i.getStringExtra("convoID");
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction()
        .add(R.id.navigation_bar,ToolbarFragment.class,bundle)
        .commit();
        ImageView img = findViewById(R.id.down_ek1);
        addMessages(UserID, OtherID);
        ConvoAdapter list = new ConvoAdapter(this);
        TextView textView = findViewById(R.id.____label);
        textView.setText(OtherID);
        listView.setAdapter(list);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = chatField.getText().toString().trim();


                chatHistory.add(message);


                ContentValues value = new ContentValues();
                value.put(DatabaseHelper.KEY_MESSAGE,message);
                value.put(DatabaseHelper.KEY_SENT_BY,UserID);
                value.put(DatabaseHelper.KEY_SENT_TO,OtherID);
                BaseHolder.insert(DatabaseHelper.TABLE_Of_My_ITEMS,null,value);
                list.notifyDataSetChanged();
                chatField.setText("");
            }
        });


    }

    public void addMessages(String userID, String otherID) {
        pointer = BaseHolder.rawQuery("select * from " + DatabaseHelper.TABLE_Of_My_ITEMS + " where " + DatabaseHelper.KEY_SENT_BY
                + "=? AND " + DatabaseHelper.KEY_SENT_TO + "=? OR " + DatabaseHelper.KEY_SENT_BY + "=? AND "
                + DatabaseHelper.KEY_SENT_TO + "=?", new String[]{userID, otherID, otherID, userID});
        if (pointer.getCount() >= 0) {
            pointer.moveToFirst();
            while (!pointer.isAfterLast()) {
                @SuppressLint("Range") String sender = pointer.getString(pointer.getColumnIndex(DatabaseHelper.KEY_SENT_BY));
                @SuppressLint("Range") String message = pointer.getString(pointer.getColumnIndex(DatabaseHelper.KEY_MESSAGE));
                chatHistory.add(message);
                sendHistory.add(sender);
                pointer.moveToNext();
            }
        }

        pointer.close();

    }
    private class ConvoAdapter extends ArrayAdapter<String> {
        private Cursor checker;
        public ConvoAdapter(Context ctx){
            super(ctx,0);
        }
        public int getCount(){
            return chatHistory.size();
        }
        public String getItem(int position){
            return chatHistory.get(position);
        }
        @SuppressLint("Range")
        public Long getItemID(int position){
            DatabaseHelper data = new DatabaseHelper(getContext());
            pointer.moveToPosition(position);
            if(pointer.getColumnIndex(DatabaseHelper.KEY_ID)==-1){
                Long l = Long.valueOf(-1);
                return l;
            }
            else return pointer.getLong(pointer.getColumnIndex(DatabaseHelper.KEY_ID));
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = PersonalChat.this.getLayoutInflater();
            View result;
            TextView message;
            String Message,sentTo,SentBy;

            if(!sendHistory.get(position).equals(UserID)) {
                result = inflater.inflate(R.layout.chat_incoming, null);

            }
            else{
                result = inflater.inflate(R.layout.chat_outgoing, null);

            }
            message = result.findViewById(R.id.message_);


            message.setText(this.getItem(position));
            return result;
        }


    }


}