package com.example.myticket.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ChairsResult;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainChairs;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainReservationDetails;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ReservationMain;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ReservationResult;
import com.example.myticket.Model.Network.StadiumModel.Reservation.TicketType;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.R;
import com.example.myticket.View.Activity.tler.MainPaymentActivity;
import com.example.myticket.View.Adapter.StadChairsAdapter;
import com.example.myticket.View.Adapter.StadiumChairsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

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
    private ImageView stadImage;
    private RecyclerView recyclerView;
    private List<com.example.myticket.Model.Network.StadiumModel.Reservation.TicketType> ticketTypes;
    private ArrayList<String> ticketsStrings;
    private ArrayList<String> blocksStrings;
    private ArrayList<String> blockName;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private String stadiumId;
    private String date;
    private String id;
    private String matchId;
    private String chossenOne;
    private String choosenTwo;
    private String choosenThree;
    private String blockImage;
    private String action;
    static public ArrayList<ResultTicketsStad> resultTicketsStads;
    static final int PICK_CHAIRS_REQUEST = 111;
    private View view1;
    private TextView yourSeats;
    private TextView individualTitle;
    private TextView priceEq;
    private View view2;
    private TextView subTotalTitle;
    private TextView priceTotal;

    private ProgressBar progressBar;
    private Button retry;

    private String mainUrl;
    private int limit;
    private ReservationResult reservation;
    List<ReservationResult> reservationResult;
    String url;
    private EditText ticketsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_stadium_tickets_options);
        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);
        setToolbar();
        findRefs();
        Intent intent = getIntent();
        if (intent.getAction() != null) {
            action = intent.getAction();
            if (action.equals("tickets")) {
                matchId = intent.getStringExtra("matchId");
                limit = intent.getIntExtra("limit",4);
                senarioOne();
                apiCalling.getReservationDetails(matchId,sessionManager.handleLogin(),sessionManager.getDeviceLanguage(),this::getApiResponse);
            }


//            else if (action.equals("chairs")){
//                String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
//                GsonBuilder gsonBuilder = new GsonBuilder();
//                Gson gson = gsonBuilder.create();
//                ResultTicketsStad[] results = gson.fromJson(stringData, ResultTicketsStad[].class);
//                resultTicketsStads = new ArrayList<>(Arrays.asList(results));
//                chossenOne = intent.getStringExtra("firstChoice");
//                choosenTwo = intent.getStringExtra("secondChoice");
//                blockImage = intent.getStringExtra("blockImage");
//                matchId = intent.getStringExtra("matchId");
//                progressBar.setVisibility(View.GONE);
//                senarioTwo();
//            }
        }








    }
    private void senarioOne(){
        action = "";
        ticketsStrings.clear();
        blocksStrings.clear();
        resultTicketsStads.clear();
        chossenOne = "";
        choosenTwo = "";
        placeSpinner.setEnabled(false);
        placeSpinner.setClickable(false);

        chairSpinner.setEnabled(false);
        chairSpinner.setClickable(false);
        chairSpinner.setText(" ");
        progressBar.setVisibility(View.VISIBLE);
        viewsGone();




    }
    private void senarioTwo() {

//        if (resultTicketsStads != null && action.equals("chairs") && resultTicketsStads.size() > 0) {
//            if (!blockImage.equals("")) {
//                Picasso.get().load(blockImage).into(stadImage);
//            }
//            ticketsStrings.add(chossenOne);
//            ticketsStrings.add(getResources().getString(R.string.change_selection));


//            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.spinner_text, ticketsStrings);
//            adapter.setDropDownViewResource(R.layout.checked_text_spinner);
//            classSpinner.setAdapter(adapter);
//            classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if (position == 1){
//                        senarioOne();
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
//            blocksStrings.add(choosenTwo);
//            blocksStrings.add(getResources().getString(R.string.change_selection));
//            ArrayAdapter<CharSequence> placeAdapter = new ArrayAdapter(this, R.layout.spinner_text, blocksStrings);
//            placeAdapter.setDropDownViewResource(R.layout.checked_text_spinner);
//            placeSpinner.setAdapter(placeAdapter);
//            placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if (position == 1){
//                        senarioOne();
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });

       // }
    }

    private void viewsGone(){
        confirmBtn.setVisibility(View.GONE);
        view1.setVisibility(View.GONE);
        yourSeats.setVisibility(View.GONE);
        individualTitle.setVisibility(View.GONE);
        priceEq.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        subTotalTitle.setVisibility(View.GONE);
        priceTotal.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }


    private void findRefs() {
        ticketsStrings = new ArrayList<>();
        blocksStrings = new ArrayList<>();
        resultTicketsStads = new ArrayList<>();
        stadImage = findViewById(R.id.stad_details_image);
        classSpinner = findViewById(R.id.spinner_type);
        placeSpinner = findViewById(R.id.spinner_type_place);
        chairSpinner = findViewById(R.id.spinner_chair);
        confirmBtn = findViewById(R.id.select_tickets_btn);
        recyclerView = findViewById(R.id.seats_titles_rv);

        view1 = findViewById(R.id.view1);
        yourSeats = findViewById(R.id.your_seats_title);
        individualTitle = findViewById(R.id.individual_tile);
        priceEq = findViewById(R.id.price_eq);
        view2 = findViewById(R.id.view2);
        subTotalTitle = findViewById(R.id.subTotal_title);
        priceTotal = findViewById(R.id.price_total);
        progressBar = findViewById(R.id.pb_book);
        retry = findViewById(R.id.retry_book);
        ticketsEditText = findViewById(R.id.tickets_edit_text);

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_CHAIRS_REQUEST && resultCode == RESULT_OK){
            Toast.makeText(StadiumTicketsOptions.this,"OK",Toast.LENGTH_LONG).show();
            String stringData = String.valueOf(data.getData().getSchemeSpecificPart());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResultTicketsStad[] results = gson.fromJson(stringData, ResultTicketsStad[].class);
                resultTicketsStads = new ArrayList<>(Arrays.asList(results));

            priceEq.setText(" "+resultTicketsStads.size() + " X " + resultTicketsStads.get(0).getPrice());
            String price = String.valueOf(resultTicketsStads.size() * Integer.parseInt(resultTicketsStads.get(0).getPrice()));
            priceTotal.setText(" "+price +" "+ resultTicketsStads.get(0).getCurrency());

            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            StadChairsAdapter stadChairsAdapter = new StadChairsAdapter(this, resultTicketsStads);
            recyclerView.setAdapter(stadChairsAdapter);
            chairSpinner.setText("");
            for (int i = 0 ; i < resultTicketsStads.size() ; i++){
                if (i != resultTicketsStads.size() -1 ){
                    chairSpinner.append(" " + resultTicketsStads.get(i).getChairSymbol()+"-" +resultTicketsStads.get(i).getChairNum()+",");
                }
                else
                    chairSpinner.append(" " + resultTicketsStads.get(i).getChairSymbol()+ "-"+resultTicketsStads.get(i).getChairNum());
            }


            confirmBtn.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            yourSeats.setVisibility(View.VISIBLE);
            individualTitle.setVisibility(View.VISIBLE);
            priceEq.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            subTotalTitle.setVisibility(View.VISIBLE);
            priceTotal.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(StadiumTicketsOptions.this,
                            MainPaymentActivity.class);
                    intent.putExtra("amount" , priceTotal.getText().toString());
                    startActivity(intent);

//                    progressBar.setVisibility(View.VISIBLE);
//                    apiCalling.clubReservation("Bearer " + sessionManager.getUserToken(), sessionManager.getDeviceLanguage(),
//                            resultTicketsStads, StadiumTicketsOptions.this);
                }
            });


        }
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse)
    {
        retry.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        if (tApiResponse instanceof ReservationMain){
            placeSpinner.setEnabled(true);
            placeSpinner.setClickable(true);
            blocksStrings.clear();
            chairSpinner.setText("");
            ReservationMain reservationMain = (ReservationMain) tApiResponse;
             reservationResult = reservationMain.getReservationResult();
            if (reservationResult.size() > 0) {

                blocksStrings.add(getResources().getString(R.string.select_block_type));
                viewsGone();
            for (int i = 0; i < reservationResult.size(); i++) {
                blocksStrings.add(reservationResult.get(i).getName());
            }

            ArrayAdapter<CharSequence> placeAdapter = new ArrayAdapter(this, R.layout.spinner_text, blocksStrings);
            placeAdapter.setDropDownViewResource(R.layout.checked_text_spinner);
            placeSpinner.setAdapter(placeAdapter);
            placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0){
                        reservation = reservationResult.get(position -1 );
                        choosenTwo = reservation.getName();
                        url = reservation.getImage();
                        if (!url.equals("")) {
                            Picasso.get().load(url).into(stadImage);
                        }
                        apiCalling.getChairs(stadiumId, reservation.getName(), matchId,"Bearer " + sessionManager.getUserToken(),
                                StadiumTicketsOptions.this::getApiResponse);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            }

        }

        else if (tApiResponse instanceof MainReservationDetails){
            MainReservationDetails mainReservationDetails = (MainReservationDetails) tApiResponse;
            ticketTypes = mainReservationDetails.getReservationDetails().getTicketType();
            mainUrl = mainReservationDetails.getReservationDetails().getStatdiumPlan();
            if (!mainUrl.equals("")) {
                Picasso.get().load(mainUrl).into(stadImage);
            }
            date = mainReservationDetails.getReservationDetails().getMatchDate();
            stadiumId = mainReservationDetails.getReservationDetails().getStadiumId();

            ticketsStrings.add(getResources().getString(R.string.select_ticket_type));
            for (int i = 0 ; i<ticketTypes.size();i++){
                ticketsStrings.add(ticketTypes.get(i).getName());
            }
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.spinner_text, ticketsStrings);
            adapter.setDropDownViewResource(R.layout.checked_text_spinner);
            classSpinner.setAdapter(adapter);
            classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        TicketType ticketType = ticketTypes.get(position - 1);
                        chossenOne = ticketType.getName();
                        apiCalling.getStadiumBlocks(ticketType.getId().toString(), sessionManager.getUserToken(), StadiumTicketsOptions.this::getApiResponse);
                    }
                    else {
                        Toast.makeText(StadiumTicketsOptions.this,getResources().getString(R.string.choose_a_type),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter<CharSequence> placeAdapter = new ArrayAdapter(this, R.layout.spinner_text, blocksStrings);
            placeAdapter.setDropDownViewResource(R.layout.checked_text_spinner);
            placeSpinner.setAdapter(placeAdapter);

        }

        else if (tApiResponse instanceof GeneralApiesponse){
            GeneralApiesponse generalApiesponse = (GeneralApiesponse) tApiResponse;
            Intent intent = new Intent(StadiumTicketsOptions.this,HomeStadBottomNav.class);
            startActivity(intent);
            String msg = generalApiesponse.getMessage();
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }

        else if (tApiResponse instanceof MainChairs) {
            MainChairs mainChairs = (MainChairs) tApiResponse;
            String msg = mainChairs.getMessage();
            if (msg.equals("empty")) {
                ticketsEditText.setVisibility(View.VISIBLE);
                chairSpinner.setVisibility(View.GONE);
                //TODO: send number of chairs

            }
            else if (!mainChairs.getSuccess()){
                ticketsEditText.setVisibility(View.GONE);
                chairSpinner.setVisibility(View.GONE);
                Toast.makeText(StadiumTicketsOptions.this,mainChairs.getMessage(),Toast.LENGTH_LONG).show();
            }
            else {
                viewsGone();
                ticketsEditText.setVisibility(View.GONE);
                chairSpinner.setVisibility(View.VISIBLE);
                chairSpinner.setClickable(true);
                chairSpinner.setEnabled(true);
                chairSpinner.setText(getString(R.string.select_chairs));

                chairSpinner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(StadiumTicketsOptions.this, StadiumChairs.class);
                        intent.putExtra("stadId", stadiumId);
                        intent.putExtra("text", reservation.getName());
                        intent.putExtra("matchId", matchId);
                        intent.putExtra("date", date);
                        intent.putExtra("price", reservationResult.get(0).getPrice());
                        intent.putExtra("currency", reservationResult.get(0).getCurrency());
                        intent.putExtra("firstChoice", chossenOne);
                        intent.putExtra("secondChoice", choosenTwo);
                        intent.putExtra("limit", limit);
                        if (!url.equals("")) {
                            intent.putExtra("blockImage", reservation.getImage());
                        }
                        intent.putExtra("blockImage", mainUrl);

                        startActivityForResult(intent,PICK_CHAIRS_REQUEST);
                    }
                });

            }
        }

//        else if (tApiResponse instanceof BaseNoResult){
//            BaseNoResult baseNoResult = (BaseNoResult) tApiResponse;
//            if (baseNoResult.getMessage().equals("empty")){
//                ticketsEditText.setVisibility(View.VISIBLE);
//                chairSpinner.setVisibility(View.GONE);
//                //TODO: send number of chairs
//            }
//        }

        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(this,getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    senarioOne();
                }
            });
        }
    }

}
