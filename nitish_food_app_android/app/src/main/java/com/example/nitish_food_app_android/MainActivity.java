package com.example.nitish_food_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void userLoginButtonOnClick(View view){
        Intent intent = new Intent(getApplicationContext(), UserLoginActivity2.class);
        startActivity(intent);
    }

    public void adminLoginButtonOnClick(View view){
        Intent intent = new Intent(getApplicationContext(), AdminLoginActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}