//package com.example.myticket;
//
//import android.support.annotation.LayoutRes;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.view.MenuItem;
//
//public class CustomAppCompat extends AppCompatActivity {
//    Toolbar toolbar;
//    protected DrawerLayout drawerLayout;
//    protected ActionBarDrawerToggle toggle;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//    protected void setLayout(@LayoutRes int layout) {
//        setContentView(layout);
//        toolbar = findViewById(R.id.toolbar);
//        drawerLayout = findViewById(R.id.home_cinema_drawer_layout);
//        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_nav,R.string.close_nav);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}
