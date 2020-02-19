package com.bridgelabz.service;

public class AirportSecurity implements ParkingLotObserver {


    public static boolean status;

    @Override
    public void updateParkingStatus(boolean status) {
        this.status=status;
    }
}
