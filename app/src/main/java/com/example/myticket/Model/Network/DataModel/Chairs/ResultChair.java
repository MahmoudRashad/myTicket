package com.example.myticket.Model.Network.DataModel.Chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultChair
{

//    @SerializedName("vip")
//    @Expose
//    private List<Chair> vip = null;
//    @SerializedName("economy")
//    @Expose
//    private List<Chair> economy = null;
//
//    public List<Chair> getVip() {
//        return vip;
//    }
//
//    public void setVip(List<Chair> vip) {
//        this.vip = vip;
//    }
//
//    public List<Chair> getEconomy() {
//        return economy;
//    }
//
//    public void setEconomy(List<Chair> economy) {
//        this.economy = economy;
//    }

    @SerializedName("symbol_chair")
    @Expose
    private String symbolChair;
    @SerializedName("char_num")
    @Expose
    private String charNum;
    private final static long serialVersionUID = -9098935261308725878L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResultChair() {
    }

    /**
     *
     * @param symbolChair
     * @param charNum
     */
    public ResultChair(String symbolChair, String charNum) {
        super();
        this.symbolChair = symbolChair;
        this.charNum = charNum;
    }

    public String getSymbolChair() {
        return symbolChair;
    }

    public void setSymbolChair(String symbolChair) {
        this.symbolChair = symbolChair;
    }

    public String getCharNum() {
        return charNum;
    }

    public void setCharNum(String charNum) {
        this.charNum = charNum;
    }

}
