package com.bridgelabz.service;

import java.time.LocalDateTime;

public class ParkingLotOwner implements ParkingLotStatus {

    public static boolean parkingLot;
    public static LocalDateTime parkedTimeData;

    public void parkTimeData(LocalDateTime data) {
        this.parkedTimeData = data;
    }

    public LocalDateTime getDetails() {
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
