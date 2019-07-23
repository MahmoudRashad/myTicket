
package com.example.myticket.Model.Network.StadiumModel.Match;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchDetails implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cyclic_name")
    @Expose
    private String cyclicName;
    @SerializedName("team1_name")
    @Expose
    private String team1Name;
    @SerializedName("team1_image")
    @Expose
    private String team1Image;
    @SerializedName("team2_name")
    @Expose
    private String team2Name;
    @SerializedName("team2_image")
    @Expose
    private String team2Image;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stadium_name")
    @Expose
    private String stadiumName;
    @SerializedName("follow_status")
    @Expose
    private Integer followStatus;
    private final static long serialVersionUID = -7614335464837363393L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MatchDetails() {
    }

    /**
     * 
     * @param followStatus
     * @param startTime
     * @param team2Image
     * @param id
     * @param team1Name
     * @param team2Name
     * @param date
     * @param cyclicName
     * @param stadiumName
     * @param team1Image
     */
    public MatchDetails(Integer id, String cyclicName, String team1Name, String team1Image, String team2Name, String team2Image, String startTime, String date, String stadiumName, Integer followStatus) {
        super();
        this.id = id;
        this.cyclicName = cyclicName;
        this.team1Name = team1Name;
        this.team1Image = team1Image;
        this.team2Name = team2Name;
        this.team2Image = team2Image;
        this.startTime = startTime;
        this.date = date;
        this.stadiumName = stadiumName;
        this.followStatus = followStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCyclicName() {
        return cyclicName;
    }

    public void setCyclicName(String cyclicName) {
        this.cyclicName = cyclicName;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam1Image() {
        return team1Image;
    }

    public void setTeam1Image(String team1Image) {
        this.team1Image = team1Image;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public String getTeam2Image() {
        return team2Image;
    }

    public void setTeam2Image(String team2Image) {
        this.team2Image = team2Image;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

}
