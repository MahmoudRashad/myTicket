package com.example.myticket.Business;

import com.example.myticket.Model.Network.DataModel.Chairs.Chair;
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

//    public static Map<String , Chair> avilableChairsMap = new HashMap<>() ;


    public static Map<String , Chair> selectedChairsMap = new HashMap<>();

    public static double totalPrice = 0;

    public static void addChair(Chair avaliableChair)
    {
        if( selectedChairsMap == null)
            selectedChairsMap = new HashMap<>();

//        ResultTickets resultTickets = new ResultTickets();
//        resultTickets.set

        selectedChairsMap.put(avaliableChair.getSymbolChair()+
                        avaliableChair.getCharNum(),
                avaliableChair);
        totalPrice += Double.valueOf(avaliableChair.getPrice());

    }

    public static void removeChair(Chair avaliableChair)
    {
        if( selectedChairsMap == null)
            selectedChairsMap = new HashMap<>();

        selectedChairsMap.remove(avaliableChair.getSymbolChair()+
                avaliableChair.getCharNum() );
        totalPrice -= Double.valueOf(avaliableChair.getPrice());
    }
}
