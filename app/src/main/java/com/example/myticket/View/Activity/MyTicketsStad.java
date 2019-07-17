package com.example.myticket.View.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadMyTicketsViewPagerAdapter;
import com.example.myticket.View.Fragments.StadMyTickets;

public class MyTicketsStad extends AppCompatActivity {
    private TabLayout ticketsTab;
    private ViewPager ticketsViewPager;
    private StadMyTicketsViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets_stad);

        ticketsTab = findViewById(R.id.sliding_tabs);
        ticketsViewPager = findViewById(R.id.stad_tickets_viewpager);
        viewPagerAdapter = new StadMyTicketsViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new StadMyTickets(),"UPCOMING");
        viewPagerAdapter.addFragment(new StadMyTickets(),"PAST");

        ticketsViewPager.setAdapter(viewPagerAdapter);
        ticketsTab.setupWithViewPager(ticketsViewPager);

        ticketsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
