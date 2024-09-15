package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.mapper.BookingDTOToBookingMapper;
import com.hrs.hotel.booking.mapper.BookingToBookingDTOMapper;
import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.service.UpdateBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
/**
 * Delegate class responsible for handling the updating of bookings.
 */
@Component
public class UpdateBookingDelegate {
    private final UpdateBookingService updateBookingService;
    private final BookingToBookingDTOMapper bookingToBookingDTOMapper;
    private final BookingDTOToBookingMapper bookingDTOToBookingMapper;
    private final Logger logger = LoggerFactory.getLogger(UpdateBookingDelegate.class);

    /**
     * Constructor for UpdateBookingDelegate with constructor injection.
     *
     * @param updateBookingService the service to update bookings
     * @param bookingToBookingDTOMapper the mapper to convert booking requests to booking DTOs
     * @param bookingDTOToBookingMapper the mapper to convert booking DTOs to booking responses
     */
    public UpdateBookingDelegate(UpdateBookingService updateBookingService,
                                 BookingToBookingDTOMapper bookingToBookingDTOMapper,
                                 BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        this.updateBookingService = updateBookingService;
        this.bookingToBookingDTOMapper = bookingToBookingDTOMapper;
        this.bookingDTOToBookingMapper = bookingDTOToBookingMapper;
    }

    /**
     * Executes the updating of a booking with the given ID.
     *
     * @param id the ID of the booking to be updated
     * @param updateBooking the booking request with updated details
     * @return a ResponseEntity containing the updated booking response
     */
    public ResponseEntity<Booking> execute(Long id, Booking updateBooking) {
        logger.debug("Starting update for booking with ID: {}", id);

        logger.debug("Mapping updateBooking request to BookingDTO");
        BookingDTO bookingDTO = bookingToBookingDTOMapper.map(updateBooking);

        logger.debug("Calling UpdateBookingService to update booking");
        BookingDTO updatedBookingDTO = updateBookingService.updateBooking(id, bookingDTO);

        logger.debug("Mapping updated BookingDTO to updateBooking response");
        Booking updateBookingResponse = bookingDTOToBookingMapper.map(updatedBookingDTO);

        logger.debug("Successfully updated booking with ID: {}", id);
        return new ResponseEntity<>(updateBookingResponse, HttpStatus.OK);
    }
}