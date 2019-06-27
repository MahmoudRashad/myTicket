package com.example.myticket.Model.Network.DataModel.Tickets;

import com.example.myticket.Model.Network.DataModel.EditUserData.ResultEditData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketResponse
{
    @SerializedName("result")
    @Expose
    private List<ResultTickets> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<ResultTickets> getResult() {
        return result;
    }

    public void setResult(List<ResultTickets> result) {
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
