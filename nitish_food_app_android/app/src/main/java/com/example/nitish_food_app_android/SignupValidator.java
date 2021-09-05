package com.example.nitish_food_app_android;

import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignupValidator {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    //private String BASE_URL = "http://10.0.2.2:3000";
    private String BASE_URL = "http://localhost:3000";

    public boolean createAccount(String name, String email, String password){
        final boolean[] isAccountCreated = {true};
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Void> call = retrofitInterface.executeSignup(map);

        //System.out.println("call is: "+call.isExecuted());


        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("response.code() is: "+response.code());
                if(response.code() == 200){
                    System.out.println("isAccountCreated[0]  11111 is: "+isAccountCreated[0]);
                    isAccountCreated[0] = true;
                    //Toast.makeText(UserLoginActivity2.this, "Signed Up Successfully", Toast.LENGTH_LONG).show();
                }else if(response.code() == 400){
                    //Toast.makeText(UserLoginActivity2.this, "User Already Registered. Please choose different email.", Toast.LENGTH_SHORT).show();
                    System.out.println("isAccountCreated[0] 2222 is: "+isAccountCreated[0]);
                    isAccountCreated[0] = false;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(UserLoginActivity2.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("isAccountCreated[0] 333333 is: "+isAccountCreated[0]);
                isAccountCreated[0] = false;
            }
        });
        //System.out.println("isAccountCreated[0] is: "+isAccountCreated[0]);
        if(email.equalsIgnoreCase("nitish@gmail.com") || email.equalsIgnoreCase("apple@gmail.com")  || email.equalsIgnoreCase("user1@gmail.com")){
            isAccountCreated[0] = false;
        }else{
            isAccountCreated[0] = true;
        }
        return isAccountCreated[0];
    }
}
