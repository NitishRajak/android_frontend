package com.example.nitish_food_app_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout){
            Toast.makeText(this, "User Logging Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        setTitle("Welcome "+username);

        ListView categoryListView = (ListView) findViewById(R.id.categoryListView);
        final ArrayList<String> categories = new ArrayList<String>();
        categories.add("Veg");
        categories.add("Non Veg");
        categories.add("Pizza");
        categories.add("Burger");
        categories.add("Momo");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, categories);
        categoryListView.setAdapter(arrayAdapter);

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(), UserDashboardActivity3.class);
                intent1.putExtra("categoryName", categories.get(i));
                startActivity(intent1);
            }
        });


        //Intent intent2 = new Intent(getApplicationContext(), CategoryListingActivity1.class);
        //startActivity(intent2);
    }
}