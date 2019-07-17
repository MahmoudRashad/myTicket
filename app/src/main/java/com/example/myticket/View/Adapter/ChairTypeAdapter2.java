package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
import com.example.myticket.R;

import java.util.List;

public class ChairTypeAdapter2 extends RecyclerView.Adapter<ChairTypeAdapter2.ReviewsViewHolder> {

    private Context context;
    private List<TypeChair> typeChairs;

    public ChairTypeAdapter2(Context context, List<TypeChair> typeChairs)
    {
        this.context = context;
        this.typeChairs = typeChairs;
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_chair_type,viewGroup,false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder reviewsViewHolder, int i)
    {
        if (typeChairs.size() != 0)
        {
            TypeChair typeChair = typeChairs.get(i);
            if( !typeChair.getName().equals("-1"))
            {
                reviewsViewHolder.chairType.setText(typeChair.getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (typeChairs != null)
        return typeChairs.size();
        else return 0;
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{
        private TextView chairType ;
        EditText countChairEt;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            chairType = itemView.findViewById(R.id.textView30);
            countChairEt = itemView.findViewById(R.id.editText);
        }
    }
}
