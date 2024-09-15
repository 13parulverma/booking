package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for handling the fetching of bookings.*/
@Service
public class FetchBookingService {
    private final Logger logger = LoggerFactory.getLogger(FetchBookingService.class);
    private final BookingRepository bookingRepository;

    /**
     * Constructor for FetchBookingService with constructor injection.
     *
     * @param bookingRepository the repository to handle booking persistence
     */
    public FetchBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    /**
     * Fetches a booking with the given ID.
     *
     * @param id the ID of the booking to be fetched
     * @return the fetched booking DTO
     */
    public Optional<BookingDTO> fetchBookingbyId(Long id) {

        Optional<BookingDTO> booking;
        try{
            logger.debug("Fetching booking with ID: {}", id);
            booking= bookingRepository.findById(id);
        }catch (Exception ex) {
            logger.error("Error while fetching booking with ID: {} from couchbase", id);
            throw new CouchbaseException("Error while fetching booking with ID: " + id + " from couchbase");
        }
        return booking;
    }

    public List<BookingDTO> fetchAllBookings(){
        try {
            logger.debug("Fetching all bookings");
            return bookingRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error while fetching all bookings", ex);
            throw new CouchbaseException("Error while fetching bookings in couchbase");
        }
    }
}