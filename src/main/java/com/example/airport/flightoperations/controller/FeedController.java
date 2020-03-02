package com.example.airport.flightoperations.controller;

import com.example.airport.flightoperations.service.FlightOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Feed controller that handle all CSV, XML, JSON feed file upload HTTP request operations
 */
@Controller
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FlightOperationService flightOperationService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("status", true);
        return "feed";
    }

    @PostMapping("/flights")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            model.addAttribute("status", false);
        } else {
            // process uploaded flights operations feed file
            flightOperationService.processFile(file);

            // return successfully
            model.addAttribute("status", true);
        }

        return "feed";
    }

}
