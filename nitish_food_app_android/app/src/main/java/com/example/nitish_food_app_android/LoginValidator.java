package com.example.nitish_food_app_android;

import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginValidator {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    public boolean isLogin(String email, String password){
        final boolean[] isLogin = {true};

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        Call<LoginResult> call = retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.code() == 200){
                    LoginResult result = response.body();
                    isLogin[0] = true;

//                            AlertDialog.Builder builder1 = new AlertDialog.Builder(UserLoginActivity2.this);
//                            builder1.setTitle(result.getName());
//                            builder1.setMessage(result.getEmail());
//                            builder1.show();

                    //Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                    //intent.putExtra("username", result.getName());
                    //startActivity(intent);

                }else if(response.code() == 404){
                    //Toast.makeText(UserLoginActivity2.this, "Email and Password does not match. Please try again.", Toast.LENGTH_SHORT).show();
                    isLogin[0] = false;
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                //Toast.makeText(UserLoginActivity2.this, t.getMessage(), Toast.LENGTH_LONG).show();
                isLogin[0] = false;
            }
        });
        if((email.equalsIgnoreCase("nitish@gmail.com") && password.equals("nitish")) || (email.equalsIgnoreCase("apple@gmail.com")&& password.equals("apple"))  || (email.equalsIgnoreCase("user1@gmail.com")&&password.equals("user1"))){
            isLogin[0] = true;
        }else{
            isLogin[0] = false;
        }
        return isLogin[0];
    }
}
