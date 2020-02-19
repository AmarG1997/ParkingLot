package com.bridgelabz.enumeration;

import com.bridgelabz.ParkingSystem;
import com.bridgelabz.model.Vehicle;

public enum DriverType {
    NORMAL {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            parkingSystem.carParking(vehicle);
        }
    }, HANDICAP {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            parkingSystem.handicapCarParking(vehicle);
        }
    };

    public abstract void carParking(ParkingSystem parkingSystem, Vehicle vehicle);
}


