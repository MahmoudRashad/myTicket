
package com.example.myticket.Model.Network.StadiumModel.MyTicket;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTicketMainDetail implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<MyTicketDetailResult> myTicketDetailResult = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 7449502918513258816L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyTicketMainDetail() {
    }

    /**
     * 
     * @param message
     * @param myTicketDetailResult
     * @param success
     */
    public MyTicketMainDetail(List<MyTicketDetailResult> myTicketDetailResult, Boolean success, String message) {
        super();
        this.myTicketDetailResult = myTicketDetailResult;
        this.success = success;
        this.message = message;
    }

    public List<MyTicketDetailResult> getMyTicketDetailResult() {
        return myTicketDetailResult;
    }

    public void setMyTicketDetailResult(List<MyTicketDetailResult> myTicketDetailResult) {
        this.myTicketDetailResult = myTicketDetailResult;
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
