
package com.example.myticket.Model.Network.StadiumModel.MyTicket;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTicketMain implements Serializable
{

    @SerializedName("result")
    @Expose
    private MyTicketsResult myTicketsResult;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 8975986185253316462L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyTicketMain() {
    }

    /**
     * 
     * @param message
     * @param myTicketsResult
     * @param success
     */
    public MyTicketMain(MyTicketsResult myTicketsResult, Boolean success, String message) {
        super();
        this.myTicketsResult = myTicketsResult;
        this.success = success;
        this.message = message;
    }

    public MyTicketsResult getMyTicketsResult() {
        return myTicketsResult;
    }

    public void setMyTicketsResult(MyTicketsResult myTicketsResult) {
        this.myTicketsResult = myTicketsResult;
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
