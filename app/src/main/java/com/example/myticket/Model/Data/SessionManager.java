package com.example.myticket.Model.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myticket.helper.Variables;

import java.util.Locale;


/**
 * Created by eldsokey on 2017-12-08.
 */

public class SessionManager
{
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;
    private Variables variables;
    private Context context;

    public SessionManager(Context context)
    {
        this.context = context;
        variables = new Variables();
        sharedPreferences = context.getSharedPreferences(variables.appPreference , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserToken(String userToken)
    {
        editor.putString(variables.userToken , userToken);
        editor.commit();
    }

    public String getUserToken()
    {
        return sharedPreferences.getString( variables.userToken , "");
    }


//    public void setUserTokenFCM(String userToken)
//    {
//        editor.putString("tokenFCM" , userToken);
//        editor.commit();
//    }
//
//    public String getUserTokenFCM()
//    {
//        return sharedPreferences.getString( "tokenFCM" , "");
//    }



    public void setUserImage(String url)
    {
        editor.putString("userImage" , url);
        editor.commit();
    }



    public String getUserImage()
    {
        return sharedPreferences.getString( "userImage", "");
    }



    public void setUserPhone(String userPhone)
    {
        editor.putString("userPhone" , userPhone);
        editor.commit();
    }



    public String getUserPhone()
    {
        return sharedPreferences.getString( "userPhone", "");
    }


    public void setUserEmail(String userEmail)
    {
        editor.putString("userEmail" , userEmail);
        editor.commit();
    }



    public String getUserEmail()
    {
        return sharedPreferences.getString( "userEmail", "");
    }



    public void setUserAddress(String userAddress)
    {
        editor.putString("userAddress" , userAddress);
        editor.commit();
    }



    public String getUserAddress()
    {
        return sharedPreferences.getString( "userAddress", "");
    }

    public String getDeviceLanguage(){
//        sharedPreferences = getSharedPreferences(
//                prefFile, MODE_PRIVATE);
//        language = sharedPreferences.getString(LANG_KEY, "ar");


        String language = Locale.getDefault().getLanguage();
        return language;


    }








    public void setNameOfUser(String name)
    {
        editor.putString("name" , name);
        editor.commit();
    }

    public String getNameOfUser()
    {
        return sharedPreferences.getString( "name", "");
    }



    public void setUserId(long userId)
    {
        editor.putLong(variables.userId , userId);
        editor.commit();
    }

    public long getUserId()
    {
        return sharedPreferences.getLong( variables.userId , -1);
    }




    public void setActivityOpened(String activityName)
    {
        editor.putString(variables.activityName , activityName);
        editor.commit();
    }

    public void setActivityOpenedId(String id)
    {
        editor.putString("id", id);
        editor.commit();
    }

    public String getActivityOpened()
    {
        return sharedPreferences.getString( variables.activityName , "");
    }

    public String getActivityOpenedId()
    {
        return sharedPreferences.getString("id" , "");
    }


    public void clearSessionManager()
    {

        setUserToken("");
        setUserId(-1);
        setAppVersion(-1);
//        editor.clear();
//        editor.commit();
    }


    public void setAppVersion(int appVersion)
    {
        editor.putInt( "appVersion", appVersion);
        editor.commit();
    }

    public int getAppVersion()
    {
        return sharedPreferences.getInt( "appVersion" , -1);
    }


    public void incrementNotificationId(int count)
    {
        int counter = sharedPreferences.getInt( "notificationId" , 0);
        if(counter == 1000000)
            counter =0;
        counter += count;
        editor.putInt( "notificationId", counter);
        editor.commit();
    }

    public int getNotificationId()
    {
        return sharedPreferences.getInt( "notificationId" , 0);
    }

}
