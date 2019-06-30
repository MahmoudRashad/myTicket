package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Enum.TicketsEnum;
import com.example.myticket.Model.Network.DataModel.ReserveModel.AvaliableChair;
import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;
import com.example.myticket.R;

import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.ReviewsViewHolder> {

    private Context context;
    private List<ResultTickets> resultTicketsList;
    private List<AvaliableChair> avaliableChairList;
    int adpterType;
    private Typeface myfont;




    public TicketsAdapter(Context context, List<ResultTickets> resultTicketsList,
                          int adpterType)
    {
        this.context = context;
        this.resultTicketsList = resultTicketsList;
        this.adpterType = adpterType;
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }


    public TicketsAdapter(int adpterType, Context context, List<AvaliableChair> avaliableChairList )
    {
        this.context = context;
        this.avaliableChairList = avaliableChairList;
        this.adpterType = adpterType;
        myfont = Typeface.createFromAsset(context.getAssets(),"fonts/segoe_ui.ttf");

    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_ticket,viewGroup,false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i)
    {
        if(adpterType == TicketsEnum.confirmTickets.getValue())
        {

            AvaliableChair avaliableChair = avaliableChairList.get(i);
            reviewsViewHolder.cinemaName.setText(TicketCinemaBusiness.reserveCinema);
            reviewsViewHolder.movieName.setText(TicketCinemaBusiness.movieName);
            reviewsViewHolder.date.setText(TicketCinemaBusiness.reserveDate);
            reviewsViewHolder.hall.setText(TicketCinemaBusiness.hallName);
            reviewsViewHolder.time.setText(TicketCinemaBusiness.reserveTime);
            reviewsViewHolder.seatNum.setText(context.getString(R.string.seat_number)+avaliableChair.getChairNum());
            reviewsViewHolder.seatType.setText(avaliableChair.getDetail().getName());
            reviewsViewHolder.location.setText(TicketCinemaBusiness.cinemaLocation);
        }
        else
        {
            if (resultTicketsList.size() != 0)
            {

                ResultTickets resultTicket = resultTicketsList.get(i);
                reviewsViewHolder.cinemaName.setText(resultTicket.getCinemaName());
                reviewsViewHolder.movieName.setText(resultTicket.getMovieName());
                reviewsViewHolder.date.setText(resultTicket.getDate());
                reviewsViewHolder.hall.setText(resultTicket.getHallName());
                reviewsViewHolder.time.setText(resultTicket.getTime());
                reviewsViewHolder.seatNum.setText(context.getString(R.string.seat_number)+resultTicket.getChairNum());
                reviewsViewHolder.seatType.setText(resultTicket.getChairType());
                reviewsViewHolder.location.setText(resultTicket.getCinemaLocation());
            }
        }
    }

    @Override
    public int getItemCount()
    {
        if(adpterType == TicketsEnum.confirmTickets.getValue())
        {
            Log.e("test**" , avaliableChairList.size()+"");
            return avaliableChairList.size();
        }
        else {
            return resultTicketsList.size();
        }
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private TextView cinemaName ,
        movieName , date , time , hall ,seatNum , seatType,
        location;


        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);

            cinemaName = itemView.findViewById(R.id.textView9);
            movieName = itemView.findViewById(R.id.textView14);
            date = itemView.findViewById(R.id.textView15);
            time = itemView.findViewById(R.id.textView17);
            hall = itemView.findViewById(R.id.textView16);
            seatNum = itemView.findViewById(R.id.textView18);
            seatType= itemView.findViewById(R.id.textView20);
            location= itemView.findViewById(R.id.textView21);
            cinemaName.setTypeface(myfont);
            movieName.setTypeface(myfont);
            date.setTypeface(myfont);
            time.setTypeface(myfont);
            hall.setTypeface(myfont);
            seatNum.setTypeface(myfont);
            seatType.setTypeface(myfont);
            location.setTypeface(myfont);

        }
    }
}
