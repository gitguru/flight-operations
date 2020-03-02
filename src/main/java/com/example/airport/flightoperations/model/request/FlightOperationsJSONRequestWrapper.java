package com.example.airport.flightoperations.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * This class represents a flight operation feed for a JSON file
 *
 * e.g.
 * {
 *   "FlightOperations": [
 *     {
 *         "Number": "MM-233",
 *         "DepartureTime": "2020-03-01T12:00",
 *         "ArrivalTime": "2020-03-01T17:33",
 *         "Origin": "SYQ",
 *         "Destination": "BCL",
 *         "Airline": "IDX",
 *         "Gate": "66",
 *         "Status": "ON_TIME"
 *     },
 *     {
 *         "Number": "MM-234",
 *         "DepartureTime": "2020-03-01T18:50",
 *         "ArrivalTime": "2020-03-01T20:00",
 *         "Origin": "BCL",
 *         "Destination": "SYQ",
 *         "Airline": "IDX",
 *         "Gate": "67",
 *         "Status": "ON_TIME"
 *     }
 *   ]
 * }
 */
@Data
public class FlightOperationsJSONRequestWrapper {

    @JsonProperty(required = true, value = "FlightOperations")
    private List<FlightOperationsJSONRequest> flightOperations;

}
