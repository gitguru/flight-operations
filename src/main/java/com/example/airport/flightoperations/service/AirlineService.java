package com.example.airport.flightoperations.service;

import com.example.airport.flightoperations.model.Airline;

/**
 * Interface to be implemented by a services handling all airline crud operations
 */
public interface AirlineService {

    /**
     * Get a list of all airlines
     *
     * @return - a list of airlines present in database or empty if none
     */
    Iterable<Airline> getAirlines();

}
