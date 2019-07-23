
package com.example.myticket.Model.Network.StadiumModel.StadiumList;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StadiumDetailsByID implements Serializable
{

    @SerializedName("result")
    @Expose
    private StadDetails result;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -6464341161087620234L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StadiumDetailsByID() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public StadiumDetailsByID(StadDetails result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public StadDetails getResult() {
        return result;
    }

    public void setResult(StadDetails result) {
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
