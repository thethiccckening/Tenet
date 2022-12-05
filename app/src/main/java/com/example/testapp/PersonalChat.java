package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;

public class PersonalChat extends AppCompatActivity {
    SQLiteDatabase BaseHolder;
    String[] allItems = new String[]{String.valueOf(ChatDatabaseHelper.KEY_ID),ChatDatabaseHelper.KEY_MESSAGE};
    Cursor pointer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        ChatDatabaseHelper tempBase = new ChatDatabaseHelper(this);
        BaseHolder = tempBase.getWritableDatabase();
        RecyclerView recycleView = findViewById(R.id.ChatList);
        EditText chatField = findViewById(R.id.search_messages);

        addMessages();
        ChatAdapter messageAdapter =new ChatAdapter( this );
        recycleView.setAdapter(messageAdapter);
    }

    public void addMessages(){

        if(pointer.getCount()>=0) {
            pointer.moveToFirst();
            while (!pointer.isAfterLast()) {

                @SuppressLint("Range") String message = pointer.getString(pointer.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));

                pointer.moveToNext();
            }
        }

        pointer.close();

    }
    private class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
        private Cursor checker;
        public ChatAdapter(Context ctx){

        }

        public TextView messageText;

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                messageText = (TextView) itemView.findViewById(R.id.message_);
            }
        }

        @NonNull
        @Override
        public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.chat_incoming, parent, false);
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {

        }



        @Override
        public int getItemCount() {
            return 0;
        }

        }
    }
