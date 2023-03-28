package com.airline.flight.controller;

import com.airline.flight.dto.FlightDto;
import com.airline.flight.service.Iservice.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/flight")
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/save")
    public ResponseEntity<FlightDto> saveFlight(@Valid @RequestBody FlightDto flightDto){
        LOGGER.info("Saving flight to db {}", flightDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/flight/save").toUriString());
        return ResponseEntity.created(uri).body(flightService.save(flightDto));
    }

    @GetMapping("")
    public ResponseEntity<List<FlightDto>> fetchFlights(){
        LOGGER.info("Fetching flights");
        return ResponseEntity.ok().body(flightService.fetchFlights());
    }

}
