package com.example.p4paysecurepayment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class splash_scrren extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scrren);
        sharedPreferences = getSharedPreferences("p4pay",0);
        editor = sharedPreferences.edit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences.getString("FirstTime","").equals( "launch")){
                    startActivity(new Intent(getApplicationContext(),Login_page.class));
                }else{
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                //startActivity(new Intent(getApplicationContext(),Login_page.class));
            }
        },1000);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }
}
