package com.example.airport.flightoperations.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class that represents an airport object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "airports")
public class Airport {
    @Id
    @Column(length = 3)
    private String code;
    private String name;
    private String city;
    private String country;
}
