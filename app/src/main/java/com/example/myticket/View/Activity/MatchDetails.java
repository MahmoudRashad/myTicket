package com.example.myticket.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myticket.R;

public class MatchDetails extends AppCompatActivity {
    private TextView stadiumName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        stadiumName = findViewById(R.id.stadium_name_text);
        stadiumName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchDetails.this,StadiumDetails.class);
                startActivity(intent);
            }
        });
    }
}
