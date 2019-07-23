package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof StadiumDetailsByID){
            StadiumDetailsByID stadiumDetailsByID = (StadiumDetailsByID) tApiResponse;
            stadDetails = stadiumDetailsByID.getResult();
            setStadDetails(stadDetails);
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
