package com.briddgelabz;

public class AirportSecurity implements ParkingLotStatus {

    public static boolean parkingLot;

    @Override
    public void isFull() {
        this.parkingLot = true;
    }
}
