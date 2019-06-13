package com.example.myticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.View.Adapter.AllReviewsAdapter;

import java.util.ArrayList;

public class MovieDetailsPage extends AppCompatActivity {
    private Button dropDown;
    private RecyclerView reviewsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_details);
        Recently recently = getIntent().getParcelableExtra("recently");
        if (getIntent().getParcelableExtra("recently") != null){
            Toast.makeText(this,"rec",Toast.LENGTH_LONG).show();


        }
        else if (getIntent().getParcelableExtra("coming") != null){
            Toast.makeText(this,"com",Toast.LENGTH_LONG).show();

        }

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
