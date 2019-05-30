package com.example.myticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Gate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gate);
    }
}
