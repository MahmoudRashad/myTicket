package com.example.myticket.Model.Network.DataModel.MyTickets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTicketsResponse
{

    @SerializedName("result")
    @Expose
    private ResultMyTicket result;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public ResultMyTicket getResult() {
        return result;
    }

    public void setResult(ResultMyTicket result) {
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
