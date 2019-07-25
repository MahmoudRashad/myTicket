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
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;
import com.example.myticket.View.Activity.CinemaDetailsPage;
import com.example.myticket.View.Activity.MovieDetailsPage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StadSearchAdapter extends RecyclerView.Adapter<StadSearchAdapter.SearchViewHolder> {
    private Context context;
    private ArrayList<MatchDetails> results;
    private Typeface myfont;


    public StadSearchAdapter(Context context, ArrayList<MatchDetails> results) {
        this.context = context;
        this.results = results;
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @NonNull
    @Override
    public  SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.btola_rv_item,viewGroup,false);
        return new StadSearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

    }


    @Override
    public int getItemCount() {
            if (results!= null)
                return results.size();
            return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
