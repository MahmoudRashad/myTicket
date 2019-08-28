
package com.example.myticket.Model.Network.StadiumModel.MyTicket;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat implements Serializable
{

    @SerializedName("seat_num")
    @Expose
    private String seatNum;

    @SerializedName("symbol_chair")
    @Expose
    private String symbol_chair;
    private final static long serialVersionUID = -7559269203485223492L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Seat() {
    }

    /**
     * 
     * @param seatNum
     */
    public Seat(String seatNum) {
        super();
        this.seatNum = seatNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getSymbol_chair() {
        return symbol_chair;
    }
}
