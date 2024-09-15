package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.exception.EntityAlreadyExistsException;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service class responsible for handling the creation of bookings.
 */
@Service
public class CreateBookingService {
    /**
     * Logger for logging CreateBookingService events.
     */
    private final Logger logger = LoggerFactory.getLogger(CreateBookingService.class);

    /**
     * Service for fetching bookings.
     */
    private final FetchBookingService fetchBookingService;

    /**
     * Repository for handling booking persistence.
     */
    private final BookingRepository bookingRepository;

    /**
     * Constructor for CreateBookingService with constructor injection.
     *
     * @param fetchBookingService the service to fetch bookings
     * @param bookingRepository the repository to handle booking persistence
     */
    @Autowired
    public CreateBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        this.fetchBookingService = fetchBookingService;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Creates a new booking.
     *
     * @param bookingDTO the booking DTO
     * @return the persisted booking DTO
     * @throws EntityAlreadyExistsException if the booking already exists
     * @throws CouchbaseException if there is an error while persisting the booking
     */
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        try {
            // Check if booking already exists
            if (fetchBookingService.fetchBookingbyId(bookingDTO.getBookingId()).isPresent()) {
                logger.debug("Booking with ID: {} already exists", bookingDTO.getBookingId());
                throw new EntityAlreadyExistsException("Booking with ID: " + bookingDTO.getBookingId() + " already exists");
            }
            logger.debug("Persisting booking in couchbase");
            BookingDTO bookingDTOResponse = bookingRepository.save(bookingDTO);
            logger.debug("Persisting booking with ID: {}", bookingDTO.getBookingId());
            return bookingDTOResponse;
        } catch (Exception ex) {
            if (ex instanceof EntityAlreadyExistsException) {
                throw ex;
            } else {
                logger.error("Error while persisting booking in couchbase");
                throw new CouchbaseException("Error while persisting booking in couchbase");
            }
        }
    }
}