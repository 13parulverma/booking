package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.exception.EntityAlreadyExistsException;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.persistence.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service class responsible for handling the creation of hotels.
 */
@Service
public class CreateHotelService {
    /**
     * Logger for logging CreateHotelService events.
     */
    private final Logger logger = LoggerFactory.getLogger(CreateHotelService.class);

    /**
     * Service for fetching hotels.
     */
    private final FetchHotelService fetchHotelService;

    /**
     * Repository for handling hotel persistence.
     */
    private final HotelRepository hotelRepository;

    /**
     * Constructor for CreateHotelService with constructor injection.
     *
     * @param fetchHotelService the service to fetch hotels
     * @param hotelRepository the repository to handle hotel persistence
     */
    @Autowired
    public CreateHotelService(FetchHotelService fetchHotelService, HotelRepository hotelRepository) {
        this.fetchHotelService = fetchHotelService;
        this.hotelRepository = hotelRepository;
    }

    /**
     * Creates a new hotel.
     *
     * @param hotelDTO the hotel DTO
     * @return the persisted hotel DTO
     * @throws EntityAlreadyExistsException if the hotel already exists
     * @throws CouchbaseException if there is an error while persisting the hotel
     */
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        try {
            // Check if hotel already exists
            if (fetchHotelService.fetchHotelById(hotelDTO.getHotelId()).isPresent()) {
                logger.debug("Hotel with ID: {} already exists", hotelDTO.getHotelId());
                throw new EntityAlreadyExistsException("Hotel with ID: " + hotelDTO.getHotelId() + " already exists");
            }

            logger.debug("Persisting hotel in couchbase");
            HotelDTO hotelDTOResponse = hotelRepository.save(hotelDTO);
            logger.debug("Persisting hotel with ID: {}", hotelDTO.getHotelId());
            return hotelDTOResponse;
        } catch (Exception ex) {
            if(ex instanceof EntityAlreadyExistsException) {
                throw ex;
            }
            else {
                logger.error("Error while persisting hotel in couchbase", ex);
                throw new CouchbaseException("Error while persisting hotel in couchbase");
            }
        }
    }
}