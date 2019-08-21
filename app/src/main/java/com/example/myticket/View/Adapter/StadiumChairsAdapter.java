package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.myticket.Model.Network.StadiumModel.Reservation.ChairsResult;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.R;

import java.util.ArrayList;
import java.util.List;

public class StadiumChairsAdapter extends  RecyclerView.Adapter<StadiumChairsAdapter.ChairViewHolder> {
    private List<ChairsResult> chairsResults;
    private Context context;
    private saveChairs saveChairs;
    private String matchId;
    private String date;
    private ArrayList<ResultTicketsStad> resultTicketsStad;

    public StadiumChairsAdapter(List<ChairsResult> chairsResults, Context context, saveChairs saveChairs,String matchId,String date) {
        this.chairsResults = chairsResults;
        this.context = context;
        this.saveChairs = saveChairs;
        this.matchId = matchId;
        this.date = date;
    }

    @NonNull
    @Override
    public ChairViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stad_chairs_list,viewGroup,false);
        return new ChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChairViewHolder chairViewHolder, int i) {
        ChairsResult chairsResult = chairsResults.get(i);
        String chairName = chairsResult.getChairSymbol() + chairsResult.getChairNum();
        chairViewHolder.checkedTextView.setText(chairName);
        resultTicketsStad = new ArrayList<>();
        chairViewHolder.checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        chairViewHolder.checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = chairViewHolder.checkedTextView.isChecked();
                ResultTicketsStad resultTicketsStad = new ResultTicketsStad(matchId,date,chairsResult.getChairNum(),
                        chairsResult.getId().toString(),"33");
                // Check which checkbox was clicked
                saveChairs.saveChairs(checked,resultTicketsStad);

            }
        });

    }

    @Override
    public int getItemCount() {
        return chairsResults.size();
    }

    public class ChairViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkedTextView;
        public ChairViewHolder(@NonNull View itemView) {
            super(itemView);
            checkedTextView = itemView.findViewById(R.id.checked_tv_stad_chairs);
            this.setIsRecyclable(false);
        }
    }

    public interface saveChairs{
        public void saveChairs(boolean checked, ResultTicketsStad resultTicketsStad);
    }
}