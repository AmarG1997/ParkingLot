package com.bridgelabz.service;

public class AirportSecurity implements ParkingLotStatus {

    public static boolean parkingLot;

    @Override
    public void isFull() {
        this.parkingLot = true;
    }

    @Override
    public void isEmpty() {
        this.parkingLot = false;
    }
}
