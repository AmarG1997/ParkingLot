package com.briddgelabz;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ParkingSystem {

    public int parkingLotSize = 100;
    static int key = 0;

    AirportSecurity airportSecurity = new AirportSecurity();

    Map<Integer, Vehicle> mapData = new HashMap<>();
    ParkingLotOwner owner = new ParkingLotOwner();
    int noOfParkingLot = 4;
    int lots = parkingLotSize / noOfParkingLot;
    int changeSlot = 1;
    int i = 1;
    int count = 1;

    private void assignSlot(Object vehicle) {
        boolean res = mapData.containsKey(i);
        if (res == false) {
            if (count == 5) {
                changeSlot = changeSlot + 1;
                i = changeSlot;
                count = 1;
            }
            mapData.put(i, (Vehicle) vehicle);
            i = i + lots;
            count++;
        }
    }

    public void park(Object vehicleDetails) throws ParkingLotException {
        if (mapData.size() == parkingLotSize) {
            airportSecurity.isFull();
            throw new ParkingLotException("Parking Lot Is Full");
        }
        if (mapData.size() < parkingLotSize) {
            assignSlot(vehicleDetails);
        }
    }

    public boolean isVehicleParked(Object vehicle) {
        for (int i = 1; i <= mapData.size(); i++) {
            if (mapData.get(i) == vehicle)
                return true;
        }
        return false;
    }

    public int getSlotNo(Object vehicle) {
        for (Map.Entry<Integer, Vehicle> entry : mapData.entrySet()) {
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
            LocalTime parkTime = mapData.get(key).getTime();
            owner.parkTimeData(parkTime);
            mapData.remove(key);
            if (mapData.size() < parkingLotSize) {
                airportSecurity.isEmpty();
            }
            return true;
        }
        return false;
    }

    public void getDetails() {
        System.out.println(mapData);
    }
}

