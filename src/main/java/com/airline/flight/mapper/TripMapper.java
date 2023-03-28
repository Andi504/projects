package com.airline.flight.mapper;

import com.airline.flight.dto.TripDto;
import com.airline.flight.entity.Trip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TripMapper implements AbstractMapper<TripDto, Trip>{
}
