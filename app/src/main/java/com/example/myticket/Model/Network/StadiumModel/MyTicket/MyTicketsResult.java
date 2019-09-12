
package com.example.myticket.Model.Network.StadiumModel.MyTicket;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTicketsResult implements Serializable
{

    @SerializedName("past")
    @Expose
    private List<Past> past = null;
    @SerializedName("coming")
    @Expose
    private List<Past> coming = null;

    @SerializedName("pending")
    @Expose
    private List<Past> pending = null;

    public List<Past> getPending() {
        return pending;
    }

    public void setPending(List<Past> pending) {
        this.pending = pending;
    }

    private final static long serialVersionUID = -3598838758896061184L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyTicketsResult() {
    }

    /**
     * 
     * @param coming
     * @param past
     */
    public MyTicketsResult(List<Past> past, List<Past> coming) {
        super();
        this.past = past;
        this.coming = coming;
    }

    public List<Past> getPast() {
        return past;
    }

    public void setPast(List<Past> past) {
        this.past = past;
    }

    public List<Past> getComing() {
        return coming;
    }

    public void setComing(List<Past> coming) {
        this.coming = coming;
    }

}
