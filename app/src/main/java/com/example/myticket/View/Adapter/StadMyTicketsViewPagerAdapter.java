package com.example.myticket.View.Adapter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myticket.Model.Network.StadiumModel.MyTicket.Past;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StadMyTicketsViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    public StadMyTicketsViewPagerAdapter(FragmentManager fm) {
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

    public void clear(){
        fragmentList.clear();
        fragmentTitles.clear();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
    public void addFragment(Fragment fragment, String fragmentTitle, List<Past> pastTickets){
        Bundle bundle = new Bundle();
        String ListDumb = new Gson().toJson(pastTickets);
        bundle.putString("list",ListDumb);
        fragment.setArguments(bundle);
        fragmentList.add(fragment);
        fragmentTitles.add(fragmentTitle);
        notifyDataSetChanged();
    }
}
