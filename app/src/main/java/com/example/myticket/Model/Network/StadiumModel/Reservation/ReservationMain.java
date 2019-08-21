
package com.example.myticket.Model.Network.StadiumModel.Reservation;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationMain implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<ReservationResult> reservationResult = null;


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -7716231543298058235L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReservationMain() {
    }

    /**
     * 
     * @param message
     * @param reservationResult
     * @param success
     */
    public ReservationMain(List<ReservationResult> reservationResult, Boolean success, String message) {
        super();
        this.reservationResult = reservationResult;
        this.success = success;
        this.message = message;
    }

    public List<ReservationResult> getReservationResult() {
        return reservationResult;
    }

    public void setReservationResult(List<ReservationResult> reservationResult) {
        this.reservationResult = reservationResult;
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
