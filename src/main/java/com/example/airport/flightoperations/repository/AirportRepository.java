package com.example.airport.flightoperations.repository;

import com.example.airport.flightoperations.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    Optional<Airport> findById(String code);

}
