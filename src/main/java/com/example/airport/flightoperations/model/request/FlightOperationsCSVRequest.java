package com.example.airport.flightoperations.model.request;

import com.example.airport.flightoperations.model.Airline;
import com.example.airport.flightoperations.model.Airport;
import com.example.airport.flightoperations.model.Flight;
import com.example.airport.flightoperations.util.Constants;
import com.example.airport.flightoperations.util.FlightStatus;
import com.example.airport.flightoperations.util.LocalFlightStatusConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;

/**
 * This class represents a flight operation feed for a CSV entry
 *
 * e.g.
 * Number,DepartureTime,ArrivalTime,Origin,Destination,Airline,Gate,Status
 * IBE - 844,2020-02-27T20:00,2020-02-27T22:00,BAI,UPL,KUH,111,ON_TIME
 */
@Data
public class FlightOperationsCSVRequest {

    @CsvBindByName(required = true, column = "Number")
    private String number;
    @CsvDate(value = Constants.DATE_FORMAT)
    @CsvBindByName(required = true, column = "DepartureTime")
    private Date departureTime;
    @CsvDate(value = Constants.DATE_FORMAT)
    @CsvBindByName(required = true, column = "ArrivalTime")
    private Date arrivalTime;
    @CsvBindByName(required = true, column = "Origin")
    private String origin;
    @CsvBindByName(required = true, column = "Destination")
    private String destination;
    @CsvBindByName(required = true, column = "Airline")
    private String airline;
    @CsvBindByName(column = "Gate")
    private int gate;
    @CsvCustomBindByName(required = true, converter = LocalFlightStatusConverter.class, column = "Status")
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
