package com.example.myticket.Model.Network.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.myticket.Enum.ErrorTypeEnum;
import com.example.myticket.Model.Data.SessionManager;
import com.example.myticket.Model.MainResult;
import com.example.myticket.Model.Network.DataModel.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.Network.DataModel.LoginModel.ModelLogin;
import com.example.myticket.Model.Network.DataModel.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.Network.DataModel.MapModel.NearByFullModel;
import com.example.myticket.Model.Network.DataModel.Resgister.MainResponceReg;
import com.example.myticket.View.Activity.Login;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    public ApiCalling(Context context )
    {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient(false).create(ApiInterface.class);
        sessionManager = new SessionManager(context);
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

    public void register(String lang , Map<String, String> queryMap , final GeneralListener registerListener )
    {
        Call<MainResponceReg> call;
        MultipartBody.Part body ;
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


    public void login(String email , String password, String mac
            , final GeneralListener generalListener,
                      String lang)
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
//    public void mainSliderCall(Call<SliderResponce> call, final onResponceInterface onResponceInterface) {
//        call.enqueue(new Callback<SliderResponce>() {
//            @Override
//            public void onResponse(Call<SliderResponce> call, Response<SliderResponce> response) {
//                if (response.isSuccessful()) {
//                    Log.v("SliderSuccess", "SliderSuccess");
//                    onResponceInterface.onSuccess(response.body());
//                    Log.e("SliderSuccess", "SliderSuccess");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SliderResponce> call, Throwable t) {
//                Log.e("SliderError","Slider");
//                Log.e("SliderError",t.getMessage());
//            }
//        });
//    }
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


}
