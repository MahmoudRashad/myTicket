package com.example.myticket.View.Activity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myticket.Business.TicketCinemaBusiness;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Enum.TicketsEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.AvaliableChair;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.TypeChair;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;
import com.example.myticket.View.Adapter.ChairTypeAdapter;
import com.example.myticket.View.Adapter.RowChairsAdapter;
import com.example.myticket.View.Adapter.TicketsAdapter;
import com.example.myticket.helper.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmTicketsActivity extends AppCompatActivity
        implements GeneralListener
{
    ApiCalling apiCalling;
    SessionManager sessionManager;
    ProgressDialog dialog;
    TicketsAdapter ticketsAdapter;



    //--------------------------------  references of views -------------------------------------------------//
    private ConstraintLayout layout ;
    RecyclerView ticketsRv ;
    Button payBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        findViewsToReferences();
        setListenerOfViews();

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


    private void setDataOfViews()
    {
//        try {

        List<AvaliableChair> listTemp = new ArrayList<AvaliableChair>(TicketCinemaBusiness.selectedChairsMap.values());

        TicketsAdapter chairTypeAdapter = new TicketsAdapter( TicketsEnum.confirmTickets.getValue()
                , this, listTemp );
        ticketsRv.setAdapter(chairTypeAdapter);


        LinearLayoutManager chairTypeLayoutManger =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,false);

        ticketsRv.setLayoutManager(chairTypeLayoutManger);
        ticketsRv.setHasFixedSize(false);
        ticketsRv.setNestedScrollingEnabled(false);

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
//        if(status == ErrorTypeEnum.noError.getValue())
//        {
//            if( tApiResponse instanceof GeneralApiesponse)
//            {
//                this.chairResponse =
//                        (GeneralApiesponse) tApiResponse;
//
//                Toast.makeText(this , "updated successfully"
//                    , Toast.LENGTH_LONG).show();
//
//            }
//
//
//        }
//        else
//        {
//            Toast.makeText(this , "failed"
//                    , Toast.LENGTH_LONG).show();
//        }
    }


}
