package com.airline.flight.service.Iservice;

import com.airline.flight.dto.TripDto;
import com.airline.flight.enums.TripStatus;
import com.airline.flight.request.TripRequest;

import java.util.List;

public interface TripService {

    TripDto save(TripDto tripDto);

    void updateTripStatus(TripRequest tripRequest);

    List<TripDto> fetchAllTripsByStatus();

    void updateTripStatusById(Long id, String tripStatus);

    TripStatus addFlightDependingOnStatus(Long tip, Long flightNumber) ;

    void deleteTrip(Long id);

    TripDto updateTrip(Long id, TripDto tripDto);
}
