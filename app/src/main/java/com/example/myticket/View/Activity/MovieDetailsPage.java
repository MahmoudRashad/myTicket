package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Comments;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Result;
import com.example.myticket.Model.Network.DataModel.HomeResult.Category;
import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.example.myticket.Model.Network.DetailsMovie.DetailsMovie;
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
    private TextView submittedText;
    private RatingBar ratingBar;
    private RecyclerView categoryRV;
    private Button makeReviewBtn;
    private ImageView playImage;
    private ImageView shareImage;
    private ImageView closeReviewBtn;
    private ImageView closeReviewResult;
    private Button submitReview;
    private EditText writtenComment;
    private ConstraintLayout allRevsLayout;
    private FrameLayout submittedLayout;
    private FrameLayout makeReviewLayout;
    private ImageView closeAllRevs;

    private ArrayList<Result> allComments;
    private ArrayList<Category> categorList;
    private Recently movieDetails;

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;

    private ProgressBar progressBar;
    private Typeface myfont;

    ApiCalling apiCalling;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        apiCalling = new ApiCalling(this);
        Intent intent = getIntent();
        sessionManager= new SessionManager(this);

        dropDown = findViewById(R.id.dropdown_revs);
        ReserveBtn = findViewById(R.id.reserve_btn);
        ReserveBtn.setTypeface(myfont);
        coverPhoto = findViewById(R.id.cover_photo);
        movieTitle = findViewById(R.id.details_movie_title);
        movieTitle.setTypeface(myfont);
        movieDuration = findViewById(R.id.detail_duration);
        movieDuration.setTypeface(myfont);
        movieDate = findViewById(R.id.detail_movie_date);
        movieDate.setTypeface(myfont);
        movieRate = findViewById(R.id.avg_movie_rate);
        movieRate.setTypeface(myfont);
        makeReviewBtn = findViewById(R.id.make_movie_review);
        makeReviewBtn.setTypeface(myfont);
        movieTotalReviewsNumber = findViewById(R.id.reviews_number);
        movieTotalReviewsNumber.setTypeface(myfont);
        ratingBar = findViewById(R.id.rating_bar);
        reviewsRv = findViewById(R.id.details_rev_rv);
        categoryRV = findViewById(R.id.category_rv);
        playImage = findViewById(R.id.icon_play_movie);
        playImage.setVisibility(View.GONE);
        shareImage = findViewById(R.id.icon_share_movie);
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
        closeAllRevs = findViewById(R.id.close_All_reviews);
        progressBar = findViewById(R.id.progressBar_movieDetails);
        TextView categoryTitle = findViewById(R.id.category_title);
        categoryTitle.setTypeface(myfont);
        TextView ratingTitle = findViewById(R.id.rating_title);
        ratingTitle.setTypeface(myfont);
        TextView total = findViewById(R.id.reviews_number_title);
        total.setTypeface(myfont);

        reviewsRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        categoryRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        if (intent.getData() != null){
            if (intent.getAction()!= null){
                progressBar.setVisibility(View.GONE);
                playImage.setVisibility(View.VISIBLE);
                String action = intent.getAction();
                String stringData = String.valueOf(intent.getData().getSchemeSpecificPart());
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                movieDetails = gson.fromJson(stringData,Recently.class);
                if (action.equals("coming")){
                    ReserveBtn.setVisibility(View.INVISIBLE);
                }
                setDetails();
                setToolbar();
            }
        }

        if (intent.hasExtra("id")){
            String id = intent.getStringExtra("id");
            apiCalling.getMovieDetails(id,this);
        }





    }

    private void setToolbar() {

        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(movieDetails.getName());
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailsPage.this,SearchPage.class);
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
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,categorList,null,null,null, null);
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

        makeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(MovieDetailsPage.this);
                //check if he is logged in or not
                final String token = "Bearer "+ sessionManager.getUserToken();
                if (!token.equals("Bearer ")) {
                    makeReviewLayout.setVisibility(View.VISIBLE);
                    final String id = movieDetails.getId().toString();
                    submitReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String comment = writtenComment.getText().toString();
                            //call make comment and make review
                            String rate = String.valueOf(ratingBar.getRating()*2);

                            String lang = sessionManager.getDeviceLanguage();
                            if (!comment.equals("")) {
                                apiCalling.submitComment(token, lang, id, comment, MovieDetailsPage.this);
                            }
                            if (ratingBar.getRating()>=1) {
                                apiCalling.makeRate(token, lang, id, rate, MovieDetailsPage.this);
                            }
                        }
                    });

                }
                else {
                    Intent goToLoginIntent = new Intent(MovieDetailsPage.this,Login.class);
                    startActivity(goToLoginIntent);
                }

            }
        });

        dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(movieDetails.getId());
                allRevsLayout.setVisibility(View.VISIBLE);
                apiCalling.showAllReviews(id,MovieDetailsPage.this);
                closeAllRevs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        allRevsLayout.setVisibility(View.GONE);
                    }
                });

            }
        });

        ReserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionManager.getUserToken() == null ||
                        sessionManager.getUserToken().equals(""))
                {
                    Intent intent1 = new Intent(
                            MovieDetailsPage.this, Login.class
                    );
                    startActivity(intent1);
                }
                else
                {
                    TicketCinemaBusiness.movieId = movieDetails.getId();
                    TicketCinemaBusiness.movieName = movieDetails.getName();

                    Intent intent1 = new Intent(
                            MovieDetailsPage.this, ReserveActivity.class
                    );
                    intent1.putExtra("movie_id" , movieDetails.getId());
                    intent1.putExtra("movie_image" , movieDetails.getImage());
                    startActivity(intent1);
                }

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
        else if (tApiResponse instanceof DetailsMovie){
            progressBar.setVisibility(View.GONE);
            playImage.setVisibility(View.VISIBLE);
            DetailsMovie Details = (DetailsMovie) tApiResponse;
            movieDetails = Details.getResult();
            setDetails();
            setToolbar();
        }
    }
}
