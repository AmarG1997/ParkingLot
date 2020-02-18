package com.bridgelabz.model;

import java.time.LocalTime;

public class Vehicle {

    public Vehicle() {
    }

    LocalTime time = LocalTime.now();

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "time=" + time +
                '}';
    }
}
