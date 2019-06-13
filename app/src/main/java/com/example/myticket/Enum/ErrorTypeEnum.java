package com.example.myticket.Enum;

/**
 * Created by Amr Eldsokey on 07/03/2018.
 */

public enum ErrorTypeEnum
{
    noError(0),
    BackendLogicFail  (1) ,
    ServerCodeFail  (2) ,
    InternetConnectionFail  (3) ,
    ConversionIssueFail (4) ,
    other(5);

    private final int value;

    ErrorTypeEnum(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
