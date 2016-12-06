package com.example.zacharius.sma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.zacharius.sma.LoginActivity.context;

/**
 * Created by jakew on 11/30/2016.
 */

public class ResetPasswordActivity extends AppCompatActivity {
    private String newPassword;
    private String passwordConfirm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Log.d("ResetPasswordActivity", "Activity starting");


    }

    public void onClickResetPW(View view){

        context = getApplicationContext();

        EditText nPassword = (EditText)findViewById(R.id.newPassword);
        EditText cnPassword = (EditText)findViewById(R.id.confirmPassword);
        newPassword = nPassword.getText().toString();
        passwordConfirm = cnPassword.getText().toString();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(newPassword);
        boolean found = matcher.find();



        if(newPassword.equals(passwordConfirm) &&
                newPassword.length() <= 32 &&
                newPassword.length() >= 8  &&
                 !found){
            ServerComm.changePassword(newPassword);
            Intent i = new Intent(view.getContext(), ContactListActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(view.getContext(), "Invalid entry please try again\n",
                    Toast.LENGTH_SHORT).show();
            nPassword.setText("");
            cnPassword.setText("");

        }
        
    }
}
