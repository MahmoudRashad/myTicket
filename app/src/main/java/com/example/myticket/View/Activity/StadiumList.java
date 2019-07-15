package com.example.myticket.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadiumsAdapter;

public class StadiumList extends AppCompatActivity {
    private RecyclerView stadiumRv;
    private StadiumsAdapter stadiumsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_list);
        stadiumRv = findViewById(R.id.stadiums_rv);
        stadiumsAdapter = new StadiumsAdapter(this);
        stadiumRv.setLayoutManager(new LinearLayoutManager(this));
        stadiumRv.setAdapter(stadiumsAdapter);
    }
}
