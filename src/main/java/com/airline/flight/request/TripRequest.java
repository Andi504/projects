package com.airline.flight.request;

import com.airline.flight.enums.TripStatus;

public class TripRequest {


    private Long tip;
    private TripStatus tripStatus;

    public TripRequest() {
    }

    public TripRequest(Long tip, TripStatus tripStatus) {
        this.tip = tip;
        this.tripStatus = tripStatus;
    }

    public Long getTip() {
        return tip;
    }

    public void setTip(Long tip) {
        this.tip = tip;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    @Override
    public String toString() {
        return "TripRequest{" +
                "tip=" + tip +
                ", tripStatus=" + tripStatus +
                '}';
    }
}
