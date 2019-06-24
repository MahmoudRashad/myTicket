package com.example.myticket.Enum;

/**
 * Created by Amr Eldsokey on 07/03/2018.
 */

public enum ReservetypeEnum
{
    cinema(0),
    date  (1) ,
    time  (2) ;

    private final int value;

    ReservetypeEnum(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
