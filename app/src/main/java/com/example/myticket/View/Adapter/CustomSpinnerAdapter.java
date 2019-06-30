package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.ReserveModel.ResultReserveCinema;
import com.example.myticket.R;


import java.util.List;

/**
 * Created by Amr Eldsokey on 2/6/2018.
 */

public class CustomSpinnerAdapter extends BaseAdapter
{
    Context context;
    List<ResultReserveCinema> selectors;
    LayoutInflater inflter;
    private Typeface myfont;


    public CustomSpinnerAdapter(Context context, List<ResultReserveCinema> selectors) {
        this.context = context;
        this.selectors = selectors;
        inflter = (LayoutInflater.from(context));
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @Override
    public int getCount() {
        return selectors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = inflter.inflate(R.layout.row_filter, null);
        TextView title =  view.findViewById(R.id.textView13);
        title.setTypeface(myfont);
        title.setText(selectors.get(i).getName());

//        Typeface typeLight= Typeface.createFromAsset(context.getAssets(),"montserrat_alternates_light.otf");
//
//        title.setTypeface(typeLight);

        return view;
    }

//    @Override
//    public boolean isEnabled(int position) {
//        if (position == 0) {
//            // Disable the first item from Spinner
//            // First item will be use for hint
//            return false;
//        } else {
//            return true;
//        }
//    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view.findViewById(R.id.textView13);
        tv.setTypeface(myfont);
//        if (position == 0) {
//            // Set the hint text color gray
//            tv.setTextColor(Color.GRAY);
//        } else {
//            tv.setTextColor(Color.BLACK);
//        }
        return view;
    }
}
