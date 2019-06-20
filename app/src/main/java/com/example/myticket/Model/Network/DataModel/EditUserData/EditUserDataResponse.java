package com.example.myticket.Model.Network.DataModel.EditUserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditUserDataResponse
{
    @SerializedName("result")
    @Expose
    private List<ResultEditData> result = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<ResultEditData> getResult() {
        return result;
    }

    public void setResult(List<ResultEditData> result) {
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
