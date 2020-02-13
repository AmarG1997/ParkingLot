package com.briddgelabz;

import java.util.*;

public class ParkingSystem {

    Map<Integer, Object> noOfParkingCar = new HashMap<Integer, Object>();
    AirportSecurity airportSecurity = new AirportSecurity();

    int parkingLotSize = 100;
    private Object vehicle = null;

    public void park(Object vehicle)  {
        this.vehicle = vehicle;
    }

    public boolean isVehicle(Object vehicle){
        if (this.vehicle.equals(vehicle)){
            return true;
        }
        return false;
    }

    public void getDetails() {
        System.out.println(noOfParkingCar);
    }

    public boolean unPark(Object vehicle)  {
            noOfParkingCar.remove(vehicle);
            return true;
    }


    public boolean isEmpty() {
        if (noOfParkingCar.size() < parkingLotSize) {
            airportSecurity.isRedirect = false;
            return true;
        }
        airportSecurity.isRedirect = true;
        return false;
    }

}



