package com.airline.flight.mapper;

import com.airline.flight.dto.FlightDto;
import com.airline.flight.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FlightMapper implements AbstractMapper<FlightDto, Flight>{
}
