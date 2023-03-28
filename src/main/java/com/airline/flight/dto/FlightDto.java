package com.airline.flight.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FlightDto {

    @NotNull(message = "Flight number should not be empty")
    @Min(100)
    private Long flightNumber;
    @NotNull(message = "Departure date should not be empty")
    @FutureOrPresent(message = "You can not choose past dates")
    private LocalDateTime departureDate;
    @NotNull(message = "Arrival date should not be empty")
    @FutureOrPresent(message = "You can not choose past dates")
    private LocalDateTime arrivalDate;
    @NotNull(message = "Location can not be empty")
    private String fromLocation;
    @NotNull(message = "Destination location can not be empty")
    private String toLocation;

    public FlightDto() {
    }

    public FlightDto(Long flightNumber, LocalDateTime departureDate, LocalDateTime arrivalDate, String fromLocation, String toLocation) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
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

    @Override
    public String toString() {
        return "FlightDto{" +
                "flightNumber=" + flightNumber +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                '}';
    }
}
