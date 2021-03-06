package com.example.myticket.Model.Network.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.myticket.Enum.ClubReservationEnum;
import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.MainResult;
import com.example.myticket.Model.Network.DataModel.BaseNoResult.BaseNoResult;
import com.example.myticket.Model.Network.DataModel.Chairs.ChairResponse2;
import com.example.myticket.Model.Network.DataModel.Chairs.MainHallType;
import com.example.myticket.Model.Network.DataModel.CommentsModel.Comments;
import com.example.myticket.Model.Network.DataModel.DetailsCinema.DetailsCinema;
import com.example.myticket.Model.Network.DataModel.EditUserData.EditUserDataResponse;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.GeneralApiesponse;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MoviesList.MoviesList;
import com.example.myticket.Model.Network.DataModel.MyTickets.MyTicketsResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ChairResponse;
import com.example.myticket.Model.Network.DataModel.ReserveModel.ReserveCinemaResponse;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.DataModel.Search.CategoryResult;
import com.example.myticket.Model.Network.DataModel.Search.SearchResponce;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;
import com.example.myticket.Model.Network.DetailsMovie.DetailsMovie;
import com.example.myticket.Model.Network.StadiumModel.Match.MainHomeMatches;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatchDetails;
import com.example.myticket.Model.Network.StadiumModel.Match.MainMatches;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketMain;
import com.example.myticket.Model.Network.StadiumModel.MyTicket.MyTicketMainDetail;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainChairs;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainLimit;
import com.example.myticket.Model.Network.StadiumModel.Reservation.MainReservationDetails;
import com.example.myticket.Model.Network.StadiumModel.Reservation.ReservationMain;
import com.example.myticket.Model.Network.StadiumModel.ResultTicketsStad.ResultTicketsStad;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumDetailsByID;
import com.example.myticket.Model.Network.StadiumModel.StadiumList.StadiumListMain;
import com.example.myticket.View.Activity.Login;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalling
{

    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private Context context;
    private SessionManager sessionManager;
    private String lang;

    public ApiCalling(Context context )
    {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient(false).create(ApiInterface.class);
        sessionManager = new SessionManager(context);
        lang = sessionManager.getDeviceLanguage();
    }

//    public void apiCall(Call<NearByFullModel> call, final onResponceInterface onResponceInterface) {
//
//        call.enqueue(new Callback<NearByFullModel>() {
//            @Override
//            public void onResponse(Call<NearByFullModel> call, final Response<NearByFullModel> response) {
//                if (response.isSuccessful()) {
//                    Log.v("ApiSuccess", "FirstSuccess");
//                    NearByFullModel nearByFullModel = response.body();
//                    onResponceInterface.onSuccess(nearByFullModel);
//                    Log.e("ApiSuccess", "Success");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NearByFullModel> call, final Throwable t) {
//                Log.e("ApiError","Failed");
//                Log.e("ApiError",t.getMessage());
//
//            }
//        });
//    }

    public void register(Map<String, String> queryMap , final GeneralListener registerListener )
    {
        Call<MainResponceReg> call;
     //   MultipartBody.Part body ;
//        if(sessionManager.getUserType() == UserTypeEnum.real.getValue())
//        {
//
//            String path = queryMap.get("imageReal");
//            String extension = path.substring(path.lastIndexOf(".") + 1);
//            final String new_file_name = UUID.randomUUID().toString() + "." + extension;
//
////            Log.e("newfilename**", new_file_name);
//            File file = new File(path);
//
//            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//            body = MultipartBody.Part.createFormData("image", new_file_name, reqFile);
//
//            call = apiInterface.registerReal(lang ,body , queryMap  );
//        }
//        else {
            call = apiInterface.registerResult( lang  , queryMap );
//        }

        call.enqueue(new Callback<MainResponceReg>() {
            @Override
            public void onResponse(Call<MainResponceReg> call, Response<MainResponceReg> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        registerListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }
                    else if (response.code() == 401) {
                        // Handle unauthorized
                        Log.e("onResponse" ,"logout");
                        Intent   i= new Intent(context , Login.class);

                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(i);
                    }
                    else
                    {
                        registerListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                response.body().getMessage() , response.body());
                    }
                }
                else
                {
                    registerListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }

            }
            @Override
            public void onFailure(Call<MainResponceReg> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    registerListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    registerListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

//    public void regCall(Call<MainResponceReg> call, final onResponceInterface onResponceInterface) {
//        call.enqueue(new Callback<MainResponceReg>() {
//            @Override
//            public void onResponse(Call<MainResponceReg> call, final Response<MainResponceReg> response) {
//                if (response.isSuccessful()) {
//
//                            Log.v("ApiRegSuccess", "RegSuccess");
//                            MainResponceReg mainResponceReg = response.body();
//                            onResponceInterface.onSuccess(mainResponceReg);
//                            Log.e("ApiRegSuccess", "RegSuccess");
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MainResponceReg> call, Throwable t)
//            {
//                onResponceInterface.onFail(null);
//                Log.e("RegError","Failed");
//                Log.e("RegError",t.getMessage());
//            }
//        });
//
//    }


    public void editUserData(String authToken ,
                             Map<String, String> queryMap ,
                             final GeneralListener generalListener )
    {

        Call<EditUserDataResponse> call;
        MultipartBody.Part body ;
        if( queryMap.get("imagePath") != null )
        {
            String path = queryMap.get("imagePath");
            String extension = path.substring(path.lastIndexOf(".") + 1);
            final String new_file_name = UUID.randomUUID().toString() + "." + extension;

//            Log.e("newfilename**", new_file_name);
            File file = new File(path);

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            body = MultipartBody.Part.createFormData("image", new_file_name, reqFile);

            call = apiInterface.saveUserImage(lang ,authToken ,body , queryMap  );
        }
        else {
            call = apiInterface.saveEditProfile( lang ,authToken , queryMap );
        }

        call.enqueue(new Callback<EditUserDataResponse>() {
            @Override
            public void onResponse(Call<EditUserDataResponse> call, Response<EditUserDataResponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }

            }
            @Override
            public void onFailure(Call<EditUserDataResponse> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void login(String email , String password, String mac
            , final GeneralListener generalListener
                      )
    {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("username",email);
        queryMap.put("password",password);

        String token = "test";//FirebaseInstanceId.getInstance().getToken();

//        if(tokenTest != token)
//            tokenTest = token;

        // 1 for android
        queryMap.put("device_type","1");
        queryMap.put("device_token",token);
        queryMap.put("mac",mac);


        Call<MainResponceReg> call = apiInterface.loginResult(lang,queryMap);
        call.enqueue(new Callback<MainResponceReg>() {
            @Override
            public void onResponse(Call<MainResponceReg> call, Response<MainResponceReg> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess())
                {

                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());


//                        FirebaseMessaging.getInstance().subscribeToTopic("announcements")
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (!task.isSuccessful()) {
//                                            Log.e("testFCM", "faile subscribe topic");
//                                        }
//                                        else
//                                        {
//                                            Log.e("testFCM", "Success subscribe topic");
//                                        }
//                                    }
//                                });

                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }

            }
            @Override
            public void onFailure(Call<MainResponceReg> call, Throwable t)
            {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void mainSliderCall(final GeneralListener generalListener) {

        Call<SliderResponce> call = apiInterface.mainSlider();
        call.enqueue(new Callback<SliderResponce>() {
            @Override
            public void onResponse(Call<SliderResponce> call, Response<SliderResponce> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<SliderResponce> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void homeApiCall(final GeneralListener generalListener) {
        Call<MainResult> call = apiInterface.homeResponce();
        call.enqueue(new Callback<MainResult>() {
        @Override
        public void onResponse(Call<MainResult> call, Response<MainResult> response) {
            if (response.isSuccessful()) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }
            }
        }

        @Override
        public void onFailure(Call<MainResult> call, Throwable t) {
            //fail internet connection
            if (t instanceof IOException)
            {
                Log.e("ApiCheck**" , "no internet connection");
                generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                        t.getMessage() , null);
            }
            //fail conversion issue
            else {
                generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                        t.getMessage() , null);
            }
        }
    });
}
    public void forgetPasswordCall(String email , final GeneralListener generalListener) {
       Map<String,String> queryMap = new HashMap<>();
       queryMap.put("email" , email);
        Call<ForgetPasswordResponce> call = apiInterface.forgetPassword(lang,queryMap);
        call.enqueue(new Callback<ForgetPasswordResponce>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponce> call, Response<ForgetPasswordResponce> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponce> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }
    public void showAllReviews(String filmId , final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("film_id",filmId);
        Call<Comments> call = apiInterface.getAllComments(map);
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }
    public void makeRate(String authToken , String filmId, String rate, final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("film_id",filmId);
        map.put("rate",rate);
        map.put("comment",null);
        Call<BaseNoResult> call = apiInterface.makeRate(lang,authToken,map);
        call.enqueue(new Callback<BaseNoResult>() {
            @Override
            public void onResponse(Call<BaseNoResult> call, Response<BaseNoResult> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseNoResult> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }
    public void submitComment(String authToken ,String filmId, String comment, final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("film_id",filmId);
        map.put("comment",comment);
        Call<BaseNoResult> call = apiInterface.submitComment(lang,authToken,map);
        call.enqueue(new Callback<BaseNoResult>() {
            @Override
            public void onResponse(Call<BaseNoResult> call, Response<BaseNoResult> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseNoResult> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }
    public void search(String query, final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("name",query);
        Call<SearchResponce> call = apiInterface.Search(map);
        call.enqueue(new Callback<SearchResponce>() {
            @Override
            public void onResponse(Call<SearchResponce> call, Response<SearchResponce> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResponce> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void getCinemaMoviesList(String query, final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("cinema_id",query);
        Call<MoviesList> call = apiInterface.getCinemaMoviesList(map);
        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void getCinemaDetails(String query, final GeneralListener generalListener) {
        Map<String, String> map = new HashMap<>();
        map.put("id", query);
        Call<DetailsCinema> call = apiInterface.getCinemaDetails(map);
        call.enqueue(new Callback<DetailsCinema>() {
            @Override
            public void onResponse(Call<DetailsCinema> call, Response<DetailsCinema> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailsCinema> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });
    }
    public void getMovieDetails(String query, final GeneralListener generalListener) {
        Map<String, String> map = new HashMap<>();
        map.put("id", query);
        Call<DetailsMovie> call = apiInterface.getMovieDetails(map);
        call.enqueue(new Callback<DetailsMovie>() {
            @Override
            public void onResponse(Call<DetailsMovie> call, Response<DetailsMovie> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailsMovie> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });
    }
    public void getSearchCategories(final GeneralListener generalListener) {
        Call<CategoryResult> call = apiInterface.getCategories();
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });
    }
//    public void loginCall(Call<ModelLogin> call, final onResponceInterface onResponceInterface) {
//        call.enqueue(new Callback<ModelLogin>() {
//            @Override
//            public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
//                if (response.isSuccessful()) {
//
//                    Log.v("ApiRegSuccess", "RegSuccess");
//                    ModelLogin mainResponceReg = response.body();
//                    onResponceInterface.onSuccess(mainResponceReg);
//                    Log.e("ApiRegSuccess", "RegSuccess");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ModelLogin> call, Throwable t) {
//                    Log.e("RegError","Failed");
//                    Log.e("RegError",t.getMessage());
//                }
//
//        });
//
//    }
//    public void forgetCall(Call<ForgetPasswordResponce> call, final onResponceInterface onResponceInterface) {
//        call.enqueue(new Callback<ForgetPasswordResponce>() {
//            @Override
//            public void onResponse(Call<ForgetPasswordResponce> call, Response<ForgetPasswordResponce> response) {
//                if (response.isSuccessful()) {
//
//                    Log.v("ApiForgetSuccess", "ForgetSuccess");
//                  //  ForgetPasswordResponce mainResponceReg = response.body();
//                    onResponceInterface.onSuccess(response.body());
//                    Log.e("ApiRegSuccess", "RegSuccess");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ForgetPasswordResponce> call, Throwable t) {
//                Log.e("RegError","Failed");
//                Log.e("RegError",t.getMessage());
//            }
//        });
//
//    }
//

//public void ApiCall(Call<MainResult> call, final onResponceInterface onResponceInterface) {
//    call.enqueue(new Callback<MainResult>() {
//        @Override
//        public void onResponse(Call<MainResult> call, Response<MainResult> response) {
//            if (response.isSuccessful()) {
//                Log.v("Success", "Success");
//                onResponceInterface.onSuccess(response.body());
//                Log.e("Success", "Success");
//            }
//        }
//
//        @Override
//        public void onFailure(Call<MainResult> call, Throwable t) {
//            Log.e("Error","API ERROR");
//            Log.e("Error",t.getMessage());
//        }
//    });
//}



    public void getCinemasOfMovie(String authToken ,

                             Map<String, String> queryMap ,
                             final GeneralListener generalListener )
    {



        Call<ReserveCinemaResponse> call =
                apiInterface.getReserveCinema( lang ,authToken , queryMap );


        call.enqueue(new Callback<ReserveCinemaResponse>() {
            @Override
            public void onResponse(Call<ReserveCinemaResponse> call, Response<ReserveCinemaResponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }
            }
            @Override
            public void onFailure(Call<ReserveCinemaResponse> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }


    public void getChairs(String authToken ,
                                  Map<String, String> queryMap ,
                                  final GeneralListener generalListener )
    {

        Call<ChairResponse2> call =
                apiInterface.getChairsCinema( lang ,authToken , queryMap );

        call.enqueue(new Callback<ChairResponse2>() {
            @Override
            public void onResponse(Call<ChairResponse2> call, Response<ChairResponse2> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }
            }
            @Override
            public void onFailure(Call<ChairResponse2> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }


    public void getDatesOfMovie(String authToken ,
                                  Map<String, String> queryMap ,
                                  final GeneralListener generalListener )
    {



        Call<ReserveCinemaResponse> call =
                apiInterface.getReserveDate( lang ,authToken , queryMap );


        call.enqueue(new Callback<ReserveCinemaResponse>() {
            @Override
            public void onResponse(Call<ReserveCinemaResponse> call, Response<ReserveCinemaResponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }
            }
            @Override
            public void onFailure(Call<ReserveCinemaResponse> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }


    public void getTimesOfMovie(String authToken ,
                                Map<String, String> queryMap ,
                                final GeneralListener generalListener )
    {



        Call<ReserveCinemaResponse> call =
                apiInterface.getReserveTime( lang ,authToken , queryMap );


        call.enqueue(new Callback<ReserveCinemaResponse>() {
            @Override
            public void onResponse(Call<ReserveCinemaResponse> call, Response<ReserveCinemaResponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }
            }
            @Override
            public void onFailure(Call<ReserveCinemaResponse> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }


    public void changePassword(String authToken ,  Map<String,String>queryMap , final GeneralListener generalListener)
    {

        Call<ForgetPasswordResponce> call = apiInterface.changePassword(lang,
                authToken,queryMap);
        call.enqueue(new Callback<ForgetPasswordResponce>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponce> call, Response<ForgetPasswordResponce> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue(),
                            null, response.body());
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponce> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }


    public void confirmReservation(String authorization , String lang , List<ResultTickets> resultCartItemList
            , final GeneralListener generalListener )
    {

        Gson gson = new Gson();

        JsonElement jsonObject = gson.toJsonTree(resultCartItemList);

        Map<String, JsonElement> queryMap = new HashMap<>();
        queryMap.put("ticket" , jsonObject);
//        String temp = "ddjkdd";
//        queryMap.put("user_note" , gson.toJsonTree(orderNote));
//        queryMap.put("products" , jsonObject);

        jsonObject = gson.toJsonTree(queryMap);
//        JSONObject obj=new JSONObject(queryMap);
        String json = gson.toJson(jsonObject);
        Log.e( "request**", json);

//        HashMap<String, JSONObject > queryMapFinal = new HashMap<>();
//        queryMapFinal.put("json" , obj);

        Call<GeneralApiesponse> call = apiInterface.confirmReservation(
                authorization , lang  , jsonObject  );

        call.enqueue(new Callback<GeneralApiesponse>() {
            @Override
            public void onResponse(Call<GeneralApiesponse> call, Response<GeneralApiesponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                            null , response.body());
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            null , null);
                }

            }
            @Override
            public void onFailure(Call<GeneralApiesponse> call, Throwable t)
            {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() ,null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() ,null);
                }
            }
        });
    }


    public void getMyTickets(String authToken ,
                                final GeneralListener generalListener )
    {

        Call<MyTicketsResponse> call =
                apiInterface.getMyTickets( lang ,authToken );


        call.enqueue(new Callback<MyTicketsResponse>() {
            @Override
            public void onResponse(Call<MyTicketsResponse> call, Response<MyTicketsResponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }
            }
            @Override
            public void onFailure(Call<MyTicketsResponse> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }


    public void getTypeOfHall(String authToken ,
                                Map<String, String> queryMap ,
                                final GeneralListener generalListener )
    {
        Call<MainHallType> call =
                apiInterface.getHallType( lang ,authToken , queryMap );

        call.enqueue(new Callback<MainHallType>() {
            @Override
            public void onResponse(Call<MainHallType> call, Response<MainHallType> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    if(response.body().getSuccess())
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                                null , response.body());
                    }

                    else
                    {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue() ,
                                null , response.body());
                    }
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            response.body().getMessage() , null);
                }
            }
            @Override
            public void onFailure(Call<MainHallType> call, Throwable t)
            {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }



    /////////////////////////////////////Stadiums////////////////////////////////////////////

    public void StadiumsListCall(final GeneralListener generalListener) {

        Call<StadiumListMain> call = apiInterface.getStadiumsList();
        call.enqueue(new Callback<StadiumListMain>() {
            @Override
            public void onResponse(Call<StadiumListMain> call, Response<StadiumListMain> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<StadiumListMain> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void getMatchesSlider(final GeneralListener generalListener) {

        Call<MainMatches> call = apiInterface.getMatchesSlider();
        call.enqueue(new Callback<MainMatches>() {
            @Override
            public void onResponse(Call<MainMatches> call, Response<MainMatches> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<MainMatches> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void getHomeMatches(String gameID
            , String flag
            , String lang
            , String auth, final GeneralListener generalListener) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("game_id" , gameID);
        queryMap.put("flag" , flag);
        Call<MainHomeMatches> call = apiInterface.getHomeMatches(lang, auth, queryMap);
        call.enqueue(new Callback<MainHomeMatches>() {
            @Override
            public void onResponse(Call<MainHomeMatches> call, Response<MainHomeMatches> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<MainHomeMatches> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void getStadiumDetail(String stadiumID ,final GeneralListener generalListener) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("id" , stadiumID);
        Call<StadiumDetailsByID> call = apiInterface.getStadiumDetails(queryMap);
        call.enqueue(new Callback<StadiumDetailsByID>() {
            @Override
            public void onResponse(Call<StadiumDetailsByID> call, Response<StadiumDetailsByID> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<StadiumDetailsByID> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void getTeamSearchResults(String query,final GeneralListener generalListener) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("team_name" , query);
        Call<MainMatches> call = apiInterface.getTeamSearchResults(queryMap);
        call.enqueue(new Callback<MainMatches>() {
            @Override
            public void onResponse(Call<MainMatches> call, Response<MainMatches> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<MainMatches> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void getStadiumsSerachResults(String query,final GeneralListener generalListener) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("stadium_name" , query);
        Call<StadiumListMain> call = apiInterface.getStadSearchResults(queryMap);
        call.enqueue(new Callback<StadiumListMain>() {
            @Override
            public void onResponse(Call<StadiumListMain> call, Response<StadiumListMain> response) {
                Log.e("onResponse", response.raw().toString());
                if (response.body().getSuccess()) {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                            null, response.body());
                }
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                            response.body().getMessage(), response.body());
                }


            }

            @Override
            public void onFailure(Call<StadiumListMain> call, Throwable t) {
                Log.e("onResponse" ,call.request().toString());
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });
    }

    public void follow(String authToken , String matchID, final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("match_id",matchID);
        Call<BaseNoResult> call = apiInterface.follow(lang,authToken,map);
        call.enqueue(new Callback<BaseNoResult>() {
            @Override
            public void onResponse(Call<BaseNoResult> call, Response<BaseNoResult> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseNoResult> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void getMatchDetails(String id, String token,final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("match_id",id);
        Call<MainMatches> call = apiInterface.getMatchDetails(token,map);
        call.enqueue(new Callback<MainMatches>() {
            @Override
            public void onResponse(Call<MainMatches> call, Response<MainMatches> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MainMatches> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void getStadiumBlocks(String id, String token,final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("type_id",id);
        Call<ReservationMain> call = apiInterface.getBlocks("Bearer " + token,map);
        call.enqueue(new Callback<ReservationMain>() {
            @Override
            public void onResponse(Call<ReservationMain> call, Response<ReservationMain> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ReservationMain> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void getChairs(String id,String blockName,String matchId, String token,final GeneralListener generalListener) {
        Map<String,String> map = new HashMap<>();
        map.put("stadium_id",id);
        map.put("block_name",blockName);
        map.put("match_id",matchId);
        Call<MainChairs> call = apiInterface.getChairs(token,map);
        call.enqueue(new Callback<MainChairs>() {
            @Override
            public void onResponse(Call<MainChairs> call, Response<MainChairs> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    }
                    else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MainChairs> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() , null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() , null);
                }
            }
        });

    }

    public void clubReservation(String authorization ,
                                String lang ,
                                List<ResultTicketsStad> resultItemList,
            String key,
            Integer status
            , final GeneralListener generalListener )
    {

        Gson gson = new Gson();

        JsonElement jsonObject;

        Map<String, Object> queryMap = new HashMap<>();

        queryMap.put("status" , status);

        if( status == ClubReservationEnum.pending.getValue())
        {
             gson = new Gson();

             jsonObject = gson.toJsonTree(resultItemList);


            queryMap.put("ticket" , jsonObject);
        }
        else if(status == ClubReservationEnum.accept.getValue())
        {
            queryMap.put("key" , key);
        }


        jsonObject = gson.toJsonTree(queryMap);

        String json = gson.toJson(jsonObject);
        Log.e( "request**", json);


        Call<GeneralApiesponse> call = apiInterface.clubReservation(
                authorization , lang  , jsonObject);

        call.enqueue(new Callback<GeneralApiesponse>() {
            @Override
            public void onResponse(Call<GeneralApiesponse> call, Response<GeneralApiesponse> response)
            {
                Log.e("onResponse" ,response.raw().toString());
                if(response.isSuccessful())
                {
                    generalListener.getApiResponse(ErrorTypeEnum.noError.getValue() ,
                            null , response.body());
                }
                else if (response.code() == 401) {
                    // Handle unauthorized
                    Log.e("onResponse" ,"logout");
                    Intent   i= new Intent(context , Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }
                else
                {
                    generalListener.getApiResponse(ErrorTypeEnum.ServerCodeFail.getValue() ,
                            null , null);
                }

            }
            @Override
            public void onFailure(Call<GeneralApiesponse> call, Throwable t)
            {
                //fail internet connection
                if (t instanceof IOException)
                {
                    Log.e("ApiCheck**" , "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue() ,
                            t.getMessage() ,null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue() ,
                            t.getMessage() ,null);
                }
            }
        });
    }

    public void getMyTicketsStad(String authorization , String lang,final GeneralListener generalListener ) {
        Call<MyTicketMain> call = apiInterface.getMyTicketsStad(
                lang, authorization);

        call.enqueue(new Callback<MyTicketMain>() {
            @Override
            public void onResponse(Call<MyTicketMain> call, Response<MyTicketMain> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyTicketMain> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });

    }

    public void getMyTicketDetailStad(String matchId ,String authorization , String lang,final GeneralListener generalListener ) {

        Map<String,String> map = new HashMap<>();
        map.put("match_id",matchId);

        Call<MyTicketMainDetail> call = apiInterface.getMyTicketDetail(
                lang, authorization,map);

        call.enqueue(new Callback<MyTicketMainDetail>() {
            @Override
            public void onResponse(Call<MyTicketMainDetail> call, Response<MyTicketMainDetail> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyTicketMainDetail> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });

    }

    public void getReservationDetails(String matchId ,String authorization , String lang,final GeneralListener generalListener ) {

        Map<String,String> map = new HashMap<>();
        map.put("match_id",matchId);

        Call<MainReservationDetails> call = apiInterface.getReservationDetails(
                lang, authorization,map);

        call.enqueue(new Callback<MainReservationDetails>() {
            @Override
            public void onResponse(Call<MainReservationDetails> call, Response<MainReservationDetails> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MainReservationDetails> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });

    }

    public void getLimit(String matchId ,String authorization , String lang,final GeneralListener generalListener ) {

        Map<String,String> map = new HashMap<>();
        map.put("match_id",matchId);

        Call<MainLimit> call = apiInterface.getLimit(
                lang, authorization,map);

        call.enqueue(new Callback<MainLimit>() {
            @Override
            public void onResponse(Call<MainLimit> call, Response<MainLimit> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MainLimit> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });

    }


    public void getChairsOfHall(String cinemaId , String hall , String type,String authorization ,
                                String lang,final GeneralListener generalListener ) {

        Map<String,String> map = new HashMap<>();
        map.put("cinema_id",cinemaId);
        map.put("hall",hall);
        map.put("type",type);

        Call<ChairResponse2> call = apiInterface.getChairsHall(lang,authorization,map);

        call.enqueue(new Callback<ChairResponse2>() {
            @Override
            public void onResponse(Call<ChairResponse2> call, Response<ChairResponse2> response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse", response.raw().toString());
                    if (response.body().getSuccess()) {
                        generalListener.getApiResponse(ErrorTypeEnum.noError.getValue(),
                                null, response.body());
                    } else {
                        generalListener.getApiResponse(ErrorTypeEnum.BackendLogicFail.getValue(),
                                response.body().getMessage(), response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ChairResponse2> call, Throwable t) {
                //fail internet connection
                if (t instanceof IOException) {
                    Log.e("ApiCheck**", "no internet connection");
                    generalListener.getApiResponse(ErrorTypeEnum.InternetConnectionFail.getValue(),
                            t.getMessage(), null);
                }
                //fail conversion issue
                else {
                    generalListener.getApiResponse(ErrorTypeEnum.other.getValue(),
                            t.getMessage(), null);
                }
            }
        });

    }
 }
