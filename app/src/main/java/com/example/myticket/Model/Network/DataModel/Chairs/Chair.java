package com.example.myticket.Model.Network.DataModel.Chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chair
{
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chair_type_id")
    @Expose
    private String chairTypeId;
    @SerializedName("cinema_film_id")
    @Expose
    private String cinemaFilmId;
    @SerializedName("char_num")
    @Expose
    private String charNum;
    @SerializedName("symbol_chair")
    @Expose
    private String symbolChair;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChairTypeId() {
        return chairTypeId;
    }

    public void setChairTypeId(String chairTypeId) {
        this.chairTypeId = chairTypeId;
    }

    public String getCinemaFilmId() {
        return cinemaFilmId;
    }

    public void setCinemaFilmId(String cinemaFilmId) {
        this.cinemaFilmId = cinemaFilmId;
    }

    public String getCharNum() {
        return charNum;
    }

    public void setCharNum(String charNum) {
        this.charNum = charNum;
    }

    public String getSymbolChair() {
        return symbolChair;
    }

    public void setSymbolChair(String symbolChair) {
        this.symbolChair = symbolChair;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
