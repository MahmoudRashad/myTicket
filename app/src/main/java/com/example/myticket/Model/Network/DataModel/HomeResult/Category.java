
package com.example.myticket.Model.Network.DataModel.HomeResult;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Serializable, Parcelable
{

    @SerializedName("category")
    @Expose
    private String category;
    public final static Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2759993876638493502L;

    protected Category(Parcel in) {
        this.category = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Category() {
    }

    /**
     * 
     * @param category
     */
    public Category(String category) {
        super();
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(category);
    }

    public int describeContents() {
        return  0;
    }

}
