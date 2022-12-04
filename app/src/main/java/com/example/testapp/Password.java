package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends AppCompatActivity {

    //declaring variables
    EditText username;
    Button resetPass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //init variables
        username = findViewById(R.id.inputNewPass);
        resetPass = findViewById(R.id.newPassButton);
        db = new DatabaseHelper(this);

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String to store username
                String user = username.getText().toString();
                //toast if user does not put in username
                String toastMsg4 = "Please enter User";
                String toastMsg5 = "User does not exist";

                //db checking if user exists
                Boolean checkuser = db.checkUserName(user);
                if(user.equals("")){
                    Toast.makeText(Password.this, toastMsg4, Toast.LENGTH_SHORT).show();
                }
                else{
                    if(checkuser == true){
                        //opening SignUp Activity
                        Intent intentResetActivity = new Intent(Password.this, ResetActivity.class);
                        intentResetActivity.putExtra("username",user);
                        startActivity(intentResetActivity);

                    }
                    else{
                        Toast.makeText(Password.this, toastMsg5, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}