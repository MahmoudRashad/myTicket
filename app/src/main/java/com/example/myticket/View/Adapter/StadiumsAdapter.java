package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
    private int flag = 0;
    private String stadId ="";
    private Typeface myfont;

    public StadiumsAdapter(Context context, ArrayList<StadDetails> stadiumsList, int flag) {
        this.context = context;
        this.list = stadiumsList;
        this.flag = flag;
        if (context!= null)
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

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
        stadId = stad.getId().toString();
        stadiumsViewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StadiumDetails.class);
                intent.putExtra("id",stad.getId().toString());
                context.startActivity(intent);
            }
        });

        stadiumsViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StadiumDetails.class);
                intent.putExtra("id",stad.getId().toString());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        else if (flag!= 0)
        {
            return 5;
        }
        return 0;
    }

    public class StadiumsViewHolder extends RecyclerView.ViewHolder  {
        private ImageView stadImage;
        private TextView stadName;
        private TextView stadAddress;
        private TextView stadMap;
        private Button details;
        private ConstraintLayout layout;

        public StadiumsViewHolder(@NonNull View itemView) {
            super(itemView);
            stadImage = itemView.findViewById(R.id.stad_image_item);
            stadImage.setClipToOutline(true);
            stadName = itemView.findViewById(R.id.stadium_name_text);
            stadName.setTypeface(myfont);
            stadAddress = itemView.findViewById(R.id.stadium_address_text);
            stadAddress.setTypeface(myfont);
            stadMap = itemView.findViewById(R.id.location_on_map);
            stadMap.setTypeface(myfont);
            details = itemView.findViewById(R.id.details_btn);
            details.setTypeface(myfont);
            layout = itemView.findViewById(R.id.stadiums_item);


        }

//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(context, StadiumDetails.class);
//            intent.putExtra("id",stadId);
//            context.startActivity(intent);
//        }
    }
}
