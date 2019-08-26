package com.example.myticket.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.myticket.R;
import com.example.myticket.View.Adapter.MatchesAdapter;
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
    private TextView firstText;
    private TextView secondText;
    private TextView thirdText;
    private TextView fourthText;
    private TextView firstPrice;
    private TextView secondPrice;
    private TextView thirdPrice;
    private TextView fourthPrice;
    private Button bookBtn;

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

    private ProgressBar progressBar;
    private Button retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_match_details);

        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);

        Intent intent = getIntent();
        if (intent.hasExtra("matchId")) {
            matchId = intent.getStringExtra("matchId");
            String token = sessionManager.handleLogin();
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
                finish();
            }
        });

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = sessionManager.handleLogin();
                if (!token.equals("")) {
                    //set api
                    apiCalling.follow(token, matchId, MatchDetails.this::getApiResponse);
                    handleNotificationLayout(matchDetails);

                } else {
                    Intent intent = new Intent(MatchDetails.this, Login.class);
                    intent.putExtra("id", matchDetails.getId());
                    intent.putExtra("name", "home");
                    intent.putExtra("flag", "stad");
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
        btolaName = findViewById(R.id.detail_btola_name);
        teamOneName = findViewById(R.id.team_one_name);
        teamTwoName = findViewById(R.id.team_two_name);
        teamOneImage = findViewById(R.id.team_one_image_card_view);
        teamTwoImage = findViewById(R.id.team_two_image_card_view);
        time = findViewById(R.id.cardView_time);
        date = findViewById(R.id.date_cardView);
        address = findViewById(R.id.stadium_address_text);
        bookBtn = findViewById(R.id.book_btn);

        firstText = findViewById(R.id.the_first_text);
        secondText = findViewById(R.id.the_second_text);
        thirdText = findViewById(R.id.the_third_text);
        fourthText = findViewById(R.id.the_fourth_text);

        firstPrice = findViewById(R.id.the_first_price);
        secondPrice = findViewById(R.id.the_second_price);
        thirdPrice = findViewById(R.id.the_third_price);
        fourthPrice = findViewById(R.id.the_fourth_price);

        toolbarTitle = findViewById(R.id.toolbar_title);
        notificationIcon = findViewById(R.id.toolbar_notifications);
        searchIcon = findViewById(R.id.toolbar_Search);
        toolbarBack = findViewById(R.id.toolbar_back);
        frameLayout = findViewById(R.id.notification_activated_layout);
        notificationImage = findViewById(R.id.notification_image);
        notificationText = findViewById(R.id.activated_text);
        closeBtn = findViewById(R.id.close_notifictaion);
        toolbar = findViewById(R.id.top_match_details);

        progressBar = findViewById(R.id.slider_stad_pb);
        retry = findViewById(R.id.retry_btn_match_details);

    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        progressBar.setVisibility(View.GONE);
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
                notificationIcon.setImageResource(R.drawable.ic_notifications_off_black_24dp);
                notificationImage.setImageResource(R.drawable.ic_notifications_off_black_24dp);
                notificationIcon.setColorFilter(R.color.white);

            } else {
                notificationText.setText(msg);
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                notificationIcon.setImageResource(R.drawable.ic_notifications_active_24dp);
                notificationImage.setImageResource(R.drawable.ic_notifications_active_24dp);
                notificationIcon.setColorFilter(R.color.white);

            }

        }

        else// if (message.contains("connection abort")|| message.contains("Failed to connect"))
        {
            Toast.makeText(this,"Check your internet connection", Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MatchDetails.this, HomeStadBottomNav.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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

        if (matchDetails.getTicketType().size() > 0){
        if (matchDetails.getTicketType().get(0) != null) {
            firstText.setText(matchDetails.getTicketType().get(0).getName());
            firstPrice.setText(matchDetails.getTicketType().get(0).getPrice() + " " + matchDetails.getTicketType().get(0).getCurrency());
        }

        if (matchDetails.getTicketType().size() > 1 && matchDetails.getTicketType().get(1) != null) {
            secondText.setText(matchDetails.getTicketType().get(1).getName());
            secondPrice.setText(matchDetails.getTicketType().get(1).getPrice() + " " + matchDetails.getTicketType().get(1).getCurrency());
        }

        if (matchDetails.getTicketType().size() > 2 && matchDetails.getTicketType().get(2) != null) {
            thirdText.setText(matchDetails.getTicketType().get(2).getName());
            thirdPrice.setText(matchDetails.getTicketType().get(2).getPrice() + " " + matchDetails.getTicketType().get(2).getCurrency());

          if (matchDetails.getTicketType().size() > 3 && matchDetails.getTicketType().get(3) != null) {
                fourthText.setText(matchDetails.getTicketType().get(3).getName());
                fourthPrice.setText(matchDetails.getTicketType().get(3).getPrice() + " " + matchDetails.getTicketType().get(3).getCurrency());
            }
        }

            bookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sessionManager.handleLogin().equals("")) {
                        Intent intent = new Intent(MatchDetails.this, StadiumTicketsOptions.class);
                        intent.setAction("tickets");
                        intent.putExtra("date",matchDetails.getDate());
                        intent.putExtra("matchId",matchId);
                        String ListDumb = new Gson().toJson(matchDetails.getTicketType());
                        intent.setData(Uri.fromParts("schemeTicket", ListDumb, null));
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(MatchDetails.this,Login.class);
                        //TODO: send this place with the login
                        intent.putExtra("name","home");
                        intent.putExtra("flag","flag");
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
