package com.example.myticket.View.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketMain;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketsResult;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.Past;
import com.example.myticket.R;
import com.example.myticket.View.Activity.Login;
import com.example.myticket.View.Adapter.StadMyTicketsViewPagerAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyTicketsStad extends Fragment implements GeneralListener {
    private TabLayout ticketsTab;
    private ViewPager ticketsViewPager;
    private StadMyTicketsViewPagerAdapter viewPagerAdapter;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;

    public FragmentMyTicketsStad() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiCalling = new ApiCalling(getContext());
        sessionManager = new SessionManager(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tickets_stad, container, false);
        ticketsTab = view.findViewById(R.id.sliding_tabs);
        ticketsViewPager = view.findViewById(R.id.stad_tickets_viewpager);
        viewPagerAdapter = new StadMyTicketsViewPagerAdapter(getChildFragmentManager());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String token = sessionManager.handleLogin();
        if (!token.equals("")) {
            apiCalling.getMyTicketsStad(sessionManager.handleLogin(),sessionManager.getDeviceLanguage(),this);

        }
        else{
            Intent intent = new Intent(getContext(), Login.class);
            intent.putExtra("flag","stad");
            startActivity(intent);
        }
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof MyTicketMain){
            MyTicketMain myTicketMain = (MyTicketMain) tApiResponse;
            MyTicketsResult myTicketsResult = myTicketMain.getMyTicketsResult();
            List<Past> pastTickets = myTicketsResult.getPast();
            List<Past> upcomingTickets = myTicketsResult.getComing();
            setData(pastTickets,upcomingTickets);
        }
    }

    private void setData(List<Past> pastTickets, List<Past> upcomingTickets) {
        viewPagerAdapter.clear();
        viewPagerAdapter.addFragment(new StadMyTickets(),"UPCOMING",upcomingTickets);
        viewPagerAdapter.addFragment(new StadMyTickets(),"PAST",pastTickets);
        viewPagerAdapter.notifyDataSetChanged();
        ticketsViewPager.setAdapter(viewPagerAdapter);
        ticketsTab.setupWithViewPager(ticketsViewPager);
    }
}
