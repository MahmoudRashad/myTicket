package com.example.myticket.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.View.Activity.MatchDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesAdapterViewHolder> {
    private Context context;
    private List<com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails> matchesList;

    public MatchesAdapter(Context context, List<com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails> matchesList) {
        this.context = context;
        this.matchesList = matchesList;
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
        com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails matchDetails = matchesList.get(i);
        matchesAdapterViewHolder.timeText.setText(matchDetails.getStartTime());
        matchesAdapterViewHolder.dateText.setText(matchDetails.getDate());
        matchesAdapterViewHolder.teamOneName.setText(matchDetails.getTeam1Name());
        matchesAdapterViewHolder.teamTwoName.setText(matchDetails.getTeam2Name());
        matchesAdapterViewHolder.stadiumName.setText(matchDetails.getStadiumName());
        Picasso.get()
                .load(matchDetails.getTeam1Image())
                .into(matchesAdapterViewHolder.teamOneImage);
        Picasso.get()
                .load(matchDetails.getTeam2Image())
                .into(matchesAdapterViewHolder.teamTwoImage);

    }

    @Override
    public int getItemCount() {
        if (matchesList != null)
        return matchesList.size();
        return 0;
    }


    public class MatchesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView timeText;
        private TextView dateText;
        private ImageView teamOneImage;
        private ImageView teamTwoImage;
        private TextView teamOneName;
        private TextView teamTwoName;
        private TextView stadiumName;
        private ImageView followImage;

        public MatchesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.cardView_time);
            dateText = itemView.findViewById(R.id.date_cardView);
            teamOneImage = itemView.findViewById(R.id.team_one_image_card_view);
            teamTwoImage = itemView.findViewById(R.id.team_two_image_card_view);
            stadiumName = itemView.findViewById(R.id.cardView_stad_name);
            teamOneName = itemView.findViewById(R.id.team_one_name_card_view);
            teamTwoName = itemView.findViewById(R.id.team_two_name_card_view);
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
