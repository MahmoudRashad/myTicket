package com.example.myticket.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myticket.R;

public class StadAllSearchResults extends AppCompatActivity {
    private RecyclerView allResultsRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stad_all_search_results);
        allResultsRv = findViewById(R.id.all_results_search_rv);
        allResultsRv.setLayoutManager(new LinearLayoutManager(this));
         }
}
