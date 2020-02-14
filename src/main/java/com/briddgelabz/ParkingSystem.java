package com.briddgelabz;

import java.util.HashMap;
import java.util.Map;

public class ParkingSystem {

    int parkingLotSize = 2;
    public static int key = 0;

    private Object vehicle = null;
    Map<Integer, Object> mapData = new HashMap<>();
    ParkingLotOwner owner = new ParkingLotOwner();

    public void park(Object vehicle) throws ParkingLotException {
        if (mapData.size() == parkingLotSize) {
            owner.isFull();
            throw new ParkingLotException("Parking Lot Is Full");
        }
        this.vehicle = vehicle;
        if (mapData.size() < parkingLotSize) {
            mapData.put(key, vehicle);
            key++;
        }
    }


    public boolean isVehicleParked(Object vehicle) {
        for (int i = 0; i < mapData.size(); i++) {
            if (mapData.get(i) == vehicle)
                return true;
        }
        return false;
    }

    public int getSlotNo(Object vehicle) {
        for (Map.Entry<Integer, Object> entry : mapData.entrySet()) {
            if (vehicle.equals(entry.getValue())) {
                key = entry.getKey();
            }
        }
        return key;
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("Enter Vehicle Details");
        }

        boolean vehicleParked = isVehicleParked(vehicle);

        if (vehicleParked == true) {
            key = getSlotNo(vehicle);
            mapData.remove(key);
            if (mapData.size() < parkingLotSize) {
                owner.isEmpty();
            }
            return true;
        }
        return false;
    }

}

