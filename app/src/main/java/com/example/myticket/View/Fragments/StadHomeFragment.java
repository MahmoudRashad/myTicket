package com.example.myticket.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;
import com.example.myticket.View.Activity.HomeStadBottomNav;
import com.example.myticket.View.Activity.StadAllResults;
import com.example.myticket.View.Activity.StadMainSearch;
import com.example.myticket.View.Adapter.StadHomeViewPagerAdapter;
import com.example.myticket.View.Adapter.StadiumSliderAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class StadHomeFragment extends Fragment implements
        GeneralListener {

    private TabLayout weeksTabLayout;
    private ViewPager weeksViewPager;
    private StadHomeViewPagerAdapter stadHomeViewPagerAdapter;
    private ViewPager sliderPager;
    private StadiumSliderAdapter stadiumSliderAdapter;
    private Timer timer;
    private ArrayList<MatchDetails> listSlide;
    private ApiCalling apiCalling;
    private ProgressBar progressBar;
    private Button retry;
    private TextView matches_now;

    public StadHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        apiCalling = new ApiCalling(getContext());
        apiCalling.getMatchesSlider(this);

        stadHomeViewPagerAdapter = new StadHomeViewPagerAdapter(getChildFragmentManager());

        weeksViewPager.setAdapter(stadHomeViewPagerAdapter);
        weeksTabLayout.setupWithViewPager(weeksViewPager);

        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),getResources().getString(R.string.today),1);
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),getResources().getString(R.string.week),2);
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),getResources().getString(R.string.next_week),3);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stad_home, container, false);

        weeksTabLayout = view.findViewById(R.id.stadium_tabs);
        weeksViewPager = view.findViewById(R.id.viewpager_stad);
        sliderPager = view.findViewById(R.id.stadium_home_banner);
        progressBar = view.findViewById(R.id.slider_stad_pb);
        matches_now = view.findViewById(R.id.no_matches_now);
        retry = view.findViewById(R.id.slider_retry_btn);




        return view;
    }



    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
        if (tApiResponse instanceof MainMatches) {
            MainMatches sliderResponce = (MainMatches) tApiResponse;
            listSlide = (ArrayList<MatchDetails>) sliderResponce.getResult();
            if (listSlide.size() > 0) {
                stadiumSliderAdapter = new StadiumSliderAdapter(getContext(), listSlide);
                sliderPager.setAdapter(stadiumSliderAdapter);
                timer = new Timer();
                timer.scheduleAtFixedRate(new StadHomeFragment.SliderTimer(), 3000, 4000);
            }
            else{
                matches_now.setVisibility(View.VISIBLE);
            }
        }
      else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
            {
            Toast.makeText(getContext(),getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), HomeStadBottomNav.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }
    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
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
}
