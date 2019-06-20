package com.example.myticket.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;

import static com.example.myticket.View.Activity.Register.isEmailValid;

public class ForgetPassword extends AppCompatActivity implements GeneralListener {

    private EditText email;
    private Button btnForget;
    private String mEmail;
    private ProgressBar progressBar;
    ApiCalling apiCalling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password);

        apiCalling = new ApiCalling(this);

        email = findViewById(R.id.email_forget);
        btnForget = findViewById(R.id.forget_btn);
        progressBar = findViewById(R.id.progressBar_forget);
        progressBar.setVisibility(View.GONE);


        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = email.getText().toString();

                if ( TextUtils.isEmpty(mEmail)) {
                    Toast.makeText(ForgetPassword.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(mEmail)){
                    Toast.makeText(ForgetPassword.this, "Email Not Valid", Toast.LENGTH_LONG).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    apiCalling.forgetPasswordCall("en",mEmail,ForgetPassword.this);
                  //  ForgetPasswordModel model = new ForgetPasswordModel(mEmail);
//                    ApiClient apiClient = new ApiClient(model, ForgetPassword.this, ForgetPassword.this);
//                    apiClient.initializeClientForget();

                }
            }
        });
    }

    @Override
    public void getApiResponse(int status, String message, Object tApiResponse) {
        ForgetPasswordResponce forgetPasswordResponce = (ForgetPasswordResponce) tApiResponse;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,forgetPasswordResponce.getMessage(),Toast.LENGTH_LONG).show();
    }
}