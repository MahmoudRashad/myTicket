package com.example.myticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.helper.Variables;

public class EditAccount extends AppCompatActivity {


    private ApiCalling apiCalling;

    Variables variables;
    //    AppDialog appDialog;
    ProgressDialog pd;
    SessionManager sessionManager;

    //--------------------------------  references of views -------------------------------------------------//
    private ConstraintLayout layout ;
    TextView nameTv , phoneTv,EmailTv,addressTv;
    ImageView userIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        findViewsToReferences();
        setListenerOfViews();

        sessionManager = new SessionManager(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setDataOfViews();
    }

    private void setDataOfViews()
    {
//        try {

        Log.e("test**" , sessionManager.getNameOfUser() );
            nameTv.setText(sessionManager.getNameOfUser());
            phoneTv.setText(sessionManager.getUserPhone());
            EmailTv.setText(sessionManager.getUserEmail());
            addressTv.setText(sessionManager.getUserAddress());

        if (sessionManager.getUserImage() != null && sessionManager.getUserImage() != "")
        {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.my_ticket_white_logo)
                    .error(R.drawable.my_ticket_white_logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH);


            Glide.with(this)
                    .load(sessionManager.getUserImage())
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
                    .into(userIv);
        }

//        }catch (Exception e)
//        {
//
//        }
    }

    public void findViewsToReferences()
    {
//        try {



        layout = findViewById(R.id.container);
        nameTv = findViewById(R.id.name);
        phoneTv = findViewById(R.id.phone);
        EmailTv = findViewById(R.id.email);
        addressTv = findViewById(R.id.address);
        userIv = findViewById(R.id.profile_image);




//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }

    }

    public void setListenerOfViews()
    {
//        try {

//        }
//        catch ( Exception e)
//        {
//            Log.e("exception" , e.getMessage());
//        }
    }
}
