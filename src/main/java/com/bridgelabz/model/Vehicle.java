package com.bridgelabz.model;

import com.bridgelabz.enumeration.DriverType;

import java.time.LocalDateTime;

public class Vehicle {

    private String model;
    private String carNumber;
    private String name;
    private String color;
    public DriverType type;
    LocalDateTime now = LocalDateTime.now();

    public Vehicle(DriverType type) {
        this.type = type;
    }

    public Vehicle(DriverType type, String color) {
        this.type = type;
        this.color = color;
    }

    public Vehicle(DriverType type, String name, String carNumber, String color, String model) {
        this.type = type;
        this.name = name;
        this.carNumber = carNumber;
        this.color = color;
        this.model = model;
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
