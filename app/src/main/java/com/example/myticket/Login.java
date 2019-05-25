package com.example.myticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myticket.Model.LoginModel.ModelLogin;
import com.example.myticket.Model.LoginModel.User;
import com.example.myticket.Network.Retrofit.ApiClient;
import com.example.myticket.Network.Retrofit.onResponceInterface;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity implements onResponceInterface {
    private EditText userName;
    private EditText password;
    private Button btnLogin;

    private String mUsername;
    private String mPassword;
    private String macAddress = "mac";
    private String deviceToken = "987654321";
    private String deviceType = "1";

    private User loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.login_btn);

        mUsername = userName.getText().toString();
        mPassword = password.getText().toString();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = userName.getText().toString();
                mPassword = password.getText().toString();
                loginUser = new User(mUsername,mPassword,deviceToken,deviceType,macAddress);
                ApiClient apiClient = new ApiClient(loginUser,Login.this);
                apiClient.initializeClientLogin("http://iscoapps.com/cinema/api/");
            }
        });


    }


    @Override
    public void onSuccess(Object responce) {
        ModelLogin modelLogin = (ModelLogin) responce;
        Toast.makeText(this,modelLogin.getStatusText() + "Successful Login",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(Object responce) {
        Toast.makeText(this,"failed to login",Toast.LENGTH_SHORT).show();
    }
}
