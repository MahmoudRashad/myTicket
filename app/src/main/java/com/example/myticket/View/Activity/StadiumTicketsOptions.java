package com.example.myticket.View.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.TicketType;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ReservationMain;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ReservationResult;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.StadChairsAdapter;
import com.example.myticket.View.Adapter.StadiumChairsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StadiumTicketsOptions extends AppCompatActivity implements GeneralListener {
    private Spinner classSpinner;
    private Spinner placeSpinner;
    private Button chairSpinner;
    private Button confirmBtn;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private RecyclerView recyclerView;
    private ArrayList<TicketType> ticketTypes;
    private ArrayList<String> ticketsStrings;
    private ArrayList<String> blockName;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private String stadiumId;
    private String date;
    private String id;
    private String matchId;

    private ArrayList<ResultTicketsStad> resultTicketsStads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_stadium_tickets_options);
        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);
        setToolbar();


        Intent intent = getIntent();
        if (intent.getAction() != null) {
            String action = intent.getAction();
            if (action.equals("tickets")) {
                if (intent.getData() != null) {
                    date = intent.getStringExtra("date");
                    matchId = intent.getStringExtra("matchId");
                    String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    TicketType[] results = gson.fromJson(stringData, TicketType[].class);
                    ticketTypes = new ArrayList<>(Arrays.asList(results));
                    ticketsStrings = new ArrayList<>();
                    for (int i = 0 ; i<ticketTypes.size();i++){
                        ticketsStrings.add(ticketTypes.get(i).getName());
                    }
                }
            }

            else if (action.equals("chairs")){
                String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResultTicketsStad[] results = gson.fromJson(stringData, ResultTicketsStad[].class);
                resultTicketsStads = new ArrayList<>(Arrays.asList(results));
            }
        }

        findRefs();


    }

    private void findRefs() {
        //TODO: put data from lists
        classSpinner = findViewById(R.id.spinner_type);
        placeSpinner = findViewById(R.id.spinner_type_place);
        chairSpinner = findViewById(R.id.spinner_chair);
        confirmBtn = findViewById(R.id.select_tickets_btn);
        if (resultTicketsStads != null) {
            recyclerView = findViewById(R.id.seats_titles_rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            StadChairsAdapter stadChairsAdapter = new StadChairsAdapter(this, resultTicketsStads);
            recyclerView.setAdapter(stadChairsAdapter);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(StadiumTicketsOptions.this,StadPaymentConfirm.class);
//                startActivity(intent);
                Toast.makeText(StadiumTicketsOptions.this, "Please Select Chairs First", Toast.LENGTH_LONG).show();
            }
        });
    }else {
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.spinner_text, ticketsStrings);
//        CustomStadSpinnerAdapter stadSpinnerAdapter = new CustomStadSpinnerAdapter(this,R.layout.spinner_text,ticketTypes);
            adapter.setDropDownViewResource(R.layout.checked_text_spinner);
            classSpinner.setAdapter(adapter);
            int position = classSpinner.getSelectedItemPosition();
            TicketType ticketType = ticketTypes.get(position);
            String text = ticketType.getName();
            //TODO:fix the id take it after user selection
            apiCalling.getStadiumBlocks(ticketType.getId().toString(), sessionManager.getUserToken(), this);

            for (int i = 0; i < ticketTypes.size(); i++) {
                if (ticketTypes.get(i).getName().equals(text)) {
                    id = ticketTypes.get(i).getId().toString();
                }
            }


//TODO:then remove this
            ArrayAdapter<CharSequence> placeAdapter = new ArrayAdapter(this, R.layout.spinner_text, ticketsStrings);
            placeAdapter.setDropDownViewResource(R.layout.checked_text_spinner);
            placeSpinner.setAdapter(placeAdapter);
        }
    }

    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
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
                Intent intent = new Intent(StadiumTicketsOptions.this,StadMainSearch.class);
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
        if (tApiResponse instanceof ReservationMain){
            ReservationMain reservationMain = (ReservationMain) tApiResponse;
            List<ReservationResult> reservationResult = reservationMain.getReservationResult();
            if (reservationResult.size() > 0) {
                String stadId = reservationResult.get(0).getStadiumId();
                String text = classSpinner.getSelectedItem().toString();
                chairSpinner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StadiumTicketsOptions.this, StadiumChairs.class);
                        intent.putExtra("stadId", stadId);
                        intent.putExtra("text", text);
                        intent.putExtra("matchId", matchId);
                        intent.putExtra("date", date);
                        intent.putExtra("price", reservationResult.get(0).getPrice());
                        intent.putExtra("currency", reservationResult.get(0).getCurrency());
                        startActivity(intent);
                    }
                });
            }


////            blockName = new ArrayList<>();
////            for (int i = 0 ; i<reservationResult.size();i++){
////                blockName.add(reservationResult.get(i).getName());
////            }
//            ArrayAdapter<CharSequence> placeAdapter = new ArrayAdapter(this, R.layout.spinner_text,ticketsStrings);
//            placeAdapter.setDropDownViewResource(R.layout.checked_text_spinner);
//            classSpinner.setAdapter(placeAdapter);

        }


    }

}
