//package com.example.myticket.View.Adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
//import com.example.myticket.R;
//
//public class RowChairsAdapter extends RecyclerView.Adapter<RowChairsAdapter.ReviewsViewHolder> {
//
//    private Context context;
////    private List<TypeChair> typeChairs;
//
//    public RowChairsAdapter(Context context/*, List<TypeChair> typeChairs*/) {
//        this.context = context;
////        this.typeChairs = typeChairs;
//    }
//
//    @NonNull
//    @Override
//    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.row_of_chair,viewGroup,false);
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
//        ChairsAdapter chairsAdapter = new ChairsAdapter(context,i);
//        reviewsViewHolder.rowOfChairsRv.setAdapter(chairsAdapter);
//
//        LinearLayoutManager chairTypeLayoutManger =
//                new LinearLayoutManager(context,
//                        LinearLayoutManager.HORIZONTAL,true);
//
//        reviewsViewHolder.rowOfChairsRv.setLayoutManager(chairTypeLayoutManger);
//        reviewsViewHolder.rowOfChairsRv.setHasFixedSize(false);
//        reviewsViewHolder.rowOfChairsRv.setNestedScrollingEnabled(false);
//
//    }
//
//    @Override
//    public int getItemCount() {
////        if (typeChairs != null)
////        return typeChairs.size();
////        else return 0;
//
//        return 20;
//    }
//
//    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
////        private TextView chairType ;
//        RecyclerView rowOfChairsRv;
//
//        public ReviewsViewHolder(@NonNull View itemView) {
//            super(itemView);
////            chairType = itemView.findViewById(R.id.category_text);
//            rowOfChairsRv = itemView.findViewById(R.id.row_of_chairs);
//        }
//    }
//}
