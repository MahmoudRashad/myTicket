
package com.example.myticket.Model.Network.DataModel.LoginModel;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLogin implements Serializable
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
    private Result result;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("success")
    @Expose
    private Boolean statusCode;
    @SerializedName("message")
    @Expose
    private String statusText;
    private final static long serialVersionUID = -7612101418676054871L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ModelLogin() {
    }

    /**
     * 
     * @param statusCode
     * @param result
     * @param tokenType
     * @param accessToken
     * @param status
     * @param expiresIn
     * @param statusText
     * @param refreshToken
     */
    public ModelLogin(String tokenType, Integer expiresIn, String accessToken, String refreshToken, Result result, Boolean status, Boolean statusCode, String statusText) {
        super();
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.result = result;
        this.status = status;
        this.statusCode = statusCode;
        this.statusText = statusText;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

}
