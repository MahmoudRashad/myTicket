package com.example.myticket.Model.Network.DataModel.MyTickets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coming
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("movie_name")
    @Expose
    private String movieName;
    @SerializedName("cinema_name")
    @Expose
    private String cinemaName;
    @SerializedName("date_movie")
    @Expose
    private String dateMovie;
    @SerializedName("hall_name")
    @Expose
    private String hallName;
    @SerializedName("date_reserve")
    @Expose
    private String dateReserve;
    @SerializedName("seat_number")
    @Expose
    private String seatNumber;
    @SerializedName("seat_type")
    @Expose
    private String seatType;
    @SerializedName("time_movie")
    @Expose
    private String timeMovie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getDateMovie() {
        return dateMovie;
    }

    public void setDateMovie(String dateMovie) {
        this.dateMovie = dateMovie;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getDateReserve() {
        return dateReserve;
    }

    public void setDateReserve(String dateReserve) {
        this.dateReserve = dateReserve;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getTimeMovie() {
        return timeMovie;
    }

    public void setTimeMovie(String timeMovie) {
        this.timeMovie = timeMovie;
    }

}
