
package com.example.myticket.Model.HomeResult;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable, Parcelable
{

    @SerializedName("coming")
    @Expose
    private List<Coming> coming = null;
    @SerializedName("recently")
    @Expose
    private List<Recently> recently = null;
    @SerializedName("cinema")
    @Expose
    private List<Cinema> cinema = null;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4973588480134216078L;

    protected Result(Parcel in) {
        in.readList(this.coming, (Coming.class.getClassLoader()));
        in.readList(this.recently, (Recently.class.getClassLoader()));
        in.readList(this.cinema, (Cinema.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param cinema
     * @param recently
     * @param coming
     */
    public Result(List<Coming> coming, List<Recently> recently, List<Cinema> cinema) {
        super();
        this.coming = coming;
        this.recently = recently;
        this.cinema = cinema;
    }

    public List<Coming> getComing() {
        return coming;
    }

    public void setComing(List<Coming> coming) {
        this.coming = coming;
    }

    public List<Recently> getRecently() {
        return recently;
    }

    public void setRecently(List<Recently> recently) {
        this.recently = recently;
    }

    public List<Cinema> getCinema() {
        return cinema;
    }

    public void setCinema(List<Cinema> cinema) {
        this.cinema = cinema;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(coming);
        dest.writeList(recently);
        dest.writeList(cinema);
    }

    public int describeContents() {
        return  0;
    }

}
