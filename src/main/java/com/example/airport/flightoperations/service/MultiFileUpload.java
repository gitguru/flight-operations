package com.example.airport.flightoperations.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface to be implemented by services processing feed flights operations
 */
public interface MultiFileUpload {

    /**
     * Process feed file
     * @param file - a multipart file uploaded from www
     */
    void processFile(MultipartFile file);

}
