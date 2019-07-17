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

import com.example.myticket.R;
import com.example.myticket.View.Adapter.MyTicketsStadAdapter;

public class StadMyTickets extends Fragment {
    private RecyclerView ticketsRV;
    private MyTicketsStadAdapter myTicketsStadAdapter;

    public StadMyTickets() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stad_my_tickets, container, false);
        ticketsRV = view.findViewById(R.id.my_tickets_rv);
        myTicketsStadAdapter = new MyTicketsStadAdapter(getContext());
        ticketsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        ticketsRV.setAdapter(myTicketsStadAdapter);
        return view;
    }

}
