package com.example.myticket.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.AllReviewsAdapter;

import java.util.ArrayList;

public class MovieDetailsPage extends AppCompatActivity {
    private Button dropDown;
    private RecyclerView reviewsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_details);

        dropDown = findViewById(R.id.dropdown_revs);


        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  fabText.setVisibility(View.VISIBLE);

            }
        });

        reviewsRv = findViewById(R.id.details_rev_rv);
        ArrayList<String> texts = new ArrayList<>();
        texts.add("basma");
        texts.add("Reda");
        texts.add("mohamed samy");
        texts.add("elmihy");
        texts.add("samar");
        texts.add("hanan");
        texts.add("ahmed");
        AllReviewsAdapter allReviewsAdapter = new AllReviewsAdapter(this,texts);
       reviewsRv.setAdapter(allReviewsAdapter);
      reviewsRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



    }

}
