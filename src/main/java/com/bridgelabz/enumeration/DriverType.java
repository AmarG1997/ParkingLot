package com.bridgelabz.enumeration;

import com.bridgelabz.ParkingSystem;
import com.bridgelabz.model.Vehicle;

public enum DriverType {
    SMALL_VEHICLE_DRIVER {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            parkingSystem.carParking(vehicle);
        }
    }, HANDICAP {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            parkingSystem.handicapCarParking(vehicle);
        }
    }, LARGE_VEHICLE_DRIVER {
        @Override
        public void carParking(ParkingSystem parkingSystem, Vehicle vehicle) {
            parkingSystem.largeCarParking(vehicle);
        }
    };

    public abstract void carParking(ParkingSystem parkingSystem, Vehicle vehicle);
}


