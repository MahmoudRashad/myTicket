package com.example.myticket.View.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.Model.Network.StadiumModel.Match.Leagues;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;

import java.util.ArrayList;
import java.util.List;

public class HomeStadiumMainAdapter extends RecyclerView.Adapter<HomeStadiumMainAdapter.AllChampsViewHolder>  {
    private Context context;
    private List<Leagues> leaguesList;
    private int layout;
    private Typeface myfont;


    public HomeStadiumMainAdapter(Context context, List<Leagues> leaguesList,int layout) {
        this.context = context;
        this.leaguesList = leaguesList;
        this.layout = layout;
        if (context != null) {
            myfont = Typeface.createFromAsset(context.getAssets(), "fonts/segoe_ui.ttf");
        }

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
        Leagues league = leaguesList.get(i);
        allChampsViewHolder.btolaName.setText(league.getCyclicName());
        MatchesAdapter matchesAdapter  = new MatchesAdapter(context, league.getMatches(),layout);
        allChampsViewHolder.recyclerView.setNestedScrollingEnabled(false);
        allChampsViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        allChampsViewHolder.recyclerView.setAdapter(matchesAdapter);

    }

    @Override
    public int getItemCount() {
        if (leaguesList != null) {
            return leaguesList.size();
        }
        return 0;
    }

    public class AllChampsViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView btolaName;
        private ConstraintLayout layout;

        public AllChampsViewHolder(@NonNull View itemView) {
            super(itemView);
            btolaName = itemView.findViewById(R.id.btola_name);
            btolaName.setTypeface(myfont);
            recyclerView = itemView.findViewById(R.id.matches_rv);
            layout = itemView.findViewById(R.id.layout_btolat);

        }
    }
}
