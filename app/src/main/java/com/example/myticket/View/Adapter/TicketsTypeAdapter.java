package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.Model.Network.StadiumModel.Match.TicketType;
import com.example.myticket.R;
import com.example.myticket.View.Activity.MatchDetails;

import java.util.List;

public class TicketsTypeAdapter extends RecyclerView.Adapter<TicketsTypeAdapter.TicketsType> {
    Context context;
    List<TicketType> ticketsTypes;
    public TicketsTypeAdapter(Context context, List<TicketType> ticketType) {
        this.context = context;
        this.ticketsTypes = ticketType;
    }

    @NonNull
    @Override
    public TicketsType onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ticket_categories_rv_item,viewGroup,false);
        return new TicketsType(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsType vh, int i) {
        TicketType ticketType = ticketsTypes.get(i);
        vh.type.setText(ticketType.getName());
        vh.price.setText(ticketType.getPrice() + " " + ticketType.getCurrency());

    }

    @Override
    public int getItemCount() {
        return ticketsTypes.size();
    }

    public class TicketsType extends RecyclerView.ViewHolder{
        private TextView type;
        private TextView price;
        public TicketsType(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.the_type_text);
            price = itemView.findViewById(R.id.the_type_price);
        }
    }
}
