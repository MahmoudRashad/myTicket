package com.example.myticket.helper;

import java.util.ArrayList;

/**
 * Created by eldsokey on 2017-12-01.
 */

public class Variables
{
    public final String appPreference = "isco.myTicket" ,
    keyLanguage = "appLanguage",
    activityName = "activityName",
    skipSplash = "skipSplash",
    errorCheck = "errorCheck",
    userToken = "userToken",
    userId = "userId",
    urlLoadImage = "http://elborsa.net/",
    itemUnit = "itemUnit";
    public ArrayList<String> categories = new ArrayList<>();
    public ArrayList<String> cinemasCategories = new ArrayList<>();

    public void setCategories(){
        categories.add("Action");
        categories.add("Adventure");
        categories.add("Family");
        categories.add("Comedy");
        categories.add("Crime");
        categories.add("Drama");
        categories.add("Epic");
        categories.add("Fantasy");
        categories.add("History");
        categories.add("Horror");
        categories.add("Musical");
        categories.add("Mystery");
        categories.add("Romance");
        categories.add("Science Fiction");
        categories.add("Spy");
        categories.add("Thriller");
        categories.add("War");
        categories.add("Westren");

    }
    public ArrayList<String> getCategories(){
        return categories;
    }

    public void setCinemasCategories() {
        cinemasCategories.add("Near By");
    }

    public ArrayList<String> getCinemasCategories() {
        return cinemasCategories;
    }
}
