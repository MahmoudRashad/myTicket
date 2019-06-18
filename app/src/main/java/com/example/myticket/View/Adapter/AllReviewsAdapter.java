package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.CommentsModel.Result;
import com.example.myticket.R;

import java.util.ArrayList;

public class AllReviewsAdapter extends RecyclerView.Adapter<AllReviewsAdapter.ReviewsViewHolder> {
    private Context context;
    private ArrayList<Result> allComments;

    public AllReviewsAdapter(Context context, ArrayList<Result> movieDetails) {
        this.context = context;
        this.allComments = movieDetails;
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
        Result comment = allComments.get(i);
        reviewsViewHolder.name.setText(comment.getName());
        reviewsViewHolder.comment.setText(comment.getComment());
        reviewsViewHolder.date.setText(comment.getDate());
        float rate = Float.parseFloat(comment.getRate());
        reviewsViewHolder.ratingBar.setRating(rate);

    }

    @Override
    public int getItemCount() {
        return allComments.size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView comment;
        private TextView date;
        private RatingBar ratingBar;
        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_rev_title);
            date = itemView.findViewById(R.id.rev_date);
            comment = itemView.findViewById(R.id.review);
            ratingBar = itemView.findViewById(R.id.review_element_stars);
        }
    }
}
