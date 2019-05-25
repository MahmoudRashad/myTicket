
package com.example.myticket.Model.MainSliderResponce;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderResponce implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<com.example.myticket.Model.MainSliderResponce.Result> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 6274282209446161695L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SliderResponce() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public SliderResponce(List<com.example.myticket.Model.MainSliderResponce.Result> result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public List<com.example.myticket.Model.MainSliderResponce.Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
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
