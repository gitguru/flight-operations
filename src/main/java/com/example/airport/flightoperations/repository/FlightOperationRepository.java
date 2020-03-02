package com.example.airport.flightoperations.repository;

import com.example.airport.flightoperations.model.Airport;
import com.example.airport.flightoperations.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightOperationRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByNumber(String number);
    Iterable<Flight> findAllByOriginOrderByArrivalTime(Airport origin);
    Iterable<Flight> findAllByDestinationOrderByDepartureTime(Airport origin);

}
