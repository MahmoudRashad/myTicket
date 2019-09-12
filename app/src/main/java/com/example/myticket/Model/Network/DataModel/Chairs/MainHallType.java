package com.example.myticket.Model.Network.DataModel.Chairs;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainHallType implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<HallTypeResult> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -1056696892341968390L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MainHallType() {
    }

    /**
     *
     * @param message
     * @param result
     * @param success
     */
    public MainHallType(List<HallTypeResult> result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public List<HallTypeResult> getResult() {
        return result;
    }

    public void setResult(List<HallTypeResult> result) {
        this.result = result;
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


