package com.example.myticket.Model.Network.DataModel.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("device_token")
    @Expose
    private String device_token;
    @SerializedName("device_type")
    @Expose
    private String device_type;
    @SerializedName("mac")
    @Expose
    private String mac_address;

    public User(String username, String password, String device_token, String device_type, String mac_address) {
        this.username = username;
        this.password = password;
        this.device_token = device_token;
        this.device_type = device_type;
        this.mac_address = mac_address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }
}
