package com.briddgelabz;

import java.util.*;

public class ParkingSpot {

    Map<Integer,Car> parkingLotData =new HashMap<Integer, Car>();

    int parkingLotSize=100;

    public boolean park(String userName,String carNumber,String carColor) {
        if (parkingLotData.size()<parkingLotSize) {
            parkingLotData.put(parkingLotData.size() + 1, new Car(userName, carNumber, carColor));
            return true;
        }
        return false;
    }

    public void getDetails() {
        System.out.println(parkingLotData);
    }

    public boolean unPark(String userName) throws ParkingLotException {
        Integer key = 0;
        for (Map.Entry<Integer, Car> entry : parkingLotData.entrySet()) {
            if (userName.equalsIgnoreCase(entry.getValue().getUserName())) {
                key = entry.getKey();
                parkingLotData.remove(key);
                return true;
            }
        }
        throw new ParkingLotException("NO CAR DATA FOUND", ParkingLotException.ExceptionType.NULL_POINTER_EXCEPTION);
    }
}



