package com.bridgelabz.enumeration;

import com.bridgelabz.ParkingSystem;
import com.bridgelabz.model.Vehicle;

public enum DriverType {
    NORMAL_DRIVER {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            if (vehicle.size.toString().contains("SMALL")) {
                parkingSystem.carParking(vehicle);
            } else {
                parkingSystem.largeCarParking(vehicle);
            }
        }
    }, HANDICAP_DRIVER {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            parkingSystem.handicapCarParking(vehicle);
        }
    };

    public abstract void carParking(ParkingSystem parkingSystem, Vehicle vehicle);
}


