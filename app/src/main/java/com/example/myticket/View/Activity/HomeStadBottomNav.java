package com.example.myticket.View.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.StadHomeFragment;

public class HomeStadBottomNav extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new StadHomeFragment())
                            .commit();

                    return true;

                case R.id.stadiums_list:
                    //  mTextMessage.setText("dashboard");

                    return true;
                case R.id.matches_list:
                  //  mTextMessage.setText("dashboard");

                    return true;
                case R.id.settings:
                  //  mTextMessage.setText("notifications");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_stad_bottom_nav);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new StadHomeFragment())
                .commit();
    }

}
