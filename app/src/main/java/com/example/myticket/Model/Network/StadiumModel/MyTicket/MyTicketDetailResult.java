
package com.example.myticket.Model.Network.StadiumModel.MyTicket;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTicketDetailResult implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("seat_num")
    @Expose
    private String seatNum;
    @SerializedName("symbol_chair")
    @Expose
    private String symbol_chair;
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
    @SerializedName("team1_image")
    @Expose
    private String team1Image;
    @SerializedName("team2_image")
    @Expose
    private String team2Image;

    @SerializedName("block_name")
    @Expose
    private String blockName;
    @SerializedName("qr_code")
    @Expose
    private String qrCode;
    private final static long serialVersionUID = -4599240185655148702L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyTicketDetailResult() {
    }

    /**
     * 
     * @param team2Image
     * @param id
     * @param team1Name
     * @param time
     * @param team2Name
     * @param qrCode
     * @param seatNum
     * @param date
     * @param type
     * @param team1Image
     * @param stadiumName
     */
    public MyTicketDetailResult(Integer id, String type, String date, String seatNum, String team1Name, String team2Name, String time, String stadiumName, String team1Image, String team2Image, String qrCode) {
        super();
        this.id = id;
        this.type = type;
        this.date = date;
        this.seatNum = seatNum;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.time = time;
        this.stadiumName = stadiumName;
        this.team1Image = team1Image;
        this.team2Image = team2Image;
        this.qrCode = qrCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
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

    public String getTeam1Image() {
        return team1Image;
    }

    public void setTeam1Image(String team1Image) {
        this.team1Image = team1Image;
    }

    public String getTeam2Image() {
        return team2Image;
    }

    public void setTeam2Image(String team2Image) {
        this.team2Image = team2Image;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getSymbol_chair() {
        return symbol_chair;
    }

    public void setSymbol_chair(String symbol_chair) {
        this.symbol_chair = symbol_chair;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}
