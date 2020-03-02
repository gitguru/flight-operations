package com.example.airport.flightoperations.model.request;

import com.example.airport.flightoperations.util.FlightOperationType;
import lombok.Data;

/**
 * This class represents a request object to retrieve a list of flight operations arrivals or departures from given airport
 */
@Data
public class FlightOperationRequest {
    private FlightOperationType flightOperationType = FlightOperationType.ARRIVALS;
    private String airport;
}
