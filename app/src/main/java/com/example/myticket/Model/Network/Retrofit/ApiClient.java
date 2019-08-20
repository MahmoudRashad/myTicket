package com.example.myticket.Model.Network.Retrofit;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myticket.Model.MainResult;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordModel;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.LoginModel.ModelLogin;
import com.example.myticket.Model.Network.DataModel.LoginModel.User;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MapModel.NearByFullModel;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.Model.Network.DataModel.Resgister.UserRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    String API_MAP_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
//
//    String latlng;
//    Integer radius;
//    String placeType;
//    String keyword;
//    String apiKey;
//    onResponceInterface onResponceInterface;
//    UserRegister userRegister;
//    User userLogin;
//    ForgetPasswordModel forgetPasswordModel;
//    SliderResponce sliderResponce;

//    public ApiClient(String latlng, Integer radius, String placeType, String keyword, String apiKey,onResponceInterface onResponceInterfacem,
//                     Context context) {
//        this.latlng = latlng;
//        this.radius = radius;
//        this.placeType = placeType;
//        this.keyword = keyword;
//        this.apiKey = apiKey;
//        this.onResponceInterface = onResponceInterface;
//        this.context = context;
//    }
//    public ApiClient(UserRegister userRegister, onResponceInterface onResponceInterface, Context context){
//        this.userRegister = userRegister;
//        this.onResponceInterface = onResponceInterface;
//        this.context = context;
//    }

//    public ApiClient(User loginUser, onResponceInterface onResponceInterface, Context context){
//        this.userLogin = loginUser;
//        this.onResponceInterface = onResponceInterface;
//        this.context = context;
//    }



//    public ApiClient(ForgetPasswordModel forgetPasswordModeldel, onResponceInterface onResponceInterface, Context context){
//        this.context = context;
//        this.forgetPasswordModel = forgetPasswordModeldel;
//        this.onResponceInterface = onResponceInterface;
//    }

//    public ApiClient(onResponceInterface onResponceInterface){
//        this.onResponceInterface = onResponceInterface;
//    }
//Map not working because I changed the url
//    public void initializeClient() {
//        ApiInterface client = ApiSingelton.getInstance(API_MAP_URL).getClient();
//
//        Call<NearByFullModel> call =
//                client.getPlaces(latlng,radius,placeType,keyword,apiKey);
//        ApiCalling apiCalling = new ApiCalling();
//        apiCalling.apiCall(call,onResponceInterface);
//
//    }

    //iscoapps.com/cinema/api/register
//    public void initializeClientRegister() {
//        ApiInterface client = ApiSingelton.getInstance().getClient();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.shared",Context.MODE_PRIVATE);
//        String lang = sharedPreferences.getString("lang","ar");
//
//        Call<MainResponceReg> call =
//                client.registerResult(lang,userRegister);
//        ApiCalling apiCalling = new ApiCalling();
//        apiCalling.regCall(call,onResponceInterface);
//
//    }

    //iscoapps.com/cinema/api/register
//    public void initializeClientLogin() {
//        ApiInterface client = ApiSingelton.getInstance().getClient();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.shared",Context.MODE_PRIVATE);
//        String lang = sharedPreferences.getString("lang","ar");
//
//        Call<ModelLogin> call =
//                client.loginResult(lang,userLogin);
//        ApiCalling apiCalling = new ApiCalling();
//        apiCalling.loginCall(call,onResponceInterface);
//
//    }

//    public void initializeClientForget() {
//        ApiInterface client = ApiSingelton.getInstance().getClient();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.shared",Context.MODE_PRIVATE);
//        String lang = sharedPreferences.getString("lang","ar");
//
//        Call<ForgetPasswordResponce> call =
//                client.forgetPassword(lang, forgetPasswordModel);
//        ApiCalling apiCalling = new ApiCalling();
//        apiCalling.forgetCall(call,onResponceInterface);
//
//    }
//    public void initializeClientMainSlider() {
//        ApiInterface client = ApiSingelton.getInstance().getClient();
//        Call<SliderResponce> call =
//                client.mainSlider();
//        ApiCalling apiCalling = new ApiCalling();
//        apiCalling.mainSliderCall(call,onResponceInterface);
//
//    }
//    public void intializeHomeResponce(){
//        ApiInterface client = ApiSingelton.getInstance().getClient();
//        Call<MainResult> call =
//                client.homeResponce();
//        ApiCalling apiCalling = new ApiCalling();
//        apiCalling.ApiCall(call,onResponceInterface);
//    }

    //-----------------------------amr code---------------------------------//

    public final String BASE_URL = "http://iscoapps.com/myticket_dashboard/cinema_api/api/";
    public final String BASE_URL_Firebase = "https://fcm.googleapis.com/";

    // public  final String PATH_URL = "/androidApi/";
    private  Retrofit retrofit = null;
    private Context context;
//    private SessionManager sessionManager;

    public ApiClient(Context context)
    {
        this.context = context;
//        sessionManager = new SessionManager(context);
    }

//    public Retrofit getClientAuth(/*String portNumber*/)
//    {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        if(retrofit == null)
//        {
//            String url = BASE_URL/*portNumber + PATH_URL*/;
//            Log.e("url**" , url);
//            Log.e("token**" , sessionManager.getUserToken()
//            );
//
//
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException
//                {
//                    Request newRequest  = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer " + sessionManager.getUserToken())
////                            .addHeader("Content-Type" , "application/json")
////                            .addHeader("X-Requested-With" , "XMLHttpRequest")
//                            .build();
//                    return chain.proceed(newRequest);
//                }
//            }).build();
//
//            retrofit = new Retrofit.Builder()
//                    .client(client)
//                    .baseUrl(url)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit;
//    }

    public Retrofit getClient(/*String portNumber*/ Boolean isFireBase)
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .build();

        if(retrofit == null)
        {
            String url;

            if( isFireBase )
                url = BASE_URL_Firebase;
            else
                url = BASE_URL/*portNumber + PATH_URL*/;

            Log.e("url**" , url);

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
