package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.persistence.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service class responsible for handling the fetching of hotels.
 */
@Service
public class FetchHotelService {
    /**
     * Logger for logging FetchHotelService events.
     */
    private final Logger logger = LoggerFactory.getLogger(FetchHotelService.class);

    /**
     * Repository for handling hotel persistence.
     */
    private final HotelRepository hotelRepository;

    /**
     * Constructor for FetchHotelService with constructor injection.
     *
     * @param hotelRepository the repository to handle hotel persistence
     */
    @Autowired
    public FetchHotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Fetches a hotel by the given ID.
     *
     * @param hotelId the ID of the hotel to be fetched
     * @return an Optional containing the fetched hotel DTO if found, otherwise empty
     * @throws CouchbaseException if there is an error while fetching the hotel
     */
    public Optional<HotelDTO> fetchHotelById(Long hotelId) {
        Optional<HotelDTO> hotelDTO;
        try {
            logger.debug("Fetching hotel with ID: {}", hotelId);
            hotelDTO = hotelRepository.findById(hotelId);
        } catch (Exception ex) {
            logger.error("Error while fetching hotel with ID: {}", hotelId, ex);
            throw new CouchbaseException("Error while fetching hotel with ID: " + hotelId + " from couchbase");
        }
        return hotelDTO;
    }

    /**
     * Fetches all hotels.
     *
     * @return a list of all hotel DTOs
     * @throws CouchbaseException if there is an error while fetching the hotels
     */
    public List<HotelDTO> fetchAllHotels() {
        try {
            logger.debug("Fetching all hotels");
            return hotelRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error while fetching all hotels", ex);
            throw new CouchbaseException("Error while fetching hotels in couchbase");
        }
    }
}