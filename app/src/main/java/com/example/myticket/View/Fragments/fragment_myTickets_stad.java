package com.example.myticket.View.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadMyTicketsViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_myTickets_stad extends Fragment {
    private TabLayout ticketsTab;
    private ViewPager ticketsViewPager;
    private StadMyTicketsViewPagerAdapter viewPagerAdapter;

    public fragment_myTickets_stad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_tickets_stad, container, false);
        ticketsTab = view.findViewById(R.id.sliding_tabs);
        ticketsViewPager = view.findViewById(R.id.stad_tickets_viewpager);
        viewPagerAdapter = new StadMyTicketsViewPagerAdapter(getActivity().getSupportFragmentManager());

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
        return view;
    }

}
