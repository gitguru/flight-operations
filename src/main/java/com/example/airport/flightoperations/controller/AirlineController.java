package com.example.airport.flightoperations.controller;

import com.example.airport.flightoperations.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Airline controller that handle all airline HTTP request operations
 */
@Controller
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping()
    public String index(Model model) {
        // add airlines list to model
        model.addAttribute("airlines", airlineService.getAirlines());
        model.addAttribute("status", true);

        return "airlines";
    }

}
