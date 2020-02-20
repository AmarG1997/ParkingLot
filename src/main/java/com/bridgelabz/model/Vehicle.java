package com.bridgelabz.model;

import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleDetails;

import java.time.LocalDateTime;

public class Vehicle {

    private VehicleDetails color;
    private VehicleDetails model;
    private String carNumber;
    private String name;

    public DriverType type;
    LocalDateTime now = LocalDateTime.now();

    public Vehicle(DriverType type) {
        this.type = type;
    }

    public Vehicle(DriverType type, VehicleDetails color) {
        this.type = type;
        this.color = color;
    }

    public Vehicle(DriverType type, String name, String carNumber, VehicleDetails color, VehicleDetails model) {
        this.type = type;
        this.name = name;
        this.carNumber = carNumber;
        this.color = color;
        this.model = model;
    }

    public LocalDateTime getTimeAndDate() {
        return now;
    }

    public VehicleDetails getColor() {
        return color;
    }

    public VehicleDetails getModel() {
        return model;
    }

    public DriverType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", type=" + type +
                ", time=" + now +
                '}';
    }
}
