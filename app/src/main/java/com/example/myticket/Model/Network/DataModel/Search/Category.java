
package com.example.myticket.Model.Network.DataModel.Search;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Serializable
{

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = -1996802514211409615L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Category() {
    }

    /**
     * 
     * @param id
     * @param category
     */
    public Category(String category, Integer id) {
        super();
        this.category = category;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
