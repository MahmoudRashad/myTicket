package com.example.myticket.Model.Network.Retrofit;

import com.example.myticket.Model.MainResult;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Comments;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MapModel.NearByFullModel;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
    Call<MainResponceReg> registerResult(@Header("lang") String lang,@QueryMap Map<String, String> queryMap);


    @POST("login")
    Call<MainResponceReg> loginResult(@Header("lang") String lang,@QueryMap Map<String, String> queryMap);



    @POST("forget_password")
    Call<ForgetPasswordResponce> forgetPassword(@Header("lang") String lang, String email);


    @GET("slider")
    Call<SliderResponce> mainSlider();

    @GET("home")
    Call<MainResult> homeResponce();

    @POST("show_comments")
    Call<Comments> getAllComments(@Body String filmId);

    @POST("make_comment")
    Call<BaseNoResult> submitComment(@Header("lang") String lang,@Header("Authorization") String token,@QueryMap Map<String, String> queryMap);

    @POST("make_rate")
    Call<BaseNoResult> makeRate(@Header("lang") String lang, @Header("Authorization") String authTtoken,@QueryMap Map<String, String> queryMap);


//    @POST("save_editprofile")
//    Call<GeneralApiesponse> saveEditProfile(@Header("lang") String lang,
//                                            @Header("Authorization") String authorization,
//                                            @QueryMap Map<String, String> queryMap);
//


    @Multipart
    @POST("save_editprofile")
    Call<GeneralApiesponse> saveUserImage(@Header("lang") String lang,
                                           @Header("Authorization") String authorization,
                                          @Part MultipartBody.Part image,
                                        @QueryMap Map<String, String> queryMap);


    @POST("save_editprofile")
    Call<GeneralApiesponse> saveEditProfile(@Header("lang") String lang,
                                           @Header("Authorization") String authorization,
                                        @QueryMap Map<String, String> queryMap);



}
