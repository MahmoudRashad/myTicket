
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainLimit implements Serializable
{

    @SerializedName("result")
    @Expose
    private Limit limit;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 8313999027698516100L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainLimit() {
    }

    /**
     * 
     * @param message
     * @param limit
     * @param success
     */
    public MainLimit(Limit limit, Boolean success, String message) {
        super();
        this.limit = limit;
        this.success = success;
        this.message = message;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
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
