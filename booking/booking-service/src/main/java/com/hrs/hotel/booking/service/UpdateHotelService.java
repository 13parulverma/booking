package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.persistence.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for handling the updating of hotels.
 */
@Service
public class UpdateHotelService {
    private final Logger logger = LoggerFactory.getLogger(UpdateHotelService.class);
    private final FetchHotelService fetchHotelService;
    private final HotelRepository hotelRepository;

    /**
     * Constructor for UpdateHotelService with constructor injection.
     *
     * @param fetchHotelService the service to fetch hotels
     * @param hotelRepository the repository to handle hotel persistence
     */
    public UpdateHotelService(FetchHotelService fetchHotelService, HotelRepository hotelRepository) {
        this.fetchHotelService = fetchHotelService;
        this.hotelRepository = hotelRepository;
    }

    /**
     * Updates a hotel with the given ID.
     *
     * @param id the ID of the hotel to be updated
     * @param hotelDTO the hotel DTO with updated details
     */
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Optional<HotelDTO> fetchedHotel;
        try {
            fetchedHotel = fetchHotelService.fetchHotelById(id);
        } catch (Exception ex) {
            logger.error("Error while fetching hotel with ID: {} in couchbase", id);
            throw new CouchbaseException("Error while fetching hotel with ID: " + id + " in couchbase");
        }
        if (fetchedHotel.isEmpty()) {
            logger.debug("Cannot update hotel, because hotel with ID: {} not found", id);
            throw new EntityNotFoundException("Cannot update hotel, because hotel with ID: " + id + " not found");
        } else {
            try {
                logger.debug("Updating hotel with ID: {}", id);
                hotelDTO.setHotelId(fetchedHotel.get().getHotelId());
                HotelDTO updatedHotel = hotelRepository.save(hotelDTO);
                logger.debug("Updated hotel with ID: {}", id);
                return updatedHotel;
            } catch (Exception ex) {
                logger.error("Error while updating hotel with ID: {} in couchbase", id);
                throw new CouchbaseException("Error while updating hotel with ID: " + id + " in couchbase");
            }
        }
    }
}