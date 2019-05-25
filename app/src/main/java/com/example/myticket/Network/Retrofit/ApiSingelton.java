package com.example.myticket.Network.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSingelton {
    private static ApiSingelton instance = null;
    private ApiInterface client;

    public static ApiSingelton getInstance(String url) {
        if (instance == null) {
            instance = new ApiSingelton(url);
        }

        return instance;
    }
    // Build retrofit once when creating a single instance
    private ApiSingelton(String url) {
        // Implement a method to build your retrofit
        buildRetrofit(url);
    }

    private void buildRetrofit(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(url)
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
