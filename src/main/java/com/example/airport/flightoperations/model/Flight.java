package com.example.airport.flightoperations.model;

import com.example.airport.flightoperations.util.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class that represents a flight object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flights", indexes = {@Index(name = "flights_number_idx", columnList = "number", unique = false)})
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 10, nullable = false)
    private String number;
    @Column(nullable = false)
    private Date departureTime;
    @Column(nullable = false)
    private Date arrivalTime;
    @ManyToOne
    private Airport origin;
    @ManyToOne
    private Airport destination;
    @ManyToOne
    private Airline airline;
    @Column(nullable = false)
    private int gate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

}
