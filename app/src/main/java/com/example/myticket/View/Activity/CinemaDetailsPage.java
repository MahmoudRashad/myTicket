package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Comments;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Result;
import com.example.myticket.Model.Network.DataModel.DetailsCinema.DetailsCinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.DetailsMovie.DetailsMovie;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.AllReviewsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CinemaDetailsPage extends AppCompatActivity implements GeneralListener {
    private RecyclerView reviewsRv;
    private Cinema cinemaDetails;
    private ImageView shareBtn;
    private ImageView playCinema;
    private ImageView cinemaCover;
    private TextView nameCinema;
    private TextView detailAddress;
    private TextView detailTime;
    private Button movieListBtn;
    private Button makeReviewBtn;
    private TextView reviewsNumber;
    private TextView avgRating;

    private FrameLayout submittedLayout;
    private FrameLayout makeReviewLayout;

    private ImageView closeReviewBtn;
    private ImageView closeReviewResult;
    private Button submitReview;
    private EditText writtenComment;
    private ConstraintLayout allRevsLayout;
   private TextView submittedText;
   private RatingBar ratingBar;
   private Button dropDownRevs;
   private ImageView closeAllRevs;
    private Toolbar toolbar;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private ProgressBar progressBar;
    private Button cinema_movies_location_btn;

    private ArrayList<Result> allComments;
    private Typeface myfont;

    private ApiCalling apiCalling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_details_page);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        reviewsRv = findViewById(R.id.details_rev_rv);
        shareBtn = findViewById(R.id.icon_share_cinema);
        playCinema = findViewById(R.id.icon_play_cinema);
        playCinema.setVisibility(View.GONE);
        cinemaCover = findViewById(R.id.cover_photo_cinema);
        nameCinema = findViewById(R.id.details_cinema_title);
        nameCinema.setTypeface(myfont);
        detailAddress = findViewById(R.id.detail_address);
        detailAddress.setTypeface(myfont);
        detailTime = findViewById(R.id.detail_time);
        detailTime.setTypeface(myfont);
        avgRating = findViewById(R.id.avg_cinema_rate);
        avgRating.setTypeface(myfont);
        reviewsNumber = findViewById(R.id.reviews_cinema_number);
        reviewsNumber.setTypeface(myfont);
        movieListBtn = findViewById(R.id.cinema_movies_list_btn);
        movieListBtn.setTypeface(myfont);
        makeReviewBtn = findViewById(R.id.make_cinema_review);
        makeReviewBtn.setTypeface(myfont);

        makeReviewLayout = findViewById(R.id.frame_make_review);
        closeReviewBtn = findViewById(R.id.close_review_movie);
        submitReview = findViewById(R.id.submit_btn);
        submitReview.setTypeface(myfont);
        writtenComment = findViewById(R.id.subject_rev_detail);
        writtenComment.setTypeface(myfont);
        allRevsLayout = findViewById(R.id.all_revs_layout);
        submittedText = findViewById(R.id.submitted_text);
        submittedText.setTypeface(myfont);
        submittedLayout = findViewById(R.id.submitted_layout);
        closeReviewResult = findViewById(R.id.close_review_result_movie);
        ratingBar  = findViewById(R.id.rating_bar);
        dropDownRevs = findViewById(R.id.dropdown_revs);
        closeAllRevs = findViewById(R.id.close_All_reviews);
        progressBar = findViewById(R.id.progressBar_cinemaDetails);
        cinema_movies_location_btn = findViewById(R.id.cinema_movies_location_btn);
        cinema_movies_location_btn.setTypeface(myfont);
        TextView ratingTitle = findViewById(R.id.rating_title);
        ratingTitle.setTypeface(myfont);
        TextView total = findViewById(R.id.reviews_number_title);
        total.setTypeface(myfont);
        reviewsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        apiCalling = new ApiCalling(this);

        Intent intent = getIntent();
        if (intent.getData() != null) {
            progressBar.setVisibility(View.GONE);
                String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                cinemaDetails = gson.fromJson(stringData, Cinema.class);
                setDetails();
            setToolbar();
            }
        else if (intent.hasExtra("cinemaID")){
            String id = intent.getStringExtra("cinemaID");
            apiCalling.getCinemaDetails(id,this);
        }


    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(cinemaDetails.getName());
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinemaDetailsPage.this,SearchPage.class);
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
    private void setDetails() {
        Picasso.get()
                .load(cinemaDetails.getImage())
                .into(cinemaCover);
        nameCinema.setText(cinemaDetails.getName());
        detailAddress.setText(cinemaDetails.getAddress());
        String times = "From: "+ cinemaDetails.getOpen() + " To: " + cinemaDetails.getClose();
        detailTime.setText(times);
        String rate = String.valueOf(cinemaDetails.getRate());
        avgRating.setText(rate);
        String reviews = String.valueOf(cinemaDetails.getReviews());
        reviewsNumber.setText(reviews);
        String link = (String) cinemaDetails.getYoutube();
        if (link != null) {
            playCinema.setVisibility(View.VISIBLE);
            shareBtn.setVisibility(View.VISIBLE);
            playCinema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (link != null) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        startActivity(intent);
                    }
                }
            });
            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String link = (String) cinemaDetails.getYoutube();
                    if (link != null) {
                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.putExtra(Intent.EXTRA_TEXT, link);
                        startActivity(share);
                    }
                }
            });
        }


        movieListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinemaDetailsPage.this,AnyResultsPage.class);
                intent.setAction("CinemaMoviesList");
                String id = String.valueOf(cinemaDetails.getId());
                intent.putExtra("cinemaID",id);
                startActivity(intent);
            }
        });
        makeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(CinemaDetailsPage.this);
                //check if he is logged in or not
                final String token = "Bearer "+ sessionManager.getUserToken();
                if (!token.equals("Bearer ")) {
                    makeReviewLayout.setVisibility(View.VISIBLE);
                    final String id = cinemaDetails.getId().toString();
                    submitReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String comment = writtenComment.getText().toString();
                            //call make comment and make review
                            String rate = String.valueOf(ratingBar.getRating()*2);
                            if (!comment.equals("")) {
                                apiCalling.submitComment(token, id, comment, CinemaDetailsPage.this);
                            }
                            if (ratingBar.getRating()>=1) {
                                apiCalling.makeRate(token, id, rate, CinemaDetailsPage.this);
                            }
                        }
                    });

                }
                else {
                    Intent goToLoginIntent = new Intent(CinemaDetailsPage.this,Login.class);
                    startActivity(goToLoginIntent);
                }

            }
        });
        dropDownRevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(cinemaDetails.getId());
                allRevsLayout.setVisibility(View.VISIBLE);
                apiCalling.showAllReviews(id,CinemaDetailsPage.this);
                closeAllRevs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        allRevsLayout.setVisibility(View.GONE);
                    }
                });
            }
        });

        closeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReviewLayout.setVisibility(View.GONE);
            }
        });
        closeReviewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submittedLayout.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        if (tApiResponse instanceof Comments) {
            Comments comments = (Comments) tApiResponse;
            allComments = (ArrayList<Result>) comments.getResult();
            AllReviewsAdapter allReviewsAdapter = new AllReviewsAdapter(this, allComments);
            reviewsRv.setAdapter(allReviewsAdapter);
        }
        else if (tApiResponse instanceof BaseNoResult){
            BaseNoResult userCommentResponce = (BaseNoResult) tApiResponse;
            //Show submitted
            makeReviewLayout.setVisibility(View.GONE);
            submittedLayout.setVisibility(View.VISIBLE);
            submittedText.setText(userCommentResponce.getMessage());

        }
        else if (tApiResponse instanceof DetailsCinema){
            progressBar.setVisibility(View.GONE);
            DetailsCinema Details = (DetailsCinema) tApiResponse;
            cinemaDetails = Details.getResult();
            setDetails();
            setToolbar();
        }
    }
}

