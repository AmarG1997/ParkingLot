package com.briddgelabz;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ParkingSystem {
    AirportSecurity airportSecurity = new AirportSecurity();
    ParkingLotOwner owner = new ParkingLotOwner();

    Map<Integer, Vehicle> vehicleData = new HashMap<>();

    public int parkingLotSize;
    public int noOfParkingLot;
    int handicap = 1;

    public ParkingSystem(int parkingLotSize, int noOfParkingLot) {
        this.parkingLotSize = parkingLotSize;
        this.noOfParkingLot = noOfParkingLot;
    }

    int key = 0;
    int changeSlot = 1;
    int count = 1;
    int i = 1;

    private void assignSlot(Object vehicle, boolean driver) {
        Vehicle vehicle1 = null;
        int noOfLots = parkingLotSize / noOfParkingLot;
        int lots = noOfParkingLot + 1;
        boolean res = vehicleData.containsKey(i);
        if (driver == false && res == false) {
            if (count == lots) {
                changeSlot = changeSlot + 1;
                i = changeSlot;
                count = 1;
            }
            vehicleData.put(i, (Vehicle) vehicle);
            i = i + noOfLots;
            count++;
        }

        if (driver == true) {
            for (int slot = handicap; slot < parkingLotSize; slot++) {
                if (vehicleData.containsKey(slot)) {
                    vehicle1 = vehicleData.get(slot);
                    vehicleData.put(slot, (Vehicle) vehicle);
                    handicap = handicap + 1;
                    assignSlot(vehicle1, false);
                    break;
                }
                else
                {
                    vehicleData.put(slot, (Vehicle) vehicle);
                    break;
                }
            }
        }
    }


    public void park(Object vehicleDetails, boolean driver) throws ParkingLotException {
        if (vehicleData.size() == parkingLotSize) {
            owner.isFull();
            airportSecurity.isFull();
            throw new ParkingLotException("Parking Lot Is Full");
        }
        assignSlot(vehicleDetails, driver);
//        if (driver == false) {
//            if (vehicleData.size() < parkingLotSize) {
//                assignSlot(vehicleDetails, driver);
//            }
//        }
    }

    public boolean isVehicleParked(Object vehicle) {
        for (int i = 1; i <= parkingLotSize; i++) {
            if (vehicleData.get(i) == vehicle) {
                System.out.println(i);
                return true;
            }
        }
        return false;
    }

    public int getSlotNo(Object vehicle) {
        for (Map.Entry<Integer, Vehicle> entry : vehicleData.entrySet()) {
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
            LocalTime parkTime = vehicleData.get(key).getTime();
            owner.parkTimeData(parkTime);
            vehicleData.remove(key);
            if (vehicleData.size() < parkingLotSize) {
                airportSecurity.isEmpty();
                owner.isEmpty();
            }
            return true;
        }
        return false;
    }

    public void getDetails() {
        System.out.println(vehicleData);
    }
}

