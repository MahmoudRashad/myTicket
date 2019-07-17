package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.R;

public class MyTicketsStadAdapter  extends RecyclerView.Adapter<MyTicketsStadAdapter.TicketsViewHolder> {
    private Context context;
    public MyTicketsStadAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_event_ticket_item, viewGroup, false);
        return new TicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder TicketsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class TicketsViewHolder extends RecyclerView.ViewHolder {

        public TicketsViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

