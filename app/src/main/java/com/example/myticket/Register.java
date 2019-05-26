package com.example.myticket;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myticket.Model.Resgister.MainResponceReg;
import com.example.myticket.Model.Resgister.UserRegister;
import com.example.myticket.Network.Retrofit.ApiClient;
import com.example.myticket.Network.Retrofit.onResponceInterface;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class Register extends AppCompatActivity implements onResponceInterface {
    private EditText fullname;
    private EditText password;
    private EditText repPassword;
    private EditText userName;
    private EditText address;
    private EditText phone;
    private Button btnReg;

    private String mFullname;
    private String mPassword;
    private String mRepPassword;
    private String mUsername;
    private String mAddress;
    private String mPhone;

    private String macAddress;
    private String deviceToken = "987654321";
    private String deviceType = "1";
    private String email = "iam@gmail.com";

    private UserRegister userRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.fullname_reg);
        password = findViewById(R.id.password_reg);
        repPassword = findViewById(R.id.rep_password_reg);
        userName = findViewById(R.id.username_reg);
        address = findViewById(R.id.address_reg);
        phone = findViewById(R.id.phone_reg);
        btnReg = findViewById(R.id.reg_btn);
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


                userRegister = new UserRegister(mFullname,email,mUsername,mPhone,mAddress,deviceToken,mPassword,deviceType,macAddress);
                ApiClient apiClient = new ApiClient(userRegister,Register.this);
                apiClient.initializeClientRegister("http://iscoapps.com/cinema/api/");
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
        Toast.makeText(this,responceReg.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(Object responce) {
        MainResponceReg responceReg = (MainResponceReg) responce;
        Toast.makeText(this,responceReg.getMessage(),Toast.LENGTH_SHORT).show();

    }
}
