package com.example.myticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        findViewsToReferences();
        setListenerOfViews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setDataOfViews();
    }

    private void setDataOfViews()
    {
        try {

            nameTv.setText(sessionManager.getNameOfUser());
            phoneTv.setText(sessionManager.getUserPhone());
            EmailTv.setText(sessionManager.getUserEmail());
            addressTv.setText(sessionManager.getUserAddress());
        }catch (Exception e)
        {

        }
    }

    public void findViewsToReferences()
    {
//        try {



        layout = findViewById(R.id.container);
        nameTv = findViewById(R.id.name);
        phoneTv = findViewById(R.id.phone);
        EmailTv = findViewById(R.id.email);
        addressTv = findViewById(R.id.address);




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
