
package com.example.myticket.Model.Network.StadiumModel.Match;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainHomeMatches implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<Leagues> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 2774887953867145267L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainHomeMatches() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public MainHomeMatches(List<Leagues> result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public List<Leagues> getResult() {
        return result;
    }

    public void setResult(List<Leagues> result) {
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
