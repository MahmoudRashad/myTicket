package com.example.myticket.View.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadDetails;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumDetailsByID;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.example.myticket.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StadiumDetails extends AppCompatActivity implements GeneralListener {
    private ApiCalling apiCalling;
    private StadDetails stadDetails;
    private ImageView stadCover;
    private TextView stadName;
    private TextView stadAddress;
    private TextView stadDescription;
    private TextView toolbarTitle;
    private ImageView backBtn;
    private ImageView searchIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_stadium_details);
        apiCalling = new ApiCalling(this);
        Intent intent = getIntent();
        if (intent.hasExtra("id")){
            String id = intent.getStringExtra("id");
            apiCalling.getStadiumDetail(id,this);

        }
        stadCover = findViewById(R.id.cover_photo_stad);
        stadName = findViewById(R.id.details_stadium_name);
        stadAddress = findViewById(R.id.stadium_address_text);
        stadDescription = findViewById(R.id.description_text);


    }

    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
    }



    private void setToolbar(StadDetails stadDetails) {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(stadDetails.getName());
//        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StadiumDetails.this,StadMainSearch.class);
                intent.putExtra("tag","stadiums");
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
        if (tApiResponse instanceof StadiumDetailsByID){
            StadiumDetailsByID stadiumDetailsByID = (StadiumDetailsByID) tApiResponse;
            stadDetails = stadiumDetailsByID.getResult();
            setStadDetails(stadDetails);
            setToolbar(stadDetails);
        }
    }

    private void setStadDetails(StadDetails stadDetails) {
        stadName.setText(stadDetails.getName());
        stadAddress.setText(stadDetails.getAddress());
        stadDescription.setText(stadDetails.getDescribtion());
        Picasso.get()
                .load(stadDetails.getImage())
                .into(stadCover);

    }
}
