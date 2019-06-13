package com.example.myticket.Model.Network.Retrofit;

public interface GeneralListener<T>
{
    void getApiResponse(int status, String message,
                        T tApiResponse);
}
