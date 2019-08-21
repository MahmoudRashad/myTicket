package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ChairsResult;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainChairs;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadiumChairsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StadiumChairs extends AppCompatActivity implements GeneralListener, StadiumChairsAdapter.saveChairs {

    private RecyclerView chairsRv;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private StadiumChairsAdapter stadiumChairsAdapter;
    private Button confirmBtn;
    private ArrayList<ResultTicketsStad> resultTicketsStads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_chairs);
        chairsRv = findViewById(R.id.stad_chairs_rv);
        confirmBtn = findViewById(R.id.select_chairs_btn);
        chairsRv.setLayoutManager(new LinearLayoutManager(this));
        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);
        resultTicketsStads = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.hasExtra("stadId")&&intent.hasExtra("text")) {
            String stadId = intent.getStringExtra("stadId");
            String text = intent.getStringExtra("text");
            apiCalling.getChairs(stadId, text, "Bearer " + sessionManager.getUserToken(), this);
        }

    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof MainChairs){
            MainChairs mainChairs = (MainChairs) tApiResponse;
            List<ChairsResult> chairsResult = mainChairs.getChairsResult();
            //TODO: get match id and date from new api
            stadiumChairsAdapter = new StadiumChairsAdapter(chairsResult,this,this,"1","2019-07-18");
            chairsRv.setAdapter(stadiumChairsAdapter);
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   resultTicketsStads.size();
                   //TODO: send this in api
                }
            });
        }
    }

    @Override
    public void saveChairs(boolean checked, ResultTicketsStad resultTicketsStad) {
        if (checked)
        resultTicketsStads.add(resultTicketsStad);
        else{
            resultTicketsStads.remove(resultTicketsStad);
        }
    }
}
