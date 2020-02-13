package com.briddgelabz;

public class ParkingLotOwner implements ParkingLotStatus{

    AirportSecurity airportSecurity = new AirportSecurity();
    @Override
    public void isFull() {
        airportSecurity.isFull();
    }

    @Override
    public void isEmpty() {
        airportSecurity.isEmpty();
    }
}
