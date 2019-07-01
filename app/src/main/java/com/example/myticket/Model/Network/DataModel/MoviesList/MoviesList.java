
package com.example.myticket.Model.Network.DataModel.MoviesList;

import java.io.Serializable;
import java.util.List;

import com.example.myticket.Model.Network.DataModel.HomeResult.Recently;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesList implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<Recently> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 6175359351056556817L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MoviesList() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public MoviesList(List<Recently> result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public List<Recently> getResult() {
        return result;
    }

    public void setResult(List<Recently> result) {
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
