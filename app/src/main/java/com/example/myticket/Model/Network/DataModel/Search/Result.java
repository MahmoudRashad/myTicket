
package com.example.myticket.Model.Network.DataModel.Search;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
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
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("reviews")
    @Expose
    private Integer reviews;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("address")
    @Expose
    private String address;
    private final static long serialVersionUID = 6474562189137681659L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param id
     * @param category
     * @param reviews
     * @param rate
     * @param address
     * @param name
     * @param image
     * @param date
     * @param type
     */
    public Result(Integer id, String image, String name, String type, Integer rate, Integer reviews, String date, List<Category> category, String address) {
        super();
        this.id = id;
        this.image = image;
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.reviews = reviews;
        this.date = date;
        this.category = category;
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
