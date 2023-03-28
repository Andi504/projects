package com.airline.flight.controller;

import com.airline.flight.dto.TripDto;
import com.airline.flight.enums.TripStatus;
import com.airline.flight.request.TripRequest;
import com.airline.flight.service.Iservice.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/trip")
public class TripController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/save")
    public ResponseEntity<TripDto> saveTrip(@RequestBody @Valid TripDto tripDto) {
        LOGGER.info("saving trip to db {}", tripDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/trip/save").toUriString());
        return ResponseEntity.created(uri).body(tripService.save(tripDto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTripStatus(@RequestBody @Valid TripRequest tripRequest) {
        LOGGER.info("Updating trip with trip_id {} and status {}", tripRequest.getTip(), tripRequest.getTripStatus());
        tripService.updateTripStatus(tripRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/send-approval")
    public ResponseEntity<?> sendApprovalRequest(@PathVariable("id") Long tripId) {
        tripService.updateTripStatusById(tripId, TripStatus.WAITING_FOR_APPROVAL.name());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/trips")
    public ResponseEntity<List<TripDto>> fetchTripsByStatus() {
        LOGGER.info("fetching trips");
        return ResponseEntity.ok().body(tripService.fetchAllTripsByStatus());
    }

    @PostMapping("/flights")
    public ResponseEntity<TripStatus> addFlight(@RequestParam Long tip,
                                                @RequestParam Long flightNumber) {
        LOGGER.info("Adding flight to {} with flight number {}", tip, flightNumber);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/trip/flights").toUriString());
        return ResponseEntity.created(uri).body(tripService.addFlightDependingOnStatus(tip, flightNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        LOGGER.info("Deleting trip with id {} ", id);
        tripService.deleteTrip(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @RequestBody @Valid TripDto tripDto) {
        LOGGER.info("Updating trip with id {} and trip {} ", id, tripDto);
        tripService.updateTrip(id, tripDto);
        return ResponseEntity.ok().build();
    }
}

