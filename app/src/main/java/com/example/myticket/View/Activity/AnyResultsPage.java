package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Typeface myfont;
    //TODO: add a layout for vertical rv and fix its design

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_results_page);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        apiCalling = new ApiCalling(this);
        titleTwo = findViewById(R.id.titleTwo_results_page);
        titleOne = findViewById(R.id.titleOne_results_page);
        titleOne.setTypeface(myfont);
        titleTwo.setTypeface(myfont);
        recyclerViewOne = findViewById(R.id.rv_movies_now);
        recyclerViewTwo = findViewById(R.id.rv_movies_soon);
        recyclerViewOne.setLayoutManager(new GridLayoutManager(this,3));
        setToolbar();

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
                    setupFromHome(getString(R.string.now_playing));
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
                    setupFromHome(getString(R.string.coming_soon));
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
                    setupFromHome(getString(R.string.cinemas));
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

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.all_results));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnyResultsPage.this,SearchPage.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupCinemaMovies(String id) {
        titleOne.setText(getString(R.string.now_playing));
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
