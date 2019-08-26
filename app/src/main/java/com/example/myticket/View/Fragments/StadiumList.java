package com.example.myticket.View.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.example.myticket.R;
import com.example.myticket.View.Activity.HomeStadBottomNav;
import com.example.myticket.View.Adapter.StadiumsAdapter;

import java.util.ArrayList;

public class StadiumList extends Fragment implements
        GeneralListener {
    private RecyclerView stadiumRv;
    private StadiumsAdapter stadiumsAdapter;
    private ApiCalling apiCalling;
    private ArrayList<StadDetails> stadiumsList;
    private Button retry;
    private ProgressBar progressBar;

    public StadiumList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stadium_list, container, false);
        apiCalling = new ApiCalling(getContext());
        apiCalling.StadiumsListCall(this);
        stadiumRv = view.findViewById(R.id.stadiums_rv);
        retry = view.findViewById(R.id.stad_list_retry_btn);
        progressBar = view.findViewById(R.id.slider_stad_pb);
        stadiumRv.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
        if (tApiResponse instanceof StadiumListMain) {
            StadiumListMain ListResponce = (StadiumListMain) tApiResponse;
            stadiumsList = (ArrayList<StadDetails>) ListResponce.getStadDetails();
            stadiumsAdapter = new StadiumsAdapter(getContext(),stadiumsList,0);
            stadiumRv.setAdapter(stadiumsAdapter);
        }
        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(getContext(),"Check your internet connection", Toast.LENGTH_SHORT).show();
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
