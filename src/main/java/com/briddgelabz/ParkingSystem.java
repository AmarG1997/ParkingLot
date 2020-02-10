package com.briddgelabz;

import java.util.*;

public class ParkingSystem implements parkingLotStatus{

    Map<Integer,Car> noOfParkingCar =new HashMap<Integer, Car>();

    int parkingLotSize=100;

    public boolean park(String userName,String carNumber,String carColor) throws ParkingLotException {
        if (isEmpty()==true) {
            noOfParkingCar.put(noOfParkingCar.size() + 1, new Car(userName, carNumber, carColor));
            return true;
        }
        throw new ParkingLotException("PARKING_LOT_IS_FULL", ParkingLotException.ExceptionType.OUT_OF_MEMORY);
    }

    public void getDetails() {
        System.out.println(noOfParkingCar);
    }

    public boolean unPark(String userName) throws ParkingLotException {
        Integer key = 0;
        for (Map.Entry<Integer, Car> entry : noOfParkingCar.entrySet()) {
            if (userName.equalsIgnoreCase(entry.getValue().getUserName())) {
                key = entry.getKey();
                noOfParkingCar.remove(key);
                return true;
            }
        }
        throw new ParkingLotException("NO CAR DATA FOUND", ParkingLotException.ExceptionType.NULL_POINTER_EXCEPTION);
    }


    @Override
    public boolean isEmpty() {
        if (noOfParkingCar.size()<parkingLotSize) {
            return true;
        }
        return false;
    }
}



