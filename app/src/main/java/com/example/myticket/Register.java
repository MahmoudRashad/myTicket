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

import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.DataModel.Resgister.UserRegister;
import com.example.myticket.Model.Network.Retrofit.ApiClient;
import com.example.myticket.Model.Network.Retrofit.onResponceInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements onResponceInterface {
    private EditText fullname;
    private EditText password;
    private EditText repPassword;
    private EditText userName;
    private EditText address;
    private EditText phone;
    private EditText email;
    private Button btnReg;
    private ProgressBar progressBar;

    private String mFullname;
    private String mPassword;
    private String mRepPassword;
    private String mUsername;
    private String mAddress;
    private String mPhone;
    private String mEmail;

    private String mPrefEmail;
    private String mPrefFullname;
    private String mPerfUsername;

    private String macAddress;
    private String deviceToken;
    private String deviceType = "1";
    private SharedPreferences sharedPreferences;
    private String prefFile = "com.example.android.shared";
    private String TOKEN_KEY = "token";
    private String EMAIL_KEY = "email";
    private String USERNAME_KEY = "username";
    private String FULLNAME_KEY = "fullname";
    private String ID_KEY = "user_id";

    private UserRegister userRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.fullname_reg);
        password = findViewById(R.id.password_reg);
        repPassword = findViewById(R.id.rep_password_reg);
        userName = findViewById(R.id.username_reg);
        address = findViewById(R.id.address_reg);
        phone = findViewById(R.id.phone_reg);
        email = findViewById(R.id.email_reg);
        btnReg = findViewById(R.id.reg_btn);
        progressBar = findViewById(R.id.progressBar_reg);
        progressBar.setVisibility(View.GONE);

        macAddress = getMacAddress();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFullname = fullname.getText().toString();
                mPassword = password.getText().toString();
                mRepPassword = repPassword.getText().toString();
                mUsername = userName.getText().toString();
                mAddress = address.getText().toString();
                mPhone = phone.getText().toString();
                mEmail = email.getText().toString();

                sharedPreferences = getSharedPreferences(
                        prefFile, MODE_PRIVATE);
                deviceToken = sharedPreferences.getString(TOKEN_KEY, "0");

                if (TextUtils.isEmpty(mFullname) || TextUtils.isEmpty(mPassword) || TextUtils.isEmpty(mRepPassword) ||
                        TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mAddress)  ||
                        TextUtils.isEmpty(mPhone) || TextUtils.isEmpty(mEmail)){
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else if (!mPassword.equals(mRepPassword)){
                    Toast.makeText(Register.this, "Please make sure passwords match", Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(mEmail)){
                    Toast.makeText(Register.this, "Email Not Valid", Toast.LENGTH_LONG).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    userRegister = new UserRegister(mFullname, mEmail, mUsername, mPhone, mAddress, deviceToken, mPassword, deviceType, macAddress);
                    ApiClient apiClient = new ApiClient(userRegister, Register.this, Register.this);
                    apiClient.initializeClientRegister("http://iscoapps.com/cinema/api/");
                }
            }
        });

    }

    private String getMacAddress(){
//        try {
//            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface nif : all) {
//                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
//
//                byte[] macBytes = nif.getHardwareAddress();
//                if (macBytes == null) {
//                    return "";
//                }
//
//                StringBuilder res1 = new StringBuilder();
//                for (byte b : macBytes) {
//                    res1.append(String.format("%02X:",b));
//                }
//
//                if (res1.length() > 0) {
//                    res1.deleteCharAt(res1.length() - 1);
//                }
//                return res1.toString();
//            }
//        } catch (Exception ex) {
//        }
        return "02:00:00:00:00:00";
    }

    @Override
    public void onSuccess(Object responce) {
        MainResponceReg responceReg = (MainResponceReg) responce;
        progressBar.setVisibility(View.GONE);
        deviceToken = responceReg.getTokenType()+responceReg.getAccessToken();
        mPrefEmail = responceReg.getResult().getEmail();
        mPerfUsername= responceReg.getResult().getUsername();
        mPrefFullname= responceReg.getResult().getUserName();
        Toast.makeText(this,responceReg.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(Object responce) {
        MainResponceReg responceReg = (MainResponceReg) responce;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,responceReg.getMessage(),Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (sharedPreferences != null) {
            SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
            preferencesEditor.putString(TOKEN_KEY, deviceToken);
            preferencesEditor.putString(EMAIL_KEY, mPrefEmail);
            preferencesEditor.putString(USERNAME_KEY, mPerfUsername);
            preferencesEditor.putString(FULLNAME_KEY,mPrefFullname);
            preferencesEditor.apply();
        }
    }
    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
