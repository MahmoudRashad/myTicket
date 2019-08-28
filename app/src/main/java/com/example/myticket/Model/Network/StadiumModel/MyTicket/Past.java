
package com.example.myticket.Model.Network.StadiumModel.MyTicket;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Past implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("team1_name")
    @Expose
    private String team1Name;
    @SerializedName("team2_name")
    @Expose
    private String team2Name;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("stadium_name")
    @Expose
    private String stadiumName;
    @SerializedName("seats")
    @Expose
    private List<Seat> seats = null;
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("symbol_chair")
    @Expose
    private String symbol_chair;
    private final static long serialVersionUID = -6308416309511160434L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Past() {
    }

    /**
     * 
     * @param id
     * @param team1Name
     * @param time
     * @param team2Name
     * @param matchId
     * @param seats
     * @param date
     * @param stadiumName
     */
    public Past(Integer id, String matchId, String team1Name, String team2Name, String time, String stadiumName, List<Seat> seats, String date) {
        super();
        this.id = id;
        this.matchId = matchId;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.time = time;
        this.stadiumName = stadiumName;
        this.seats = seats;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol_chair() {
        return symbol_chair;
    }

    public void setSymbol_chair(String symbol_chair) {
        this.symbol_chair = symbol_chair;
    }
}
