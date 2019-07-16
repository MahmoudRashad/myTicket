package com.example.myticket.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Enum.TicketsEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.Chairs.Chair;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.AvaliableChair;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.TicketsAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfirmTicketsActivity extends AppCompatActivity
        implements GeneralListener
{
    ApiCalling apiCalling;
    SessionManager sessionManager;
    ProgressDialog dialog;
    TicketsAdapter ticketsAdapter;
    private Typeface myfont;
    List<Chair> listTemp;
    List<ResultTickets> resultTicketsList;



    //--------------------------------  references of views -------------------------------------------------//
    private NestedScrollView layout ;
    RecyclerView ticketsRv ;
    Button payBtn;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        findViewsToReferences();
        setListenerOfViews();
        setToolbar();
//        if( getIntent().getExtras() != null )
//        {
//            movieId = getIntent().getExtras().getInt("movie_id" , -1);
//            movieImagePath = getIntent().getExtras().getString("movie_image" , "");
//        }

        sessionManager = new SessionManager(this);
        apiCalling = new ApiCalling(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setDataOfViews();
    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.confirm_payment));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmTicketsActivity.this,SearchPage.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showWatingDialog();

                makeList();

                apiCalling.confirmReservation("Bearer " +sessionManager.getUserToken(),
                        "ar",
                        resultTicketsList ,ConfirmTicketsActivity.this);

            }
        });
    }


    private void makeList()
    {
        resultTicketsList = new ArrayList<>();
        for(int i =0;i<listTemp.size();i++)
        {
            ResultTickets resultTickets = new ResultTickets();
            resultTickets.setCinemaName(TicketCinemaBusiness.reserveCinema);

            resultTickets.setCinemaId(TicketCinemaBusiness.reserveCinemaId+"");
            resultTickets.setFilmId(TicketCinemaBusiness.movieId+"");
            resultTickets.setMovieName(TicketCinemaBusiness.movieName);
            resultTickets.setChairNum(listTemp.get(i).getSymbolChair()+
                    listTemp.get(i).getCharNum());
            resultTickets.setChairType(listTemp.get(i).getType());
            resultTickets.setCinemaLocation(TicketCinemaBusiness.cinemaLocation);
            resultTickets.setHallName(TicketCinemaBusiness.hallName);
            resultTickets.setDate(TicketCinemaBusiness.reserveDate);
            resultTickets.setTime(TicketCinemaBusiness.reserveTime);
            resultTickets.setQrCode(generateRandomNumber());


            resultTicketsList.add(resultTickets);

        }
    }

    private String generateRandomNumber(){
        Random r = new Random();
        int random = r.nextInt(10000000 - 1) + 1;
        String randomString = String.valueOf(random);
        return randomString;
    }


    private void setDataOfViews()
    {
//        try {

        listTemp = new ArrayList<Chair>(TicketCinemaBusiness.selectedChairsMap.values());

        TicketsAdapter chairTypeAdapter = new TicketsAdapter( TicketsEnum.confirmTickets.getValue()
                , this, listTemp );
        ticketsRv.setAdapter(chairTypeAdapter);


        LinearLayoutManager chairTypeLayoutManger =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,false);

        ticketsRv.setLayoutManager(chairTypeLayoutManger);
        ticketsRv.setHasFixedSize(false);
        ticketsRv.setNestedScrollingEnabled(false);

        payBtn.setText("Pay " + TicketCinemaBusiness.totalPrice + " SR");

//        }catch (Exception e)
//        {
//
//        }
    }

    public void findViewsToReferences()
    {
//        try {


        layout = findViewById(R.id.container);
        payBtn = findViewById(R.id.button5);
        ticketsRv = findViewById(R.id.chairs_type);
        payBtn.setTypeface(myfont);

//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }

    }


    private void showWatingDialog()
    {
        dialog = new ProgressDialog(ConfirmTicketsActivity.this);
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
            if( tApiResponse instanceof GeneralApiesponse)
            {
                GeneralApiesponse generalApiesponse =
                        (GeneralApiesponse)tApiResponse;
                Toast.makeText(this , generalApiesponse.getMessage()
                    , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ConfirmTicketsActivity.this,
                        HomeCinema.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        else
        {
            Toast.makeText(this , "failed"
                    , Toast.LENGTH_LONG).show();
        }
    }

}
