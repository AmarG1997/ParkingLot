package com.briddgelabz;

public class ParkingLotException extends Exception {

    public enum ExceptionType {}

    public ParkingLotException(String message) {
        super(message);
    }

    public ExceptionType type;
}
