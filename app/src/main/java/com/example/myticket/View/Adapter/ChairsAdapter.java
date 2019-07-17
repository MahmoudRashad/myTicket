//package com.example.myticket.View.Adapter;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Color;
//import android.support.annotation.NonNull;
//import android.support.constraint.ConstraintLayout;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.myticket.Business.TicketCinemaBusiness;
//import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
//import com.example.myticket.R;
//import com.example.myticket.View.Activity.ChairsActivity;
//
//import java.util.List;
//
//
//public class ChairsAdapter extends RecyclerView.Adapter<ChairsAdapter.ReviewsViewHolder> {
//
//    private Context context;
////    private List<TypeChair> typeChairs;
//    int numOfRow ;
//
//    public ChairsAdapter(Context context , int numOfRow) {
//        this.context = context;
////        this.typeChairs = typeChairs;
//        this.numOfRow = numOfRow;
//    }
//
//    @NonNull
//    @Override
//    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.row_chair,viewGroup,false);
//        return new ReviewsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i)
//    {
////        if (typeChairs.size() != 0)
////        {
////            TypeChair typeChair = typeChairs.get(i);
////            reviewsViewHolder.chairType.setText(typeChair.getName());
////            reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor(
////                    typeChair.getColor()
////            ));
////        }
//
//        // ( i*25 + o ) equetion to get chair number .
//
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
//
//            reviewsViewHolder.layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    // remove chair
//                    if( TicketCinemaBusiness.avilableChairsMap.get(chairNum).isChecked())
//                    {
//
//                        reviewsViewHolder.chairColor.setImageDrawable(null);
//                        TicketCinemaBusiness.avilableChairsMap.get(chairNum).setChecked(false);
//                        TicketCinemaBusiness.removeChair(TicketCinemaBusiness.avilableChairsMap.get(chairNum));
//
//                    }
//                    // add chair
//                    else {
//                        if(TicketCinemaBusiness.selectedChairsMap.size()<
//                                TicketCinemaBusiness.ticketLimits) {
//                            reviewsViewHolder.chairColor.setImageDrawable(
//                                    context.getDrawable(R.drawable.ic_action_name));
//                            TicketCinemaBusiness.avilableChairsMap.get(chairNum).setChecked(true);
//                            TicketCinemaBusiness.addChair(TicketCinemaBusiness.avilableChairsMap.get(chairNum));
//                        }
//                        else
//                        {
//                            showAlertDialog("Limit Tickets",
//                                    "Take Care ! your maximum number of tickets equal  "+
//                                            TicketCinemaBusiness.ticketLimits +"  Tickets");
//                        }
//                    }
//
//                }
//            });
//
//        }
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
//    }
//
//    private void showAlertDialog(String title , String message)
//    {
//        AlertDialog.Builder builder1 = new AlertDialog.
//                Builder(context);
//        builder1.setTitle(title);
//        builder1.setMessage(message);
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "ok",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
////        builder1.setNegativeButton(
////                "No",
////                new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        dialog.cancel();
////                    }
////                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }
//
//    @Override
//    public int getItemCount() {
////        if (typeChairs != null)
////        return typeChairs.size();
////        else return 0;
//
//        return 25;
//    }
//
//    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
//         TextView chairNum ;
//        ImageView chairColor;
//        ConstraintLayout layout;
//
//        public ReviewsViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            chairNum = itemView.findViewById(R.id.tv2);
//            chairColor = itemView.findViewById(R.id.imageView2);
//            layout = itemView.findViewById(R.id.container);
//        }
//    }
//}
