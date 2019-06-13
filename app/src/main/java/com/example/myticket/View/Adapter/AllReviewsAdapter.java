package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.R;

import java.util.ArrayList;

public class AllReviewsAdapter extends RecyclerView.Adapter<AllReviewsAdapter.ReviewsViewHolder> {
    private Context context;
    private ArrayList<String> texts;

    public AllReviewsAdapter(Context context, ArrayList<String> movieDetails) {
        this.context = context;
        this.texts = movieDetails;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_details_rv_item,viewGroup,false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i) {
        //MovieDetailsPage movie = movieDetails.get(i);
        reviewsViewHolder.movieTitle.setText("movie name");

    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private TextView movieTitle;
        private TextView reviewsTotal;
        private TextView reviewRate;
        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.name_rev_title);
        }
    }
}
