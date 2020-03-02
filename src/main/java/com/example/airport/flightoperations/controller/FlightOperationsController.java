package com.example.airport.flightoperations.controller;

import com.example.airport.flightoperations.model.request.FlightOperationRequest;
import com.example.airport.flightoperations.service.AirportService;
import com.example.airport.flightoperations.service.FlightOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Flight Operations controller that handle all flight arrival, departure HTTP requests operations
 */
@Controller
@Slf4j
@RequestMapping("/flightOperations")
public class FlightOperationsController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private FlightOperationService flightOperationService;

    @GetMapping()
    public String index(Model model) {
        // add required data to model (airports, current flight status)
        model.addAttribute("airports", airportService.getAirports());
        // provide initial state
        model.addAttribute("state", new FlightOperationRequest());
        return "flightOperations";
    }

    @PostMapping()
    public String index(@ModelAttribute FlightOperationRequest flightOperationRequest, Model model) {
        // add required data to model (airports, current flight status)
        model.addAttribute("airports", airportService.getAirports());
        model.addAttribute("flightOperations", flightOperationService.getFlightOperations(flightOperationRequest));
        model.addAttribute("state", flightOperationRequest);
        return "flightOperations";
    }

}
