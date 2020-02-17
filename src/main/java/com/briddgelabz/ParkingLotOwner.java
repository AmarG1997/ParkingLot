package com.briddgelabz;

import java.time.LocalTime;

public class ParkingLotOwner implements ParkingLotStatus {

    public static boolean parkingLot;
    public static LocalTime parkedTimeData;

    public void parkTimeData(LocalTime data){
        this.parkedTimeData=data;
    }

    public LocalTime getDetails(){
        return parkedTimeData;
    }


    @Override
    public void isFull() {
        this.parkingLot = true;
    }

    @Override
    public void isEmpty() {
        this.parkingLot = false;
    }
}
