package com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultTicketsStad {
    @SerializedName("match_id")
    @Expose
    private String id;

    @SerializedName("date_match")
    @Expose
    private String date;
    @SerializedName("seat_num")
    @Expose
    private String chairNum;

    @SerializedName("seat_id")
    @Expose
    private String seatId;

    @SerializedName("qr_code")
    @Expose
    private String qrCode;

    public ResultTicketsStad(String id, String date, String chairNum, String seatId, String qrCode) {
        this.id = id;
        this.date = date;
        this.chairNum = chairNum;
        this.seatId = seatId;
        this.qrCode = qrCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
