package com.example.myticket.Model.Network.DataModel.Chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultChair
{

    @SerializedName("vip")
    @Expose
    private List<Chair> vip = null;
    @SerializedName("economy")
    @Expose
    private List<Chair> economy = null;

    public List<Chair> getVip() {
        return vip;
    }

    public void setVip(List<Chair> vip) {
        this.vip = vip;
    }

    public List<Chair> getEconomy() {
        return economy;
    }

    public void setEconomy(List<Chair> economy) {
        this.economy = economy;
    }
}
