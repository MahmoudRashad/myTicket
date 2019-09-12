package com.example.myticket.Enum;

public enum ClubReservationEnum {
    pending(0),
    accept  (1) ;

    private final int value;

    ClubReservationEnum(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }

}
