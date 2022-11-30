package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    //debugging
    protected static final String ACTIVITY_NAME = "LoginActivity"; //debugging message

    //EditTexts
    EditText loginText, passwordText;

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
        signIn = findViewById(R.id.signinAction);

        //init EditTexts for login and password
        loginText = findViewById(R.id.signupSource);
        passwordText = findViewById(R.id.passwordSource);

        //init database
        db = new DatabaseHelper(this);

        //init sp
        sp = getSharedPreferences("DefaultSP",MODE_PRIVATE);

        //SignUp Event
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //storing user/pass source into strings
                String userVar = loginText.getText().toString();
                String passVar = passwordText.getText().toString();

                //string for toasts
                String toastMsg, toastMsg2, toastMsg3;
                toastMsg = "Please enter all the fields";
                toastMsg2 = "Registration Successful";
                toastMsg3 = "Already Registered";


                if(userVar.equals("") || passVar.equals("")){
                    Toast.makeText(Signup.this, toastMsg, Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUser = db.checkUserName(userVar);
                    if(checkUser == false){
                        Boolean insertUser = db.insertData(userVar, passVar);
                        if(insertUser == true){
                            Toast.makeText(Signup.this, toastMsg2, Toast.LENGTH_SHORT).show();

                            //starting the Search Activity after the user and pass have been added to db
                            Intent intentSearch = new Intent(Signup.this, Search.class);
                            startActivity(intentSearch);
                        }
                        else{
                            Toast.makeText(Signup.this, toastMsg3, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        //SignIn Event
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
}