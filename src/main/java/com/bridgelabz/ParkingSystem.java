package com.bridgelabz;

import com.bridgelabz.model.Vehicle;
import com.bridgelabz.service.AirportSecurity;
import com.bridgelabz.service.ParkingLotException;
import com.bridgelabz.service.ParkingLotOwner;
import com.bridgelabz.service.ParkingStatusObserver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingSystem {

    AirportSecurity airportSecurity = new AirportSecurity();
    ParkingLotOwner owner = new ParkingLotOwner();
    ParkingStatusObserver parkingStatusObserver = new ParkingStatusObserver();

    Map<Integer, Vehicle> vehicleData = new HashMap<>();

    public int PARKINGLOTSIZE;
    public int NOOFPARKINGLOT;
    int noOfLots = 0;
    int handicap = 1;
    int key = 0;
    int changeSlot = 1;
    int count = 1;
    int i = 1;
    Vehicle parkedVehicle = null;

    public ParkingSystem(int PARKINGLOTSIZE, int NOOFPARKINGLOT) {
        this.PARKINGLOTSIZE = PARKINGLOTSIZE;
        this.NOOFPARKINGLOT = NOOFPARKINGLOT;
        noOfLots = this.PARKINGLOTSIZE / this.NOOFPARKINGLOT;
        parkingStatusObserver.add(airportSecurity);
        parkingStatusObserver.add(owner);
    }

    public void handicapCarParking(Object vehicle) {
        for (int slot = handicap; slot < PARKINGLOTSIZE; slot++) {
            if (vehicleData.containsKey(slot)) {
                parkedVehicle = vehicleData.get(slot);
                vehicleData.put(slot, (Vehicle) vehicle);
                handicap = handicap + 1;
                parkedVehicle.getType().carParking(this, parkedVehicle);
                break;
            } else {
                vehicleData.put(slot, (Vehicle) vehicle);
                handicap = handicap + 1;
                break;
            }
        }
    }

    public void carParking(Vehicle vehicle) {
        int lots = this.NOOFPARKINGLOT + 1;
        if (count == lots) {
            changeSlot = changeSlot + 1;
            i = changeSlot;
            count = 1;
        }
            vehicleData.putIfAbsent(i,vehicle);
            i = i + noOfLots;
            count++;
    }

    public void largeCarParking(Vehicle vehicle) {
        for (int i = 1; i < PARKINGLOTSIZE; i++) {
            if (vehicleData.get(i) == null && vehicleData.get(i + 1) == null && vehicleData.get(i - 1) == null) {
                vehicleData.putIfAbsent(i, vehicle);
                break;
            }
        }
    }

    public void park(Vehicle vehicleDetails) throws ParkingLotException {
        if (vehicleData.size() == PARKINGLOTSIZE) {
            parkingStatusObserver.notifyUpdate(true);
            throw new ParkingLotException("Parking Lot Is Full");
        }
        vehicleDetails.getType().carParking(this, vehicleDetails);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        for (int i = 1; i <= PARKINGLOTSIZE; i++) {
            if (vehicleData.get(i) == vehicle) {
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

    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("Enter Vehicle Details");
        }

        boolean vehicleParked = isVehicleParked(vehicle);
        if (vehicleParked == true) {
            key = getSlotNo(vehicle);
            LocalDateTime parkTime = vehicleData.get(key).getTimeAndDate();
            owner.parkTimeData(parkTime);
            vehicleData.remove(key);
            if (vehicleData.size() < PARKINGLOTSIZE) {
                parkingStatusObserver.notifyUpdate(false);
            }
            return true;
        }
        return false;
    }

    public Map<Integer, Vehicle> getDetails(String... finDBY) {
        Map<Integer, Vehicle> searchedVehicle;
        searchedVehicle = vehicleData.entrySet().stream()
                .filter(integerVehicleEntry -> integerVehicleEntry.getValue().toString().contains(finDBY[0]))
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        if (finDBY.length > 1) {
            searchedVehicle = searchedVehicle.entrySet().stream()
                    .filter(integerVehicleEntry -> integerVehicleEntry.getValue()
                            .getModel() == finDBY[1])
                    .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
            return searchedVehicle;
        }
        return searchedVehicle;
    }

    public void details() {
        System.out.println(vehicleData);
        System.out.println("________________________");
    }
}

