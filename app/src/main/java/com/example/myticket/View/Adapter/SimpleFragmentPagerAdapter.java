package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;
import com.example.myticket.R;
import com.example.myticket.View.Fragments.UpcComingFragment;

import java.util.List;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter
{
    private Context mContext;
    List<ResultTickets> pastList;
    List<ResultTickets> comingList;
    //private Typeface myfont;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm,
                                      List<ResultTickets> comingList,
                                      List<ResultTickets> pastList) {
        super(fm);
        mContext = context;


        this.pastList = pastList;
        this.comingList = comingList;
        //myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UpcComingFragment(comingList)  ;
        }
//        else if (position == 1){
//            return new PlacesFragment();
//        } else if (position == 2){
//            return new FoodFragment();
//        }
        else {
            return new UpcComingFragment(pastList);
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position)
    {
        // Generate title based on item position
        switch (position)
        {
            case 0:
                return mContext.getString(R.string.upcomming);
            case 1:
                return mContext.getString(R.string.past);
            default:
                return null;
        }
    }
}
