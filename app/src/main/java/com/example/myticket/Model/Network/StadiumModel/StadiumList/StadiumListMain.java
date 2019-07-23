
package com.example.myticket.Model.Network.StadiumModel.StadiumList;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StadiumListMain implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<StadDetails> stadDetails = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 587210435093770540L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StadiumListMain() {
    }

    /**
     * 
     * @param message
     * @param stadDetails
     * @param success
     */
    public StadiumListMain(List<StadDetails> stadDetails, Boolean success, String message) {
        super();
        this.stadDetails = stadDetails;
        this.success = success;
        this.message = message;
    }

    public List<StadDetails> getStadDetails() {
        return stadDetails;
    }

    public void setStadDetails(List<StadDetails> stadDetails) {
        this.stadDetails = stadDetails;
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
