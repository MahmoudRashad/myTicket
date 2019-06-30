
package com.example.myticket.Model.Network.DataModel.HomeResult;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cinema implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("reviews")
    @Expose
    private Integer reviews;
    @SerializedName("address")
    @Expose
    private String address;



    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("youtube")
    @Expose
    private Object youtube;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("close")
    @Expose
    private String close;
    public final static Creator<Cinema> CREATOR = new Creator<Cinema>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cinema createFromParcel(Parcel in) {
            return new Cinema(in);
        }

        public Cinema[] newArray(int size) {
            return (new Cinema[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6504794720100532657L;

    protected Cinema(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.rate = ((String) in.readValue((String.class.getClassLoader())));
        this.reviews = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.youtube = ((Object) in.readValue((Object.class.getClassLoader())));
        this.open = ((String) in.readValue((String.class.getClassLoader())));
        this.close = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cinema() {
    }

    /**
     * 
     * @param id
     * @param open
     * @param reviews
     * @param rate
     * @param address
     * @param name
     * @param youtube
     * @param image
     * @param close
     */
    public Cinema(Integer id, String image, String name, String rate, Integer reviews, String address, Object youtube, String open, String close) {
        super();
        this.id = id;
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.reviews = reviews;
        this.address = address;
        this.youtube = youtube;
        this.open = open;
        this.close = close;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getYoutube() {
        return youtube;
    }

    public void setYoutube(Object youtube) {
        this.youtube = youtube;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(image);
        dest.writeValue(name);
        dest.writeValue(rate);
        dest.writeValue(reviews);
        dest.writeValue(address);
        dest.writeValue(youtube);
        dest.writeValue(open);
        dest.writeValue(close);
    }

    public int describeContents() {
        return  0;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String get_long() {
        return _long;
    }

    public void set_long(String _long) {
        this._long = _long;
    }

}
