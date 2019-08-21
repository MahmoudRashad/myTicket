
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationResult implements Serializable
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
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("stadium_id")
    @Expose
    private String stadiumId;
    private final static long serialVersionUID = -1783554273673202206L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReservationResult() {
    }

    /**
     * 
     * @param id
     * @param price
     * @param name
     * @param image
     * @param currency
     */
    public ReservationResult(Integer id, String name, String price, String currency, String image) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId;
    }
}
