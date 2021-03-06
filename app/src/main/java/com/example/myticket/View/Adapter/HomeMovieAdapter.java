package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.View.Activity.CinemaDetailsPage;
import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Coming;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.example.myticket.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Recently> movieDetails;
    private List<Coming> comingList;
    private List<Cinema> cinemaList;
    private Typeface myfont;
    @LayoutRes
    private int layoutId;


    public HomeMovieAdapter(Context context, List<Recently> movieDetails, List<Coming> comingList,List<Cinema> cinemaList, int layoutId ) {
        this.context = context;
        this.movieDetails = movieDetails;
        this.comingList = comingList;
        this.cinemaList = cinemaList;
        this.layoutId = layoutId;
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {


        if (movieDetails != null) {
            Recently movie = movieDetails.get(i);
            Picasso.get()
                    .load(movie.getImage())
                    .into(movieViewHolder.moviePhoto);
            movieViewHolder.movieTitle.setText(movie.getName());
            if (movie.getRate() != null) {
                movieViewHolder.reviewRate.setText(movie.getRate());
            }
            if (movie.getReviews() != null) {
                String reviews= movie.getReviews().toString();
                movieViewHolder.reviewsTotal.setText(reviews);
            }
        }
        else if (comingList != null){
            Coming movie = comingList.get(i);
            Picasso.get()
                    .load(movie.getImage())
                    .into(movieViewHolder.moviePhoto);
            movieViewHolder.movieTitle.setText(movie.getName());
            if (movie.getRate() != null) {
                movieViewHolder.reviewRate.setText(movie.getRate());
            }
            if (movie.getReviews() != null) {
                String reviews = movie.getReviews().toString();
                movieViewHolder.reviewsTotal.setText(reviews);
            }

        }
        else if (cinemaList != null){
            Cinema cinema = cinemaList.get(i);
            Picasso.get()
                    .load(cinema.getImage())
                    .into(movieViewHolder.moviePhoto);
            movieViewHolder.movieTitle.setText(cinema.getName());
            if (cinema.getRate() != null) {
                movieViewHolder.reviewRate.setText(cinema.getRate());
            }
            if (cinema.getReviews() != null) {
                String reviews = cinema.getReviews().toString();
                movieViewHolder.reviewsTotal.setText(reviews);
            }
        }


    }

    @Override
    public int getItemCount() {
        if (movieDetails != null)
        return movieDetails.size();
        else if (comingList != null)
            return comingList.size();
        else if (cinemaList != null)
            return cinemaList.size();
        else
            return 0;
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
            movieTitle.setTypeface(myfont);
            reviewsTotal.setTypeface(myfont);
            reviewRate.setTypeface(myfont);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (movieDetails != null){
                Recently recently = movieDetails.get(position);
                Intent intent = new Intent(context, MovieDetailsPage.class);
                intent.setAction("recently");
                String dumb = new Gson().toJson(recently);
                intent.setData(Uri.fromParts("scheme",dumb,null));
                context.startActivity(intent);
            }
            else if (comingList != null){
                Coming coming = comingList.get(position);
                Intent intent = new Intent(context, MovieDetailsPage.class);
                intent.setAction("coming");
                String dumb = new Gson().toJson(coming);
                intent.setData(Uri.fromParts("scheme",dumb,null));
                context.startActivity(intent);
            }

            else if (cinemaList != null){
                Cinema cinema = cinemaList.get(position);
                Intent intent = new Intent(context, CinemaDetailsPage.class);
                String dumb = new Gson().toJson(cinema);
                intent.setData(Uri.fromParts("scheme",dumb,null));
                context.startActivity(intent);

            }

        }
    }
}
