package com.airline.flight.service.Iservice;

import com.airline.flight.dto.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto save(FlightDto flightDto);

    List<FlightDto> fetchFlights();

}
