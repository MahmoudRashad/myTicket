package com.example.myticket.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
        GeneralListener
{


    /////------------------- reference of views -----------------------//
    private EditText userName;
    private EditText password;
    private Button btnLogin;
    private ProgressBar progressBar;
    TextView registerTv;

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


    ///////////////////////////////////////////////////////////////

    SessionManager sessionManager ;
    ApiCalling apiCalling ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        apiCalling = new ApiCalling(this);


        sessionManager = new SessionManager(this)  ;

        userName = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressBar_login);
        registerTv = findViewById(R.id.register);
        forgotText = findViewById(R.id.forgot_text);
        progressBar.setVisibility(View.GONE);
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
                        TextUtils.isEmpty(mUsername)){
                    Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else {

                    loginUser = new User(mUsername, mPassword, deviceToken, deviceType, macAddress);
                    apiCalling.login(mUsername , mPassword,macAddress
                            ,Login.this,
                            "ar");
                }
            }
        });

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });


    }
    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Login");
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


//    @Override
//    public void onSuccess(Object responce)
//    {
//        ModelLogin modelLogin = (ModelLogin) responce;
//        deviceToken = modelLogin.getTokenType()+modelLogin.getAccessToken();
//        progressBar.setVisibility(View.GONE);
//        mPrefEmail = modelLogin.getResult().getEmail();
//
//        mPrefFullname= modelLogin.getResult().getUserName();
//
//        sessionManager.setNameOfUser(modelLogin.getResult().getUserName());
//        sessionManager.setUserId(modelLogin.getResult().getId());
//        sessionManager.setUserImage(modelLogin.getResult().getImage());
//        sessionManager.setUserPhone(modelLogin.getResult().getPhone());
//        sessionManager.setUserAddress(modelLogin.getResult().getAddress());
//        sessionManager.setUserEmail(modelLogin.getResult().getEmail());
//        sessionManager.setUserToken(modelLogin.getAccessToken());
//
//        Intent intent = new Intent(Login.this, MainActivity.class);
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


        if(status == ErrorTypeEnum.noError.getValue())
        {
            MainResponceReg responceReg = (MainResponceReg) tApiResponse;
            progressBar.setVisibility(View.GONE);


            sessionManager.setNameOfUser(responceReg.getResult().getUserName());
            sessionManager.setUserId(responceReg.getResult().getId());
            sessionManager.setUserImage(responceReg.getResult().getImage());
            sessionManager.setUserPhone(responceReg.getResult().getPhone());
            sessionManager.setUserAddress(responceReg.getResult().getAddress());
            sessionManager.setUserEmail(responceReg.getResult().getEmail());
            sessionManager.setUserToken(responceReg.getAccessToken());

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        }
        else
        {
            progressBar.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        }
    }
}
