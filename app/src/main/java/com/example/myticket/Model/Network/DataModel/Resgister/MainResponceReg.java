
package com.example.myticket.Model.Network.DataModel.Resgister;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainResponceReg implements Serializable
{

    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("result")
    @Expose
    private com.example.myticket.Model.Network.DataModel.LoginModel.Result result;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -4033320509756240727L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainResponceReg() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param tokenType
     * @param accessToken
     * @param status
     * @param expiresIn
     * @param refreshToken
     * @param success
     */
    public MainResponceReg(String tokenType, Integer expiresIn, String accessToken, String refreshToken,
                           com.example.myticket.Model.Network.DataModel.LoginModel.Result result, Boolean status, Boolean success, String message) {
        super();
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.result = result;
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public com.example.myticket.Model.Network.DataModel.LoginModel.Result getResult() {
        return result;
    }

    public void setResult(com.example.myticket.Model.Network.DataModel.LoginModel.Result result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
