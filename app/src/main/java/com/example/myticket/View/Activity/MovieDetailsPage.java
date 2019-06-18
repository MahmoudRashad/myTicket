package com.example.myticket.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myticket.Model.Network.DataModel.CommentsModel.Comments;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Result;
import com.example.myticket.Model.Network.DataModel.HomeResult.Category;
import com.example.myticket.Model.Network.DataModel.HomeResult.Coming;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.AllReviewsAdapter;
import com.example.myticket.View.Adapter.CategoryAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailsPage extends AppCompatActivity implements GeneralListener {
    private Button dropDown;
    private Button ReserveBtn;
    private RecyclerView reviewsRv;
    private ImageView coverPhoto;
    private TextView movieTitle;
    private TextView movieDuration;
    private TextView movieDate;
    private TextView movieRate;
    private TextView movieTotalReviewsNumber;
    private RatingBar ratingBar;
    private RecyclerView categoryRV;
    private Button makeReviewBtn;
    private ImageView playImage;
    private ImageView shareImage;
    private ImageView backBtn;
    private ImageView closeReviewBtn;
    private Button submitReview;
    private EditText writtenComment;
    private FrameLayout allRevsLayout;
    private FrameLayout makeReviewLayout;


    private ArrayList<Result> allComments;
    private ArrayList<Category> categorList;
    private Recently movieDetails;


    ApiCalling apiCalling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        apiCalling = new ApiCalling(this);
        Intent intent = getIntent();



        dropDown = findViewById(R.id.dropdown_revs);
        ReserveBtn = findViewById(R.id.reserve_btn);
        coverPhoto = findViewById(R.id.cover_photo);
        movieTitle = findViewById(R.id.details_movie_title);
        movieDuration = findViewById(R.id.detail_duration);
        movieDate = findViewById(R.id.detail_movie_date);
        movieRate = findViewById(R.id.avg_movie_rate);
        makeReviewBtn = findViewById(R.id.make_movie_review);
        movieTotalReviewsNumber = findViewById(R.id.reviews_number);
        ratingBar = findViewById(R.id.rating_bar);
        reviewsRv = findViewById(R.id.details_rev_rv);
        categoryRV = findViewById(R.id.category_rv);
        playImage = findViewById(R.id.icon_play_movie);
        shareImage = findViewById(R.id.icon_share_movie);
        backBtn = findViewById(R.id.icon_back_movie);
        makeReviewLayout = findViewById(R.id.frame_make_review);
        closeReviewBtn = findViewById(R.id.close_review_movie);
        submitReview = findViewById(R.id.submit_btn);
        writtenComment = findViewById(R.id.subject_rev_detail);
        allRevsLayout = findViewById(R.id.all_revs_layout);
        reviewsRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        categoryRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        if (intent.getData() != null){
            if (intent.getAction()!= null){
                String action = intent.getAction();
                String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                movieDetails = gson.fromJson(stringData,Recently.class);
                if (action.equals("coming")){
                    ReserveBtn.setVisibility(View.INVISIBLE);
                }
            }
        }

        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(movieDetails.getId());
                //reviews not working, TODO fix this api
//                apiCalling.showAllReviews("4",MovieDetailsPage.this);
         //       allRevsLayout.setVisibility(View.VISIBLE);



            }
        });
        setDetails();
    }

    private void setDetails() {
        Picasso.get()
                .load(movieDetails.getImage())
                .into(coverPhoto);
        movieTitle.setText(movieDetails.getName());
        movieDuration.setText(movieDetails.getPeriod());
        movieDate.setText(movieDetails.getDate());
        String revs = String.valueOf(movieDetails.getReviews());
        movieRate.setText(revs);
        String rates = String.valueOf(movieDetails.getRate());
        movieTotalReviewsNumber.setText(rates);
        categorList = (ArrayList<Category>) movieDetails.getCategory();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,categorList);
        categoryRV.setAdapter(categoryAdapter);
        ratingBar.setRating(movieDetails.getReviews());
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = movieDetails.getYoutube();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, movieDetails.getYoutube());
                startActivity(share);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(MovieDetailsPage.this);
                finish();
            }
        });
        makeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReviewLayout.setVisibility(View.VISIBLE);
            }
        });
        closeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReviewLayout.setVisibility(View.GONE);
            }
        });

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = writtenComment.getText().toString();
                //call make comment and make review
                final int[] rateTotal = new int[1];
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if (rating >= 1) {
                            int rate = (int) rating;
                            rateTotal[0] = rate * 2;
                        }
                    }
                });



            }
        });
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        Comments comments = (Comments) tApiResponse;
        allComments = (ArrayList<Result>) comments.getResult();
        AllReviewsAdapter allReviewsAdapter = new AllReviewsAdapter(this,allComments);
        reviewsRv.setAdapter(allReviewsAdapter);

    }
}
