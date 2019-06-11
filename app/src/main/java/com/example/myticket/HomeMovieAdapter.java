package com.example.myticket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.MovieModel.MovieDetails;

import java.util.ArrayList;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<MovieDetails> movieDetails;

    public HomeMovieAdapter(Context context, ArrayList<MovieDetails> movieDetails) {
        this.context = context;
        this.movieDetails = movieDetails;
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
        //MovieDetailsPage movie = movieDetails.get(i);
        movieViewHolder.moviePhoto.setImageResource(R.drawable.movie);
        movieViewHolder.movieTitle.setText("movie name");
        movieViewHolder.reviewsTotal.setText("1500");
        movieViewHolder.reviewRate.setText("9.5");

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
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
        }
    }
}
