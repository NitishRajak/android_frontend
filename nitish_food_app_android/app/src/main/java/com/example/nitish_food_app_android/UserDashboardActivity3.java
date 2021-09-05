package com.example.nitish_food_app_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

public class UserDashboardActivity3 extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard3);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        Intent intent = getIntent();
        String categoryNameSelected = intent.getStringExtra("categoryName");
        setTitle("Please Select "+categoryNameSelected);

        HashMap<String, String> map = new HashMap<>();
        map.put("category", categoryNameSelected);

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
                        if(categoryNameSelected != null){
                            if(categoryNameSelected.equalsIgnoreCase(f.getCategory())){
                                String food_name = f.getFood_name();
                                final TextView foodNameTextView = new TextView(getApplicationContext());
                                foodNameTextView.setText("Food Name: "+food_name);
                                //foodNameTextView.setGravity(Gravity.CENTER);
                                foodNameTextView.setTextSize(20);
                                String food_price = f.getFood_price();
                                final TextView foodPriceTextView = new TextView(getApplicationContext());
                                foodPriceTextView.setText("Food Price: RS."+food_price);
                                //foodPriceTextView.setGravity(Gravity.CENTER);
                                foodPriceTextView.setTextSize(20);
                                final TextView foodDescriptionTextView = new TextView(getApplicationContext());
                                foodDescriptionTextView.setText("Fodd Description: "+f.getFood_description());
                                foodDescriptionTextView.setTextSize(20);
                                final Button addToCartButton = new Button(getApplicationContext());
                                addToCartButton.setText("Add to Cart");
                                addToCartButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(UserDashboardActivity3.this, "Food Added to Cart", Toast.LENGTH_LONG).show();
                                        //Intent intent = new Intent(getApplicationContext(), CartActivity4.class);
                                        //startActivity(intent);

                                    }
                                });

                                linearLayout.addView(foodNameTextView);
                                linearLayout.addView(foodPriceTextView);
                                linearLayout.addView(foodDescriptionTextView);
                                linearLayout.addView(addToCartButton);
                            }
                        }
                    }
                }else if(response.code() == 404){
                    Toast.makeText(UserDashboardActivity3.this, "No any food items for this category exist.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FoodByCategoryResult>> call, Throwable t) {

            }
        });




    }
}