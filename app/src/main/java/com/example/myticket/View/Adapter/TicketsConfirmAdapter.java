package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketDetailResult;
import com.example.myticket.R;
import com.example.myticket.View.Activity.QrcodePage;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TicketsConfirmAdapter extends RecyclerView.Adapter<TicketsConfirmAdapter.ConfirmTicketsViewHolder> {

    private Context context;
    private List<MyTicketDetailResult> myTicketDetailResult;
    private MyTicketDetailResult myTicket;

    public TicketsConfirmAdapter(Context context,List<MyTicketDetailResult> myTicketDetailResult) {
        this.context = context;
        this.myTicketDetailResult = myTicketDetailResult;
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
        myTicket = myTicketDetailResult.get(i);
        confirmTicketsViewHolder.teamOne.setText(myTicket.getTeam1Name());
        confirmTicketsViewHolder.teamTwo.setText(myTicket.getTeam2Name());
        confirmTicketsViewHolder.stadiumName.setText(myTicket.getStadiumName());
        confirmTicketsViewHolder.class_type.setText(myTicket.getType());
        confirmTicketsViewHolder.seat.setText(myTicket.getSymbol_chair() +" " +myTicket.getSeatNum());
        confirmTicketsViewHolder.date.setText(myTicket.getDate() + " ," + myTicket.getTime());
        Picasso.get()
                .load(myTicket.getTeam1Image())
                .into(confirmTicketsViewHolder.teamOneImage);
        Picasso.get()
                .load(myTicket.getTeam2Image())
                .into(confirmTicketsViewHolder.teamTwoImage);


    }

    @Override
    public int getItemCount() {
       return myTicketDetailResult.size();
    }

    public class ConfirmTicketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView teamOne;
        private TextView teamTwo;
        private ImageView teamOneImage;
        private ImageView teamTwoImage;
        private TextView stadiumName;
        private TextView seat;
        private TextView class_type;
        private TextView date;

        public ConfirmTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            teamOne = itemView.findViewById(R.id.team_one_name);
            teamTwo = itemView.findViewById(R.id.team_two_name);
            teamOneImage = itemView.findViewById(R.id.image_one);
            teamTwoImage = itemView.findViewById(R.id.image_two);
            stadiumName = itemView.findViewById(R.id.stadium_name_text);
            date = itemView.findViewById(R.id.match_time_text);
            class_type = itemView.findViewById(R.id.ticket_class_value);
            seat = itemView.findViewById(R.id.ticket_seat_value);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String qrCode = myTicketDetailResult.get(position).getQrCode();
            Intent intent = new Intent(context, QrcodePage.class);
            intent.setAction("green");
            intent.putExtra("qr",qrCode);
            context.startActivity(intent);
            }
        }
    }

