package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.mapper.BookingDTOToBookingMapper;
import com.hrs.hotel.booking.mapper.BookingToBookingDTOMapper;
import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.service.CreateBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Delegate class responsible for handling the creation of bookings.
 */
@Component
public class CreateBookingDelegate {

    private final CreateBookingService createBookingService;
    private final BookingToBookingDTOMapper bookingToBookingDTOMapper;
    private final BookingDTOToBookingMapper bookingDTOToBookingMapper;
    private final Logger logger = LoggerFactory.getLogger(CreateBookingDelegate.class);

    /**
     * Constructor for CreateBookingDelegate with constructor injection.
     *
     * @param createBookingService the service to create bookings
     * @param bookingToBookingDTOMapper the mapper to convert booking requests to booking DTOs
     * @param bookingDTOToBookingMapper the mapper to convert booking DTOs to booking responses
     */
    public CreateBookingDelegate(CreateBookingService createBookingService,
                                 BookingToBookingDTOMapper bookingToBookingDTOMapper,
                                 BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        this.createBookingService = createBookingService;
        this.bookingToBookingDTOMapper = bookingToBookingDTOMapper;
        this.bookingDTOToBookingMapper = bookingDTOToBookingMapper;
    }

    /**
     * Executes the creation of a new booking.
     *
     * @param createBooking the booking request
     * @return a ResponseEntity containing the created booking response
     */
    public ResponseEntity<Booking> execute(Booking createBooking) {
        validateRequest(createBooking);

        logger.debug("Mapping CreateBooking to BookingDTO");
        BookingDTO bookingDTO = bookingToBookingDTOMapper.map(createBooking);

        logger.debug("Calling CreateBookingservice");
        BookingDTO createBookingDTO = createBookingService.createBooking(bookingDTO);

        logger.debug("Mapping BookingDTO to CreateBooking");
        Booking bookingResponse = bookingDTOToBookingMapper.map(createBookingDTO);
        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }

    /**
     * Validates the booking request.
     *
     * @param createBooking the booking request to validate
     * @throws IllegalArgumentException if the booking request is invalid
     */
    private void validateRequest(Booking createBooking) {
        if (createBooking == null) {
            throw new IllegalArgumentException("Booking request cannot be null");
        }
        if (createBooking.getUserId() == null || createBooking.getUserId().isEmpty()) {
            throw new IllegalArgumentException("user ID cannot be null or empty");
        }
        if (createBooking.getUserName() == null || createBooking.getUserName().isEmpty()) {
            throw new IllegalArgumentException("user name cannot be null or empty");
        }
        if (createBooking.getHotelName() == null || createBooking.getHotelName().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be null or empty");
        }
    }
}