package com.briddgelabz;

import java.util.ArrayList;

public class ParkingSystem {

    int parkingLotSize=2;

    private Object vehicle = null;
    ArrayList list = new ArrayList();

    public void park(Object vehicle) throws ParkingLotException {
        if (list.size()==parkingLotSize){
            throw new ParkingLotException("Parking Lot Is Full");
        }
        this.vehicle = vehicle;
        if (list.size()<parkingLotSize) {
            list.add(vehicle);
        }
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            return true;
        }
        return false;
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("Enter Vehicle Details");
        }
        boolean vehicleParked = isVehicleParked(vehicle);
        if (vehicleParked==true)
        {
            list.remove(vehicle);
            return true;
        }
        return false;
    }

}



