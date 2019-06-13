package com.example.myticket.Model.Network.Retrofit;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordModel;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.LoginModel.ModelLogin;
import com.example.myticket.Model.Network.DataModel.LoginModel.User;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MapModel.NearByFullModel;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.DataModel.Resgister.UserRegister;

import retrofit2.Call;

public class ApiClient {
    String API_MAP_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";

    String latlng;
    Integer radius;
    String placeType;
    String keyword;
    String apiKey;
    onResponceInterface onResponceInterface;
    UserRegister userRegister;
    User userLogin;
    ForgetPasswordModel forgetPasswordModel;
    SliderResponce sliderResponce;
    Context context;
    public ApiClient(String latlng, Integer radius, String placeType, String keyword, String apiKey,onResponceInterface onResponceInterfacem,
                     Context context) {
        this.latlng = latlng;
        this.radius = radius;
        this.placeType = placeType;
        this.keyword = keyword;
        this.apiKey = apiKey;
        this.onResponceInterface = onResponceInterface;
        this.context = context;
    }
    public ApiClient(UserRegister userRegister, onResponceInterface onResponceInterface, Context context){
        this.userRegister = userRegister;
        this.onResponceInterface = onResponceInterface;
        this.context = context;
    }

    public ApiClient(User loginUser, onResponceInterface onResponceInterface, Context context){
        this.userLogin = loginUser;
        this.onResponceInterface = onResponceInterface;
        this.context = context;
    }

    public ApiClient(ForgetPasswordModel forgetPasswordModeldel, onResponceInterface onResponceInterface, Context context){
        this.context = context;
        this.forgetPasswordModel = forgetPasswordModeldel;
        this.onResponceInterface = onResponceInterface;
    }

    public ApiClient(onResponceInterface onResponceInterface){
        this.onResponceInterface = onResponceInterface;
    }

    public void initializeClient() {
        ApiInterface client = ApiSingelton.getInstance(API_MAP_URL).getClient();

        Call<NearByFullModel> call =
                client.getPlaces(latlng,radius,placeType,keyword,apiKey);
        ApiCalling apiCalling = new ApiCalling();
        apiCalling.apiCall(call,onResponceInterface);

    }

    //iscoapps.com/cinema/api/register
    public void initializeClientRegister(String API_REG_URL) {
        ApiInterface client = ApiSingelton.getInstance(API_REG_URL).getClient();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.shared",Context.MODE_PRIVATE);
        String lang = sharedPreferences.getString("lang","ar");

        Call<MainResponceReg> call =
                client.registerResult(lang,userRegister);
        ApiCalling apiCalling = new ApiCalling();
        apiCalling.regCall(call,onResponceInterface);

    }

    //iscoapps.com/cinema/api/register
    public void initializeClientLogin(String API_LOGIN_URL) {
        ApiInterface client = ApiSingelton.getInstance(API_LOGIN_URL).getClient();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.shared",Context.MODE_PRIVATE);
        String lang = sharedPreferences.getString("lang","ar");

        Call<ModelLogin> call =
                client.loginResult(lang,userLogin);
        ApiCalling apiCalling = new ApiCalling();
        apiCalling.loginCall(call,onResponceInterface);

    }

    public void initializeClientForget(String API_FORGET_URL) {
        ApiInterface client = ApiSingelton.getInstance(API_FORGET_URL).getClient();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.shared",Context.MODE_PRIVATE);
        String lang = sharedPreferences.getString("lang","ar");

        Call<ForgetPasswordResponce> call =
                client.forgetPassword(lang, forgetPasswordModel);
        ApiCalling apiCalling = new ApiCalling();
        apiCalling.forgetCall(call,onResponceInterface);

    }
    public void initializeClientMainSlider(String API_SLIDER_URL) {
        ApiInterface client = ApiSingelton.getInstance(API_SLIDER_URL).getClient();
        Call<SliderResponce> call =
                client.mainSlider();
        ApiCalling apiCalling = new ApiCalling();
        apiCalling.mainSliderCall(call,onResponceInterface);

    }
}
