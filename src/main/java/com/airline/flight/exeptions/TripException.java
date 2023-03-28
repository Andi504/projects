package com.airline.flight.exeptions;

public class TripException extends RuntimeException {

    private String tripStatus;

    public TripException(String message, String tripStatus) {
        super(message);
        this.tripStatus = tripStatus;
    }
}
