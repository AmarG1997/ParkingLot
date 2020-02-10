package com.briddgelabz;

import java.time.LocalTime;

public class Car {

    private String userName=null;
    private String numberPlate=null;
    private String color = null;

    long millis=System.currentTimeMillis();
    java.sql.Date date=new java.sql.Date(millis);
    LocalTime time = LocalTime.now();

    public Car(String userName, String numberPlate, String color) {
        this.userName = userName;
        this.numberPlate = numberPlate;
        this.color = color;
    }

    public String getUserName() {
        return userName;
    }


    @Override
    public String toString() {
        return
                "userName='" + userName + '\'' +
                ", numberPlate='" + numberPlate + '\'' +
                ", color='" + color + '\'' +
                ", millis=" + millis +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
