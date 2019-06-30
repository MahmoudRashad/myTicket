package com.example.myticket.Business;

import com.example.myticket.Model.Network.DataModel.ReserveModel.AvaliableChair;
import com.example.myticket.Model.Network.DataModel.Tickets.ResultTickets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketCinemaBusiness
{
    public static int movieId=-1,reserveCinemaId=-1,reserveDateId=-1,reserveTimeId=-1,
    ticketLimits = 0;
    public static String reserveCinema ,
            reserveDate , reserveTime ,movieName  , hallName , cinemaLocation = "test";

    public static Map<String , AvaliableChair> avilableChairsMap = new HashMap<>() ;


    public static Map<String , AvaliableChair> selectedChairsMap = new HashMap<>();

    public static double totalPrice = 0;

    public static void addChair(AvaliableChair avaliableChair)
    {
        if( selectedChairsMap == null)
            selectedChairsMap = new HashMap<>();

//        ResultTickets resultTickets = new ResultTickets();
//        resultTickets.set

        selectedChairsMap.put(avaliableChair.getChairNum() ,
                avaliableChair);
        totalPrice += Double.valueOf(avaliableChair.getDetail().getPrice());

    }

    public static void removeChair(AvaliableChair avaliableChair)
    {
        if( selectedChairsMap == null)
            selectedChairsMap = new HashMap<>();

        selectedChairsMap.remove(avaliableChair.getChairNum() );
        totalPrice -= Double.valueOf(avaliableChair.getDetail().getPrice());
    }
}
