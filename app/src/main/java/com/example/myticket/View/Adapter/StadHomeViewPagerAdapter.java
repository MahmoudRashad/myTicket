package com.example.myticket.View.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myticket.View.Activity.StadAllResults;

import java.util.ArrayList;
import java.util.List;

public class StadHomeViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    public StadHomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
    public void addFragment(Fragment fragment , String fragmentTitle){
        fragmentList.add(fragment);
        fragmentTitles.add(fragmentTitle);
    }
}

