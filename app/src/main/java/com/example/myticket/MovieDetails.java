package com.example.myticket;

import android.content.Intent;
import android.support.annotation.Px;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MovieDetails extends AppCompatActivity {
    private Button dropDown;
    private com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton floatingActionButton;
    private android.support.v7.widget.Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getSupportActionBar().hide();
        setContentView(R.layout.activity_movie_details);
        dropDown = findViewById(R.id.dropdown_revs);
        floatingActionButton = findViewById(R.id.custom_fab);
       // toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        int height = collapsingToolbarLayout.getHeight();
//        if (height <= 48){
//            collapsingToolbarLayout.setLayoutMode();
//        }
     //   toolbar.setTitle("Movie Name");
//        collapsingToolbarLayout.setTitleEnabled(true);
//        collapsingToolbarLayout.setTitle("The Name");


        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  fabText.setVisibility(View.VISIBLE);
                floatingActionButton.setFabText("");
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetails.this,HomeCinema.class);
                startActivity(intent);
            }
        });


    }

}
