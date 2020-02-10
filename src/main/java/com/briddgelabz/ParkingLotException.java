package com.briddgelabz;

public class ParkingLotException extends Exception {

    public enum ExceptionType {NULL_POINTER_EXCEPTION}
    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public ExceptionType type;
}
