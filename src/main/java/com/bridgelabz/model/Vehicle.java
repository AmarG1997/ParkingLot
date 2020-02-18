package com.bridgelabz.model;

import java.time.LocalTime;

public class Vehicle {

    private  String color;
    public VehicleType size;
    public boolean handicap;
    LocalTime time = LocalTime.now();

    public Vehicle() {
    }

    public Vehicle(boolean handicap , VehicleType size) {
        this.handicap=handicap;
        this.size=size;
    }

    public Vehicle(boolean handicap , VehicleType size,String color) {
        this.handicap=handicap;
        this.size=size;
        this.color=color;
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

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return
                "color='" + color + '\'' +
                ", size=" + size +
                ", handicap=" + handicap +
                ", time=" + time +
                '}';
    }
}
