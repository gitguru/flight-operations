package com.example.airport.flightoperations.model.request;

import com.example.airport.flightoperations.model.Airline;
import com.example.airport.flightoperations.model.Airport;
import com.example.airport.flightoperations.model.Flight;
import com.example.airport.flightoperations.util.FlightStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * This class represents a flight operation feed for a JSON entry
 *
 * e.g.
 * {
 *         "Number": "MM-233",
 *         "DepartureTime": "2020-03-01T12:00",
 *         "ArrivalTime": "2020-03-01T17:33",
 *         "Origin": "SYQ",
 *         "Destination": "BCL",
 *         "Airline": "IDX",
 *         "Gate": "66",
 *         "Status": "ON_TIME"
 * }
 */
@Data
public class FlightOperationsJSONRequest {

    @JsonProperty(required = true, value = "Number")
    private String number;
    @JsonProperty(required = true, value = "DepartureTime")
    private Date departureTime;
    @JsonProperty(required = true, value = "ArrivalTime")
    private Date arrivalTime;
    @JsonProperty(required = true, value = "Origin")
    private String origin;
    @JsonProperty(required = true, value = "Destination")
    private String destination;
    @JsonProperty(required = true, value = "Airline")
    private String airline;
    @JsonProperty(value = "Gate")
    private int gate;
    @JsonProperty(required = true, value = "Status")
    private FlightStatus status;

    public Flight convertToFlight(Flight flight) {
        flight.setNumber(this.getNumber());
        flight.setDepartureTime(this.getDepartureTime());
        flight.setArrivalTime(this.getArrivalTime());
        flight.setOrigin(Airport.builder().code(this.getOrigin()).build());
        flight.setDestination(Airport.builder().code(this.getDestination()).build());
        flight.setAirline(Airline.builder().code(this.getAirline()).build());
        flight.setGate(this.getGate());
        flight.setStatus(this.getStatus());
        return flight;
    }

}
