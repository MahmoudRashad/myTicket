package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.View.Activity.StadiumDetails;

public class StadiumsAdapter extends RecyclerView.Adapter<StadiumsAdapter.StadiumsViewHolder> {
    private Context context;

    public StadiumsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StadiumsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stadium_list_item,viewGroup,false);
        return new StadiumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StadiumsAdapter.StadiumsViewHolder stadiumsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class StadiumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView stadImage;

        public StadiumsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            stadImage = itemView.findViewById(R.id.stad_image_item);
            stadImage.setClipToOutline(true);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, StadiumDetails.class);
            context.startActivity(intent);
        }
    }
}
