package com.bridgelabz.model;

import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleDetails;

import java.time.LocalDateTime;

public class Vehicle {

    public Integer lot;
    public VehicleDetails size;
    public VehicleDetails color;
    public VehicleDetails model;
    public String carNumber;
    public String name;
    public DriverType type;
    LocalDateTime time = LocalDateTime.now();

    public Vehicle(DriverType type, VehicleDetails color, VehicleDetails size) {
        this.type = type;
        this.color = color;
        this.size = size;
    }

    public Vehicle(DriverType type, VehicleDetails size, String name, String carNumber, VehicleDetails color, VehicleDetails model) {
        this.type = type;
        this.size = size;
        this.name = name;
        this.carNumber = carNumber;
        this.color = color;
        this.model = model;
    }

    public LocalDateTime getTimeAndDate() {
        return time;
    }

    @Override
    public String toString() {
        return
                "lot='" + lot + '\'' +
                ", size=" + size +
                ", color=" + color +
                ", model=" + model +
                ", carNumber='" + carNumber + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
