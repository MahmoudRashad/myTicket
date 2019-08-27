
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainReservationDetails implements Serializable
{

    @SerializedName("result")
    @Expose
    private ReservationDetails reservationDetails;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 334678181367761342L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainReservationDetails() {
    }

    /**
     * 
     * @param message
     * @param reservationDetails
     * @param success
     */
    public MainReservationDetails(ReservationDetails reservationDetails, Boolean success, String message) {
        super();
        this.reservationDetails = reservationDetails;
        this.success = success;
        this.message = message;
    }

    public ReservationDetails getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(ReservationDetails reservationDetails) {
        this.reservationDetails = reservationDetails;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
