package com.example.myticket.View.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.Leagues;
import com.example.myticket.Model.Network.StadiumModel.Match.MainHomeMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.HomeStadiumMainAdapter;
import com.example.myticket.View.Adapter.StadiumSliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class matchesFragment extends Fragment implements
        GeneralListener {
    private RecyclerView mainBtolatRv;
    private HomeStadiumMainAdapter adapter;
    private List<Leagues> todayList;
    private List<Leagues> weekList;
    private List<Leagues> nextWeekList;
    private int flag;
    private ApiCalling apiCalling;
    public matchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiCalling = new ApiCalling(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        if (this.getArguments() != null) {
            flag = this.getArguments().getInt("flag");
        }
        apiCalling.getHomeMatches("1", String.valueOf(flag),this);
        mainBtolatRv = view.findViewById(R.id.btolat_rv);

        mainBtolatRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof MainHomeMatches) {
            MainHomeMatches Leagues = (MainHomeMatches) tApiResponse;
            //TODO: make date accurate in api
            switch(flag){
                case 1:
                    todayList =  Leagues.getResult();
                    adapter = new HomeStadiumMainAdapter(getContext(),todayList);
                case 2:
                    weekList = Leagues.getResult();
                    adapter = new HomeStadiumMainAdapter(getContext(),weekList);
                case 3:
                    nextWeekList = Leagues.getResult();
                    adapter = new HomeStadiumMainAdapter(getContext(),nextWeekList);
            }


            mainBtolatRv.setAdapter(adapter);

        }
    }
}
