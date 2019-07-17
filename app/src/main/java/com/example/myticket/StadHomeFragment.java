package com.example.myticket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myticket.View.Activity.HomeStadium;
import com.example.myticket.View.Activity.MyNotifications;
import com.example.myticket.View.Activity.StadAllResults;
import com.example.myticket.View.Activity.StadMainSearch;
import com.example.myticket.View.Adapter.StadHomeViewPagerAdapter;
import com.example.myticket.View.Fragments.matchesFragment;


public class StadHomeFragment extends Fragment {

    private TabLayout weeksTabLayout;
    private ViewPager weeksViewPager;
    private StadHomeViewPagerAdapter stadHomeViewPagerAdapter;
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

        stadHomeViewPagerAdapter = new StadHomeViewPagerAdapter(getFragmentManager());

        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Today");
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Week");
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Next Week");

        weeksViewPager.setAdapter(stadHomeViewPagerAdapter);
        weeksTabLayout.setupWithViewPager(weeksViewPager);

        weeksTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() != null && tab.getText().equals("Week")){
                    Intent intent = new Intent(getContext(), StadAllResults.class);
                    intent.putExtra("Week","Week");
                    startActivity(intent);
                }
                else if (tab.getText() != null && tab.getText().equals("Next Week")) {
                    Intent intent = new Intent(getContext(), StadMainSearch.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
