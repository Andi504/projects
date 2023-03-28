package com.airline.flight.entity;

import com.airline.flight.enums.TripReason;
import com.airline.flight.enums.TripStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip")
    private Long tip;

    @Column(name = "trip_description", nullable = false)
    private String tripDescription;

    @Column(name = "reason", nullable = false)
    @Enumerated(EnumType.STRING)
    private TripReason reasonOfTraveling;

    @Column(name = "trip_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    @Column(name = "from_location", nullable = false)
    private String fromLocation;

    @Column(name = "to_location", nullable = false)
    private String toLocation;

    @Column(name = "departure_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureDate;


    @Column(name = "arrival_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Trip() {
    }

    public Trip(Long tip, String tripDescription, TripReason reasonOfTraveling, TripStatus tripStatus, String fromLocation, String toLocation, LocalDateTime departureDate, LocalDateTime arrivalDate, Flight flight, User user) {
        this.tip = tip;
        this.tripDescription = tripDescription;
        this.reasonOfTraveling = reasonOfTraveling;
        this.tripStatus = tripStatus;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.flight = flight;
        this.user = user;
    }

    public Long getTip() {
        return tip;
    }

    public void setTip(Long tip) {
        this.tip = tip;
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

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tip=" + tip +
                ", tripDescription='" + tripDescription + '\'' +
                ", reasonOfTraveling=" + reasonOfTraveling +
                ", tripStatus=" + tripStatus +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", flight=" + flight +
                ", user=" + user +
                '}';
    }
}
