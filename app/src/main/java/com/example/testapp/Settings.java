package com.example.testapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    EditText username, password;

    String email;

    Signup signupOb = new Signup();

    Signin signinOb = new Signin();

    //db helper
    DatabaseHelper db;


    //debugging
    protected static final String ACTIVITY_NAME = "LoginActivity"; //debugging message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = findViewById(R.id.emailSource);
        password = findViewById(R.id.passwordETsource);

        username.setText(signinOb.userVar);
        password.setText(signinOb.passVar);

        email = signupOb.emailVar;


        //init db for navbar
        db = new DatabaseHelper(this);
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.navigation_bar,ToolbarFragment.class,bundle)
                .commit();
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
        String toastMsg02 = getString(R.string.EntryUpdated);
        String toastMsg03 = getString(R.string.EntryNotUpdated);


        String usernameUpdate = username.getText().toString();
        String passwordUpdate = password.getText().toString();

        Boolean updateData = db.updateData(email, usernameUpdate, passwordUpdate);
        if(updateData == true){
            Toast.makeText(Settings.this, toastMsg02, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Settings.this, toastMsg03, Toast.LENGTH_SHORT).show();
        }

    }

    public void viewEvent(View view){
        //string "entries not exist"
        String toastMsg00 = getString(R.string.entries);
        Cursor res = db.getData();
        if(res.getCount() == 0){
            Toast.makeText(Settings.this, toastMsg00, Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Email: " +res.getString(2)+"\n");
            buffer.append("Username: " +res.getString(1)+"\n");
            buffer.append("Password: " +res.getString(3)+"\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle(R.string.builderUserInfo);
        builder.setCancelable(true);
        builder.setMessage(buffer.toString());
        builder.show();

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
                String toastMsg4 = getString(R.string.toastSigninWelcome);
                Toast.makeText(Settings.this, toastMsg4, Toast.LENGTH_SHORT).show();

                Intent intentSignIn = new Intent(Settings.this, Signin.class);
                startActivity(intentSignIn);
                finish();
            }
        });
        builder.show();

    }
}

