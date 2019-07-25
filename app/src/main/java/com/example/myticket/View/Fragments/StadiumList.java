package com.example.myticket.View.Fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadiumsAdapter;

import java.util.ArrayList;

public class StadiumList extends Fragment implements
        GeneralListener {
    private RecyclerView stadiumRv;
    private StadiumsAdapter stadiumsAdapter;
    private ApiCalling apiCalling;
    private ArrayList<StadDetails> stadiumsList;

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
        stadiumRv.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof StadiumListMain) {
            StadiumListMain ListResponce = (StadiumListMain) tApiResponse;
            stadiumsList = (ArrayList<StadDetails>) ListResponce.getStadDetails();
            stadiumsAdapter = new StadiumsAdapter(getContext(),stadiumsList,0);
            stadiumRv.setAdapter(stadiumsAdapter);
        }

    }
}
