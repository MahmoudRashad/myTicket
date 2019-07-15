package com.example.myticket.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myticket.R;

public class HomeStadiumMainAdapter extends RecyclerView.Adapter<HomeStadiumMainAdapter.AllChampsViewHolder>  {
    private Context context;

    public HomeStadiumMainAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AllChampsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.btolat_recyclerview_item,viewGroup,false);
        return new AllChampsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllChampsViewHolder allChampsViewHolder, int i) {
        allChampsViewHolder.btolaName.setText("Name Of Btola");
        allChampsViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        allChampsViewHolder.recyclerView.setAdapter(new MatchesAdapter(context));


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class AllChampsViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView btolaName;

        public AllChampsViewHolder(@NonNull View itemView) {
            super(itemView);
            btolaName = itemView.findViewById(R.id.btola_name);
            recyclerView = itemView.findViewById(R.id.matches_rv);

        }
    }
}
