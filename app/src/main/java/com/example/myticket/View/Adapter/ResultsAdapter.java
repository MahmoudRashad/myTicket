package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Coming;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.R;
import com.example.myticket.View.Activity.CinemaDetailsPage;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MovieViewHolder> {
    private Context context;
    private List<Result> results;
    public ResultsAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ResultsAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_list_item,viewGroup,false);
        return new ResultsAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.MovieViewHolder movieViewHolder, int i) {
        Result result = results.get(i);
        movieViewHolder.movieTitle.setText(result.getName());
        String rate = result.getRate().toString();
        movieViewHolder.reviewRate.setText(rate);
        String reviews = result.getReviews().toString();
        movieViewHolder.reviewsTotal.setText(reviews);
        Picasso.get()
                .load(result.getImage())
                .into(movieViewHolder.moviePhoto);
    }

    @Override
    public int getItemCount() {
       if (results != null)
           return results.size();
       else return 0;
    }

    public void update(ArrayList<Result> arrayList){
        if (arrayList != null) {
            results.clear();
            results.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView moviePhoto;
        private TextView movieTitle;
        private TextView reviewsTotal;
        private TextView reviewRate;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePhoto = itemView.findViewById(R.id.nowPlaying_image);
            movieTitle = itemView.findViewById(R.id.nowPlaying_name);
            reviewRate = itemView.findViewById(R.id.home_rate_value);
            reviewsTotal = itemView.findViewById(R.id.reviewsNumber);

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
