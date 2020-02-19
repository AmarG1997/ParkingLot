package com.bridgelabz.model;

import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleType;

import java.time.LocalDateTime;

public class Vehicle {

    private String model;
    private String carNumber;
    private String name;
    private String color;
    public VehicleType size;
    public DriverType type;
    LocalDateTime now = LocalDateTime.now();

    public Vehicle(DriverType type, VehicleType size) {
        this.type = type;
        this.size = size;
    }

    public Vehicle(DriverType type, VehicleType size, String color) {
        this.type = type;
        this.size = size;
        this.color = color;
    }

    public Vehicle(DriverType type, VehicleType size, String name, String carNumber ,String color , String model) {
        this.type = type;
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
                ", type=" + type +
                ", time=" + now +
                '}';
    }
}
