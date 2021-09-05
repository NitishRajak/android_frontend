package com.example.nitish_food_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddFoodActivity4 extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    public void addFood(View view){
        Log.i("nitish", "one");


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.i("nitish", "two");
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        EditText foodNameEditText = findViewById(R.id.foodNameEditText);
        EditText foodPriceEditText = findViewById(R.id.foodPriceEditText);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //String category = spinner.getSelectedItem().toString();
        EditText foodDescriptionEditText = findViewById(R.id.foodDescriptionEditText);

        HashMap<String, String> map = new HashMap<>();
        map.put("food_name", foodNameEditText.getText().toString());
        map.put("food_price", foodPriceEditText.getText().toString());
        map.put("category", spinner.getSelectedItem().toString());
        map.put("food_description", foodDescriptionEditText.getText().toString());
        Log.i("nitish", "upto here");

        Call<Void> call = retrofitInterface.executeAddFood(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    Toast.makeText(AddFoodActivity4.this, "Food added successfully", Toast.LENGTH_LONG).show();
                }else if(response.code() == 400){
                    Toast.makeText(AddFoodActivity4.this, "Same food already exist. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddFoodActivity4.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food4);

        Spinner categorySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Veg");
        categories.add("Non Veg");
        categories.add("Pizza");
        categories.add("Burger");
        categories.add("Momo");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(arrayAdapter);
    }
}