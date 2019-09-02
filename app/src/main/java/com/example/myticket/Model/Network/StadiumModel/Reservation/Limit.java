
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Limit implements Serializable
{

    @SerializedName("limit")
    @Expose
    private Integer limit;
    private final static long serialVersionUID = -7493197427759938749L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Limit() {
    }

    /**
     * 
     * @param limit
     */
    public Limit(Integer limit) {
        super();
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
