package com.example.testapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    //debugging
    protected static final String ACTIVITY_NAME = "LoginActivity"; //debugging message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    public void doneEvent(View view){
        String toastMsg4 = "done";
        Toast.makeText(Settings.this, toastMsg4, Toast.LENGTH_SHORT).show();

    }

    public void viewEvent(View view){
        String toastMsg10 = "View";
        Toast.makeText(Settings.this, toastMsg10, Toast.LENGTH_SHORT).show();

    }

    public void logoutEvent(View view){
//        String toastMsg4 = "log out";
//        Toast.makeText(Settings.this, toastMsg4, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle(R.string.DialogTitle);
        builder.setMessage(R.string.DialogMsg);

        //ok option
        builder.setPositiveButton(R.string.DialogOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton(R.string.DialogCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();

    }
}

