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

import com.google.android.material.snackbar.Snackbar;

public class Signup extends AppCompatActivity {

    //public variables for username, email, password
    public static String emailVar,userVar,passVar;
    private String snackMsg = "please Sign in";

    //debugging
    protected static final String ACTIVITY_NAME = "LoginActivity"; //debugging message

    //EditTexts
    EditText loginText, passwordText, emailText;

    //TextViews
    TextView signIn, signUp;

    //Database for user accounts
    DatabaseHelper db;

    //Shared Pref
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //init Textview "buttons"
        signUp = findViewById(R.id.signupAction);
        signIn = findViewById(R.id.signInAction2);

//

        //init EditTexts for login and password
        emailText = findViewById(R.id.emailSource);
        loginText = findViewById(R.id.usernameSource);
        passwordText = findViewById(R.id.passwordSource);

        //init database
        db = new DatabaseHelper(this);

        //init sp
        sp = getSharedPreferences("DefaultSP",MODE_PRIVATE);

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

    //SignUp button/event
    public void signUpEvent(View view) {
        //storing user/pass source into strings
        emailVar = emailText.getText().toString();
        userVar = loginText.getText().toString();
        passVar = passwordText.getText().toString();

        //string for toasts
        String toastMsg, toastMsg2, toastMsg3;
        toastMsg = getString(R.string.toastEnterField);
        toastMsg2 = getString(R.string.toastRegSuccess);
        toastMsg3 = getString(R.string.toastAlrReg);


        if(userVar.equals("") || passVar.equals("") || emailVar.equals("")){
            Toast.makeText(Signup.this, toastMsg, Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkUser = db.checkUserName(emailVar);
            if(checkUser == false){
                Boolean insertUser = db.insertData(emailVar,userVar, passVar);
                if(insertUser == true){
                    Toast.makeText(Signup.this, toastMsg2, Toast.LENGTH_SHORT).show();

                    //sending username to settings
                    Intent intentSettings = new Intent(Signup.this, Settings.class);
                    intentSettings.putExtra("email", emailVar);

                    //starting the SignIn Activity after the user and pass have been added to db
                    String toastMsg0;
                    toastMsg0 = "yea yea";
                    Snackbar.make(view, toastMsg, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent intentSignIn = new Intent(Signup.this, Signin.class);
                    startActivity(intentSignIn);



                }
                else{
                    Toast.makeText(Signup.this, toastMsg3, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void signInEvent2(View view) {
        String toastMsg4 = getString(R.string.toastSigninWelcome);
        Toast.makeText(Signup.this, toastMsg4, Toast.LENGTH_SHORT).show();

        Intent intentSignIn = new Intent(Signup.this, Signin.class);
        startActivity(intentSignIn);
    }
}