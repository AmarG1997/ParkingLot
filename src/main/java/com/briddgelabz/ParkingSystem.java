package com.briddgelabz;

import java.util.ArrayList;

public class ParkingSystem {

    int parkingLotSize = 2;

    private Object vehicle = null;
    ArrayList list = new ArrayList();
    ParkingLotOwner owner = new ParkingLotOwner();

    public void park(Object vehicle) throws ParkingLotException {
        if (list.size() == parkingLotSize) {
            owner.isFull();
            throw new ParkingLotException("Parking Lot Is Full");
        }
        this.vehicle = vehicle;
        if (list.size() < parkingLotSize) {
            list.add(vehicle);
            System.out.println(list.size());
        }
    }

    public boolean isVehicleParked(Object vehicle) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == vehicle)
                return true;
        }
        return false;
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("Enter Vehicle Details");
        }
        boolean vehicleParked = isVehicleParked(vehicle);
        if (vehicleParked == true) {
            System.out.println("Vehicle Removed");
            list.remove(vehicle);
            if (list.size() < parkingLotSize) {
                owner.isEmpty();
                System.out.println("isEmpty");
            }
            return true;
        }
        return false;
    }

}



