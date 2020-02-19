package com.bridgelabz.model;

import java.time.LocalDateTime;

public class Vehicle {

    private String model;
    private String carNumber;
    private String name;
    private String color;
    public VehicleType size;
    public boolean handicap;
    LocalDateTime now = LocalDateTime.now();

    public Vehicle(boolean handicap, VehicleType size) {
        this.handicap = handicap;
        this.size = size;
    }

    public Vehicle(boolean handicap, VehicleType size, String color) {
        this.handicap = handicap;
        this.size = size;
        this.color = color;
    }

    public Vehicle(boolean handicap, VehicleType size, String name, String carNumber ,String color , String model) {
        this.handicap = handicap;
        this.size = size;
        this.name=name;
        this.carNumber=carNumber;
        this.color = color;
        this.model=model;
    }

    public LocalDateTime getTimeAndDate() {
        return now;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", handicap=" + handicap +
                ", now=" + now +
                '}';
    }
}
