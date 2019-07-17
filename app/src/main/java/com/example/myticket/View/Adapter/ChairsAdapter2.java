package com.example.myticket.View.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Model.Network.DataModel.Chairs.Chair;
import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
import com.example.myticket.R;

import java.util.List;


public class ChairsAdapter2 extends RecyclerView.Adapter<ChairsAdapter2.ReviewsViewHolder> {

    private Context context;
    private List<Chair> typeChairs;
    int numOfRow ;

    public ChairsAdapter2(Context context , List<Chair> typeChairs)
    {
        this.context = context;
        this.typeChairs = typeChairs;
//        this.numOfRow = numOfRow;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_chair,viewGroup,false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i)
    {

            Chair chair = typeChairs.get(i);
            reviewsViewHolder.chairNum.setText(chair.getSymbolChair()+
                    chair.getCharNum());
            reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor(
                    chair.getColor()
            ));


        // ( i*25 + o ) equetion to get chair number .

//        String chairNum = String.valueOf(numOfRow * 25 + i+1 );
//        if(TicketCinemaBusiness.avilableChairsMap.containsKey(
//                chairNum))
//        {
//
//            reviewsViewHolder.chairNum.setText(
//                    TicketCinemaBusiness.avilableChairsMap.get(chairNum).getChairNum());
//
//            reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor(
//                    TicketCinemaBusiness.avilableChairsMap.get(chairNum).getDetail().getColor()));

            reviewsViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // remove chair
                    if( TicketCinemaBusiness.selectedChairsMap.containsKey(chair.getSymbolChair()+
                            chair.getCharNum()))
                    {

                        reviewsViewHolder.chairColor.setImageDrawable(null);
//                        TicketCinemaBusiness.selectedChairsMap.remove(
//                                chair.getSymbolChair()+
//                                        chair.getCharNum());
                        TicketCinemaBusiness.removeChair(chair);

                    }
                    // add chair
                    else {
                        if(TicketCinemaBusiness.selectedChairsMap.size()<
                                TicketCinemaBusiness.ticketLimits) {
                            reviewsViewHolder.chairColor.setImageDrawable(
                                    context.getDrawable(R.drawable.ic_action_name));
//                            TicketCinemaBusiness.avilableChairsMap.get(chair.getCharNum()).setChecked(true);
                            TicketCinemaBusiness.addChair(chair);
                        }
                        else
                        {
                            showAlertDialog("Limit Tickets",
                                    "Take Care ! your maximum number of tickets equal  "+
                                            TicketCinemaBusiness.ticketLimits +"  Tickets");
                        }
                    }

                }
            });


//        else
//        {
//            reviewsViewHolder.chairNum.setText(chairNum);
//
//            reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor("#444444"));
//
//
//            reviewsViewHolder.layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                        reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor("#444444"));
//
//                        reviewsViewHolder.chairColor.setImageDrawable(null);
//
//
//                }
//            });
//        }
    }

    private void showAlertDialog(String title , String message)
    {
        AlertDialog.Builder builder1 = new AlertDialog.
                Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public int getItemCount() {
        if (typeChairs != null)
        return typeChairs.size();
        else return 0;

//        return 25;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
         TextView chairNum ;
        ImageView chairColor;
        ConstraintLayout layout;

        public ReviewsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            chairNum = itemView.findViewById(R.id.tv2);
            chairColor = itemView.findViewById(R.id.imageView2);
            layout = itemView.findViewById(R.id.container);
        }
    }
}
