
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainChairs implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<ChairsResult> chairsResult = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -7788764057985842308L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainChairs() {
    }

    /**
     * 
     * @param message
     * @param chairsResult
     * @param success
     */
    public MainChairs(List<ChairsResult> chairsResult, Boolean success, String message) {
        super();
        this.chairsResult = chairsResult;
        this.success = success;
        this.message = message;
    }

    public List<ChairsResult> getChairsResult() {
        return chairsResult;
    }

    public void setChairsResult(List<ChairsResult> chairsResult) {
        this.chairsResult = chairsResult;
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
