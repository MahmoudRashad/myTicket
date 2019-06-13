package com.example.myticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Coming;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.View.Adapter.HomeMovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class AnyResultsPage extends AppCompatActivity {
    private TextView titleOne;
    private TextView titleTwo;
    private RecyclerView recyclerViewOne;
    private RecyclerView recyclerViewTwo;
    private HomeMovieAdapter homeMovieAdapter;

    private ArrayList<Cinema> CinemaLists;
    private ArrayList<Recently> RecentlyLists;
    private ArrayList<Coming> ComingLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_results_page);

        titleTwo = findViewById(R.id.titleTwo_results_page);
        titleOne = findViewById(R.id.titleOne_results_page);
        recyclerViewOne = findViewById(R.id.rv_movies_now);
        recyclerViewTwo = findViewById(R.id.rv_movies_soon);

        Intent intent = getIntent();
        if (getIntent().getAction() != null){
            String action = getIntent().getAction();
            if (action.equals("viewMoviesRecently")){
                RecentlyLists = getIntent().getParcelableArrayListExtra("list");
                List<Recently> Lists = RecentlyLists;
                homeMovieAdapter = new HomeMovieAdapter(this,Lists,null,null);
                setupFromHome("Now Playing");
            }
            else if (action.equals("viewMoviesSoon")){
                ComingLists = getIntent().getParcelableArrayListExtra("list");
                List<Coming> Lists = ComingLists;
                homeMovieAdapter = new HomeMovieAdapter(this,null,Lists,null);
                setupFromHome("Coming Soon");
            }
            else if (action.equals("viewCinemas")){
                CinemaLists = getIntent().getParcelableArrayListExtra("list");
                List<Cinema> Lists = CinemaLists;
                homeMovieAdapter = new HomeMovieAdapter(this,null,null,Lists);
                setupFromHome("Cinemas");
            }
        }
        titleTwo.setText("Title Two");

    }

    private void setupFromHome(String Title) {
        hideNotNeeded();
        titleOne.setText(Title);
        recyclerViewOne.setAdapter(homeMovieAdapter);
        recyclerViewOne.setLayoutManager(new GridLayoutManager(this,3));
    }
    private void hideNotNeeded(){
        titleTwo.setVisibility(View.GONE);
        recyclerViewTwo.setVisibility(View.GONE);
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
