
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChairsResult implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chair_num")
    @Expose
    private String chairNum;
    @SerializedName("chair_symbol")
    @Expose
    private String chairSymbol;
    private final static long serialVersionUID = 7009599259143169682L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ChairsResult() {
    }

    /**
     * 
     * @param id
     * @param chairSymbol
     * @param chairNum
     */
    public ChairsResult(Integer id, String chairNum, String chairSymbol) {
        super();
        this.id = id;
        this.chairNum = chairNum;
        this.chairSymbol = chairSymbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChairNum() {
        return chairNum;
    }

    public void setChairNum(String chairNum) {
        this.chairNum = chairNum;
    }

    public String getChairSymbol() {
        return chairSymbol;
    }

    public void setChairSymbol(String chairSymbol) {
        this.chairSymbol = chairSymbol;
    }

}
