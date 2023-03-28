package com.airline.flight.repository;

import com.airline.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    Optional<Flight> findByFlightNumber(Long flightNumber);
}
