package com.example.myticket;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myticket.Model.MainSliderResponce.Result;
import com.example.myticket.Model.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.MovieModel.MovieDetails;
import com.example.myticket.Network.Retrofit.ApiClient;
import com.example.myticket.Network.Retrofit.onResponceInterface;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cinema);
        sliderPager = findViewById(R.id.C_home_slider);
        SliderProgressBar = findViewById(R.id.C_home_progressBar);
        tabLayout = findViewById(R.id.cinema_tabLayout);
        moviesRV = findViewById(R.id.now_playing_rv);
        SliderProgressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);

        timer = new Timer();
        timer.scheduleAtFixedRate(new HomeCinema.SliderTimer(),3000,4000);
        ApiClient apiClient = new ApiClient(this);
        apiClient.initializeClientMainSlider("http://iscoapps.com/cinema/api/");

        ArrayList<MovieDetails> movies = new ArrayList<>();
        HomeMovieAdapter homeMovieAdapter = new HomeMovieAdapter(this,movies);
        moviesRV.setAdapter(homeMovieAdapter);
        moviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onSuccess(Object responce) {
        SliderProgressBar.setVisibility(View.GONE);
        SliderResponce sliderResponce = (SliderResponce) responce;
        listSlide = sliderResponce.getResult();
        adapter = new SliderAdapter(this, listSlide);
        sliderPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(sliderPager, true);

    }

    @Override
    public void onFail(Object responce) {

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