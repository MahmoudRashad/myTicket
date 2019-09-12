package com.example.myticket.Model.Network.DataModel.Chairs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HallTypeResult implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency")
    @Expose
    private String currency;
    private final static long serialVersionUID = 4632581454286357905L;

    /**
     * No args constructor for use in serialization
     *
     */
    public HallTypeResult() {
    }

    /**
     *
     * @param id
     * @param price
     * @param name
     * @param currency
     */
    public HallTypeResult(String name, Integer id, String price, String currency) {
        super();
        this.name = name;
        this.id = id;
        this.price = price;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
