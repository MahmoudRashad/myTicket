
package com.example.myticket.Model.Network.DataModel.Search;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResult implements Serializable
{

    @SerializedName("result")
    @Expose
    private List<Category> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 8833400476216161502L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoryResult() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public CategoryResult(List<Category> result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public List<Category> getResult() {
        return result;
    }

    public void setResult(List<Category> result) {
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
