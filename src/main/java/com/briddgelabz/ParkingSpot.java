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
}



