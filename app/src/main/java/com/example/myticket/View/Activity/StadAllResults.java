package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadHomeViewPagerAdapter;
import com.example.myticket.View.Fragments.matchesFragment;

public class StadAllResults extends AppCompatActivity {
    private TabLayout weeksTabLayout;
    private ViewPager weeksViewPager;
    private StadHomeViewPagerAdapter stadHomeViewPagerAdapter;
    private String allResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stad_all_results);

        weeksTabLayout = findViewById(R.id.stadium_tabs);
        weeksViewPager = findViewById(R.id.viewpager_stad);
        stadHomeViewPagerAdapter = new StadHomeViewPagerAdapter(getSupportFragmentManager());

        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Today");
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Week");
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Next Week");

        weeksViewPager.setAdapter(stadHomeViewPagerAdapter);
        weeksTabLayout.setupWithViewPager(weeksViewPager);

    }
}
