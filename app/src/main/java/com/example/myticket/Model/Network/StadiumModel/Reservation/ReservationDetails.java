
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationDetails implements Serializable
{

    @SerializedName("stadium_id")
    @Expose
    private String stadiumId;
    @SerializedName("match_date")
    @Expose
    private String matchDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("statdium_plan")
    @Expose
    private String statdiumPlan;
    @SerializedName("ticket_type")
    @Expose
    private List<TicketType> ticketType = null;
    private final static long serialVersionUID = -4144629399604586854L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReservationDetails() {
    }

    /**
     * 
     * @param stadiumId
     * @param matchDate
     * @param statdiumPlan
     * @param status
     * @param ticketType
     */
    public ReservationDetails(String stadiumId, String matchDate, Integer status, String statdiumPlan, List<TicketType> ticketType) {
        super();
        this.stadiumId = stadiumId;
        this.matchDate = matchDate;
        this.status = status;
        this.statdiumPlan = statdiumPlan;
        this.ticketType = ticketType;
    }

    public String getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatdiumPlan() {
        return statdiumPlan;
    }

    public void setStatdiumPlan(String statdiumPlan) {
        this.statdiumPlan = statdiumPlan;
    }

    public List<TicketType> getTicketType() {
        return ticketType;
    }

    public void setTicketType(List<TicketType> ticketType) {
        this.ticketType = ticketType;
    }

}
