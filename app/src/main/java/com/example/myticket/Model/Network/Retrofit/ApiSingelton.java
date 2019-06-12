package com.example.myticket.Model.Network.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSingelton {
    private static ApiSingelton instance = null;
    private ApiInterface client;

    public static ApiSingelton getInstance() {
        if (instance == null) {
            instance = new ApiSingelton();
        }

        return instance;
    }
    // Build retrofit once when creating a single instance
    private ApiSingelton() {
        // Implement a method to build your retrofit
        buildRetrofit();
    }

    private void buildRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl("http://iscoapps.com/cinema/api/")
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder.client(httpClient.build())
                        .build();
        this.client = retrofit.create(ApiInterface.class);
    }
    public ApiInterface getClient(){
        return this.client;
    }
}
