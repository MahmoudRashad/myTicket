package com.example.myticket.Model.Network.StadiumModel.Match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MainMatchDetails implements Serializable
{

    @SerializedName("result")
    @Expose
    private MatchDetails result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 659422514097616607L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MainMatchDetails() {
    }

    /**
     *
     * @param message
     * @param result
     * @param success
     */
    public MainMatchDetails(MatchDetails result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public MatchDetails getResult() {
        return result;
    }

    public void setResult(MatchDetails result) {
        this.result = result;
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
