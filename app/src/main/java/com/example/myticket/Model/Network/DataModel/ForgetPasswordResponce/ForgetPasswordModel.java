package com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordModel {
    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgetPasswordModel(String email) {
        this.email = email;
    }
}
