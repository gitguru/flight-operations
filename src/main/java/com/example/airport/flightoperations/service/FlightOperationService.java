package com.example.airport.flightoperations.service;

import com.example.airport.flightoperations.model.Flight;
import com.example.airport.flightoperations.model.request.FlightOperationRequest;

/**
 * Interface to be implemented by a services handling all flights operations
 * like getting flights status and to keep flights operations data up-to-date
 */
public interface FlightOperationService extends MultiFileUpload {

    /**
     * Get a list of current flights status. Accepts a flight operation type that can be arrivals or departures and a airport code
     *
     * @param flightOperationRequest - request object to determine airport and flight operation type
     * @return - a list of flights operations
     */
    Iterable<Flight> getFlightOperations(FlightOperationRequest flightOperationRequest);

}
