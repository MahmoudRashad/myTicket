package com.example.myticket.Network.Retrofit;

import com.example.myticket.Model.ForgetPasswordResponce.ForgetPasswordModel;
import com.example.myticket.Model.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.LoginModel.User;
import com.example.myticket.Model.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.MapModel.NearByFullModel;
import com.example.myticket.Model.LoginModel.ModelLogin;
import com.example.myticket.Model.Resgister.MainResponceReg;
import com.example.myticket.Model.Resgister.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("json?")
    Call<NearByFullModel> getPlaces(
            @Query("location") String latlng,
            @Query("radius") Integer radius,
            @Query("type") String placeType,
            @Query("keyword") String keyword,
            @Query("key") String apiKey

    );


    @POST("register")
    Call<MainResponceReg> registerResult(@Header("lang") String lang,@Body UserRegister userRegister);


    @POST("login")
    Call<ModelLogin> loginResult(@Header("lang") String lang,@Body User userLogin);



    @POST("forget_password")
    Call<ForgetPasswordResponce> forgetPassword(@Header("lang") String lang, @Body ForgetPasswordModel model);


    @GET("slider")
    Call<SliderResponce> mainSlider();



}
