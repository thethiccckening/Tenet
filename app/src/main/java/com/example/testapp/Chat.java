package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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
    public void onClickMap(View view){
        Intent intent = new Intent(this,Map.class);
        startActivity(intent);
    }



}