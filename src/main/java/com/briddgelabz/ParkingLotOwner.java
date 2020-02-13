package com.briddgelabz;

public class ParkingLotOwner implements ParkingLotStatus{

    @Override
    public void isFull() {
        AirportSecurity airportSecurity = new AirportSecurity();
        airportSecurity.isFull();
    }
}
