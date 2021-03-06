package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myticket.Model.Network.DataModel.MainSliderResponce.Result;
import com.example.myticket.R;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private List<Result> sliders;

    public SliderAdapter(Context context, List<Result> sliders) {
        this.context = context;
        this.sliders = sliders;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = layoutInflater.inflate(R.layout.slide_item,null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_image);
        Picasso.get()
                .load(sliders.get(position).getImage())
                .into(slideImg);
        slideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsPage.class);
                String id = String.valueOf(sliders.get(position).getId());
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

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
