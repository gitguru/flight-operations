package com.example.airport.flightoperations.service.impl;

import com.example.airport.flightoperations.model.Airline;
import com.example.airport.flightoperations.repository.AirlineRepository;
import com.example.airport.flightoperations.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Airline> getAirlines() {
        return airlineRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}
