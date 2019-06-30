package com.example.myticket.Model.Network.DataModel.ReserveModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultReserveCinema {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("hall")
    @Expose
    private String hall;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
