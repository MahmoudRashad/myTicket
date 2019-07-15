package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.View.Activity.MatchDetails;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesAdapterViewHolder> {
    private Context context;

    public MatchesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MatchesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.btola_rv_item,viewGroup,false);
        return new MatchesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapterViewHolder matchesAdapterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }


    public class MatchesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public MatchesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.findViewById(R.id.matches_rv);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, MatchDetails.class);
            context.startActivity(intent);
        }
    }

}
