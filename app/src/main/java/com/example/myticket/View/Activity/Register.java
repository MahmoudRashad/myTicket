package com.example.myticket.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.DataModel.Resgister.UserRegister;
import com.example.myticket.Model.Network.Retrofit.ApiCalling;
import com.example.myticket.Model.Network.Retrofit.GeneralListener;
import com.example.myticket.R;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements
        GeneralListener {

    /////------------------- reference of views -----------------------//
    private EditText fullname;
    private EditText password;
    private EditText repPassword;
    private EditText userName;
    private EditText address;
    private EditText phone;
    private EditText email;
    private Button btnReg;
    private ProgressBar progressBar;
    TextView loginTv;

    ///////////////////////////////////////////////////////////////////

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

    private ImageView backBtn;
    private ImageView searchIcon;
    private TextView toolbarTitle;

    SessionManager sessionManager ;
    private Typeface myfont;
    private Intent intent;
    private Boolean flag;

    private UserRegister userRegister;
    ApiCalling apiCalling;

    private ImageView visiblePass;
    private ImageView visibleRepPass;
    private boolean passShown;
    private boolean passRepShown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setTheme(R.style.AppTheme_green);

        intent = getIntent();

        flag = handleIntent();
        if (flag) changeStatusBarColor();

        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/segoe_ui.ttf");


        apiCalling = new ApiCalling(this);

        sessionManager = new SessionManager(this);
        fullname = findViewById(R.id.fullname_reg);
        fullname.setTypeface(myfont);
        password = findViewById(R.id.password_reg);
        password.setTypeface(myfont);
        repPassword = findViewById(R.id.rep_password_reg);
        repPassword.setTypeface(myfont);
        userName = findViewById(R.id.username_reg);
        userName.setTypeface(myfont);
        address = findViewById(R.id.address_reg);
        address.setTypeface(myfont);
        phone = findViewById(R.id.phone_reg);
        phone.setTypeface(myfont);
        email = findViewById(R.id.email_reg);
        email.setTypeface(myfont);
        btnReg = findViewById(R.id.reg_btn);
        btnReg.setTypeface(myfont);
        loginTv = findViewById(R.id.reg_already);
        loginTv.setTypeface(myfont);
        progressBar = findViewById(R.id.progressBar_reg);
        progressBar.setVisibility(View.GONE);
        visiblePass = findViewById(R.id.visible_pass_reg);
        visibleRepPass = findViewById(R.id.visible_pass_rep_reg);

        setToolbar();

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
                    Toast.makeText(Register.this, getString(R.string.please_fill_all_fields), Toast.LENGTH_LONG).show();
                }
                else if (!mPassword.equals(mRepPassword)){
                    Toast.makeText(Register.this, getString(R.string.make_sure_passwords_match), Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(mEmail)){
                    Toast.makeText(Register.this, getString(R.string.email_not_valid), Toast.LENGTH_LONG).show();
                }
                else {
                    btnReg.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    macAddress = getMacAddress();
                    userRegister = new UserRegister(mFullname, mEmail, mUsername, mPhone, mAddress, deviceToken, mPassword, deviceType, macAddress);

                    Map<String , String> queryMap = new HashMap<>();
                    queryMap.put("user_name" , mFullname);
                    queryMap.put("username" , mUsername);
                    queryMap.put("email" , mEmail);
                    queryMap.put("phone" , mPhone);
                    queryMap.put("address" , mAddress);
                    queryMap.put("device_token" , deviceToken);
                    queryMap.put("password" , mPassword);
                    queryMap.put("device_type" , deviceType);
                    queryMap.put("mac" , macAddress);


                    apiCalling.register(queryMap,Register.this);
                }
            }
        });

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                if (handleIntent()){
                    intent.putExtra("flag","stad");
                    intent.putExtra("name","home");
                }
                startActivity(intent);
            }
        });

        visiblePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passShown = showAndHide(password,visiblePass,passShown);
            }
        });
        visibleRepPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passRepShown = showAndHide(repPassword,visibleRepPass,passRepShown);
            }
        });

    }
    private void setToolbar() {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.register));
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

    private boolean handleIntent() {
        if (intent.hasExtra("flag")) {
            setContentView(R.layout.activity_register_stad);
            return true;
        } else {
            setContentView(R.layout.activity_register);
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



    private String getMacAddress(){
        try {
            //TODO: debug this
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

//    @Override
//    public void onSuccess(Object responce)
//    {
//        MainResponceReg responceReg = (MainResponceReg) responce;
//        progressBar.setVisibility(View.GONE);
////        deviceToken = responceReg.getTokenType()+responceReg.getAccessToken();
////        mPrefEmail = responceReg.getStadDetails().getEmail();
////        mPerfUsername= responceReg.getStadDetails().getUsername();
////        mPrefFullname= responceReg.getStadDetails().getUserName();
//
//        sessionManager.setNameOfUser(responceReg.getStadDetails().getUserName());
//        sessionManager.setUserId(responceReg.getStadDetails().getId());
//        sessionManager.setUserImage(responceReg.getStadDetails().getImage());
//        sessionManager.setUserPhone(responceReg.getStadDetails().getPhone());
//        sessionManager.setUserAddress(responceReg.getStadDetails().getAddress());
//        sessionManager.setUserEmail(responceReg.getStadDetails().getEmail());
//        sessionManager.setUserToken(responceReg.getAccessToken());
//
//        Intent intent = new Intent(Register.this, MainPaymentActivity.class);
//        startActivity(intent);
//
//        Toast.makeText(this,responceReg.getMessage(),Toast.LENGTH_SHORT).show();
//
//    }


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

    private boolean showAndHide(EditText Et, ImageView visiblity, boolean shown) {
        if (shown){
            Et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            visiblity.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_24dp));
            Et.setSelection(Et.getText().length());
            return false;
        }
        else{
            Et.setTransformationMethod(PasswordTransformationMethod.getInstance());
            visiblity.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibilit_24dp));
            Et.setSelection(Et.getText().length());
            return true;
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

            if (handleIntent()) {
                Intent intent = new Intent(Register.this, HomeStadBottomNav.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(Register.this, HomeCinema.class);
                startActivity(intent);
            }



        }
        else
        {
            progressBar.setVisibility(View.GONE);
            btnReg.setVisibility(View.VISIBLE);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        }
    }
}
