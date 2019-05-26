package com.example.myticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myticket.Model.ForgetPasswordResponce.ForgetPasswordModel;
import com.example.myticket.Model.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.LoginModel.User;
import com.example.myticket.Network.Retrofit.ApiClient;
import com.example.myticket.Network.Retrofit.onResponceInterface;

public class ForgetPassword extends AppCompatActivity implements onResponceInterface {

    private EditText email;
    private Button btnForget;
    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.email_forget);
        btnForget = findViewById(R.id.forget_btn);

        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = email.getText().toString();
                ForgetPasswordModel model = new ForgetPasswordModel(mEmail);
                ApiClient apiClient = new ApiClient(model,ForgetPassword.this,ForgetPassword.this);
                apiClient.initializeClientForget("http://iscoapps.com/cinema/api/");
            }
        });
    }

    @Override
    public void onSuccess(Object responce) {
        ForgetPasswordResponce forgetPasswordResponce = (ForgetPasswordResponce) responce;
        Toast.makeText(this,forgetPasswordResponce.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFail(Object responce) {
        ForgetPasswordResponce forgetPasswordResponce = (ForgetPasswordResponce) responce;
        Toast.makeText(this,forgetPasswordResponce.getMessage(),Toast.LENGTH_LONG).show();
    }
}
