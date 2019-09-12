package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Enum.ClubReservationEnum;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.Past;
import com.example.myticket.R;
import com.example.myticket.View.Activity.HomeStadBottomNav;
import com.example.myticket.View.Activity.StadPaymentConfirm;
import com.example.myticket.View.Activity.tler.MainPaymentActivity;
import com.example.myticket.View.Activity.tler.SuccessTransationActivity;

import org.w3c.dom.Text;

import java.time.format.TextStyle;
import java.util.ArrayList;

import static com.example.myticket.View.Activity.StadiumTicketsOptions.keyReservation;

public class MyTicketsStadAdapter  extends
        RecyclerView.Adapter<MyTicketsStadAdapter.TicketsViewHolder>
{
    private ArrayList<Past> tickets;
    private Context context;
    private Typeface myfont;
    boolean isPending = false;
    ApiCalling apiCalling;

    public MyTicketsStadAdapter(ArrayList<Past> tickets, Context context,boolean isPending) {
        this.tickets = tickets;
        this.context = context;
        this.isPending =isPending;
        apiCalling = new ApiCalling(context);
        if (context!= null)
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_event_ticket_item, viewGroup, false);
        return new TicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder TicketsViewHolder
            , int i) {

        Past ticket = tickets.get(i);

        if(this.isPending)
        {
            TicketsViewHolder.details.setBackground(context.getResources().
                    getDrawable(R.drawable.rounded_red));

            TicketsViewHolder.details.
                    setText(context.getString(R.string.re_pay));

            TicketsViewHolder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    keyReservation = ticket.getKey();
                    Intent intent = new Intent(context, MainPaymentActivity.class);
                    intent.putExtra("amount","20");
                    context.startActivity(intent);
                }
            });
        }
        else {
            TicketsViewHolder.details.
                    setText(context.getString(R.string.details));

            TicketsViewHolder.details.setBackground(context.getResources().
                    getDrawable(R.drawable.green_rect));
            TicketsViewHolder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StadPaymentConfirm.class);
                    intent.putExtra("matchId",ticket.getMatchId());
                    context.startActivity(intent);
                }
            });

        }


        TicketsViewHolder.teamOne.setText(ticket.getTeam1Name());
        TicketsViewHolder.teamTwo.setText(ticket.getTeam2Name());
        TicketsViewHolder.time.setText(ticket.getTime());
        TicketsViewHolder.date.setText(ticket.getDate());
        TicketsViewHolder.stadiumName.setText(ticket.getStadiumName());
        for (int j = 0 ; j< ticket.getSeats().size() ; j++){
           if (j != ticket.getSeats().size()-1){
                TicketsViewHolder.seats.append(ticket.getSeats().get(j).getSymbol_chair()+"-" +ticket.getSeats().get(j).getSeatNum()+", ");
            }
            else{
                TicketsViewHolder.seats.append(ticket.getSeats().get(j).getSymbol_chair()+"-" +ticket.getSeats().get(j).getSeatNum());
            }
        }


    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }



    public class TicketsViewHolder extends RecyclerView.ViewHolder {
        private ImageView fieldImage;
        private TextView teamOne;
        private TextView teamTwo;
        private TextView stadiumName;
        private TextView seats;
        private TextView time;
        private TextView date;
        private Button details;

        public TicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            fieldImage = itemView.findViewById(R.id.field_img);
            teamOne = itemView.findViewById(R.id.team_one_event);
            teamOne.setTypeface(myfont);
            teamTwo = itemView.findViewById(R.id.team_two_event);
            teamTwo.setTypeface(myfont);
            stadiumName = itemView.findViewById(R.id.stadium_name_event);
            stadiumName.setTypeface(myfont);
            seats = itemView.findViewById(R.id.seats_value);
            seats.setTypeface(myfont);
            time = itemView.findViewById(R.id.time);
            time.setTypeface(myfont);
            date = itemView.findViewById(R.id.date);
            date.setTypeface(myfont);
            details = itemView.findViewById(R.id.details_btn_event);
            details.setTypeface(myfont);

        }
    }
}

