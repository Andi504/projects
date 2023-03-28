package com.airline.flight.service.implementation;

import com.airline.flight.dto.TripDto;
import com.airline.flight.entity.Flight;
import com.airline.flight.entity.Trip;
import com.airline.flight.entity.User;
import com.airline.flight.enums.TripStatus;
import com.airline.flight.exeptions.ResourceNotFoundException;
import com.airline.flight.exeptions.TripException;
import com.airline.flight.mapper.TripMapper;
import com.airline.flight.repository.FlightRepository;
import com.airline.flight.repository.TripRepository;
import com.airline.flight.repository.UserRepository;
import com.airline.flight.request.TripRequest;
import com.airline.flight.service.Iservice.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.airline.flight.enums.TripStatus.*;

@Service
public class TripServiceImpl implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceImpl.class);

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public TripServiceImpl(TripRepository tripRepository, TripMapper tripMapper, UserRepository userRepository,
                           FlightRepository flightRepository) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TripDto save(TripDto tripDto) {
        LOGGER.debug("Saving trip {}", tripDto);
        Trip trip = tripMapper.toEntity(tripDto);
        trip.setTripStatus(CREATED);
        trip.setUser(getLoggedInUser());
        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toDTO(savedTrip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripDto> fetchAllTripsByStatus() {
        List<Trip> trips = tripRepository.findAllByTripStatus(WAITING_FOR_APPROVAL);
        return tripMapper.toDtoList(trips);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTripStatusById(Long id, String tripStatus) {
        tripRepository.updateTripStatus(tripStatus, id);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTripStatus(TripRequest tripRequest) {
        LOGGER.debug("Changing trip_status {} for WAITING_FOR_APPROVAL  with trip_id {}", tripRequest.getTripStatus(), tripRequest.getTip());
        tripRepository.updateTripStatus(tripRequest.getTripStatus().name(), tripRequest.getTip());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TripStatus addFlightDependingOnStatus(Long tip, Long flightNumber) {
        Optional<Trip> trip = tripRepository.findById(tip);
        Optional<Flight> flight = flightRepository.findByFlightNumber(flightNumber);
        if (trip.isPresent() && trip.get().getTripStatus().equals(APPROVED) && flight.isPresent()) {
            trip.get().setFlight(flight.get());
            LOGGER.debug("Adding flight to trip with id{} with flight number {}", tip, flightNumber);
            return trip.get().getTripStatus();
        }
        throw new IllegalArgumentException("Trip is not present or trip status is not approved");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteTrip(Long id){
        Optional<Trip> tripById = tripRepository.findById(id);
        if (tripById.isEmpty()){
            throw new ResourceNotFoundException("Trip does not exist");
        }
        tripRepository.delete(tripById.get());
        LOGGER.debug("Deleting trip {} with id {} ", tripById.get(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TripDto updateTrip(Long id, TripDto tripDto){
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isEmpty()){
            throw new ResourceNotFoundException("Trip does not exist");
        }
        trip.get().setTripDescription(tripDto.getTripDescription());
        trip.get().setReasonOfTraveling(tripDto.getReasonOfTraveling());
        trip.get().setFromLocation(tripDto.getFromLocation());
        trip.get().setToLocation(tripDto.getToLocation());
        trip.get().setDepartureDate(tripDto.getDepartureDate());
        trip.get().setArrivalDate(tripDto.getArrivalDate());
        trip.get().setTripStatus(WAITING_FOR_APPROVAL);
        trip.get().setUser(getLoggedInUser());
        return tripMapper.toDTO(tripRepository.save(trip.get()));
    }



    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<User> userByUsername = userRepository.findByUsername(name);
        LOGGER.debug("The logged in user is {}", userByUsername);
        return userByUsername.get();
    }
}
