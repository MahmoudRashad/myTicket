package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.Search.Result;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.R;

import java.util.ArrayList;

public class StadChairsAdapter extends RecyclerView.Adapter<StadChairsAdapter.chairViewHolder> {

    private Context context;
    private ArrayList<ResultTicketsStad> results;
    private Typeface myfont;


    public StadChairsAdapter(Context context, ArrayList<ResultTicketsStad> results) {
        this.context = context;
        this.results = results;
        if (context!= null)
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @NonNull
    @Override
    public chairViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stad_chair_item,viewGroup,false);
        return new chairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chairViewHolder chairViewHolder, int i) {
        ResultTicketsStad resultTicketsStad = results.get(i);

        if (i != results.size() -1 ){
            chairViewHolder.seat.append(" " + resultTicketsStad.getChairSymbol() +"-"+ resultTicketsStad.getChairNum()+",");
        }
        else
        chairViewHolder.seat.append(" " + resultTicketsStad.getChairSymbol() +"-"+resultTicketsStad.getChairNum());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class chairViewHolder extends RecyclerView.ViewHolder{
        private TextView seat;
        public chairViewHolder(@NonNull View itemView) {
            super(itemView);
            seat = itemView.findViewById(R.id.seat_number_text);
            seat.setTypeface(myfont);
        }
    }
}
