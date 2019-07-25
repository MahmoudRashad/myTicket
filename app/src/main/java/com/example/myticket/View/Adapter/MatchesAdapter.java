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
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Activity.CinemaDetailsPage;
import com.example.myticket.View.Activity.Login;
import com.example.myticket.View.Activity.MatchDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesAdapterViewHolder> implements GeneralListener {
    private Context context;
    private List<com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails> matchesList;
    private ApiCalling apiCalling;
    private int flag =-1;
    com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails matchDetails;

    public MatchesAdapter(Context context, List<com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails> matchesList) {
        this.context = context;
        this.matchesList = matchesList;
        apiCalling = new ApiCalling(context);
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
        matchDetails = matchesList.get(i);
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



        if (matchDetails.getFollowStatus() == 1)
        matchesAdapterViewHolder.followImage.setImageResource(R.drawable.ic_notifications_active_24dp);


    }

    @Override
    public int getItemCount() {
        if (matchesList != null)
        return matchesList.size();
        return 0;
    }

    public String handleLogin(){
        SessionManager sessionManager = new SessionManager(context);
        //check if he is logged in or not
        final String token = "Bearer "+ sessionManager.getUserToken();
        if (!token.equals("Bearer ")) {
            return token;
        }
        else {
            return "";
        }
    }



    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof  BaseNoResult) {
            BaseNoResult baseNoResult = (BaseNoResult) tApiResponse;
            String msg = baseNoResult.getMessage();
            if (msg.contains("إلغاء")||msg.contains("unFollow")){
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                flag = 0;

//                matchesAdapterViewHolder.getAdapterPosition();
//                matchesAdapterViewHolder.followImage.setImageResource(R.drawable.ic_notifications_off_black_24dp);

            }
            else{
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
                flag = 1;
//                matchesAdapterViewHolder.getAdapterPosition();
//                matchesAdapterViewHolder.followImage.setImageResource(R.drawable.ic_notifications_active_24dp);
            }

        }


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
        private ImageView greenBackground;

        public MatchesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.cardView_time);
            dateText = itemView.findViewById(R.id.date_cardView);
            teamOneImage = itemView.findViewById(R.id.team_one_image_card_view);
            teamTwoImage = itemView.findViewById(R.id.team_two_image_card_view);
            stadiumName = itemView.findViewById(R.id.cardView_stad_name);
            teamOneName = itemView.findViewById(R.id.team_one_name_card_view);
            teamTwoName = itemView.findViewById(R.id.team_two_name_card_view);
            greenBackground = itemView.findViewById(R.id.green);
            itemView.setOnClickListener(this);
            greenBackground.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            int position = getAdapterPosition();
            if (id == greenBackground.getId()){
                String token = handleLogin();
                if (!token.equals("")){
                    //set api
                    apiCalling.follow(token,matchDetails.getId().toString(),MatchesAdapter.this::getApiResponse);
                    if (flag ==1){
                        //btg3 null
                        //23mli network call l klo tani w5las
                        followImage.setImageResource(R.drawable.ic_notifications_active_24dp);
                    }

                }
                else {
                    Intent intent = new Intent(context,Login.class);
                    intent.putExtra("id",matchDetails.getId());
                    intent.putExtra("name","home");
                    intent.putExtra("flag","stad");
                    context.startActivity(intent);
                }
            }
            else{
                Intent intent = new Intent(context, MatchDetails.class);
                context.startActivity(intent);
            }
        }
    }

}
