package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatchDetails;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.TicketType;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainLimit;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.MatchesAdapter;
import com.example.myticket.View.Adapter.TicketsTypeAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchDetails extends AppCompatActivity implements GeneralListener {
    private TextView stadiumName;
    private TextView btolaName;
    private ImageView teamOneImage;
    private ImageView teamTwoImage;
    private TextView teamOneName;
    private TextView teamTwoName;
    private TextView time;
    private TextView date;
    private TextView address;
    private Button bookBtn;
    private RecyclerView ticketsTypeRv;
    private Typeface myfont;

    private ImageView notificationIcon;
    private TextView toolbarTitle;
    private ImageView searchIcon;
    private ImageView toolbarBack;
    private ImageView notificationImage;
    private TextView notificationText;
    private FrameLayout frameLayout;
    private ConstraintLayout toolbar;
    private ImageView closeBtn;


    private String matchId;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;

    private ProgressBar notificationPb;
    private ProgressBar progressBar;
    private ProgressBar bookPb;
    private Button retry;
    private String token;
    private TextView categoriesTitle;
    private TextView stadiumTitle;
    private TextView mapBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_match_details);

        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        Intent intent = getIntent();
        if (intent.hasExtra("matchId")) {
            matchId = intent.getStringExtra("matchId");
            token = sessionManager.handleLogin();
            apiCalling.getMatchDetails(matchId, token, this);
        }
        findRefs();
        setListeners();


    }

    private void setToolbar(com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails matchDetails) {
        toolbarTitle.setText(getString(R.string.match_details));
        searchIcon.setVisibility(View.GONE);
        notificationIcon.setVisibility(View.VISIBLE);
        int status = matchDetails.getFollowStatus();
        if (status == 1) {
            notificationIcon.setImageResource(R.drawable.ic_notifications_active_24dp);
        } else {
            notificationIcon.setImageResource(R.drawable.ic_notifications_off_black_24dp);
        }
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(MatchDetails.this);
            }
        });

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationPb.setVisibility(View.VISIBLE);
                String token = sessionManager.handleLogin();
                if (!token.equals("")) {
                    //set api
                    apiCalling.follow(token, matchId, MatchDetails.this::getApiResponse);
                    handleNotificationLayout(matchDetails);

                } else {
                    Intent intent = new Intent(MatchDetails.this, Login.class);
                    intent.putExtra("name", "match");
                    intent.putExtra("flag", "stad");
                    intent.putExtra("matchId", matchId);
                    startActivity(intent);
                }
            }
        });
    }

    private void handleNotificationLayout(com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails matchDetails) {
        frameLayout.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.GONE);
        int status = matchDetails.getFollowStatus();
        if (status == 1) {
            notificationImage.setImageResource(R.drawable.ic_notifications_active_24dp);
        } else {
            notificationImage.setImageResource(R.drawable.ic_notifications_off_black_24dp);
        }

    }

    private void setListeners() {
//        stadiumName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MatchDetails.this, StadiumDetails.class);
//                startActivity(intent);
//            }
//        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
            }
        });

    }

    private void findRefs() {
        stadiumName = findViewById(R.id.stadium_name_text);
        stadiumName.setTypeface(myfont);
        btolaName = findViewById(R.id.detail_btola_name);
        btolaName.setTypeface(myfont);
        teamOneName = findViewById(R.id.team_one_name);
        teamOneName.setTypeface(myfont);
        teamTwoName = findViewById(R.id.team_two_name);
        teamTwoName.setTypeface(myfont);
        teamOneImage = findViewById(R.id.team_one_image_card_view);
        teamTwoImage = findViewById(R.id.team_two_image_card_view);
        time = findViewById(R.id.cardView_time);
        time.setTypeface(myfont);
        date = findViewById(R.id.date_cardView);
        date.setTypeface(myfont);
        address = findViewById(R.id.stadium_address_text);
        address.setTypeface(myfont);
        bookBtn = findViewById(R.id.book_btn);
        bookBtn.setTypeface(myfont);
        categoriesTitle = findViewById(R.id.tickets_categories_title);
        categoriesTitle.setTypeface(myfont);

        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(myfont);
        notificationIcon = findViewById(R.id.toolbar_notifications);
        searchIcon = findViewById(R.id.toolbar_Search);
        toolbarBack = findViewById(R.id.toolbar_back);
        frameLayout = findViewById(R.id.notification_activated_layout);
        notificationImage = findViewById(R.id.notification_image);
        notificationText = findViewById(R.id.activated_text);
        notificationText.setTypeface(myfont);
        closeBtn = findViewById(R.id.close_notifictaion);
        toolbar = findViewById(R.id.top_match_details);

        progressBar = findViewById(R.id.slider_stad_pb);
        retry = findViewById(R.id.retry_btn_match_details);
        ticketsTypeRv = findViewById(R.id.tickets_categories_rv);
        ticketsTypeRv.setLayoutManager(new LinearLayoutManager(this));
        notificationPb = findViewById(R.id.noti_pb);
        stadiumTitle = findViewById(R.id.stadium_title);
        stadiumTitle.setTypeface(myfont);
        mapBtn = findViewById(R.id.location_on_map);
        mapBtn.setTypeface(myfont);
        bookPb = findViewById(R.id.pb_book_details);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bookPb.setVisibility(View.GONE);
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
        notificationPb.setVisibility(View.GONE);
        if (tApiResponse instanceof MainMatches) {
            MainMatches mainMatches = (MainMatches) tApiResponse;
            List<com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails> matchDetails = mainMatches.getResult();

            if (matchDetails.size() > 0 && matchDetails.get(0) != null)
            setDetails(matchDetails.get(0));
        }

        else if (tApiResponse instanceof BaseNoResult) {
            BaseNoResult baseNoResult = (BaseNoResult) tApiResponse;
            String msg = baseNoResult.getMessage();
            if (msg.contains("إلغاء") || msg.contains("unFollow")) {
                notificationText.setText(msg);
                notificationImage.setImageResource(R.drawable.ic_notifications_off_black_24dp);
                notificationIcon.setImageResource(R.drawable.ic_notifications_off_black_24dp);


            } else {
                notificationText.setText(msg);
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                notificationImage.setImageResource(R.drawable.ic_notifications_active_24dp);
                notificationIcon.setImageResource(R.drawable.ic_notifications_active_24dp);



            }

        }

        else if (tApiResponse instanceof MainLimit){
            MainLimit mainLimit = (MainLimit) tApiResponse;
            boolean allowedStatus = mainLimit.getSuccess();
            if (!allowedStatus){
                Toast.makeText(this,mainLimit.getMessage(),Toast.LENGTH_LONG).show();
            }
            else {
                int limit = mainLimit.getLimit().getLimit();
             if (limit > 0){
                Intent intent = new Intent(MatchDetails.this, StadiumTicketsOptions.class);
                intent.setAction("tickets");
                intent.putExtra("limit",limit);
                intent.putExtra("matchId",matchId);
                startActivity(intent);
            }
            else if (limit == 0){
                Toast.makeText(MatchDetails.this,getResources().getString(R.string.limit_warning),Toast.LENGTH_LONG).show();
            }
        }
            bookPb.setVisibility(View.GONE);
        }
        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(this,getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiCalling.getMatchDetails(matchId, token, MatchDetails.this::getApiResponse);
                }
            });
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

    private void setDetails(com.example.myticket.Model.Network.StadiumModel.Match.MatchDetails matchDetails) {
        setToolbar(matchDetails);
        stadiumName.setText(matchDetails.getStadiumName());
        btolaName.setText(matchDetails.getCyclicName());
        teamOneName.setText(matchDetails.getTeam1Name());
        teamTwoName.setText(matchDetails.getTeam2Name());
        Picasso.get()
                .load(matchDetails.getTeam1Image())
                .into(teamOneImage);
        Picasso.get()
                .load(matchDetails.getTeam2Image())
                .into(teamTwoImage);
        time.setText(matchDetails.getStartTime());
        date.setText(matchDetails.getDate());
        address.setText(matchDetails.getStadiumAddress());

        if (matchDetails.getTicketType().size() != 0) {
            TicketsTypeAdapter ticketsTypeAdapter = new TicketsTypeAdapter(this, matchDetails.getTicketType());
            ticketsTypeRv.setAdapter(ticketsTypeAdapter);
            bookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookPb.setVisibility(View.VISIBLE);
                    if (!sessionManager.handleLogin().equals("")) {
                        apiCalling.getLimit(matchId, sessionManager.handleLogin(), sessionManager.getDeviceLanguage(), MatchDetails.this::getApiResponse);
                    } else {
                        Intent intent = new Intent(MatchDetails.this, Login.class);
                        intent.putExtra("name", "match");
                        intent.putExtra("flag", "flag");
                        intent.putExtra("matchId", matchId);
                        startActivity(intent);
                    }

                }
            });
        }
        else {
            ticketsTypeRv.setVisibility(View.GONE);
            bookBtn.setVisibility(View.GONE);
            categoriesTitle.setVisibility(View.GONE);
        }
    }
    }

