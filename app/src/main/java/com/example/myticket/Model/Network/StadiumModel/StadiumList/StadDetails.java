
package com.example.myticket.Model.Network.StadiumModel.StadiumList;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StadDetails implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("describtion")
    @Expose
    private String describtion;
    @SerializedName("image")
    @Expose
    private String image;
    private final static long serialVersionUID = -8921094038383170284L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StadDetails() {
    }

    /**
     * 
     * @param id
     * @param describtion
     * @param address
     * @param name
     * @param image
     * @param lang
     * @param lat
     */
    public StadDetails(Integer id, String name, String address, String lat, String lang, String describtion, String image) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lang = lang;
        this.describtion = describtion;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
