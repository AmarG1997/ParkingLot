package com.briddgelabz;

import java.time.LocalTime;

public class ParkingLotOwner implements ParkingLotStatus{

    AirportSecurity airportSecurity = new AirportSecurity();
    public static LocalTime parkedTimeData;

    @Override
    public void isFull() {
        airportSecurity.isFull();
    }

    @Override
    public void isEmpty() {
        airportSecurity.isEmpty();
    }

    public void parkTimeData(LocalTime data){
        this.parkedTimeData=data;
    }

    public LocalTime getDetails(){
        return parkedTimeData;
    }
}
