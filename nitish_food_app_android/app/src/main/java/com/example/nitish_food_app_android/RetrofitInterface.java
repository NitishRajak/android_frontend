package com.example.nitish_food_app_android;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/addfood")
    Call<Void> executeAddFood(@Body HashMap<String, String> map);

    @POST("/getfoodbycategories")
    Call<List<FoodByCategoryResult>> executeFoodByCategory(@Body HashMap<String, String> map);

    @POST("/deletefood")
    Call<Void> executeDeleteFood(@Body HashMap<String, String> map);
}
