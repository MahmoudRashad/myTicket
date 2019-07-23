package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.MainSliderResponce.Result;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StadiumSliderAdapter extends PagerAdapter {
    private Context context;
    private List<MatchDetails> sliders;

    public StadiumSliderAdapter(Context context, List<MatchDetails> sliders) {
        this.context = context;
        this.sliders = sliders;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = layoutInflater.inflate(R.layout.stadium_slider_item,null);

        TextView dawryName = slideLayout.findViewById(R.id.dawry_name);
        TextView stadiumName = slideLayout.findViewById(R.id.banner_stadium_name);
        TextView teamOneName = slideLayout.findViewById(R.id.team_one_name);
        TextView teamTwoName = slideLayout.findViewById(R.id.team_two_name);
        ImageView teamOneImage = slideLayout.findViewById(R.id.team_one_image);
        ImageView teamTwoImage = slideLayout.findViewById(R.id.team_two_image);


        Picasso.get()
                .load(sliders.get(position).getTeam1Image())
                .into(teamOneImage);
        Picasso.get()
                .load(sliders.get(position).getTeam2Image())
                .into(teamTwoImage);
        dawryName.setText(sliders.get(position).getCyclicName());
        stadiumName.setText(sliders.get(position).getStadiumName());
        teamOneName.setText(sliders.get(position).getTeam1Name());
        teamTwoName.setText(sliders.get(position).getTeam2Name());



        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
