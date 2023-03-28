package com.airline.flight.service.implementation;

import com.airline.flight.dto.FlightDto;
import com.airline.flight.entity.Flight;
import com.airline.flight.exeptions.ExistException;
import com.airline.flight.mapper.FlightMapper;
import com.airline.flight.repository.FlightRepository;
import com.airline.flight.service.Iservice.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FlightDto save(FlightDto flightDto) {
        Optional<Flight> flightByFlightNumber = flightRepository.findByFlightNumber(flightDto.getFlightNumber());
        if (flightByFlightNumber.isPresent()){
            throw new ExistException("Flight exist");
        }
        Flight savedFlight = flightRepository.save(flightMapper.toEntity(flightDto));
        LOGGER.debug("Saving flight to database {}", flightDto);
        return flightMapper.toDTO(savedFlight);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDto> fetchFlights(){
        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                .map(flightMapper::toDTO)
                .filter(flightDto -> flightDto.getDepartureDate().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(FlightDto::getDepartureDate))
                .collect(Collectors.toList());
    }


}
