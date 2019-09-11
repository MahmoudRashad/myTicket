package com.example.myticket.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myticket.R;

public class Gate extends AppCompatActivity {
    ConstraintLayout layout;
    ConstraintLayout layoutStadium;
    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Typeface myfont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);
        TextView cinema = findViewById(R.id.cinema_text);
        TextView stadium = findViewById(R.id.stadium_text);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");
        cinema.setTypeface(myfont);
        stadium.setTypeface(myfont);
        layout = findViewById(R.id.cinema_gate);
        layoutStadium = findViewById(R.id.stadium_gate);
        setToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    private void start(){
        if (checkInternetConnection()) {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Gate.this, HomeCinema.class);
                    startActivity(intent);
                }
            });
            layoutStadium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Gate.this, HomeStadBottomNav.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.select_your_gate));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);
        backBtn.setVisibility(View.GONE);
        searchIcon.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    private boolean checkInternetConnection () {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.dialog_message))
                    .setTitle(getResources().getString(R.string.dialog_title));
            builder.setCancelable(false);
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    start();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);

            if (b != null) {
                b.setTextColor(getResources().getColor(R.color.white));
            }
        }
        return false;
    }

}
