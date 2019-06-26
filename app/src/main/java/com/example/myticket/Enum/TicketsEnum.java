package com.example.myticket.Enum;

/**
 * Created by Amr Eldsokey on 07/03/2018.
 */

public enum TicketsEnum
{
    confirmTickets(0),
    pervieousTickets(1) ;

    private final int value;

    TicketsEnum(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
