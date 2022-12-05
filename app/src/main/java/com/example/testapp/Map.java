package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navigation_bar,ToolbarFragment.class,bundle)
                .commit();
    }
}