package com.example.myticket.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.R;

import java.util.Locale;


public class MainActivity extends AppCompatActivity
{
    ///---------------  reference of views -------------------------//
    private Button MapBtn;
    private Button LoginBtn;
    private Button RegBtn;
    private Button ForgetBtn;
    private Button GoHomeBtn;
    private Button GoGateBtn;
    private Button GoDetailsBtn;
    private Button GoResetBtn;
    private Button GoSearchPageBtn;
    private Button GoListsPage;
    private Button GoCinemaDetailsPage;
    private Button userProfileBtn;



    ////////////////////////////////////////////////////////////////////
    private String language;
    private SharedPreferences sharedPreferences;
    private String prefFile = "com.example.android.shared";
    private String LANG_KEY = "lang";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        MapBtn = findViewById(R.id.MapsBtn);
        LoginBtn = findViewById(R.id.LoginBtn);
        RegBtn = findViewById(R.id.RegBtn);
        ForgetBtn = findViewById(R.id.ForgetBtn);
        GoHomeBtn = findViewById(R.id.GoHomeBtn);
        GoGateBtn = findViewById(R.id.GoGateBtn);
        GoResetBtn = findViewById(R.id.GoResetBtn);
        GoDetailsBtn = findViewById(R.id.GoMovieDetailsBtn);
        GoSearchPageBtn = findViewById(R.id.GoToSearch);
        userProfileBtn = findViewById(R.id.button2);

        GoListsPage = findViewById(R.id.GoToLists);
        GoCinemaDetailsPage = findViewById(R.id.GoToCinemaDetails);
//        sharedPreferences = getSharedPreferences(
//                prefFile, MODE_PRIVATE);
//        language = sharedPreferences.getString(LANG_KEY, "ar");
//        Log.e("lang from sp","language");
//
//        language = Locale.getDefault().getLanguage();
//        Log.e("lang from device","language");



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
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        ForgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
        GoHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeCinema.class);
                startActivity(intent);
            }
        });
        GoGateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gate.class);
                startActivity(intent);
            }
        });
        GoDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MovieDetailsPage.class);
                intent.addCategory("movie");
                startActivity(intent);
            }
        });
//        GoResetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ResetPassword.class);
//                startActivity(intent);
//            }
//        });
        MapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchPage.class);
                startActivity(intent);
            }
        });
        GoListsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnyResultsPage.class);
                startActivity(intent);
            }
        });
        GoCinemaDetailsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CinemaDetailsPage.class);
                startActivity(intent);
            }
        });

        userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(sessionManager.getUserToken() != null ||
                        sessionManager.getUserToken() != "")
                {

                    Intent intent = new Intent(MainActivity.this, EditAccount.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
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
