package com.example.myticket.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ChairsResult;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainChairs;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadiumChairsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class StadiumChairs extends AppCompatActivity implements GeneralListener, StadiumChairsAdapter.saveChairs {

    private RecyclerView chairsRv;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private StadiumChairsAdapter stadiumChairsAdapter;
    private Button confirmBtn;
    private ArrayList<ResultTicketsStad> resultTicketsStads;
    private String matchId;
    private String date;
    private String price;
    private String currency;
    private String one;
    private String two;
    private String blockImage;

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;

    private ProgressBar progressBar;
    private Button retry;

    private String stadId;
    private String text;
    private int limit;
    private TextView textLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_chairs);
        setToolbar();
        chairsRv = findViewById(R.id.stad_chairs_rv);
        confirmBtn = findViewById(R.id.select_chairs_btn);
        progressBar = findViewById(R.id.pb_chair);
        retry = findViewById(R.id.retry_chair);
        textLimit = findViewById(R.id.text_limit);
        chairsRv.setLayoutManager(new LinearLayoutManager(this));
        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);
        resultTicketsStads = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.hasExtra("stadId")&&intent.hasExtra("text")) {
            stadId = intent.getStringExtra("stadId");
            text = intent.getStringExtra("text");
            matchId = intent.getStringExtra("matchId");
            date = intent.getStringExtra("date");
            price = intent.getStringExtra("price");
            currency = intent.getStringExtra("currency");
            one = intent.getStringExtra("firstChoice");
            two =intent.getStringExtra("secondChoice");
            blockImage = intent.getStringExtra("blockImage");
            limit = intent.getIntExtra("limit",4);
            textLimit.setText(getResources().getString(R.string.your_limit_is) +" " +limit + " "+getResources().getString(R.string.tickets));
            apiCalling.getChairs(stadId, text, matchId,"Bearer " + sessionManager.getUserToken(), this);
        }

    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.reservation));

        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StadiumChairs.this,StadMainSearch.class);
                intent.putExtra("tag","home");

                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
        retry.setVisibility(View.GONE);
        if (tApiResponse instanceof MainChairs){
            MainChairs mainChairs = (MainChairs) tApiResponse;
            List<ChairsResult> chairsResult = mainChairs.getChairsResult();
            stadiumChairsAdapter = new StadiumChairsAdapter(chairsResult,this,this,matchId,date,price,currency,limit);
            chairsRv.setAdapter(stadiumChairsAdapter);
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   resultTicketsStads.size();
                    Intent intent = new Intent(StadiumChairs.this,StadiumTicketsOptions.class);
                    String ListDumb = new Gson().toJson(resultTicketsStads);
                    intent.setAction("chairs");
                    intent.setData(Uri.fromParts("schemeTicket", ListDumb, null));
                    intent.putExtra("chairs",ListDumb);
                    intent.putExtra("firstChoice",one);
                    intent.putExtra("secondChoice",two);
                    intent.putExtra("blockImage",blockImage);
                    intent.putExtra("matchId",matchId);
                    startActivity(intent);

                }
            });
        }


        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(this,getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    apiCalling.getChairs(stadId, text, matchId,"Bearer " + sessionManager.getUserToken(), StadiumChairs.this::getApiResponse);
                }
            });
        }

    }

    @Override
    public void saveChairs(boolean checked, ResultTicketsStad resultTicketsStad) {
        if (checked)
        resultTicketsStads.add(resultTicketsStad);
        else{
            for (int i = 1; i <resultTicketsStads.size() ; i++){
                if (resultTicketsStad.getId().equals(resultTicketsStads.get(i).getId())){
                    resultTicketsStads.remove(resultTicketsStad);
                    break;
                }
            }
        }
    }
}
