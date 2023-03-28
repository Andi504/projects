package com.airline.flight.dto;

import com.airline.flight.enums.TripReason;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TripDto {

    @NotNull(message = "Trip description should not be null")
    private String tripDescription;
    @NotNull(message = "Reason should not be null")
    private TripReason reasonOfTraveling;
    @NotNull(message = "From location should not be null")
    private String fromLocation;

    @NotNull(message = "To location should not be null")
    private String toLocation;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "You can not choose past dates")
    private LocalDateTime departureDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "You can not choose past dates")
    private LocalDateTime arrivalDate;


    public TripDto() {
    }

    public TripDto(String tripDescription, TripReason reasonOfTraveling, String fromLocation, String toLocation, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.tripDescription = tripDescription;
        this.reasonOfTraveling = reasonOfTraveling;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public TripReason getReasonOfTraveling() {
        return reasonOfTraveling;
    }

    public void setReasonOfTraveling(TripReason reasonOfTraveling) {
        this.reasonOfTraveling = reasonOfTraveling;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "TripDto{" +
                "tripDescription='" + tripDescription + '\'' +
                ", reasonOfTraveling=" + reasonOfTraveling +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
