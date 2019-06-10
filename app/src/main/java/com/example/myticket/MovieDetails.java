package com.example.myticket;

import android.content.Intent;
import android.support.annotation.Px;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieDetails extends AppCompatActivity {
    private Button dropDown;
    private com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton floatingActionButton;
    private android.support.v7.widget.Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView reviewsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getSupportActionBar().hide();
        setContentView(R.layout.activity_movie_details);
        dropDown = findViewById(R.id.dropdown_revs);
        floatingActionButton = findViewById(R.id.custom_fab);
       // toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
    //    int height = collapsingToolbarLayout.getHeight();
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

       // reviewsRv = findViewById(R.id.details_rev_rv);
        ArrayList<String> texts = new ArrayList<>();
        texts.add("basma");
        texts.add("Reda");
        texts.add("mohamed samy");
        texts.add("elmihy");
        texts.add("samar");
        texts.add("hanan");
        texts.add("ahmed");
        AllReviewsAdapter allReviewsAdapter = new AllReviewsAdapter(this,texts);
//        reviewsRv.setAdapter(allReviewsAdapter);
  //      reviewsRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



    }

}
