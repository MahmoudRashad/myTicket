package com.example.myticket.Network.Retrofit;

import android.util.Log;

import com.example.myticket.Model.ForgetPasswordResponce.ForgetPasswordResponce;
import com.example.myticket.Model.LoginModel.ModelLogin;
import com.example.myticket.Model.MainSliderResponce.SliderResponce;
import com.example.myticket.Model.MapModel.NearByFullModel;
import com.example.myticket.Model.Resgister.MainResponceReg;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalling {

    public void apiCall(Call<NearByFullModel> call, final onResponceInterface onResponceInterface) {

        call.enqueue(new Callback<NearByFullModel>() {
            @Override
            public void onResponse(Call<NearByFullModel> call, final Response<NearByFullModel> response) {
                if (response.isSuccessful()) {
                    Log.v("ApiSuccess", "FirstSuccess");
                    NearByFullModel nearByFullModel = response.body();
                    onResponceInterface.onSuccess(nearByFullModel);
                    Log.e("ApiSuccess", "Success");
                }
            }

            @Override
            public void onFailure(Call<NearByFullModel> call, final Throwable t) {
                Log.e("ApiError","Failed");
                Log.e("ApiError",t.getMessage());

            }
        });
    }

    public void regCall(Call<MainResponceReg> call, final onResponceInterface onResponceInterface) {
        call.enqueue(new Callback<MainResponceReg>() {
            @Override
            public void onResponse(Call<MainResponceReg> call, final Response<MainResponceReg> response) {
                if (response.isSuccessful()) {

                            Log.v("ApiRegSuccess", "RegSuccess");
                            MainResponceReg mainResponceReg = response.body();
                            onResponceInterface.onSuccess(mainResponceReg);
                            Log.e("ApiRegSuccess", "RegSuccess");


                }
            }

            @Override
            public void onFailure(Call<MainResponceReg> call, Throwable t) {
                Log.e("RegError","Failed");
                Log.e("RegError",t.getMessage());
            }
        });

    }

    public void loginCall(Call<ModelLogin> call, final onResponceInterface onResponceInterface) {
        call.enqueue(new Callback<ModelLogin>() {
            @Override
            public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                if (response.isSuccessful()) {

                    Log.v("ApiRegSuccess", "RegSuccess");
                    ModelLogin mainResponceReg = response.body();
                    onResponceInterface.onSuccess(mainResponceReg);
                    Log.e("ApiRegSuccess", "RegSuccess");
                }
            }

            @Override
            public void onFailure(Call<ModelLogin> call, Throwable t) {
                    Log.e("RegError","Failed");
                    Log.e("RegError",t.getMessage());
                }

        });

    }
    public void forgetCall(Call<ForgetPasswordResponce> call, final onResponceInterface onResponceInterface) {
        call.enqueue(new Callback<ForgetPasswordResponce>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponce> call, Response<ForgetPasswordResponce> response) {
                if (response.isSuccessful()) {

                    Log.v("ApiForgetSuccess", "ForgetSuccess");
                  //  ForgetPasswordResponce mainResponceReg = response.body();
                    onResponceInterface.onSuccess(response.body());
                    Log.e("ApiRegSuccess", "RegSuccess");
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponce> call, Throwable t) {
                Log.e("RegError","Failed");
                Log.e("RegError",t.getMessage());
            }
        });

    }

    public void mainSliderCall(Call<SliderResponce> call, final onResponceInterface onResponceInterface) {
        call.enqueue(new Callback<SliderResponce>() {
            @Override
            public void onResponse(Call<SliderResponce> call, Response<SliderResponce> response) {
                if (response.isSuccessful()) {
                    Log.v("SliderSuccess", "SliderSuccess");
                    onResponceInterface.onSuccess(response.body());
                    Log.e("SliderSuccess", "SliderSuccess");
                }
            }

            @Override
            public void onFailure(Call<SliderResponce> call, Throwable t) {
                Log.e("SliderError","Slider");
                Log.e("SliderError",t.getMessage());
            }
        });
    }
public void ApiCall(Call<Object> call, final onResponceInterface onResponceInterface) {
    call.enqueue(new Callback<Object>() {
        @Override
        public void onResponse(Call<Object> call, Response<Object> response) {
            if (response.isSuccessful()) {
                Log.v("Success", "Success");
                onResponceInterface.onSuccess(response.body());
                Log.e("Success", "Success");
            }
        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {
            Log.e("Error","API ERROR");
            Log.e("Error",t.getMessage());
        }
    });
}


}
