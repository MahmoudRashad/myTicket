package com.example.myticket.Model.Network.Retrofit;

import com.example.myticket.Model.MainResult;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.Chairs.ChairResponse2;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Comments;

import com.example.myticket.Model.Network.DataModel.DetailsCinema.DetailsCinema;
import com.example.myticket.Model.Network.DataModel.EditUserData.EditUserDataResponse;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MapModel.NearByFullModel;
import com.example.myticket.Model.Network.DataModel.MoviesList.MoviesList;
import com.example.myticket.Model.Network.DataModel.MyTickets.MyTicketsResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ReserveCinemaResponse;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.DataModel.Search.CategoryResult;
import com.example.myticket.Model.Network.DataModel.Search.SearchResponce;
import com.example.myticket.Model.Network.DetailsMovie.DetailsMovie;
import com.example.myticket.Model.Network.StadiumModel.Match.MainHomeMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatchDetails;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainChairs;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ReservationMain;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumDetailsByID;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.google.gson.JsonElement;

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
    Call<ForgetPasswordResponce> forgetPassword(@Header("lang") String lang,
                                                @QueryMap Map<String, String> queryMap);


    @GET("slider")
    Call<SliderResponce> mainSlider();

    @GET("home")
    Call<MainResult> homeResponce();

    @POST("show_comments")
    Call<Comments> getAllComments(@QueryMap Map<String, String> queryMap);

    @POST("make_comment")
    Call<BaseNoResult> submitComment(@Header("lang") String lang,@Header("Authorization") String token,@QueryMap Map<String, String> queryMap);

    @POST("make_rate")
    Call<BaseNoResult> makeRate(@Header("lang") String lang, @Header("Authorization") String authTtoken,@QueryMap Map<String, String> queryMap);

    @POST("moving_list")
    Call<MoviesList> getCinemaMoviesList(@QueryMap Map<String, String> queryMap);

    @POST("more_info")
    Call<DetailsCinema> getCinemaDetails(@QueryMap Map<String, String> queryMap);

    @POST("more_info")
    Call<DetailsMovie> getMovieDetails(@QueryMap Map<String, String> queryMap);

    @GET("category")
    Call<CategoryResult> getCategories();







//    @POST("save_editprofile")
//    Call<GeneralApiesponse> saveEditProfile(@Header("lang") String lang,
//                                            @Header("Authorization") String authorization,
//                                            @QueryMap Map<String, String> queryMap);
//


    @Multipart
    @POST("save_editprofile")
    Call<EditUserDataResponse> saveUserImage(@Header("lang") String lang,
                                             @Header("Authorization") String authorization,
                                             @Part MultipartBody.Part image,
                                             @QueryMap Map<String, String> queryMap);


    @POST("save_editprofile")
    Call<EditUserDataResponse> saveEditProfile(@Header("lang") String lang,
                                           @Header("Authorization") String authorization,
                                        @QueryMap Map<String, String> queryMap);


    @POST("reserve_cinema")
    Call<ReserveCinemaResponse> getReserveCinema(@Header("lang") String lang,
                                                @Header("Authorization") String authorization,
                                                @QueryMap Map<String, String> queryMap);
    @POST("search")
    Call<SearchResponce> Search(@QueryMap Map<String, String> queryMap);


    @POST("reserve_date")
    Call<ReserveCinemaResponse> getReserveDate(@Header("lang") String lang,
                                                 @Header("Authorization") String authorization,
                                                 @QueryMap Map<String, String> queryMap);

    @POST("reserve_time")
    Call<ReserveCinemaResponse> getReserveTime(@Header("lang") String lang,
                                               @Header("Authorization") String authorization,
                                               @QueryMap Map<String, String> queryMap);


    @POST("chair")
    Call<ChairResponse2> getChairsCinema(@Header("lang") String lang,
                                         @Header("Authorization") String authorization,
                                         @QueryMap Map<String, String> queryMap);

    @POST("change_password")
    Call<ForgetPasswordResponce> changePassword(@Header("lang") String lang,
                                                @Header("Authorization") String authorization,
                                                @QueryMap Map<String, String> queryMap);

    @POST("confirm_reservation")
    Call<GeneralApiesponse> confirmReservation(@Header("Authorization") String authorization ,
                                     @Header("lang") String lang,
                                     @Body JsonElement jsonObj);


    @GET("mytickets")
    Call<MyTicketsResponse> getMyTickets(@Header("lang") String lang,
                                            @Header("Authorization") String authorization);

    @POST("type_chair")
    Call<ChairResponse> getChairType(@Header("lang") String lang,
                                               @Header("Authorization") String authorization,
                                               @QueryMap Map<String, String> queryMap);

    ////// Stadiums Api ////////////

    @GET("stadium_list")
    Call<StadiumListMain> getStadiumsList();

    @GET("stadium_slider")
    Call<MainMatches> getMatchesSlider();

    @POST("matches")
    Call<MainHomeMatches> getHomeMatches(@Header("lang") String lang, @Header("Authorization") String authTtoken,@QueryMap Map<String, String> queryMap);

    @POST("stadium_detail")
    Call<StadiumDetailsByID> getStadiumDetails(@QueryMap Map<String, String> queryMap);

    @POST("search_stadium")
    Call<StadiumListMain> getStadSearchResults(@QueryMap Map<String, String> queryMap);

    @POST("search_team")
    Call<MainMatches> getTeamSearchResults(@QueryMap Map<String, String> queryMap);

    @POST("match_follow")
    Call<BaseNoResult> follow(@Header("lang") String lang, @Header("Authorization") String authTtoken,@QueryMap Map<String, String> queryMap);

    @POST("match_detail")
    Call<MainMatches> getMatchDetails(@Header("Authorization") String authTtoken,@QueryMap Map<String, String> queryMap);

    @POST("show_blocks")
    Call<ReservationMain> getBlocks(@Header("Authorization") String authTtoken, @QueryMap Map<String, String> queryMap);

    @POST("show_chair")
    Call<MainChairs> getChairs(@Header("Authorization") String authTtoken, @QueryMap Map<String, String> queryMap);










}
