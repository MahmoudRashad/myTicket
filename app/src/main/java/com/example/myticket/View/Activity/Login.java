package com.example.myticket.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.Network.DataModel.LoginModel.User;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;

public class Login extends AppCompatActivity implements
        GeneralListener {


    /////------------------- reference of views -----------------------//
    private EditText userName;
    private EditText password;
    private Button btnLogin;
    private ProgressBar progressBar;
    TextView registerTv, forgetPasswordTv;

    private String mUsername;
    private String mPassword;
    private String macAddress = "mac";
    private String deviceToken = "987654321";
    private String deviceType = "1";
    private SharedPreferences sharedPreferences;
    private String prefFile = "com.example.android.shared";
    private String TOKEN_KEY = "token";
    private String mPrefEmail;
    private String mPrefFullname;
    private String mPerfUsername;
    private String EMAIL_KEY = "email";
    private String USERNAME_KEY = "username";
    private String FULLNAME_KEY = "fullname";

    private User loginUser;
    private TextView forgotText;

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;
    private Typeface myfont;
    private Intent intent;
    private Boolean flag;

    ///////////////////////////////////////////////////////////////

    SessionManager sessionManager;
    ApiCalling apiCalling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        intent = getIntent();
        flag = handleIntent();
        if (flag) changeStatusBarColor();

        apiCalling = new ApiCalling(this);
        myfont = Typeface.createFromAsset(this.getAssets(), "fonts/segoe_ui.ttf");
        sessionManager = new SessionManager(this);


        userName = findViewById(R.id.username_login);
        userName.setTypeface(myfont);
        password = findViewById(R.id.password_login);
        password.setTypeface(myfont);
        btnLogin = findViewById(R.id.login_btn);
        btnLogin.setTypeface(myfont);
        progressBar = findViewById(R.id.progressBar_login);
        registerTv = findViewById(R.id.register);
        registerTv.setTypeface(myfont);
        forgetPasswordTv = findViewById(R.id.forget_password);
        forgetPasswordTv.setTypeface(myfont);

        setToolbar();

        mUsername = userName.getText().toString();
        mPassword = password.getText().toString();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = userName.getText().toString();
                mPassword = password.getText().toString();
                sharedPreferences = getSharedPreferences(
                        prefFile, MODE_PRIVATE);
                deviceToken = sharedPreferences.getString(TOKEN_KEY, "0");

                if (TextUtils.isEmpty(mPassword) ||
                        TextUtils.isEmpty(mUsername)) {
                    Toast.makeText(Login.this, getString(R.string.please_fill_all_fields), Toast.LENGTH_LONG).show();
                } else {

                    loginUser = new User(mUsername, mPassword, deviceToken, deviceType, macAddress);
                    apiCalling.login(mUsername, mPassword, macAddress
                            , Login.this);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                if (flag)
                intent.putExtra("flag","stad");
                startActivity(intent);
            }
        });


        forgetPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                if (flag)
                    intent.putExtra("flag","stad");
                startActivity(intent);
            }
        });


    }

    private boolean handleIntent() {
        if (intent.hasExtra("flag")) {
            setContentView(R.layout.activity_login_stad);
            return true;
        } else {
            setContentView(R.layout.activity_login);
            return false;
        }
    }
    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
    }


    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.login));
        toolbarTitle.setTypeface(myfont);
        searchIcon = findViewById(R.id.toolbar_Search);
        backBtn = findViewById(R.id.toolbar_back);

        searchIcon.setVisibility(View.GONE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.hasExtra("flag")){
                    handleBack(getString(R.string.error));
                }
            }
        });
    }


//    @Override
//    public void onSuccess(Object responce)
//    {
//        ModelLogin modelLogin = (ModelLogin) responce;
//        deviceToken = modelLogin.getTokenType()+modelLogin.getAccessToken();
//        progressBar.setVisibility(View.GONE);
//        mPrefEmail = modelLogin.getStadDetails().getEmail();
//
//        mPrefFullname= modelLogin.getStadDetails().getUserName();
//
//        sessionManager.setNameOfUser(modelLogin.getStadDetails().getUserName());
//        sessionManager.setUserId(modelLogin.getStadDetails().getId());
//        sessionManager.setUserImage(modelLogin.getStadDetails().getImage());
//        sessionManager.setUserPhone(modelLogin.getStadDetails().getPhone());
//        sessionManager.setUserAddress(modelLogin.getStadDetails().getAddress());
//        sessionManager.setUserEmail(modelLogin.getStadDetails().getEmail());
//        sessionManager.setUserToken(modelLogin.getAccessToken());
//
//        Intent intent = new Intent(Login.this, MainPaymentActivity.class);
//        startActivity(intent);
//
//        Toast.makeText(this,modelLogin.getStatusText() + "Successful Login",Toast.LENGTH_SHORT).show();
//    }

    //    @Override
//    public void onFail(Object responce) {
//        progressBar.setVisibility(View.GONE);
//        Toast.makeText(this,"failed to login",Toast.LENGTH_SHORT).show();
//    }
    @Override
    protected void onPause() {
        super.onPause();
        if (sharedPreferences != null) {
            SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
            preferencesEditor.putString(TOKEN_KEY, deviceToken);
            preferencesEditor.putString(EMAIL_KEY, mPrefEmail);
            preferencesEditor.putString(USERNAME_KEY, mPerfUsername);
            preferencesEditor.putString(FULLNAME_KEY, mPrefFullname);
            preferencesEditor.apply();
        }
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {


        if (status == ErrorTypeEnum.noError.getValue()) {
            MainResponceReg responceReg = (MainResponceReg) tApiResponse;
            progressBar.setVisibility(View.GONE);


            sessionManager.setNameOfUser(responceReg.getResult().getUserName());
            sessionManager.setUserId(responceReg.getResult().getId());
            sessionManager.setUserImage(responceReg.getResult().getImage());
            sessionManager.setUserPhone(responceReg.getResult().getPhone());
            sessionManager.setUserAddress(responceReg.getResult().getAddress());
            sessionManager.setUserEmail(responceReg.getResult().getEmail());
            sessionManager.setUserToken(responceReg.getAccessToken());


            handleBack(responceReg.getMessage());

        }
    }

    private void handleBack(String message) {
        if (flag) {
            if (intent.hasExtra("name")) {
                if (intent.getStringExtra("name").equals("home")) {
                    Intent goBack = new Intent(Login.this, HomeStadBottomNav.class);
                    startActivity(goBack);
                }
                else if (intent.getStringExtra("name").equals("match")){
                    String matchId = intent.getStringExtra("matchId");
                    Intent goBackToMatch = new Intent(Login.this, MatchDetails.class);
                    goBackToMatch.putExtra("matchId",matchId);
                    startActivity(goBackToMatch);
                }
                else if (intent.getStringExtra("name").equals("tickets")){
                    if (!sessionManager.handleLogin().equals("")) {
                        Intent goBack = new Intent(Login.this, HomeStadBottomNav.class);
                        goBack.putExtra("name", "myTickets");
                        startActivity(goBack);
                    }
                    else{
                        Intent goBack = new Intent(Login.this, HomeStadBottomNav.class);
                        startActivity(goBack);
                    }
                }
                else {
                    Intent intent = new Intent(Login.this, HomeCinema.class);
                    startActivity(intent);
                }
            }

        } else {
            if (!message.equals("")) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
