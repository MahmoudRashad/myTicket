
package com.example.myticket.Model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.myticket.Model.Network.DataModel.HomeResult.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainResult implements Serializable, Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<MainResult> CREATOR = new Creator<MainResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MainResult createFromParcel(Parcel in) {
            return new MainResult(in);
        }

        public MainResult[] newArray(int size) {
            return (new MainResult[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3075679308707951766L;

    protected MainResult(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainResult() {
    }

    /**
     * 
     * @param message
     * @param result
     * @param success
     */
    public MainResult(Result result, Boolean success, String message) {
        super();
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(result);
        dest.writeValue(success);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
