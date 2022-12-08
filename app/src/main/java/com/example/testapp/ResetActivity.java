package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
                String snackbarMsg = getString(R.string.resetPassSnack);

                if (pass.equals(rePass)){
                    //toast
                    String toastMsg00 = getString(R.string.passSuccess);
                    String toastMsg01 = getString(R.string.passNotSuccess);

                    Boolean PasswordUpdate = db.updatePassword(user,pass);
                    if(PasswordUpdate == true){
                        Toast.makeText(ResetActivity.this, toastMsg00, Toast.LENGTH_SHORT).show();
                        Intent intentSignIn = new Intent(ResetActivity.this, Signin.class);
                        startActivity(intentSignIn);
                        finish();
                    }
                    else{
                        Toast.makeText(ResetActivity.this, toastMsg01, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Snackbar snackMsg = Snackbar.make(view,snackbarMsg,Snackbar.LENGTH_SHORT);
                    snackMsg.show();
                }


            }
        });


    }
}