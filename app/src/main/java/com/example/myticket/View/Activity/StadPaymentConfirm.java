package com.example.myticket.View.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketDetailResult;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketMainDetail;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.Past;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.TicketsConfirmAdapter;

import java.util.List;

public class StadPaymentConfirm extends AppCompatActivity implements GeneralListener {

    private RecyclerView ticketsRV;
    private TicketsConfirmAdapter ticketsConfirmAdapter;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Past ticket;
    private String matchId;
    private ApiCalling apiCalling;
    private SessionManager sessionManager;
    private ConstraintLayout constraintLayout;
    private List<MyTicketDetailResult> myTicketDetailResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_stad_payment_confirm);
        setToolbar();

        apiCalling = new ApiCalling(this);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        constraintLayout = findViewById(R.id.stad_payment_confirm);
        if (intent.hasExtra("matchId")) {
            matchId = intent.getStringExtra("matchId");

            ticketsRV = findViewById(R.id.confirm_tickets_rv);
            ticketsRV.setLayoutManager(new LinearLayoutManager(this));
            apiCalling.getMyTicketDetailStad(matchId,sessionManager.handleLogin(),sessionManager.getDeviceLanguage(),this);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StadPaymentConfirm.this,HomeStadBottomNav.class);
        intent.putExtra("name","myTickets");
        startActivity(intent);
    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
       toolbarTitle.setText(getString(R.string.reservation));

        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);


        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StadPaymentConfirm.this,StadMainSearch.class);

                intent.putExtra("tag","home");

                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StadPaymentConfirm.this,HomeStadBottomNav.class);
                intent.putExtra("name","myTickets");
                startActivity(intent);
            }
        });
    }

    public void afterGivingPermission(List<MyTicketDetailResult> myTicketDetailResult){
        if (constraintLayout.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL){
            ticketsConfirmAdapter = new TicketsConfirmAdapter(this,myTicketDetailResult,R.layout.green_rv_item_arabic);
        }else {
            ticketsConfirmAdapter = new TicketsConfirmAdapter(this, myTicketDetailResult, R.layout.tickets_green_rv_item);
        }
        ticketsRV.setAdapter(ticketsConfirmAdapter);
    }
    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof MyTicketMainDetail){
            MyTicketMainDetail myTicketMainDetail = (MyTicketMainDetail) tApiResponse;
            myTicketDetailResult = myTicketMainDetail.getMyTicketDetailResult();


            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
            else {
                afterGivingPermission(myTicketDetailResult);
            }


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    afterGivingPermission(myTicketDetailResult);
                } else {
                    Toast.makeText(this,getResources().getString(R.string.permission),Toast.LENGTH_LONG).show();
                }
                return;

        }
    }
}
