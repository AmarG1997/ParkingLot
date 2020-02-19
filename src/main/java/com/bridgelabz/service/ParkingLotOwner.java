package com.bridgelabz.service;

import java.time.LocalDateTime;

public class ParkingLotOwner implements ParkingLotObserver  {


    public static LocalDateTime parkedTimeData;
    public static boolean status;

    public void parkTimeData(LocalDateTime data) {
        this.parkedTimeData = data;
    }

    public LocalDateTime getDetails() {
        return parkedTimeData;
    }


    @Override
    public void updateParkingStatus(boolean status) {
        this.status=status;
    }
}
