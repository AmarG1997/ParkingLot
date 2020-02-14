package com.briddgelabz;

import java.time.LocalTime;

public class Vehicle {

    LocalTime time = LocalTime.now();

    public Vehicle() {
    }

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
