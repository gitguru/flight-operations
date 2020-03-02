package com.example.airport.flightoperations.controller;

import com.example.airport.flightoperations.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Airport controller that handle all airport HTTP request operations
 */
@Controller
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping()
    public String index(Model model) {
        // add airports list to model
        model.addAttribute("airports", airportService.getAirports());
        model.addAttribute("status", true);

        return "airports";
    }

}
