package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.CinemaDetailsPage;
import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Coming;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.DataModel.MovieModel.MovieDetails;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.example.myticket.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Recently> movieDetails;
    private List<Coming> comingList;
    private List<Cinema> cinemaList;

    public HomeMovieAdapter(Context context, List<Recently> movieDetails, List<Coming> comingList,List<Cinema> cinemaList ) {
        this.context = context;
        this.movieDetails = movieDetails;
        this.comingList = comingList;
        this.cinemaList = cinemaList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item,viewGroup,false);
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
            if (movie.getReviews() != null) {
                String reviews = movie.getReviews().toString();
                movieViewHolder.reviewRate.setText(reviews);
            }
            if (movie.getReviews() != null) {
                String rate = movie.getRate().toString();
                movieViewHolder.reviewsTotal.setText(rate);
            }
        }
        else if (comingList != null){
            Coming movie = comingList.get(i);
            Picasso.get()
                    .load(movie.getImage())
                    .into(movieViewHolder.moviePhoto);
            movieViewHolder.movieTitle.setText(movie.getName());
            if (movie.getReviews() != null) {
                String reviews = movie.getReviews().toString();
                movieViewHolder.reviewRate.setText(reviews);
            }
            if (movie.getReviews() != null) {
                String rate = movie.getRate().toString();
                movieViewHolder.reviewsTotal.setText(rate);
            }

        }
        else if (cinemaList != null){
            Cinema cinema = cinemaList.get(i);
            Picasso.get()
                    .load(cinema.getImage())
                    .into(movieViewHolder.moviePhoto);
            movieViewHolder.movieTitle.setText(cinema.getName());
            if (cinema.getReviews() != null) {
                String reviews = cinema.getReviews().toString();
                movieViewHolder.reviewRate.setText(reviews);
            }
            if (cinema.getReviews() != null) {
                String rate = cinema.getRate().toString();
                movieViewHolder.reviewsTotal.setText(rate);
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (movieDetails != null){
                Recently recently = movieDetails.get(position);
                Intent intent = new Intent(context, MovieDetailsPage.class);
                String dumb = new Gson().toJson(recently);
                intent.setData(Uri.fromParts("scheme",dumb,null));
                context.startActivity(intent);
            }
            else if (comingList != null){
                Coming coming = comingList.get(position);
                Intent intent = new Intent(context, MovieDetailsPage.class);
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
