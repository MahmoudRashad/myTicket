package com.example.myticket.View.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myticket.Model.Network.StadiumModel.MyTicket.Past;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.MyTicketsStadAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class StadMyTickets extends Fragment {
    private RecyclerView ticketsRV;
    private MyTicketsStadAdapter myTicketsStadAdapter;
    private String dumb;
    private ArrayList<Past> list;
    private TextView noBookedTickets;
    private Typeface myfont;
    boolean isPending = false;


    public StadMyTickets() {

    }

//    public StadMyTickets(boolean isPending) {
//        this.isPending = isPending;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stad_my_tickets, container, false);
        myfont = Typeface.createFromAsset(getContext().getAssets(),"fonts/segoe_ui.ttf");

        ticketsRV = view.findViewById(R.id.my_tickets_rv);
        ticketsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        noBookedTickets = view.findViewById(R.id.no_matches_booked);
        noBookedTickets.setTypeface(myfont);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.getArguments() != null) {
            dumb = this.getArguments().getString("list");
            isPending = this.getArguments().getBoolean("isPending",false);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Past[] results = gson.fromJson(dumb,Past[].class);
            list = new ArrayList<>(Arrays.asList(results));
            myTicketsStadAdapter = new MyTicketsStadAdapter(list, getContext(),
                    isPending);
            ticketsRV.setAdapter(myTicketsStadAdapter);
        }
        else{
            noBookedTickets.setVisibility(View.VISIBLE);

        }
    }
}
