package com.airline.flight.repository;


import com.airline.flight.entity.Trip;
import com.airline.flight.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByTripStatus(TripStatus tripStatus);

    @Modifying
    @Query(value = "UPDATE TRIP SET TRIP_STATUS = :tripStatus WHERE TIP = :tip", nativeQuery = true)
    void updateTripStatus(String tripStatus, Long tip);

//    @Modifying
//    @Query(value = "UPDATE TRIP SET TRIP_STATUS = WAITING_FOR_APPROVAL WHERE TIP = :id", nativeQuery = true)
//    void updateTripStatusById(Long id,TripStatus tripStatus);

//    @Query(value = "SELECT TIP FROM TRIP WHERE uid = :uid")
//    Long findTripIdForLoggedInUser(Long uid);
}
