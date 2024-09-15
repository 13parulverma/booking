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
 * Service class responsible for handling the deletion of hotels.
 */
@Service
public class DeleteHotelService {
    private final FetchHotelService fetchHotelService;
    private final HotelRepository hotelRepository;
    private final Logger logger = LoggerFactory.getLogger(DeleteHotelService.class);

    /**
     * Constructor for DeleteHotelService with constructor injection.
     *
     * @param fetchHotelService the service to fetch hotels
     * @param hotelRepository the repository to handle hotel persistence
     */
    public DeleteHotelService(FetchHotelService fetchHotelService, HotelRepository hotelRepository) {
        this.fetchHotelService = fetchHotelService;
        this.hotelRepository = hotelRepository;
    }


    /**
     * Deletes a hotel with the given ID.
     *
     * @param id the ID of the hotel to be deleted
     * @throws EntityNotFoundException if the hotel is not found
     * @throws CouchbaseException if there is an error while deleting the hotel
     */
    public void deleteHotel(Long id) {
        Optional<HotelDTO> fetchedHotel;
        try {
            fetchedHotel = fetchHotelService.fetchHotelById(id);
        } catch (Exception ex) {
            logger.error("Error while fetching hotel with ID: {} in couchbase", id);
            throw new CouchbaseException("Error while fetching hotel with ID: " + id + " in couchbase");
        }
        if (fetchedHotel.isPresent()) {
            try {
                logger.debug("Deleting hotel with ID: {}", id);
                hotelRepository.deleteById(id);
                logger.debug("Deleted hotel with ID: {}", id);
            } catch (Exception ex) {
                logger.error("Error while deleting hotel with ID: {} in couchbase", id);
                throw new CouchbaseException("Error while deleting hotel with ID: " + id + " in couchbase");
            }
        } else {
            logger.debug("Cannot delete hotel, because hotel with ID: {} not found", id);
            throw new EntityNotFoundException("Cannot delete hotel, because hotel with ID: " + id + " not found");
        }
    }
}