package com.example.myticket.Model.Network.DataModel.Tickets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultTickets
{
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("movie_name")
    @Expose
    private String movieName;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @SerializedName("cinema_name")
    @Expose
    private String cinemaName;
    @SerializedName("hall_name")
    @Expose
    private String hallName;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("chair_num")
    @Expose
    private String chairNum;
    @SerializedName("chair_type")
    @Expose
    private String chairType;
    @SerializedName("cinema_location")
    @Expose
    private String cinemaLocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChairNum() {
        return chairNum;
    }

    public void setChairNum(String chairNum) {
        this.chairNum = chairNum;
    }

    public String getChairType() {
        return chairType;
    }

    public void setChairType(String chairType) {
        this.chairType = chairType;
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }
}
