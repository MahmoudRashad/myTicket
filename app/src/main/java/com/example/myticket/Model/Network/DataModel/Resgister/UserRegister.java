package com.example.myticket.Model.Network.DataModel.Resgister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegister {

    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("device_token")
    @Expose
    private String device_token;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("device_type")
    @Expose
    private String device_type;
    @SerializedName("mac")
    @Expose
    private String mac;

    public UserRegister(){

    }
    public UserRegister(String user_name, String email, String username,
                        String phone, String address, String device_token, String password, String device_type, String mac) {
        this.user_name = user_name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.device_token = device_token;
        this.password = password;
        this.device_type = device_type;
        this.mac = mac;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
