package com.example.myticket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private Button MapBtn;
    private Button LoginBtn;
    private Button RegBtn;
    private Button ForgetBtn;
    private Button GoHomeBtn;
    private Button GoGateBtn;
    private String language;
    private SharedPreferences sharedPreferences;
    private String prefFile = "com.example.android.shared";
    private String LANG_KEY = "lang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapBtn = findViewById(R.id.MapsBtn);
        LoginBtn = findViewById(R.id.LoginBtn);
        RegBtn = findViewById(R.id.RegBtn);
        ForgetBtn = findViewById(R.id.ForgetBtn);
        GoHomeBtn = findViewById(R.id.GoHomeBtn);
        GoGateBtn = findViewById(R.id.GoGateBtn);
        sharedPreferences = getSharedPreferences(
                prefFile, MODE_PRIVATE);
        language = sharedPreferences.getString(LANG_KEY, "ar");
        Log.e("lang from sp","language");

        language = Locale.getDefault().getLanguage();
        Log.e("lang from device","language");



        MapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        ForgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        GoHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomeCinema.class);
                startActivity(intent);
            }
        });
        GoGateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Gate.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString(LANG_KEY, language);
        preferencesEditor.apply();
    }
}
