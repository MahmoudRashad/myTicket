
package com.example.myticket.Model.Network.DataModel.DetailsCinema;

import java.io.Serializable;
import java.util.List;

import com.example.myticket.Model.Network.DataModel.HomeResult.Cinema;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsCinema implements Serializable
{

    @SerializedName("result")
    @Expose
    private Cinema result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 2616004497728364279L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DetailsCinema() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public DetailsCinema(Cinema result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public Cinema getResult() {
        return result;
    }

    public void setResult(Cinema result) {
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
