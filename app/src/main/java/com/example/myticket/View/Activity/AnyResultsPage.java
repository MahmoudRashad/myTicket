package com.example.myticket.View.Activity;

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
import com.example.myticket.Model.Network.DataModel.MoviesList.MoviesList;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.HomeMovieAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnyResultsPage extends AppCompatActivity implements GeneralListener {
    private TextView titleOne;
    private TextView titleTwo;
    private RecyclerView recyclerViewOne;
    private RecyclerView recyclerViewTwo;
    private HomeMovieAdapter homeMovieAdapter;

    private ArrayList<Cinema> CinemaLists;
    private ArrayList<Recently> RecentlyLists;
    private ArrayList<Coming> ComingLists;
    private ApiCalling apiCalling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_results_page);
        apiCalling = new ApiCalling(this);
        titleTwo = findViewById(R.id.titleTwo_results_page);
        titleOne = findViewById(R.id.titleOne_results_page);
        recyclerViewOne = findViewById(R.id.rv_movies_now);
        recyclerViewTwo = findViewById(R.id.rv_movies_soon);
        recyclerViewOne.setLayoutManager(new GridLayoutManager(this,3));

        Intent intent = getIntent();
        if (intent.getAction() != null){
            String action = intent.getAction();
            if (action.equals("viewMoviesRecently")){
                if (intent.getData() != null) {
                    String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Recently[] results = gson.fromJson(stringData,Recently[].class);
                    RecentlyLists = new ArrayList<>(Arrays.asList(results));
                    homeMovieAdapter = new HomeMovieAdapter(this,RecentlyLists,null,null);
                    setupFromHome("Now Playing");
                }
            }
            else if (action.equals("viewMoviesSoon")){
                if (intent.getData() != null) {
                    String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Coming[] results = gson.fromJson(stringData,Coming[].class);
                    ComingLists = new ArrayList<>(Arrays.asList(results));
                    homeMovieAdapter = new HomeMovieAdapter(this,null,ComingLists,null);
                    setupFromHome("Coming Soon");
                }
            }
            else if (action.equals("viewCinemas")){
                if (intent.getData() != null) {
                    String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Cinema[] results = gson.fromJson(stringData,Cinema[].class);
                    CinemaLists = new ArrayList<>(Arrays.asList(results));
                    homeMovieAdapter = new HomeMovieAdapter(this,null,null,CinemaLists);
                    setupFromHome("Cinemas");
                }

            }
            else if (action.equals("CinemaMoviesList")){
                if (intent.hasExtra("cinemaID")) {
                    String id = intent.getStringExtra("cinemaID");
                    setupCinemaMovies(id);
                }
            }
        }

    }

    private void setupCinemaMovies(String id) {
        titleOne.setText("Now Playing");
        titleTwo.setVisibility(View.GONE);
       // titleTwo.setText("Coming Soon");
        //Setup recycler views
        apiCalling.getCinemaMoviesList(id,this);

    }

    private void setupFromHome(String Title) {
        hideNotNeeded();
        titleOne.setText(Title);
        recyclerViewOne.setAdapter(homeMovieAdapter);

    }
    private void hideNotNeeded(){
        titleTwo.setVisibility(View.GONE);
        recyclerViewTwo.setVisibility(View.GONE);
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof MoviesList){
            MoviesList mainResult = (MoviesList) tApiResponse;
            List<Recently> moviesList = mainResult.getResult();
            homeMovieAdapter = new HomeMovieAdapter(this,moviesList,null,null);
            recyclerViewOne.setAdapter(homeMovieAdapter);

        }

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
