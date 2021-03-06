package com.example.myticket.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;

import static com.example.myticket.View.Activity.Register.isEmailValid;

public class ForgetPassword extends AppCompatActivity implements
        GeneralListener {

    private EditText email;
    private Button btnForget;
    private String mEmail;
    private ProgressBar progressBar;
    ApiCalling apiCalling;

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Typeface myfont;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();
        setTheme(R.style.AppTheme_green);

        intent = getIntent();
        if (intent.hasExtra("flag")) {
            setContentView(R.layout.activity_forget_password_stad);
            changeStatusBarColor();
        }
        else
        setContentView(R.layout.activity_forget_password);


        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");


        apiCalling = new ApiCalling(this);
        setToolbar();

        email = findViewById(R.id.email_forget);
        email.setTypeface(myfont);
        btnForget = findViewById(R.id.forget_btn);
        btnForget.setTypeface(myfont);
        progressBar = findViewById(R.id.progressBar_forget);
        progressBar.setVisibility(View.GONE);


        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = email.getText().toString();

                if ( TextUtils.isEmpty(mEmail)) {
                    Toast.makeText(ForgetPassword.this, getString(R.string.please_fill_all_fields), Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(mEmail)){
                    Toast.makeText(ForgetPassword.this, getString(R.string.email_not_valid), Toast.LENGTH_LONG).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    apiCalling.forgetPasswordCall(mEmail,ForgetPassword.this);
                  //  ForgetPasswordModel model = new ForgetPasswordModel(mEmail);
//                    ApiClient apiClient = new ApiClient(model, ForgetPassword.this, ForgetPassword.this);
//                    apiClient.initializeClientForget();

                }
            }
        });
    }

    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.reset_your_password));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setVisibility(View.GONE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
    }


    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        ForgetPasswordResponce forgetPasswordResponce = (ForgetPasswordResponce) tApiResponse;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,forgetPasswordResponce.getMessage(),Toast.LENGTH_LONG).show();
    }
}
