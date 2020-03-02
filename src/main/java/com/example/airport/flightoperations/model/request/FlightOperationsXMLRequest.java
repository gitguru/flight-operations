package com.example.airport.flightoperations.model.request;

import com.example.airport.flightoperations.model.Airline;
import com.example.airport.flightoperations.model.Airport;
import com.example.airport.flightoperations.model.Flight;
import com.example.airport.flightoperations.util.FlightStatus;
import com.example.airport.flightoperations.util.LocalJaxBDateAdapter;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * This class represents a flight operation feed for a XML entry
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
 *  }
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightOperationsXMLRequest {

    @XmlElement(required = true, name = "Number")
    private String number;
    @XmlElement(required = true, name = "DepartureTime")
    @XmlJavaTypeAdapter(LocalJaxBDateAdapter.class)
    private Date departureTime;
    @XmlElement(required = true, name = "ArrivalTime")
    @XmlJavaTypeAdapter(LocalJaxBDateAdapter.class)
    private Date arrivalTime;
    @XmlElement(required = true, name = "Origin")
    private String origin;
    @XmlElement(required = true, name = "Destination")
    private String destination;
    @XmlElement(required = true, name = "Airline")
    private String airline;
    @XmlElement(name = "Gate")
    private int gate;
    @XmlElement(required = true, name = "Status")
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
