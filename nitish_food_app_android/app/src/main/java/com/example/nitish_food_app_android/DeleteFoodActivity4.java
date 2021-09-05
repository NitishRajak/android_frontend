package com.example.nitish_food_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteFoodActivity4 extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food4);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        HashMap<String, String> map = new HashMap<>();
        map.put("category", "all");

        Call<List<FoodByCategoryResult>> call = retrofitInterface.executeFoodByCategory(map);
        call.enqueue(new Callback<List<FoodByCategoryResult>>() {
            @Override
            public void onResponse(Call<List<FoodByCategoryResult>> call, Response<List<FoodByCategoryResult>> response) {
                //Toast.makeText(UserDashboardActivity3.this, "response 0 is: "+response, Toast.LENGTH_SHORT).show();
                if(response.code() == 200){
                    //Toast.makeText(UserDashboardActivity3.this, "response is: "+response, Toast.LENGTH_SHORT).show();
                    List<FoodByCategoryResult> result = response.body();
                    //Toast.makeText(UserDashboardActivity3.this, "response body is: "+response.body(), Toast.LENGTH_SHORT).show();
                    for(FoodByCategoryResult f : result) {
                        //Toast.makeText(UserDashboardActivity3.this, "food name is: " + f.getFood_name(), Toast.LENGTH_SHORT).show();

                        String food_name = f.getFood_name();
                        final TextView foodNameTextView = new TextView(getApplicationContext());
                        foodNameTextView.setText("Food Name: " + food_name);
                        //foodNameTextView.setGravity(Gravity.CENTER);
                        foodNameTextView.setTextSize(20);
                        String food_price = f.getFood_price();
                        final TextView foodPriceTextView = new TextView(getApplicationContext());
                        foodPriceTextView.setText("Food Price: RS." + food_price);
                        //foodPriceTextView.setGravity(Gravity.CENTER);
                        foodPriceTextView.setTextSize(20);
                        String category = f.getCategory();
                        final TextView categoryTextView = new TextView(getApplicationContext());
                        categoryTextView.setText("Category: " + category);
                        //foodPriceTextView.setGravity(Gravity.CENTER);
                        categoryTextView.setTextSize(20);
                        final TextView foodDescriptionTextView = new TextView(getApplicationContext());
                        foodDescriptionTextView.setText("Food Description: " + f.getFood_description());
                        foodDescriptionTextView.setTextSize(20);
                        final Button addToCartButton = new Button(getApplicationContext());
                        addToCartButton.setText("Delete Food Item");

                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("food_name", food_name);
                        map2.put("food_price", food_price);
                        map2.put("category", f.getCategory());
                        map2.put("food_description", f.getFood_description());

                        addToCartButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Call<Void> call2 = retrofitInterface.executeDeleteFood(map2);
                                call2.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call2, Response<Void> response2) {
                                        if(response2.code() == 200){
                                            Toast.makeText(DeleteFoodActivity4.this, "Food Item Delete Successfully", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), DeleteFoodActivity4.class);
                                            startActivity(intent);
                                        }else if(response2.code() == 400){
                                            Toast.makeText(DeleteFoodActivity4.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call2, Throwable t) {
                                        Toast.makeText(DeleteFoodActivity4.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        });

                        linearLayout.addView(foodNameTextView);
                        linearLayout.addView(foodPriceTextView);
                        linearLayout.addView(categoryTextView);
                        linearLayout.addView(foodDescriptionTextView);
                        linearLayout.addView(addToCartButton);



                    }
                }else if(response.code() == 404){
                    Toast.makeText(DeleteFoodActivity4.this, "No any food items for this category exist.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FoodByCategoryResult>> call, Throwable t) {

            }
        });
    }
}