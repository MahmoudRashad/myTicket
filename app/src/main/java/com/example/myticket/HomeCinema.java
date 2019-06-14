package com.example.myticket;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myticket.Model.Network.DataModel.MainSliderResponce.Result;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MovieModel.MovieDetails;
import com.example.myticket.Model.Network.Retrofit.ApiClient;
import com.example.myticket.Model.Network.Retrofit.onResponceInterface;
import com.example.myticket.View.Adapter.HomeMovieAdapter;
import com.example.myticket.View.Adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeCinema extends AppCompatActivity implements onResponceInterface {

    private List<Result> listSlide;
    private ViewPager sliderPager;
    private SliderAdapter adapter;
    private ProgressBar SliderProgressBar;
    private TabLayout tabLayout;
    private Timer timer;
    private RecyclerView moviesRV;
    private RecyclerView comingSoonRV;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cinema);
        sliderPager = findViewById(R.id.C_home_slider);
        SliderProgressBar = findViewById(R.id.C_home_progressBar);
        tabLayout = findViewById(R.id.cinema_tabLayout);
        moviesRV = findViewById(R.id.now_playing_rv);
        comingSoonRV = findViewById(R.id.coming_soon_rv);
        drawerLayout = findViewById(R.id.home_cinema_drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SliderProgressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);


//        ApiClient apiClient = new ApiClient(this);
//        apiClient.initializeClientMainSlider();

        ArrayList<MovieDetails> movies = new ArrayList<>();
        HomeMovieAdapter homeMovieAdapter = new HomeMovieAdapter(this,movies);
        moviesRV.setAdapter(homeMovieAdapter);
        moviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<MovieDetails> moviesSoon = new ArrayList<>();
        HomeMovieAdapter ComingSoonAdapter = new HomeMovieAdapter(this,moviesSoon);
        comingSoonRV.setAdapter(ComingSoonAdapter);
        comingSoonRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onSuccess(Object responce) {
        SliderProgressBar.setVisibility(View.GONE);
        SliderResponce sliderResponce = (SliderResponce) responce;
        listSlide = sliderResponce.getResult();
        adapter = new SliderAdapter(this, listSlide);
        sliderPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(sliderPager, true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new HomeCinema.SliderTimer(),3000,4000);

    }

    @Override
    public void onFail(Object responce) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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