package com.example.airport.flightoperations.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Converter class used by CSV processor to convert a string to FlightStatus enum
 */
public class LocalFlightStatusConverter extends AbstractBeanField {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return FlightStatus.valueOf(s);
    }

}
