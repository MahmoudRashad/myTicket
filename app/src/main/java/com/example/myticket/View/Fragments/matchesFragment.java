package com.example.myticket.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.Leagues;
import com.example.myticket.Model.Network.StadiumModel.Match.MainHomeMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails;
import com.example.myticket.R;
import com.example.myticket.View.Activity.HomeStadBottomNav;
import com.example.myticket.View.Adapter.HomeStadiumMainAdapter;
import com.example.myticket.View.Adapter.StadiumSliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class matchesFragment extends Fragment implements
        GeneralListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mainBtolatRv;
    private HomeStadiumMainAdapter adapter;
    private List<Leagues> todayList;
    private List<Leagues> weekList;
    private List<Leagues> nextWeekList;
    private int flag;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private ProgressBar progressBar;
    private String lang;
    private String token;
    private Button retry;
    private int id;
    private int dir = 0;
    private Typeface myfont;
    private TextView noMatches;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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
        myfont = Typeface.createFromAsset(getContext().getAssets(),"fonts/segoe_ui.ttf");

        if (this.getArguments() != null) {
            flag = this.getArguments().getInt("flag");
        }
        id = view.getLayoutDirection();
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_container);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.green_tab));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        sessionManager = new SessionManager(getContext());
        lang = sessionManager.getDeviceLanguage();
        token = sessionManager.getUserToken();
        if (!token.equals("")){
            token = "Bearer " +  token;

        }

        mainBtolatRv = view.findViewById(R.id.btolat_rv);
        progressBar = view.findViewById(R.id.matches_pb);
        retry = view.findViewById(R.id.matches_retry_btn);
        noMatches = view.findViewById(R.id.no_matches);
        retry.setTypeface(myfont);
        noMatches.setTypeface(myfont);
        mainBtolatRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        apiCalling.getHomeMatches("1",String.valueOf(flag),lang,token ,this);
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
        noMatches.setVisibility(View.GONE);
        if (sessionManager.getDeviceLanguage().equals("ar")){
            dir = 1;
        }
        else{
            dir =2;
        }
        if (tApiResponse instanceof MainHomeMatches) {
            MainHomeMatches Leagues = (MainHomeMatches) tApiResponse;
            switch(flag){
                case 1:
                    todayList =  Leagues.getResult();
                    if (todayList.size() != 0){
                        if (dir == 1)
                        adapter = new HomeStadiumMainAdapter(getContext(),todayList,R.layout.btola_rv_item_arabic);
                        else {
                            adapter = new HomeStadiumMainAdapter(getContext(),todayList,R.layout.btola_rv_item);

                        }
                        }else{
                            noMatches.setVisibility(View.VISIBLE);
                    }
                case 2:
                    weekList = Leagues.getResult();
                    if (weekList.size() != 0){
                        if (dir == 1)
                            adapter = new HomeStadiumMainAdapter(getContext(),weekList,R.layout.btola_rv_item_arabic);
                        else {
                            adapter = new HomeStadiumMainAdapter(getContext(),weekList,R.layout.btola_rv_item);

                        }
                    }else{
                        noMatches.setVisibility(View.VISIBLE);
                    }
                case 3:
                    nextWeekList = Leagues.getResult();
                    if (nextWeekList.size() != 0){
                    if (dir == 1)
                        adapter = new HomeStadiumMainAdapter(getContext(),nextWeekList,R.layout.btola_rv_item_arabic);
                    else {
                        adapter = new HomeStadiumMainAdapter(getContext(),nextWeekList,R.layout.btola_rv_item);

                    }
                    }else{
                        noMatches.setVisibility(View.VISIBLE);
                    }
            }
            mainBtolatRv.setAdapter(adapter);

        }

        else
//            if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            if (getContext() != null) {
                Toast.makeText(getContext(), getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
                retry.setVisibility(View.VISIBLE);
                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), HomeStadBottomNav.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void onRefresh() {
        apiCalling.getHomeMatches("1",String.valueOf(flag),lang,token ,this);
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
