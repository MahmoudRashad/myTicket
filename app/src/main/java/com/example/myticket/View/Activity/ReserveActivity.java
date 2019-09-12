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
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.myticket.Model.Network.DataModel.Chairs.HallTypeResult;
import com.example.myticket.Model.Network.DataModel.Chairs.MainHallType;
import com.example.myticket.Model.Network.DataModel.EditUserData.EditUserDataResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ReserveCinemaResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ResultChair;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ResultReserveCinema;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.ChairTypeAdapter2;
//import com.example.myticket.View.Adapter.ChairsAdapter;
import com.example.myticket.View.Adapter.CustomSpinnerAdapter;
import com.example.myticket.helper.Variables;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
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
    ChairResponse chairResponse;
    CustomSpinnerAdapter customSpinnerAdapter,
            dateSpinnerAdapter,timeSpinnerAdapter,typeSpinnerAdapter;

    List<HallTypeResult> hallTypeResult;
    ChairTypeAdapter2 chairTypeAdapter2;

    //--------------------------------  references of views -------------------------------------------------//
    private ConstraintLayout layout ;
//    EditText nameTv , phoneTv,emailTv,addressTv;
    ImageView movieIv ;/*, editImageIv , nameIv,phoneIv,emailIv,addressIv;*/
    Button chairsBtn,cinemaMapBtn;
    Spinner cinemaS , dateS , timeS,typeS;
    TextView hallTv;

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    RecyclerView typesRv;
    private Typeface myfont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");

        findViewsToReferences();
        setListenerOfViews();
        setToolbar();

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


//        Glide.with(this)
//                .load(movieImagePath)
////                        .error(R.drawable.arrow_back)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.e("Glide erorr**", "failed to load image");
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .apply(options)
////                    .diskCacheStrategy(DiskCacheStrategy.NONE)
////                    .skipMemoryCache(true)
//                .into(movieIv);

        showWatingDialog();
        type = ReservetypeEnum.cinema.getValue();
        Map <String , String> queryMap = new HashMap();
        queryMap.put("film_id" , TicketCinemaBusiness.movieId+"");
        apiCalling.getCinemasOfMovie("Bearer " +sessionManager.getUserToken()
                 ,
                queryMap ,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setDataOfViews();
    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.reservation));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveActivity.this,SearchPage.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.e("test**" , "finish");
                finish();
            }
        });
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
        try {



        layout = findViewById(R.id.container);
        chairsBtn = findViewById(R.id.btn_select_chairs);
        cinemaS = findViewById(R.id.spinner_cinema);
        hallTv = findViewById(R.id.hall_name);
        hallTv.setTypeface(myfont);
        dateS = findViewById(R.id.spinner_date);
        timeS = findViewById(R.id.spinner_time);
        typeS = findViewById(R.id.spinner_ticket_type);
        movieIv = findViewById(R.id.cover_photo);


        TextView textView = findViewById(R.id.details_cinema_title);
        textView.setTypeface(myfont);

//        cinemaMapBtn = findViewById(R.id.button9);
//        cinemaMapBtn.setTypeface(myfont);
//        typesRv = findViewById(R.id.recyclerView);
        String reserveCinema ,
                reserveDate , reserveTime ;


        }
        catch ( Exception e)
        {
            Log.e("exception" , e.getMessage());
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

//        cinemaMapBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ReserveActivity.this,
//                        CinemaMapActivity.class);
//                startActivity(intent);
//            }
//        });

        cinemaS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                hallTv.setText(reserveCinemaResponse.getResult().get(position).getHall());


                TicketCinemaBusiness.hallName =
                        reserveCinemaResponse.getResult().get(position).getHall();

                TicketCinemaBusiness.reserveCinemaId =
                        reserveCinemaResponse.getResult().get(position).getId();

                TicketCinemaBusiness.reserveCinema =
                        reserveCinemaResponse.getResult().get(position).getName();

                TicketCinemaBusiness.cinemaLocation =
                        reserveCinemaResponse.getResult().get(position).getAddress();

                if( TicketCinemaBusiness.reserveCinemaId != -1)
                {
                    showWatingDialog();
                    type = ReservetypeEnum.date.getValue();
                    Map <String , String> queryMap = new HashMap();
                    queryMap.put("cinema_id" , TicketCinemaBusiness.reserveCinemaId+"");
                    apiCalling.getDatesOfMovie("Bearer " +sessionManager.getUserToken()
                            ,
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
                    apiCalling.getTimesOfMovie("Bearer " +sessionManager.getUserToken(),
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


                if( TicketCinemaBusiness.reserveTimeId != -1)
                {
                    showWatingDialog();
                    type = ReservetypeEnum.type.getValue();
                    Map <String , String> queryMap = new HashMap();
                    queryMap.put("cinema_id" , TicketCinemaBusiness.reserveCinemaId+"");
                    queryMap.put("hall_name",TicketCinemaBusiness.hallName);
                    apiCalling.getTypeOfHall("Bearer " +sessionManager.getUserToken(),
                            queryMap ,ReserveActivity.this);
                }

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

        typeS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TicketCinemaBusiness.typeName = hallTypeResult.get(position).getName();
                Intent intent = new Intent(ReserveActivity.this,CinemaChairsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
//        chairsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TicketCinemaBusiness.reserveTimeId!=-1&&
//                        TicketCinemaBusiness.reserveCinemaId!=-1&&
//                        TicketCinemaBusiness.reserveDateId!=-1)
//                {
//                    Intent intent = new Intent(ReserveActivity.this ,
//                            ChairsActivity.class);
//                    startActivity(intent);
//                }
////                else {
////                    Toast.makeText(ReserveActivity.this,
////                            "please select all fields." ,
////                            Toast.LENGTH_LONG).show();
////                }
//            }
//        });



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
                //TODO: make string
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
                //TODO: make string
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
                //TODO: make String
                resultReserveCinema.setName("select Time");
                resultReserveCinema.setId(-1);
                this.reserveTimeResponse.getResult().add(0,resultReserveCinema);
                timeSpinnerAdapter = new CustomSpinnerAdapter(
                        this, this.reserveTimeResponse.getResult() );
                timeS.setAdapter(timeSpinnerAdapter);

            }

            else if( type == ReservetypeEnum.type.getValue())
            {
                MainHallType mainHallType = (MainHallType) tApiResponse;
                hallTypeResult = mainHallType.getResult();

                dateSpinnerAdapter = new CustomSpinnerAdapter(
                        this, this.reserveDateResponse.getResult() );
                dateS.setAdapter(dateSpinnerAdapter);

                typeSpinnerAdapter = new CustomSpinnerAdapter(this,hallTypeResult,false);
                typeS.setAdapter(typeSpinnerAdapter);



//
//
//                chairTypeAdapter2 = new ChairTypeAdapter2(
//                        this, this.chairResponse.getResult().getTypeChair() );
//                typesRv.setAdapter(chairTypeAdapter2);
//
//
//                LinearLayoutManager chairTypeLayoutManger =
//                        new LinearLayoutManager(this,
//                                LinearLayoutManager.VERTICAL,false);
//
//                typesRv.setLayoutManager(chairTypeLayoutManger);
//                typesRv.setHasFixedSize(false);
//                typesRv.setNestedScrollingEnabled(false);

            }

//            Toast.makeText(this , "updated successfully"
//                    , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this , getString(R.string.failed_to_update)
                    , Toast.LENGTH_LONG).show();
        }
    }
}
