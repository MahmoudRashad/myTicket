package com.example.myticket.Model.Network.DataModel.ReserveModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeChairRespose
{
    @SerializedName("result")
    @Expose
    private ResultChair result;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public ResultChair getResult() {
        return result;
    }

    public void setResult(ResultChair result) {
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
