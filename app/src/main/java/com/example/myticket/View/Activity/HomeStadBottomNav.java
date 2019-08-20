package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.View.Fragments.StadHomeFragment;
import com.example.myticket.View.Fragments.StadiumList;

public class HomeStadBottomNav extends AppCompatActivity {

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private String tag;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    tag = "home";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new StadHomeFragment())
                            .commit();

                    return true;

                case R.id.stadiums_list:
                    tag = "stadiums";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new StadiumList())
                            .commit();


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
        changeStatusBarColor();
        setContentView(R.layout.activity_home_stad_bottom_nav);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new StadHomeFragment())
                .commit();
        tag = "home";

        setToolbar();


    }
    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
    }



    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
//        toolbarTitle.setText(getString(R.string.all_results));
//        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeStadBottomNav.this,StadMainSearch.class);
                if (tag.equals("stadiums")){
                    intent.putExtra("tag",tag);
                }
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
