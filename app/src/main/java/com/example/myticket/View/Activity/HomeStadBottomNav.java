package com.example.myticket.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.View.Fragments.SettingsFragment;
import com.example.myticket.View.Fragments.StadHomeFragment;
import com.example.myticket.View.Fragments.StadiumList;
import com.example.myticket.View.Fragments.FragmentMyTicketsStad;

public class HomeStadBottomNav extends AppCompatActivity {

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private ImageView logo;
    private String tag;
    private BottomNavigationView navView;
    private String previousPlace = "";

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

                case R.id.my_tickets:
                    tag = "home";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FragmentMyTicketsStad())
                            .commit();

                    return true;
                case R.id.settings:
                    tag = "home";
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SettingsFragment())
                            .commit();


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

        navView = findViewById(R.id.nav_view);
        Intent intent = getIntent();
        if (intent.hasExtra("name")){
            previousPlace = intent.getStringExtra("name");

        }

        tag = "home";

        setToolbar();


    }

    public void getPreviousPlace() {
        if (previousPlace.equals("stads")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new StadiumList())
                    .commit();

            navView.setSelectedItemId(R.id.stadiums_list);

        }
        else if (previousPlace.equals("settings")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SettingsFragment())
                    .commit();

            navView.setSelectedItemId(R.id.settings);

        }

        else if (previousPlace.equals("myTickets")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new  FragmentMyTicketsStad())
                    .commit();

            navView.setSelectedItemId(R.id.my_tickets);

        }
        else if (previousPlace.equals("")){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new StadHomeFragment())
                    .commit();
            navView.setSelectedItemId(R.id.navigation_home);
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new StadHomeFragment())
                    .commit();
            navView.setSelectedItemId(R.id.navigation_home);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeStadBottomNav.this,Gate.class);
        startActivity(intent);
    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
//        toolbarTitle.setText(getString(R.string.all_results));
//        toolbarTitle.setTypeface(myfont);
        logo = findViewById(R.id.logo_toolbar);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);
        backBtn.setVisibility(View.GONE);
        logo.setVisibility(View.VISIBLE);
        int id = navView.getSelectedItemId();
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeStadBottomNav.this,StadMainSearch.class);
                if (tag.equals("stadiums")){
                    intent.putExtra("tag",tag);
                }
                if (previousPlace.equals("MyTickets")|| id == R.id.navigation_home || id == R.id.my_tickets){
                    intent.putExtra("name","MyTickets");
                }
                startActivity(intent);
            }
        });
    }

    private void start() {
        getPreviousPlace();
        if (checkInternetConnection()){
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }
    }

    private boolean checkInternetConnection () {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.dialog_message))
                    .setTitle(getResources().getString(R.string.dialog_title));
            builder.setCancelable(false);
            builder.setPositiveButton(getResources().getString(R.string.retry), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    start();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);

            if (b != null) {
                b.setTextColor(getResources().getColor(R.color.white));
            }
        }
        return false;
    }



}
