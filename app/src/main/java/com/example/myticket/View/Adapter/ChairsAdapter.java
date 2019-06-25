package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
import com.example.myticket.R;
import com.example.myticket.View.Activity.ChairsActivity;

import java.util.List;

import static com.example.myticket.View.Activity.ChairsActivity.avilableChairsMap;

public class ChairsAdapter extends RecyclerView.Adapter<ChairsAdapter.ReviewsViewHolder> {

    private Context context;
//    private List<TypeChair> typeChairs;
    int numOfRow ;

    public ChairsAdapter(Context context , int numOfRow) {
        this.context = context;
//        this.typeChairs = typeChairs;
        this.numOfRow = numOfRow;
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
//        if (typeChairs.size() != 0)
//        {
//            TypeChair typeChair = typeChairs.get(i);
//            reviewsViewHolder.chairType.setText(typeChair.getName());
//            reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor(
//                    typeChair.getColor()
//            ));
//        }

        // ( i*25 + o ) equetion to get chair number .

        String chairNum = String.valueOf(numOfRow * 25 + i );
        if(avilableChairsMap.containsKey(
                chairNum))
        {
            reviewsViewHolder.chairColor.setBackgroundColor(Color.parseColor(
                    avilableChairsMap.get(chairNum).getDetail().getColor()));
        }
    }

    @Override
    public int getItemCount() {
//        if (typeChairs != null)
//        return typeChairs.size();
//        else return 0;

        return 25;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
//        private TextView chairType ;
        ImageView chairColor;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
//            chairType = itemView.findViewById(R.id.category_text);
            chairColor = itemView.findViewById(R.id.imageView2);
        }
    }
}
