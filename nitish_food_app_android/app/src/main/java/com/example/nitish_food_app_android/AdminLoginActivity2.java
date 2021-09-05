package com.example.nitish_food_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity2 extends AppCompatActivity {

    public void goBack(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void signupLogin(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        final String username = usernameEditText.getText().toString();

        if("nitish".equalsIgnoreCase(usernameEditText.getText().toString()) && "nitish123".equals(passwordEditText.getText().toString())){
            Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity3.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login2);
    }
}