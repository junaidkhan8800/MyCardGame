package com.m90.mycardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                String token = sh.getString("userId", null);

                if (token == null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,NavigationDrawerActivity.class));
                }

            }
        }, 3000);

    }
}