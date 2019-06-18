
package com.example.myticket.Model.Network.DataModel.CommentsModel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MakeCommentResponce implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 4248307449726853388L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MakeCommentResponce() {
    }

    /**
     * 
     * @param message
     * @param success
     */
    public MakeCommentResponce(Boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
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
