package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for handling the deletion of bookings.
 */
@Service
public class DeleteBookingService {
    /**
     * Service for fetching bookings.
     */
    private final FetchBookingService fetchBookingService;

    /**
     * Repository for handling booking persistence.
     */
    private final BookingRepository bookingRepository;

    /**
     * Logger for logging DeleteBookingService events.
     */
    private final Logger logger = LoggerFactory.getLogger(DeleteBookingService.class);

    /**
     * Constructor for DeleteBookingService with constructor injection.
     *
     * @param fetchBookingService the service to fetch bookings
     * @param bookingRepository the repository to handle booking persistence
     */
    public DeleteBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        this.fetchBookingService = fetchBookingService;
        this.bookingRepository = bookingRepository;
    }


    /**
     * Deletes a booking with the given ID.
     *
     * @param id the ID of the booking to be deleted
     * @throws EntityNotFoundException if the booking is not found
     * @throws CouchbaseException if there is an error while deleting the booking
     */
    public void deleteBooking(Long id) {
        Optional<BookingDTO> fetchedBooking;
        try{
            fetchedBooking = fetchBookingService.fetchBookingbyId(id);
        }catch (Exception ex){
            logger.error("Error while fetching booking with ID: {} in couchbase", id);
            throw new CouchbaseException("Error while fetching booking with ID: "+id+" in couchbase");
        }
        if (fetchedBooking.isPresent()) {
            try {
                logger.debug("Deleting booking with ID: {}", id);
                bookingRepository.deleteById(id);
                logger.debug("Deleted booking with ID: {}", id);
            }catch (Exception ex) {
                logger.error("Error while deleting booking with ID: {} in couchbase", id);
                throw new CouchbaseException("Error while deleting booking with ID: "+id+" in couchbase");
            }
        }
        else{
            logger.debug("Cannot delete booking, because booking with ID: {} not found", id);
            throw new EntityNotFoundException("Cannot delete booking, because booking with ID: "+id+" not found");
        }
    }

}