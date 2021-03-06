package com.example.myticket.View.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Enum.TicketsEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.Chairs.ChairResponse2;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.ChairTypeAdapter;
import com.example.myticket.View.Adapter.ChairsAdapter2;
//import com.example.myticket.View.Adapter.RowChairsAdapter;
import com.example.myticket.View.Adapter.TicketsAdapter;

import java.util.HashMap;
import java.util.Map;

public class ChairsActivity extends AppCompatActivity
        implements GeneralListener
{
    ApiCalling apiCalling;
    SessionManager sessionManager;
    ProgressDialog dialog;
    ChairTypeAdapter chairTypeAdapter;
    ChairResponse chairResponse;

    ChairResponse2 chairResponse2;
    ChairsAdapter2 chairsAdapter2Vip,chairsAdapter2Econmy;

    AlertDialog alertDialog;
    private Typeface myfont;

    //--------------------------------  references of views -------------------------------------------------//
    private LinearLayout layout ;
    RecyclerView chairTypeRv , chairRowsRv,
    chairsVipRv, chairsStandardRv;
    Button nextBtn,cinemaPicBtn;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private TextView screen,message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        findViewsToReferences();
        setListenerOfViews();
        setToolbar();

        layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

//        if( getIntent().getExtras() != null )
//        {
//            movieId = getIntent().getExtras().getInt("movie_id" , -1);
//            movieImagePath = getIntent().getExtras().getString("movie_image" , "");
//        }

        sessionManager = new SessionManager(this);
        apiCalling = new ApiCalling(this);


        showWatingDialog();

        Map <String , String> queryMap = new HashMap();
        queryMap.put("cinema_id" , TicketCinemaBusiness.reserveCinemaId+"");
        apiCalling.getChairs("Bearer " +sessionManager.getUserToken()
                ,
                queryMap ,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setDataOfViews();
    }

    private void showAlertDialog(String title , String message)
    {
        AlertDialog.Builder builder1 = new AlertDialog.
                Builder(this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.my_tickets));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairsActivity.this,SearchPage.class);
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
        nextBtn = findViewById(R.id.button4);
        chairTypeRv = findViewById(R.id.chairs_type);
        chairRowsRv=  findViewById(R.id.chairs_RV);
        screen = findViewById(R.id.textView2);
        message = findViewById(R.id.textView3);
        screen.setTypeface(myfont);
        message.setTypeface(myfont);
        nextBtn.setTypeface(myfont);

        chairsVipRv = findViewById(R.id.rv_standard);
        chairsStandardRv = findViewById(R.id.rv_vip);
        cinemaPicBtn = findViewById(R.id.textView2);
        cinemaPicBtn.setTypeface(myfont);

//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }

    }


    private void showWatingDialog()
    {
        dialog = new ProgressDialog(ChairsActivity.this);
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

        cinemaPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChairsActivity.this,
                        CinemaMapActivity.class);
                startActivity(intent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TicketCinemaBusiness.selectedChairsMap.size() > 0)
                {
                    Intent intent = new Intent(ChairsActivity.this,
                            ConfirmTicketsActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ChairsActivity.this,
                            "select any chair to continue" ,
                            Toast.LENGTH_LONG).show();
                }

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
            if( tApiResponse instanceof ChairResponse2)
            {
//                this.chairResponse =
//                        (ChairResponse) tApiResponse;
//
//                showWatingDialog();
//                prepareChairs();
//                drawChairs();

//                if(chairResponse.getStadDetails().getLimitReserve() == 1)
//                {

                chairResponse2 = (ChairResponse2) tApiResponse;

//                prepareChairs2();

                chairsAdapter2Vip  = new ChairsAdapter2( this, chairResponse2.getResult().get(0).getVip() );
                chairsVipRv.setAdapter(chairsAdapter2Vip);

                GridLayoutManager chairTypeLayoutManger =
                        new GridLayoutManager(this,
                                8);

                chairsVipRv.setLayoutManager(chairTypeLayoutManger);
                chairsVipRv.setHasFixedSize(false);
                chairsVipRv.setNestedScrollingEnabled(false);






                chairsAdapter2Econmy  = new ChairsAdapter2( this, chairResponse2.getResult().get(1).getEconomy() );
                chairsStandardRv.setAdapter(chairsAdapter2Econmy);

                GridLayoutManager chairTypeLayoutManger2 =
                        new GridLayoutManager(this,
                                8);

                chairsStandardRv.setLayoutManager(chairTypeLayoutManger2);
                chairsStandardRv.setHasFixedSize(false);
                chairsStandardRv.setNestedScrollingEnabled(false);


//                TicketCinemaBusiness.ticketLimits =
//                        chairResponse.getStadDetails().getLimitReserve();
                    showAlertDialog("Limit Tickets",
                            "Take Care ! your maximum number of tickets equal  "+
                                    TicketCinemaBusiness.ticketLimits +"  Tickets");
//                }


            }

//            Toast.makeText(this , "updated successfully"
//                    , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this , "failed"
                    , Toast.LENGTH_LONG).show();
        }
    }


//    public void drawChairs()
//    {
//        dialog.dismiss();
//
//        TypeChair typeChair = new TypeChair();
//        typeChair.setPrice("0");
//        typeChair.setId(-1);
//        typeChair.setColor("#444444");
//        //TODO: make this a string
//        typeChair.setName("unAvilable");
//        this.chairResponse.getStadDetails().getTypeChair().add(typeChair);
//        chairTypeAdapter = new ChairTypeAdapter(
//                this, this.chairResponse.getStadDetails().getTypeChair() );
//        chairTypeRv.setAdapter(chairTypeAdapter);
//
//
//        LinearLayoutManager chairTypeLayoutManger =
//                new LinearLayoutManager(this,
//                        LinearLayoutManager.HORIZONTAL,false);
//
//        chairTypeRv.setLayoutManager(chairTypeLayoutManger);
//        chairTypeRv.setHasFixedSize(false);
//        chairTypeRv.setNestedScrollingEnabled(false);
//
//
//        //////////////////////////////////
//
//
////                dialog.dismiss();
//
//        RowChairsAdapter rowChairsAdapter = new RowChairsAdapter(
//                this);
//
//
//        LinearLayoutManager chairRowsLayoutManger =
//                new LinearLayoutManager(this,
//                        LinearLayoutManager.VERTICAL,true);
//
//        chairRowsRv.setAdapter(rowChairsAdapter);
//        chairRowsRv.setLayoutManager(chairRowsLayoutManger);
//        chairRowsRv.setHasFixedSize(false);
//        chairRowsRv.setNestedScrollingEnabled(false);
//    }


//    public void prepareChairs()
//    {
//////        List<List<AvaliableChair>> array2DChairs = new ArrayList<>();
//////        for(int i = 0 ; i < 20 ; i++)
//////        {
//////            List<AvaliableChair> tempChairsList =
//////                    new ArrayList<>();
//////
//////            tempChairsList.
//////            for( int o = 0 ; o < 25 ; o++)
//////            {
//////                // ( i*25 + o ) equetion to get chair number .
//////            }
//////        }
//////        array2DChairs.ad
////
////        List<AvaliableChair> mainChair = new ArrayList<>();
////        for ( int i =0 ; i <500 ; i++)
////        {
////
////            boolean isExist = false;
////            for (int in  = 0 ; in < chairResponse.getStadDetails().getAvaliableChair().size();
////            in ++)
////            {
////                if(chairResponse.getStadDetails().getAvaliableChair().get(in).getChairNum().equals(
////                        i+""
////                ))
////                {
////                    mainChair.add(chairResponse.getStadDetails().getAvaliableChair().get(in));
////                    isExist = true;
////                    break;
////                }
////            }
////
////            if(isExist == false)
////            {
////                AvaliableChair avaliableChair = new AvaliableChair();
////                avaliableChair.set
////            }
////        }
//
//        TicketCinemaBusiness.avilableChairsMap = new HashMap();
//        for(int in  = 0 ; in < chairResponse.getStadDetails().getAvaliableChair().size();
//            in ++)
//            {
//                TicketCinemaBusiness.avilableChairsMap.put(
//                        chairResponse.getStadDetails().getAvaliableChair().get(in).getChairNum(),
//                        chairResponse.getStadDetails().getAvaliableChair().get(in)
//                );
//            }
//    }


//    public void prepareChairs2()
//    {
//////        List<List<AvaliableChair>> array2DChairs = new ArrayList<>();
//////        for(int i = 0 ; i < 20 ; i++)
//////        {
//////            List<AvaliableChair> tempChairsList =
//////                    new ArrayList<>();
//////
//////            tempChairsList.
//////            for( int o = 0 ; o < 25 ; o++)
//////            {
//////                // ( i*25 + o ) equetion to get chair number .
//////            }
//////        }
//////        array2DChairs.ad
////
////        List<AvaliableChair> mainChair = new ArrayList<>();
////        for ( int i =0 ; i <500 ; i++)
////        {
////
////            boolean isExist = false;
////            for (int in  = 0 ; in < chairResponse.getStadDetails().getAvaliableChair().size();
////            in ++)
////            {
////                if(chairResponse.getStadDetails().getAvaliableChair().get(in).getChairNum().equals(
////                        i+""
////                ))
////                {
////                    mainChair.add(chairResponse.getStadDetails().getAvaliableChair().get(in));
////                    isExist = true;
////                    break;
////                }
////            }
////
////            if(isExist == false)
////            {
////                AvaliableChair avaliableChair = new AvaliableChair();
////                avaliableChair.set
////            }
////        }
//
//        TicketCinemaBusiness.avilableChairsMap = new HashMap();
//        for(int in  = 0 ;
//            in < chairResponse2.getStadDetails().get(0).getVip().size();
//            in ++)
//        {
//            TicketCinemaBusiness.avilableChairsMap.put(
//                    chairResponse2.getStadDetails().get(0).getVip().get(in).getSymbolChair() +
//                            chairResponse2.getStadDetails().get(0).getVip().get(in).getCharNum(),
//                    chairResponse2.getStadDetails().get(0).getVip().get(in)
//            );
//        }
//
////        for(int in  = 0 ; in < chairResponse2.getStadDetails().getAvaliableChair().size();
////            in ++)
////        {
////            TicketCinemaBusiness.avilableChairsMap.put(
////                    chairResponse2.getStadDetails().getAvaliableChair().get(in).getChairNum(),
////                    chairResponse2.getStadDetails().getAvaliableChair().get(in)
////            );
////        }
//    }
}
