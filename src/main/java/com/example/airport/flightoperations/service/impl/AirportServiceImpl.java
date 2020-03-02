package com.example.airport.flightoperations.service.impl;

import com.example.airport.flightoperations.model.Airport;
import com.example.airport.flightoperations.repository.AirportRepository;
import com.example.airport.flightoperations.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Airport getAirport(String id) {
        String reason = String.format("Airport with id: %s not found.", id);
        return airportRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, reason));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Airport> getAirports() {
        return airportRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
