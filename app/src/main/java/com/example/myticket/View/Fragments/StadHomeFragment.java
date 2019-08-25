package com.example.myticket.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;
import com.example.myticket.View.Activity.StadAllResults;
import com.example.myticket.View.Activity.StadMainSearch;
import com.example.myticket.View.Adapter.StadHomeViewPagerAdapter;
import com.example.myticket.View.Adapter.StadiumSliderAdapter;

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
    public StadHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stad_home, container, false);

        weeksTabLayout = view.findViewById(R.id.stadium_tabs);
        weeksViewPager = view.findViewById(R.id.viewpager_stad);
        sliderPager = view.findViewById(R.id.stadium_home_banner);

        apiCalling = new ApiCalling(getContext());
        apiCalling.getMatchesSlider(this);


        stadHomeViewPagerAdapter = new StadHomeViewPagerAdapter(getChildFragmentManager());



        weeksViewPager.setAdapter(stadHomeViewPagerAdapter);
        weeksTabLayout.setupWithViewPager(weeksViewPager);

//        weeksTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getText() != null && tab.getText().equals("Week")){
//                    Intent intent = new Intent(getContext(), StadAllResults.class);
//                    intent.putExtra("Week","Week");
//                    startActivity(intent);
//                }
//                else if (tab.getText() != null && tab.getText().equals("Next Week")) {
//                    Intent intent = new Intent(getContext(), StadMainSearch.class);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Today",1);
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Week",2);
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Next Week",3);


        return view;
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof MainMatches) {
            MainMatches sliderResponce = (MainMatches) tApiResponse;
            listSlide = (ArrayList<MatchDetails>) sliderResponce.getResult();
            stadiumSliderAdapter = new StadiumSliderAdapter(getContext(),listSlide);
            sliderPager.setAdapter(stadiumSliderAdapter);
            timer = new Timer();
            timer.scheduleAtFixedRate(new StadHomeFragment.SliderTimer(), 3000, 4000);
        }
    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        if (listSlide != null) {
//                            if (sliderPager.getCurrentItem() < listSlide.size() - 1)
//                                sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
//                            else
//                                sliderPager.setCurrentItem(0);
//                        }
                    }
                });
            }
        }
    }
}
