package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    //globsl
    public static String emailVar, userVar,passVar;


    //debugging
    protected static final String ACTIVITY_NAME = "LoginActivity"; //debugging message

    //EditTexts
    EditText loginText, passwordText;

    //TextViews
    TextView signIn, signUp, forgotPass;
    public static String test;
    //Database for user accounts
    DatabaseHelper db;

    //Shared Pref
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //init Textview "buttons"
        signUp = findViewById(R.id.signupAction2);
        signIn = findViewById(R.id.signInAction);
        forgotPass = findViewById(R.id.forgotPassword);

        //init EditTexts for login and password
        loginText = findViewById(R.id.usernameSource);
        passwordText = findViewById(R.id.passwordSource2);

        //init database
        db = new DatabaseHelper(this);

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

    public void signInEvent(View view) {
        //storing user/pass source into strings
        userVar = loginText.getText().toString();
        passVar = passwordText.getText().toString();

        //string for toasts
        String toastMsg00, toastMsg01, toastMsg02;
        toastMsg00 = getString(R.string.toastEnterField);
        toastMsg01 = getString(R.string.LoginSuccessful);
        toastMsg02 = getString(R.string.InvalidCredentials);

        if(userVar.equals("") || passVar.equals("")){
            Toast.makeText(Signin.this, toastMsg00, Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkuserpass = db.checkUserPassWord(userVar, passVar);
            if(checkuserpass == true){
                Toast.makeText(Signin.this, toastMsg01, Toast.LENGTH_SHORT).show();
                test = userVar;
                //starting the Search Activity after the user and pass have been added to db
                Intent intentSearch = new Intent(Signin.this, Search.class);
                intentSearch.putExtra("username", userVar);
                startActivity(intentSearch);
            }
            else{
                Toast.makeText(Signin.this, toastMsg02, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void signUpEvent2(View view) {
        String toastMsg03;
        toastMsg03 = getString(R.string.SignUpWelcome);
        Toast.makeText(Signin.this, toastMsg03, Toast.LENGTH_SHORT).show();

        //opening SignUp Activity
        Intent intentSignUp = new Intent(Signin.this, Signup.class);
        startActivity(intentSignUp);
    }

    public void forgotPasswordClick(View view) {
        //opening SignUp Activity
        String toastMsg03;
        toastMsg03 = getString(R.string.PasswordReset);
        Toast.makeText(Signin.this, toastMsg03, Toast.LENGTH_SHORT).show();

        Intent intentPassword = new Intent(Signin.this, Password.class);
        startActivity(intentPassword);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Not calling super, disables back button in current screen.
    }
}