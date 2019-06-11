package com.example.myticket;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.myticket.Model.MovieModel.MovieDetails;

import java.util.ArrayList;

public class AnyResultsPage extends AppCompatActivity {
    private TextView titleOne;
    private TextView titleTwo;
    private RecyclerView recyclerViewOne;
    private RecyclerView recyclerViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_results_page);
        titleOne = findViewById(R.id.titleOne_results_page);
        titleTwo = findViewById(R.id.titleTwo_results_page);
        recyclerViewOne = findViewById(R.id.rv_movies_now);
        recyclerViewTwo = findViewById(R.id.rv_movies_soon);

        titleOne.setText("Title One");
        titleTwo.setText("Title Two");

        ArrayList<MovieDetails> movies = new ArrayList<>();
        HomeMovieAdapter homeMovieAdapter = new HomeMovieAdapter(this,movies);
        recyclerViewOne.setAdapter(homeMovieAdapter);
        recyclerViewOne.setLayoutManager(new GridLayoutManager(this,3));

        ArrayList<MovieDetails> moviesSoon = new ArrayList<>();
        HomeMovieAdapter ComingSoonAdapter = new HomeMovieAdapter(this,moviesSoon);
        recyclerViewTwo.setAdapter(ComingSoonAdapter);
        recyclerViewTwo.setLayoutManager(new GridLayoutManager(this,3));


    }
//    public static int calculateNoOfColumns(Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        int scalingFactor = 200;
//        int noOfColumns = (int) (dpWidth / scalingFactor);
////        if(noOfColumns < 2)
////            noOfColumns = 2;
//        return noOfColumns;
//    }
}
