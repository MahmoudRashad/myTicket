package com.example.myticket.View.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.R;


public class TicketsConfirmAdapter extends RecyclerView.Adapter<TicketsConfirmAdapter.ConfirmTicketsViewHolder> {

    private Context context;


    public TicketsConfirmAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ConfirmTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tickets_green_rv_item,viewGroup,false);
        return new ConfirmTicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmTicketsViewHolder confirmTicketsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
       return 5;
    }

    public class ConfirmTicketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ConfirmTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            }
        }
    }

