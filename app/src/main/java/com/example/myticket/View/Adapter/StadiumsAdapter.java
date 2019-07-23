package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadDetails;
import com.example.myticket.R;
import com.example.myticket.View.Activity.StadiumDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StadiumsAdapter extends RecyclerView.Adapter<StadiumsAdapter.StadiumsViewHolder> {
    private Context context;
    private ArrayList<StadDetails> list;

    public StadiumsAdapter(Context context, ArrayList<StadDetails> stadiumsList) {
        this.context = context;
        this.list = stadiumsList;
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
        StadDetails stad = list.get(i);
        stadiumsViewHolder.stadAddress.setText(stad.getAddress());
        stadiumsViewHolder.stadName.setText(stad.getName());
        Picasso.get()
                .load(stad.getImage())
                .into(stadiumsViewHolder.stadImage);


    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class StadiumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView stadImage;
        private TextView stadName;
        private TextView stadAddress;
        private TextView stadMap;
        private Button details;

        public StadiumsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            stadImage = itemView.findViewById(R.id.stad_image_item);
            stadImage.setClipToOutline(true);
            stadName = itemView.findViewById(R.id.stadium_name_text);
            stadAddress = itemView.findViewById(R.id.stadium_address_text);
            stadMap = itemView.findViewById(R.id.location_on_map);
            details = itemView.findViewById(R.id.details_btn);


        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, StadiumDetails.class);
            context.startActivity(intent);
        }
    }
}
