package com.briddgelabz;

import java.util.*;

public class ParkingSystem implements parkingLotStatus {

    Map<Integer, Car> noOfParkingCar = new HashMap<Integer, Car>();
    AirportSecurity airportSecurity = new AirportSecurity();

    int parkingLotSize = 100;

    public boolean park(String userName, String carNumber, String carColor) throws ParkingLotException {
        if (isEmpty() == true) {
            noOfParkingCar.put(noOfParkingCar.size() + 1, new Car(userName, carNumber, carColor));
            return true;
        }
        throw new ParkingLotException("PARKING_LOT_IS_FULL", ParkingLotException.ExceptionType.OUT_OF_MEMORY);
    }

    public void getDetails() {
        System.out.println(noOfParkingCar);
    }

    public boolean unPark(String userName) throws ParkingLotException {
        Integer key = getSlotNo(userName);
        if (key != 0) {
            noOfParkingCar.remove(key);
            return true;
        }
        throw new ParkingLotException("NO CAR DATA FOUND", ParkingLotException.ExceptionType.NULL_POINTER_EXCEPTION);
    }

    @Override
    public boolean isEmpty() {
        if (noOfParkingCar.size() < parkingLotSize) {
            airportSecurity.isRedirect = false;
            return true;
        }
        airportSecurity.isRedirect = true;
        return false;
    }

    @Override
    public Integer getSlotNo(String userName) {
        Integer key = 0;
        for (Map.Entry<Integer, Car> entry : noOfParkingCar.entrySet()) {
            if (userName.equalsIgnoreCase(entry.getValue().getUserName())) {
                key = entry.getKey();
                return key;
            }
        }
        return 0;
    }
}



