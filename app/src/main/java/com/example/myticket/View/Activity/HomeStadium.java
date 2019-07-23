package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadHomeViewPagerAdapter;
import com.example.myticket.View.Fragments.matchesFragment;

public class HomeStadium extends AppCompatActivity {
    private TabLayout weeksTabLayout;
    private ViewPager weeksViewPager;
    private StadHomeViewPagerAdapter stadHomeViewPagerAdapter;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mLinear;
    private LinearLayout mLinearNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_stadium);

        weeksTabLayout = findViewById(R.id.stadium_tabs);
        weeksViewPager = findViewById(R.id.viewpager_stad);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mLinear = findViewById(R.id.nav_5);
        mLinearNotifications = findViewById(R.id.nav_2);
        stadHomeViewPagerAdapter = new StadHomeViewPagerAdapter(getSupportFragmentManager());

        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Today", 1);
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Week", 1);
        stadHomeViewPagerAdapter.addFragment(new matchesFragment(),"Next Week", 1);

        weeksViewPager.setAdapter(stadHomeViewPagerAdapter);
        weeksTabLayout.setupWithViewPager(weeksViewPager);

        weeksTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() != null && tab.getText().equals("Week")){
                    Intent intent = new Intent(HomeStadium.this,StadAllResults.class);
                    intent.putExtra("Week","Week");
                    startActivity(intent);
                }
                else if (tab.getText() != null && tab.getText().equals("Next Week")) {
                    Intent intent = new Intent(HomeStadium.this,StadMainSearch.class);
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

        mLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mLinearNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeStadium.this,MyNotifications.class);
                startActivity(intent);
            }
        });

    }
}
