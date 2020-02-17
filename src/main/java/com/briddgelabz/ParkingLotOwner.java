package com.briddgelabz;

import java.time.LocalTime;

public class ParkingLotOwner {

    public static LocalTime parkedTimeData;

    public void parkTimeData(LocalTime data){
        this.parkedTimeData=data;
    }

    public LocalTime getDetails(){
        return parkedTimeData;
    }
}
