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
 * Service class responsible for handling the updating of bookings.
 */
@Service
public class UpdateBookingService {
    private final Logger logger = LoggerFactory.getLogger(UpdateBookingService.class);
    private final FetchBookingService fetchBookingService;
    private final BookingRepository bookingRepository;

    /**
     * Constructor for UpdateBookingService with constructor injection.
     *
     * @param fetchBookingService the service to fetch bookings
     * @param bookingRepository the repository to handle booking persistence
     */
    public UpdateBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        this.fetchBookingService = fetchBookingService;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Updates a booking with the given ID.
     *
     * @param id the ID of the booking to be updated
     * @param bookingDTO the booking DTO with updated details
     */
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Optional<BookingDTO> fetchedBooking;
        try{
            fetchedBooking = fetchBookingService.fetchBookingbyId(id);
        }catch (Exception ex){
            logger.error("Error while fetching booking with ID: {} in couchbase", id);
            throw new CouchbaseException("Error while fetching booking with ID: "+id+" in couchbase");
        }
        if (fetchedBooking.isEmpty()) {
            logger.debug("Cannot update booking, because booking with ID: {} not found", id);
            throw new EntityNotFoundException("Cannot update booking, because booking with ID: "+id+" not found");
        }
        else{
            try {
                logger.debug("Updating booking with ID: {}", id);
                bookingDTO.setBookingId(fetchedBooking.get().getBookingId());
                BookingDTO updatedBooking = bookingRepository.save(bookingDTO);
                logger.debug("Updated booking with ID: {}", id);
                return updatedBooking;

            }catch (Exception ex) {
                logger.error("Error while updating booking with ID: {} in couchbase", id);
                throw new CouchbaseException("Error while updating booking with ID: "+id+" in couchbase");
            }
        }

    }
}