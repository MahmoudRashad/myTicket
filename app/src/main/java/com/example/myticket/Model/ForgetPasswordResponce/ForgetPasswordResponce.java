package com.example.myticket.Model.ForgetPasswordResponce;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponce implements Serializable {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -2214061955077182131L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ForgetPasswordResponce() {
    }

    /**
     *
     * @param message
     * @param success
     */
    public ForgetPasswordResponce(Boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

