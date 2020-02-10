package com.briddgelabz;

public class ParkingLotException extends Exception {

    public enum ExceptionType {NULL_POINTER_EXCEPTION,OUT_OF_MEMORY}
    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public ExceptionType type;
}
