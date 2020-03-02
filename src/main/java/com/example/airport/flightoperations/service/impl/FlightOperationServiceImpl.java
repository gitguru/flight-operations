package com.example.airport.flightoperations.service.impl;

import com.example.airport.flightoperations.model.Airport;
import com.example.airport.flightoperations.model.Flight;
import com.example.airport.flightoperations.model.request.*;
import com.example.airport.flightoperations.repository.FlightOperationRepository;
import com.example.airport.flightoperations.service.AirportService;
import com.example.airport.flightoperations.service.FlightOperationService;
import com.example.airport.flightoperations.util.Constants;
import com.example.airport.flightoperations.util.FlightOperationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@Slf4j
public class FlightOperationServiceImpl implements FlightOperationService {

    @Autowired
    private FlightOperationRepository flightOperationRepository;
    @Autowired
    private AirportService airportService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Flight> getFlightOperations(FlightOperationRequest flightOperationRequest) {
        Airport airport = airportService.getAirport(flightOperationRequest.getAirport());
        if (FlightOperationType.ARRIVALS.equals(flightOperationRequest.getFlightOperationType())) {
            return getArrivalFlights(airport);
        } else {
            return getDepartureFlights(airport);
        }
    }

    /**
     * Get all arrival flights to given airport
     * @param airport - an arrival airport
     * @return - a list of arrivals
     */
    private Iterable<Flight> getArrivalFlights(Airport airport) {
        return flightOperationRepository.findAllByDestinationOrderByDepartureTime(airport);
    }

    /**
     * Get all departure flights from a given airport
     * @param airport - a departure airport
     * @return - a list of departures
     */
    private Iterable<Flight> getDepartureFlights(Airport airport) {
        return flightOperationRepository.findAllByOriginOrderByArrivalTime(airport);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processFile(MultipartFile file) {
        log.info("Start processing flights information feed file.");

        // select file processor based on file type
        String contentType = file.getContentType();
        log.info("File: {} of Type: {}", file.getOriginalFilename(), contentType);

        if (Constants.CSV.equals(contentType)) {
            processCSVFeed(file);
        } else if (Constants.XML.equals(contentType)) {
            processXMLFeed(file);
        } else if (Constants.JSON.equals(contentType)) {
            processJSONFeed(file);
        } else {
            log.error("Invalid file format uploaded: {}", contentType);
        }
    }

    /**
     * Process a CSV file containing flight operations data
     * @param csvFile - a CSV file
     */
    private void processCSVFeed(MultipartFile csvFile) {

        // parse CSV file to create a list of `FlightOperationsCSVRequest` objects
        try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            // create csv bean reader
            CsvToBean<FlightOperationsCSVRequest> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(FlightOperationsCSVRequest.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of flights requests for new or updates
            List<FlightOperationsCSVRequest> flightCSVRequests = csvToBean.parse();

            // save to DB
            flightCSVRequests.forEach(f -> saveOrUpdateFlightInformation(f));
        } catch (Exception ex) {
            log.error("Error processing CSV flights information feed file.", ex);
        }

    }

    /**
     * Process a XML file containing flight operations data
     * @param xmlFile - a XML file
     */
    private void processXMLFeed(MultipartFile xmlFile) {

        try {
            // parse XML file to create a list of `FlightOperationsXMLRequest` objects
            JAXBContext jaxbContext = JAXBContext.newInstance(FlightOperationsXMLRequestWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();


            try (Reader reader = new BufferedReader(new InputStreamReader(xmlFile.getInputStream()))) {
                // create xml bean reader
                FlightOperationsXMLRequestWrapper flightOperationsXMLRequest =
                        (FlightOperationsXMLRequestWrapper) jaxbUnmarshaller.unmarshal(reader);

                // save to DB
                flightOperationsXMLRequest.getFlightOperations().forEach(f -> saveOrUpdateFlightInformation(f));
            } catch (Exception ex) {
                log.error("Error processing XML flights information feed file.", ex);
            }
        } catch (JAXBException ex) {
            log.error("Error processing XML flights information feed file.", ex);
        }

    }

    /**
     * Process a JSON file containing flight operations data
     * @param jsonFile - a JSON file
     */
    private void processJSONFeed(MultipartFile jsonFile) {

        try {
            // parse JSON file to create a list of `FlightOperationsJSONRequest` objects
            ObjectMapper objectMapper = new ObjectMapper();

            JAXBContext jaxbContext = JAXBContext.newInstance(FlightOperationsXMLRequestWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            try (Reader reader = new BufferedReader(new InputStreamReader(jsonFile.getInputStream()))) {
                // create json bean reader
                FlightOperationsJSONRequestWrapper flightOperationsJSONRequest =
                        objectMapper.readValue(reader, FlightOperationsJSONRequestWrapper.class);

                // save to DB
                flightOperationsJSONRequest.getFlightOperations().forEach(f -> saveOrUpdateFlightInformation(f));
            } catch (Exception ex) {
                log.error("Error processing JSON flights information feed file.", ex);
            }
        } catch (JAXBException ex) {
            log.error("Error processing JSON flights information feed file.", ex);
        }

    }

    /**
     * Convert a CSV flight operation request object to a Flight object and save ir to database
     * @param flightCSVRequest - a CSV flight operation request
     */
    private void saveOrUpdateFlightInformation(FlightOperationsCSVRequest flightCSVRequest) {
        Flight flight = getFlightByNumberOrNew(flightCSVRequest.getNumber());
        flightCSVRequest.convertToFlight(flight);
        flightOperationRepository.save(flight);
    }

    /**
     * Convert a XML flight operation request object to a Flight object and save ir to database
     * @param flightXMLRequest - a XML flight operation request
     */
    private void saveOrUpdateFlightInformation(FlightOperationsXMLRequest flightXMLRequest) {
        Flight flight = getFlightByNumberOrNew(flightXMLRequest.getNumber());
        flightXMLRequest.convertToFlight(flight);
        flightOperationRepository.save(flight);
    }

    /**
     * Convert a JSON flight operation request object to a Flight object and save ir to database
     * @param flightJSONRequest - a JSON flight operation request
     */
    private void saveOrUpdateFlightInformation(FlightOperationsJSONRequest flightJSONRequest) {
        Flight flight = getFlightByNumberOrNew(flightJSONRequest.getNumber());
        flightJSONRequest.convertToFlight(flight);
        flightOperationRepository.save(flight);
    }

    /**
     * Get a flight by flight number or return a new Flight object that can be used
     * @param flightNumber - a flight number
     * @return - a flight object from database or a new one if not found
     */
    private Flight getFlightByNumberOrNew(String flightNumber) {
        return flightOperationRepository.findByNumber(flightNumber).orElse(new Flight());
    }
}

