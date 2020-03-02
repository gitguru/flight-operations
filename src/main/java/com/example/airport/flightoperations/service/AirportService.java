package com.example.airport.flightoperations.service;

import com.example.airport.flightoperations.model.Airport;

/**
 * Interface to be implemented by a services handling all airport crud operations
 */
public interface AirportService {

    /**
     * Get an airport by id "code"
     *
     * @param id - an airport code
     * @return - an airport object or not found exception
     */
    Airport getAirport(String id);

    /**
     * Get a list of all airports
     *
     * @return - a list of airports present in database or empty if none
     */
    Iterable<Airport> getAirports();

}
