package com.example.myticket.Model.Network.DataModel.MyTickets;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultMyTicket
{
    @SerializedName("past")
    @Expose
    private List<ResultTickets> past = null;
    @SerializedName("coming")
    @Expose
    private List<ResultTickets> coming = null;

    public List<ResultTickets> getPast() {
        return past;
    }

    public void setPast(List<ResultTickets> past) {
        this.past = past;
    }

    public List<ResultTickets> getComing() {
        return coming;
    }

    public void setComing(List<ResultTickets> coming) {
        this.coming = coming;
    }
}
