package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.R;
import com.example.myticket.View.Activity.CinemaDetailsPage;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private ArrayList<Result> results;
    private Typeface myfont;


    public SearchAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_list_item_search,viewGroup,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        Result searchResult = results.get(i);
        searchViewHolder.title.setText(searchResult.getName());
        if (searchResult.getType().equals("1"))
        {
            searchViewHolder.subTitle.setText(searchResult.getDate());
        }
        else {
            searchViewHolder.subTitle.setText(searchResult.getAddress());
        }
        Picasso.get()
                .load(searchResult.getImage())
                .into(searchViewHolder.imageView);
        float rate = Float.parseFloat(searchResult.getRate());
        searchViewHolder.ratingBar.setRating(rate);
    }

    @Override
    public int getItemCount() {
        if (results == null)
            return 0;
        else if (results.size() >= 5)
            return 5;
        else if (results.size() >= 1)
            return results.size();
        else
            return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView title;
        private TextView subTitle;
        private RatingBar ratingBar;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.search_image);
            title = itemView.findViewById(R.id.search_title);
            subTitle = itemView.findViewById(R.id.search_SubTitle);
            ratingBar = itemView.findViewById(R.id.rating_bar_search);
            title.setTypeface(myfont);
            subTitle.setTypeface(myfont);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Result searchResult = results.get(position);
            if (searchResult.getType().equals("1"))
            {
                Intent intent = new Intent(context, MovieDetailsPage.class);
                String id = String.valueOf(searchResult.getId());
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
            else{
                Intent intent = new Intent(context, CinemaDetailsPage.class);
                String id = String.valueOf(searchResult.getId());
                intent.putExtra("cinemaID",id);
                context.startActivity(intent);
            }

        }
    }

}
