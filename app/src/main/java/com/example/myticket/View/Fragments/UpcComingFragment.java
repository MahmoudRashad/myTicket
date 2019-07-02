package com.example.myticket.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myticket.Enum.TicketsEnum;
import com.example.myticket.Model.Network.DataModel.MyTickets.Coming;
import com.example.myticket.Model.Network.DataModel.MyTickets.ResultMyTicket;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.TicketsAdapter;

import java.util.List;


public class UpcComingFragment extends Fragment {

    List<ResultTickets> comingList;
    RecyclerView ticketsRv ;

    public UpcComingFragment() {
        // Required empty public constructor
    }

    public UpcComingFragment(List<ResultTickets> comingList) {
        this.comingList = comingList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upc_coming, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        ticketsRv = getView().findViewById(R.id.chairs_type);
        TicketsAdapter chairTypeAdapter = new TicketsAdapter(getContext(),
                comingList,
                TicketsEnum.pervieousTickets.getValue());
        ticketsRv.setAdapter(chairTypeAdapter);


        LinearLayoutManager chairTypeLayoutManger =
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,false);

        ticketsRv.setLayoutManager(chairTypeLayoutManger);
        ticketsRv.setHasFixedSize(false);
        ticketsRv.setNestedScrollingEnabled(false);
    }
}
