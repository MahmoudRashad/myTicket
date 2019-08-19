package com.example.myticket.Model.Network.StadiumModel.Match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TicketType implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("statdium_id")
    @Expose
    private String statdiumId;
    @SerializedName("statdium_name")
    @Expose
    private String statdiumName;
    private final static long serialVersionUID = -7179595544238291700L;

    /**
     * No args constructor for use in serialization
     *
     */
    public TicketType() {
    }

    /**
     *
     * @param id
     * @param statdiumId
     * @param price
     * @param name
     * @param statdiumName
     * @param currency
     */
    public TicketType(Integer id, String name, String price, String currency, String statdiumId, String statdiumName) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.statdiumId = statdiumId;
        this.statdiumName = statdiumName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatdiumId() {
        return statdiumId;
    }

    public void setStatdiumId(String statdiumId) {
        this.statdiumId = statdiumId;
    }

    public String getStatdiumName() {
        return statdiumName;
    }

    public void setStatdiumName(String statdiumName) {
        this.statdiumName = statdiumName;
    }

}

