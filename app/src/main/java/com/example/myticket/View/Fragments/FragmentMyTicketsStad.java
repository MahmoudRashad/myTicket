package com.example.myticket.View.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketMain;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketsResult;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.Past;
import com.example.myticket.R;
import com.example.myticket.View.Activity.HomeStadBottomNav;
import com.example.myticket.View.Activity.Login;
import com.example.myticket.View.Adapter.StadMyTicketsViewPagerAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyTicketsStad extends Fragment implements GeneralListener {
    private TabLayout ticketsTab;
    private ViewPager ticketsViewPager;
    private Button retry;
    private ProgressBar progressBar;
    private StadMyTicketsViewPagerAdapter viewPagerAdapter;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private Typeface myfont;


    public FragmentMyTicketsStad() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        apiCalling = new ApiCalling(getContext());
        sessionManager = new SessionManager(getContext());
        myfont = Typeface.createFromAsset(getContext().getAssets(),"fonts/segoe_ui.ttf");

        viewPagerAdapter = new StadMyTicketsViewPagerAdapter(getChildFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tickets_stad, container, false);
        ticketsTab = view.findViewById(R.id.sliding_tabs);
        ticketsViewPager = view.findViewById(R.id.stad_tickets_viewpager);
        retry = view.findViewById(R.id.stad_list_retry_btn);
        retry.setTypeface(myfont);
        progressBar = view.findViewById(R.id.slider_stad_pb);

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
            intent.putExtra("name","tickets");
            startActivity(intent);
        }
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
        if (tApiResponse instanceof MyTicketMain){
            ticketsViewPager.setVisibility(View.VISIBLE);
            MyTicketMain myTicketMain = (MyTicketMain) tApiResponse;
            MyTicketsResult myTicketsResult = myTicketMain.getMyTicketsResult();
            List<Past> pastTickets = myTicketsResult.getPast();
            List<Past> upcomingTickets = myTicketsResult.getComing();
            List<Past> pendingTickets = myTicketsResult.getPending();
            setData(pastTickets,upcomingTickets,pendingTickets);
        }
        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(getContext(),getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
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

    private void setData(List<Past> pastTickets
            , List<Past> upcomingTickets
            ,List<Past> pendingTickets)
    {
        if (getContext() != null) {
            viewPagerAdapter.clear();
            viewPagerAdapter.addFragment(new StadMyTickets()
                    , getResources().getString(R.string.upcoming)
                    , upcomingTickets
            ,false);
            viewPagerAdapter.addFragment(new StadMyTickets()
                    , getResources().getString(R.string.past)
                    , pastTickets,
                    false);
            viewPagerAdapter.addFragment(new StadMyTickets()
                    , getResources().getString(R.string.pending)
                    , pendingTickets
                    ,true);
            viewPagerAdapter.notifyDataSetChanged();
            ticketsViewPager.setAdapter(viewPagerAdapter);
            ticketsTab.setupWithViewPager(ticketsViewPager);
        }
    }
}
