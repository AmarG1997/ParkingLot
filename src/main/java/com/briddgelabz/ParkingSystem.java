package com.briddgelabz;

public class ParkingSystem {

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

    public boolean unPark(Object vehicle)  {
        if (vehicle == null ){
            return false;
        }
        if (vehicle.equals(this.vehicle)){
            return true;
        }
        return false;
    }
}



