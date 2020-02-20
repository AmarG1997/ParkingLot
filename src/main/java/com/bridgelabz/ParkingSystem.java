package com.bridgelabz;

import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleDetails;
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

    public final int PARKING_LOT_SIZE;
    public final int NO_OF_PARKING_LOT;
    int noOfLots;
    int key = 0;
    int changeSlot = 1;
    int count = 1;
    int i = 1;

    public ParkingSystem(int parkingLotSize, int noOfparkingLot) {
        this.PARKING_LOT_SIZE = parkingLotSize;
        this.NO_OF_PARKING_LOT = noOfparkingLot;
        noOfLots = this.PARKING_LOT_SIZE / this.NO_OF_PARKING_LOT;
        parkingStatusObserver.add(airportSecurity);
        parkingStatusObserver.add(owner);
    }

    public void handicapCarParking(Vehicle vehicle) {
        for (int i=1;i<PARKING_LOT_SIZE;i++){
            if (vehicleData.get(i)==null){
                vehicleData.put(i,vehicle);
                assignLot(vehicle);
                break;
            }

        }
    }

    public void carParking(Vehicle vehicle) {
        int lots = this.NO_OF_PARKING_LOT + 1;
        if (count == lots) {
            changeSlot = changeSlot + 1;
            i = changeSlot;
            count = 1;
        }
        vehicleData.putIfAbsent(i, vehicle);
        assignLot(vehicle);
        i = i + noOfLots;
        count++;
    }

    public void largeCarParking(Vehicle vehicle) {
        for (int i = 1; i < PARKING_LOT_SIZE; i++) {
            if (vehicleData.get(i) == null && vehicleData.get(i + 1) == null && vehicleData.get(i - 1) == null) {
                vehicleData.putIfAbsent(i, vehicle);
                assignLot(vehicle);
                break;
            }
        }
    }

    public void park(Vehicle vehicleDetails) throws ParkingLotException {
        if (vehicleData.size() == PARKING_LOT_SIZE) {
            parkingStatusObserver.notifyUpdate(true);
            throw new ParkingLotException("Parking Lot Is Full");
        }
        vehicleDetails.type.carParking(this, vehicleDetails);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        for (int i = 1; i <= PARKING_LOT_SIZE; i++) {
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
            if (vehicleData.size() < PARKING_LOT_SIZE) {
                parkingStatusObserver.notifyUpdate(false);
            }
            return true;
        }
        return false;
    }

    public Map<Integer, Vehicle> getDetails(VehicleDetails... finDBY) {
        Map<Integer, Vehicle> searchedVehicle;
        searchedVehicle = vehicleData.entrySet().stream()
                .filter(integerVehicleEntry -> integerVehicleEntry.getValue().toString().toLowerCase().contains(finDBY[0].toString().toLowerCase()))
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        if (finDBY.length > 1) {
            searchedVehicle = searchedVehicle.entrySet().stream()
                    .filter(integerVehicleEntry -> integerVehicleEntry.getValue()
                            .model == finDBY[1])
                    .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
            return searchedVehicle;
        }
        return searchedVehicle;
    }

    public Map<Integer, Vehicle> getLotData(int lotNumber, DriverType handicapDriver){
        Map<Integer, Vehicle> lotData;
        lotData=vehicleData.entrySet().stream()
                .filter(integerVehicleEntry -> integerVehicleEntry.getValue().lot==lotNumber)
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        lotData=lotData.entrySet().stream()
                .filter(data->data.getValue().type==handicapDriver)
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        return lotData;
    }

    public Map<Integer, Vehicle> getLast30MinuteParkedVehicles() {
        Map<Integer, Vehicle> vehicleBefore;
        vehicleBefore = vehicleData.entrySet().stream().filter(integerVehicleEntry -> integerVehicleEntry.getValue().getTimeAndDate()
                .isAfter(LocalDateTime.now().minusMinutes(30))).collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        return vehicleBefore;
    }

    private void assignLot(Vehicle vehicle){
        vehicleData.entrySet()
        .stream()
        .filter(data-> (data.getValue() == vehicle))
        .map(values -> {
            for (int j=1;j<NO_OF_PARKING_LOT;j++){
                if (values.getKey() <= j*(PARKING_LOT_SIZE/NO_OF_PARKING_LOT)){
                    vehicle.lot = j;
                    break;
                }
            }
            return false;
        })
        .count();
    }
}

