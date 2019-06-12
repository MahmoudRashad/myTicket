package com.example.myticket;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myticket.Model.Network.DataModel.LoginModel.ModelLogin;
import com.example.myticket.Model.Network.DataModel.LoginModel.User;
import com.example.myticket.Model.Network.Retrofit.ApiClient;
import com.example.myticket.Model.Network.Retrofit.onResponceInterface;

public class Login extends AppCompatActivity implements onResponceInterface {
    private EditText userName;
    private EditText password;
    private Button btnLogin;
    private ProgressBar progressBar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressBar_login);
        progressBar.setVisibility(View.GONE);

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
                    ApiClient apiClient = new ApiClient(loginUser, Login.this, Login.this);
                    apiClient.initializeClientLogin();
                }
            }
        });


    }


    @Override
    public void onSuccess(Object responce) {
        ModelLogin modelLogin = (ModelLogin) responce;
        deviceToken = modelLogin.getTokenType()+modelLogin.getAccessToken();
        progressBar.setVisibility(View.GONE);
        mPrefEmail = modelLogin.getResult().getEmail();
        mPerfUsername= modelLogin.getResult().getUsername();
        mPrefFullname= modelLogin.getResult().getUserName();
        Toast.makeText(this,modelLogin.getStatusText() + "Successful Login",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(Object responce) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,"failed to login",Toast.LENGTH_SHORT).show();
    }
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

}
