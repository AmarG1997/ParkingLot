package com.bridgelabz.service;

import java.util.ArrayList;
import java.util.List;

public class ParkingStatusObserver {

    private List<ParkingLotObserver> observers = new ArrayList<>();

    public void attach(ParkingLotObserver o) {
        observers.add(o);
    }

    public void notifyUpdate(boolean m) {
        for(ParkingLotObserver o: observers) {
            o.updateParkingStatus(m);
        }
    }
}
