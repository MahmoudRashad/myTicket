package com.example.myticket.View.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Enum.ReservetypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.ReserveModel.AvaliableChair;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ReserveCinemaResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ResultReserveCinema;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.ChairTypeAdapter;
import com.example.myticket.View.Adapter.CustomSpinnerAdapter;
import com.example.myticket.helper.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChairsActivity extends AppCompatActivity
        implements GeneralListener
{
    private ApiCalling apiCalling;

    Variables variables;
    //    AppDialog appDialog;
    ProgressDialog pd;
    SessionManager sessionManager;

    ProgressDialog dialog;
    ChairTypeAdapter chairTypeAdapter;
    ChairResponse chairResponse;

    //--------------------------------  references of views -------------------------------------------------//
    private ConstraintLayout layout ;
    RecyclerView chairTypeRv;
    Button nextBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs);

        findViewsToReferences();
        setListenerOfViews();

//        if( getIntent().getExtras() != null )
//        {
//            movieId = getIntent().getExtras().getInt("movie_id" , -1);
//            movieImagePath = getIntent().getExtras().getString("movie_image" , "");
//        }

        sessionManager = new SessionManager(this);
        apiCalling = new ApiCalling(this);




        showWatingDialog();

        Map <String , String> queryMap = new HashMap();
        queryMap.put("cinema_id" , ReserveActivity.reserveCinemaId+"");
        apiCalling.getChairs("Bearer " +sessionManager.getUserToken()
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
        nextBtn = findViewById(R.id.button4);
        chairTypeRv = findViewById(R.id.chairs_type);



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
            if( tApiResponse instanceof ChairResponse)
            {
                this.chairResponse =
                        (ChairResponse) tApiResponse;


                chairTypeAdapter = new ChairTypeAdapter(
                        this, this.chairResponse.getResult().getTypeChair() );
                chairTypeRv.setAdapter(chairTypeAdapter);


                LinearLayoutManager chairTypeLayoutManger =
                        new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,true);

                chairTypeRv.setLayoutManager(chairTypeLayoutManger);
                chairTypeRv.setHasFixedSize(false);
                chairTypeRv.setNestedScrollingEnabled(false);

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

//    public void prepareChairs()
//    {
////        List<List<AvaliableChair>> array2DChairs = new ArrayList<>();
////        for(int i = 0 ; i < 20 ; i++)
////        {
////            List<AvaliableChair> tempChairsList =
////                    new ArrayList<>();
////
////            tempChairsList.
////            for( int o = 0 ; o < 25 ; o++)
////            {
////                // ( i*25 + o ) equetion to get chair number .
////            }
////        }
////        array2DChairs.ad
//
//        List<AvaliableChair> mainChair = new ArrayList<>();
//        for ( int i =0 ; i <500 ; i++)
//        {
//
//            boolean isExist = false;
//            for (int in  = 0 ; in < chairResponse.getResult().getAvaliableChair().size();
//            in ++)
//            {
//                if(chairResponse.getResult().getAvaliableChair().get(in).getChairNum().equals(
//                        i+""
//                ))
//                {
//                    mainChair.add(chairResponse.getResult().getAvaliableChair().get(in));
//                    isExist = true;
//                    break;
//                }
//            }
//
//            if(isExist == false)
//            {
//                AvaliableChair avaliableChair = new AvaliableChair();
//                avaliableChair.set
//            }
//        }
//    }

}
