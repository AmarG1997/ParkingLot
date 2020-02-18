package com.bridgelabz.model;

import java.time.LocalTime;

public class Vehicle {

    public VehicleType size;
    public boolean handicap;
    LocalTime time = LocalTime.now();

    public Vehicle(boolean handicap , VehicleType size) {
        this.handicap=handicap;
        this.size=size;
    }

    public LocalTime getTime() {
        return time;
    }

    public VehicleType getSize() {
        return size;
    }

    public boolean isHandicap() {
        return handicap;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "time=" + time +
                '}';
    }
}
