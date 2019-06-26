package com.example.myticket.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Enum.ReservetypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.EditUserData.EditUserDataResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ReserveCinemaResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ResultReserveCinema;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.CustomSpinnerAdapter;
import com.example.myticket.helper.Variables;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReserveActivity extends AppCompatActivity
        implements GeneralListener {


    private ApiCalling apiCalling;

    Variables variables;
    //    AppDialog appDialog;
    ProgressDialog pd;
    SessionManager sessionManager;
    public int cameraRequest = 0 , galleryRequest = 1 ;
    Dialog  dialogChangePic ;
    ProgressDialog dialog;

    int type;


    String movieImagePath;
    ReserveCinemaResponse reserveCinemaResponse,
            reserveDateResponse,
            reserveTimeResponse;
    CustomSpinnerAdapter customSpinnerAdapter,
            dateSpinnerAdapter,timeSpinnerAdapter;

    //--------------------------------  references of views -------------------------------------------------//
    private ConstraintLayout layout ;
//    EditText nameTv , phoneTv,emailTv,addressTv;
    ImageView movieIv ;/*, editImageIv , nameIv,phoneIv,emailIv,addressIv;*/
    Button nextBtn;
    Spinner cinemaS , dateS , timeS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        findViewsToReferences();
        setListenerOfViews();

        if( getIntent().getExtras() != null )
        {
            TicketCinemaBusiness.movieId = getIntent().getExtras().getInt("movie_id" , -1);
            movieImagePath = getIntent().getExtras().getString("movie_image" , "");
        }

        sessionManager = new SessionManager(this);
        apiCalling = new ApiCalling(this);


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.my_ticket_white_logo)
                .error(R.drawable.my_ticket_white_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        Glide.with(this)
                .load(movieImagePath)
//                        .error(R.drawable.arrow_back)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide erorr**", "failed to load image");

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(options)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                .into(movieIv);

        showWatingDialog();
        type = ReservetypeEnum.cinema.getValue();
        Map <String , String> queryMap = new HashMap();
        queryMap.put("film_id" , TicketCinemaBusiness.movieId+"");
        apiCalling.getCinemasOfMovie("Bearer " +sessionManager.getUserToken()
                , "ar" ,
                queryMap ,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setDataOfViews();
    }


//    private void setDataOfViews()
//    {
////        try {
//
//        Log.e("test**" , sessionManager.getNameOfUser() );
//            nameTv.setText(sessionManager.getNameOfUser());
//            phoneTv.setText(sessionManager.getUserPhone());
//        emailTv.setText(sessionManager.getUserEmail());
//            addressTv.setText(sessionManager.getUserAddress());
//
//        if (sessionManager.getUserImage() != null && sessionManager.getUserImage() != "")
//        {
//
//            RequestOptions options = new RequestOptions()
//                    .centerCrop()
//                    .placeholder(R.drawable.my_ticket_white_logo)
//                    .error(R.drawable.my_ticket_white_logo)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .priority(Priority.HIGH);
//
//
//            Glide.with(this)
//                    .load(sessionManager.getUserImage())
////                        .error(R.drawable.arrow_back)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            Log.e("Glide erorr**", "failed to load image");
//
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            return false;
//                        }
//                    })
//                    .apply(options)
////                    .diskCacheStrategy(DiskCacheStrategy.NONE)
////                    .skipMemoryCache(true)
//                    .into(userIv);
//        }
//
////        }catch (Exception e)
////        {
////
////        }
//    }

    public void findViewsToReferences()
    {
//        try {



        layout = findViewById(R.id.container);
//        nameTv = findViewById(R.id.name);
//        phoneTv = findViewById(R.id.phone);
//        emailTv = findViewById(R.id.email);
//        addressTv = findViewById(R.id.address);
//        userIv = findViewById(R.id.profile_image);
//        editImageIv = findViewById(R.id.profile_pen);
//        nameIv = findViewById(R.id.arrowOne);
//        phoneIv= findViewById(R.id.arrowTwo);
//        emailIv= findViewById(R.id.arrowThree);
//        addressIv = findViewById(R.id.arrowFour);
        nextBtn = findViewById(R.id.button3);
        cinemaS = findViewById(R.id.spinner);
        dateS = findViewById(R.id.spinner2);
        timeS = findViewById(R.id.spinner3);
        movieIv = findViewById(R.id.cover_photo);

        String reserveCinema ,
                reserveDate , reserveTime ;


//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }

    }


    private void showWatingDialog()
    {
        dialog = new ProgressDialog(ReserveActivity.this);
        String message = getString(R.string.waiting);
        SpannableString spannableString =  new SpannableString(message);

//                            Typeface typeMed = Typeface.createFromAsset(getAssets(),"montserrat_alternates_medium.otf");
//        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getAssets(),"montserrat_alternates_medium.otf"));
//        spannableString.setSpan(typefaceSpan, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        dialog.setMessage(spannableString);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
    }


    public void setListenerOfViews()
    {
//        try {

        cinemaS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                TicketCinemaBusiness.reserveCinemaId =
                        reserveCinemaResponse.getResult().get(position).getId();

                TicketCinemaBusiness.reserveCinema =
                        reserveCinemaResponse.getResult().get(position).getName();

                if( TicketCinemaBusiness.reserveCinemaId != -1)
                {
                    showWatingDialog();
                    type = ReservetypeEnum.date.getValue();
                    Map <String , String> queryMap = new HashMap();
                    queryMap.put("cinema_id" , TicketCinemaBusiness.reserveCinemaId+"");
                    apiCalling.getDatesOfMovie("Bearer " +sessionManager.getUserToken()
                            , "ar" ,
                            queryMap ,ReserveActivity.this);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        dateS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                TicketCinemaBusiness.reserveDateId =
                        reserveDateResponse.getResult().get(position).getId();

                TicketCinemaBusiness.reserveDate =
                        reserveDateResponse.getResult().get(position).getName();

                if( TicketCinemaBusiness.reserveDateId != -1)
                {
                    showWatingDialog();
                    type = ReservetypeEnum.time.getValue();
                    Map <String , String> queryMap = new HashMap();
                    queryMap.put("day_id" , TicketCinemaBusiness.reserveDateId+"");
                    apiCalling.getTimesOfMovie("Bearer " +sessionManager.getUserToken()
                            , "ar" ,
                            queryMap ,ReserveActivity.this);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        timeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                TicketCinemaBusiness.reserveTimeId =
                        reserveTimeResponse.getResult().get(position).getId();

                TicketCinemaBusiness.reserveTime =
                        reserveTimeResponse.getResult().get(position).getName();

//                if( reserveDateId != -1)
//                {
//                    showWatingDialog();
//                    type = ReservetypeEnum.time.getValue();
//                    Map <String , String> queryMap = new HashMap();
//                    queryMap.put("day_id" , reserveDateId+"");
//                    apiCalling.getTimesOfMovie("Bearer " +sessionManager.getUserToken()
//                            , "ar" ,
//                            queryMap ,ReserveActivity.this);
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveActivity.this ,
                        ChairsActivity.class);
                startActivity(intent);
            }
        });



//        saveEditBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (TextUtils.isEmpty(nameTv.getText()) ||
//                        TextUtils.isEmpty(phoneTv.getText()) ||
//                        TextUtils.isEmpty(emailTv.getText()) ||
//                        TextUtils.isEmpty(addressTv.getText() ))
//                {
//                    Toast.makeText(ReserveActivity.this
//                            , "Please fill all fields"
//                            , Toast.LENGTH_LONG).show();
//                }
//
//                else if (!isEmailValid(emailTv.getText().toString())){
//                    Toast.makeText(ReserveActivity.this
//                            , "Email Not Valid",
//                            Toast.LENGTH_LONG).show();
//                }
//                else {
//                    showWatingDialog();
//
//                    Map<String , String> queryMap = new HashMap<>();
//                    queryMap.put("name" , nameTv.getText().toString());
//                    queryMap.put("phone" , phoneTv.getText().toString());
//                    queryMap.put("email" , emailTv.getText().toString());
//                    queryMap.put("address" , addressTv.getText().toString());
//
//                    apiCalling.editUserData("Bearer " +sessionManager.getUserToken()
//                            , "ar" ,
//                            queryMap , ReserveActivity.this );
//                }
//
//            }
//        });




//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        dialog.dismiss();
        if(status == ErrorTypeEnum.noError.getValue())
        {
            if( type == ReservetypeEnum.cinema.getValue())
            {
                this.reserveCinemaResponse =
                        (ReserveCinemaResponse) tApiResponse;

                ResultReserveCinema resultReserveCinema =
                        new ResultReserveCinema();
                resultReserveCinema.setName("select cinema");
                resultReserveCinema.setId(-1);
                this.reserveCinemaResponse.getResult().add(0,resultReserveCinema);
                customSpinnerAdapter = new CustomSpinnerAdapter(
                        this, this.reserveCinemaResponse.getResult() );
                cinemaS.setAdapter(customSpinnerAdapter);

            }
            else if( type == ReservetypeEnum.date.getValue())
            {
                this.reserveDateResponse =
                        (ReserveCinemaResponse) tApiResponse;

                ResultReserveCinema resultReserveCinema =
                        new ResultReserveCinema();
                resultReserveCinema.setName("select Date");
                resultReserveCinema.setId(-1);
                this.reserveDateResponse.getResult().add(0,resultReserveCinema);
                dateSpinnerAdapter = new CustomSpinnerAdapter(
                        this, this.reserveDateResponse.getResult() );
                dateS.setAdapter(dateSpinnerAdapter);

            }

            else if( type == ReservetypeEnum.time.getValue())
            {
                this.reserveTimeResponse =
                        (ReserveCinemaResponse) tApiResponse;

                ResultReserveCinema resultReserveCinema =
                        new ResultReserveCinema();
                resultReserveCinema.setName("select Time");
                resultReserveCinema.setId(-1);
                this.reserveTimeResponse.getResult().add(0,resultReserveCinema);
                timeSpinnerAdapter = new CustomSpinnerAdapter(
                        this, this.reserveTimeResponse.getResult() );
                timeS.setAdapter(timeSpinnerAdapter);

            }

//            Toast.makeText(this , "updated successfully"
//                    , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this , "failed updated"
                    , Toast.LENGTH_LONG).show();
        }
    }
}
