
package com.example.myticket.Model.HomeResult;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coming implements Serializable, Parcelable
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
    private Integer rate;
    @SerializedName("reviews")
    @Expose
    private Integer reviews;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("category")
    @Expose
    private List<Object> category = null;
    @SerializedName("period")
    @Expose
    private String period;
    public final static Creator<Coming> CREATOR = new Creator<Coming>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coming createFromParcel(Parcel in) {
            return new Coming(in);
        }

        public Coming[] newArray(int size) {
            return (new Coming[size]);
        }

    }
    ;
    private final static long serialVersionUID = -6730771828017038808L;

    protected Coming(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.rate = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.reviews = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.youtube = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.category, (Object.class.getClassLoader()));
        this.period = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coming() {
    }

    /**
     * 
     * @param id
     * @param category
     * @param reviews
     * @param rate
     * @param name
     * @param youtube
     * @param image
     * @param period
     * @param date
     */
    public Coming(Integer id, String image, String name, Integer rate, Integer reviews, String date, String youtube, List<Object> category, String period) {
        super();
        this.id = id;
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.reviews = reviews;
        this.date = date;
        this.youtube = youtube;
        this.category = category;
        this.period = period;
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

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public List<Object> getCategory() {
        return category;
    }

    public void setCategory(List<Object> category) {
        this.category = category;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(image);
        dest.writeValue(name);
        dest.writeValue(rate);
        dest.writeValue(reviews);
        dest.writeValue(date);
        dest.writeValue(youtube);
        dest.writeList(category);
        dest.writeValue(period);
    }

    public int describeContents() {
        return  0;
    }

}
