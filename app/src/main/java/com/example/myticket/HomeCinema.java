package com.example.myticket;

import android.content.Intent;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.MainResult;
import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Coming;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.Result;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MovieModel.MovieDetails;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.ApiClient;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.Retrofit.onResponceInterface;
import com.example.myticket.View.Activity.MainActivity;
import com.example.myticket.View.Activity.MapsActivity;
import com.example.myticket.View.Adapter.HomeMovieAdapter;
import com.example.myticket.View.Adapter.SliderAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeCinema extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        GeneralListener
{

    private List<Result> listSlide;
    private List<Cinema> CinemaLists;
    private List<Recently> RecentlyLists;
    private List<Coming> ComingLists;
    private com.example.myticket.Model.Network.DataModel.HomeResult.Result homeResult;

    private ViewPager sliderPager;
    private SliderAdapter adapter;
    private ProgressBar SliderProgressBar;
    private TabLayout tabLayout;
    private Timer timer;
    private RecyclerView moviesRV;
    private RecyclerView comingSoonRV;
    private RecyclerView cinemasRV;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TextView seeAllRecently;
    private TextView seeAllComingSoon;
    private TextView seeAllCinema;
    private TextView seeAllNearby;
    private ApiCalling apiCalling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cinema);

        apiCalling = new ApiCalling(this);

        sliderPager = findViewById(R.id.C_home_slider);
        SliderProgressBar = findViewById(R.id.C_home_progressBar);
        tabLayout = findViewById(R.id.cinema_tabLayout);
        moviesRV = findViewById(R.id.now_playing_rv);
        comingSoonRV = findViewById(R.id.coming_soon_rv);
        cinemasRV = findViewById(R.id.cinemas_rv);
        seeAllRecently = findViewById(R.id.nowPlaying_seeAll);
        seeAllComingSoon = findViewById(R.id.comingSoon_seeAll);
        seeAllCinema = findViewById(R.id.cinema_seeAll);
        seeAllNearby = findViewById(R.id.cinema_seeNearBy);
        drawerLayout = findViewById(R.id.home_cinema_drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SliderProgressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);

        setNavigationViewListener();
        apiCalling.homeApiCall(this);
        apiCalling.mainSliderCall(this);

//        NavigationView navigationView = (NavigationView) findViewById(R.id.navmenu);
//
//        // get menu from navigationView
//        Menu menu = navigationView.getMenu();
//
//        // find MenuItem you want to change
//        MenuItem nav_profile = menu.findItem(R.id.profile);
//
//        nav_profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Intent intent = new Intent(HomeCinema.this , EditAccount.class);
//                startActivity(intent);
//                Toast.makeText(HomeCinema.this,"test" , Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });

//        ApiClient apiClient = new ApiClient(this);
//        apiClient.initializeClientMainSlider();

//    initializeClientMainSlider    ApiClient clientTwo = new ApiClient(new onResponceInterface() {
//            @Override
//            public void onSuccess(Object responce) {
//                getHomeData(responce);
//            }
//
//            @Override
//            public void onFail(Object responce) {
//                Toast.makeText(HomeCinema.this,"Failed To Load",Toast.LENGTH_LONG).show();
//            }
//        });
//        clientTwo.intializeHomeResponce();
//
//        ApiClient clientTwo = new ApiClient(new onResponceInterface() {
//            @Override
//            public void onSuccess(Object responce) {
//                getHomeData(responce);
//            }
//
//            @Override
//            public void onFail(Object responce) {
//                Toast.makeText(HomeCinema.this,"Failed To Load",Toast.LENGTH_LONG).show();
//            }
//        });
//        clientTwo.intializeHomeResponce();




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Toast.makeText(HomeCinema.this,"test" , Toast.LENGTH_LONG).show();
        Log.e("test**" , "nav item");
        switch (item.getItemId()) {

            case R.id.profile: {
                //do somthing
                Intent intent = new Intent(HomeCinema.this , EditAccount.class);
                startActivity(intent);
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView =  findViewById(R.id.navmenu);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getHomeData(Object responce) {
        MainResult mainResult = (MainResult) responce;
        homeResult = mainResult.getResult();
        CinemaLists = homeResult.getCinema();
        RecentlyLists = homeResult.getRecently();
        ComingLists = homeResult.getComing();

        HomeMovieAdapter homeMovieAdapter = new HomeMovieAdapter(this,RecentlyLists,null,null);
        moviesRV.setAdapter(homeMovieAdapter);
        moviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        HomeMovieAdapter ComingSoonAdapter = new HomeMovieAdapter(this,null,ComingLists,null);
        comingSoonRV.setAdapter(ComingSoonAdapter);
        comingSoonRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        HomeMovieAdapter CinemaAdapter = new HomeMovieAdapter(this,null,null,CinemaLists);
        cinemasRV.setAdapter(CinemaAdapter);
        cinemasRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        seeAllRecently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCinema.this, AnyResultsPage.class);
                intent.setAction("viewMoviesRecently");
                String ListDumb = new Gson().toJson(RecentlyLists);
                intent.setData(Uri.fromParts("scheme",ListDumb,null));
                startActivity(intent);
            }
        });
        seeAllComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCinema.this, AnyResultsPage.class);
                intent.setAction("viewMoviesSoon");
                String ListDumb = new Gson().toJson(ComingLists);
                intent.setData(Uri.fromParts("schemeComing",ListDumb,null));
                startActivity(intent);
            }
        });
        seeAllCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCinema.this, AnyResultsPage.class);
                intent.setAction("viewCinemas");
                String ListDumb = new Gson().toJson(CinemaLists);
                intent.setData(Uri.fromParts("schemeCinema",ListDumb,null));
                startActivity(intent);
            }
        });
        seeAllNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCinema.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        SliderProgressBar.setVisibility(View.GONE);
        if (tApiResponse instanceof  SliderResponce) {
            SliderResponce sliderResponce = (SliderResponce) tApiResponse;
            listSlide = sliderResponce.getResult();
            adapter = new SliderAdapter(this, listSlide);
            sliderPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(sliderPager, true);
            timer = new Timer();
            timer.scheduleAtFixedRate(new HomeCinema.SliderTimer(), 3000, 4000);
        }
        else{
            getHomeData(tApiResponse);
        }
    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            HomeCinema.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (listSlide != null) {
                    if (sliderPager.getCurrentItem() < listSlide.size() - 1)
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                    else
                        sliderPager.setCurrentItem(0);
                }
                }
            });
        }
    }
}