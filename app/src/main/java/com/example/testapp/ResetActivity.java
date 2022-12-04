package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    //declaring variables
    TextView username;
    EditText newPass, reNewPass;
    Button confirmPass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        //init variables
        username = findViewById(R.id.username_reset_text);
        newPass = findViewById(R.id.passwordResetSource);
        reNewPass = findViewById(R.id.passwordResetSource2);
        confirmPass = findViewById(R.id.buttonReset);
        db = new DatabaseHelper(this);

        //getting username from intent stash
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirmPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = newPass.getText().toString();
                String rePass = reNewPass.getText().toString();

                //toast
                String toastMsg7 = "Passwords are not the same";

                if (pass.equals(rePass)){
                    //toast
                    String toastMsg5 = "Password Suc changed";
                    String toastMsg6 = "Password bot changed";

                    Boolean checkPasswordUpdate = db.updatePassword(user,pass);
                    if(checkPasswordUpdate == true){
                        Toast.makeText(ResetActivity.this, toastMsg5, Toast.LENGTH_SHORT).show();
                        Intent intentSearch = new Intent(ResetActivity.this, Search.class);
                        startActivity(intentSearch);
                    }
                    else{
                        Toast.makeText(ResetActivity.this, toastMsg6, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ResetActivity.this, toastMsg7, Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}