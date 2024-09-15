package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.mapper.BookingDTOToBookingMapper;
import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.service.FetchBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Delegate class responsible for handling the fetching of bookings.
 */
@Component
public class FetchBookingDelegate {
    private final FetchBookingService fetchBookingService;
    private final BookingDTOToBookingMapper bookingDTOToBookingMapper;
    private final Logger logger = LoggerFactory.getLogger(FetchBookingDelegate.class);

    /**
     * Constructor for FetchBookingDelegate with constructor injection.
     *
     * @param fetchBookingService the service to fetch bookings
     * @param bookingDTOToBookingMapper the mapper to convert booking DTOs to booking responses
     */
    public FetchBookingDelegate(FetchBookingService fetchBookingService,
                                BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        this.fetchBookingService = fetchBookingService;
        this.bookingDTOToBookingMapper = bookingDTOToBookingMapper;
    }

    /**
     * Executes the fetching of a booking with the given ID.
     *
     * @param id the ID of the booking to be fetched
     * @return a ResponseEntity containing the fetched booking response
     * @throws EntityNotFoundException if the booking with the given ID is not found
     */
    public ResponseEntity<Booking> execute(Long id) {
        logger.debug("Fetching booking with ID: {}", id);
        Optional<BookingDTO> bookingDTO = fetchBookingService.fetchBookingbyId(id);
        if (bookingDTO.isEmpty()) {
            logger.error("Booking with ID: {} not found", id);
            throw new EntityNotFoundException("Booking with ID: " + id + " not found");
        }
        Booking fetchBooking = bookingDTOToBookingMapper.map(bookingDTO.get());
        logger.debug("Successfully fetched booking with ID: {}", id);

        return new ResponseEntity<>(fetchBooking, HttpStatus.OK);
    }

    /**
     * Executes the fetching of all bookings.
     *
     * @return a ResponseEntity containing the list of all fetched booking responses
     */
    public ResponseEntity<List<Booking>> execute() {
        logger.debug("Fetching all bookings");
        List<BookingDTO> bookingDTOs = fetchBookingService.fetchAllBookings();

        List<Booking> fetchBookings = mapBookingDTOListtoBookingList(bookingDTOs);
        logger.debug("Successfully fetched all bookings");

        return new ResponseEntity<>(fetchBookings, HttpStatus.OK);
    }

    /**
     * Maps a list of BookingDTOs to a list of Bookings.
     *
     * @param bookingDTOs the list of BookingDTOs to be mapped
     * @return the list of mapped Bookings
     */
    private List<Booking> mapBookingDTOListtoBookingList(List<BookingDTO> bookingDTOs) {
        logger.debug("Mapping BookingDTO list to Booking list");

        return bookingDTOs.stream()
                .map(bookingDTOToBookingMapper::map)
                .collect(Collectors.toList());
    }
}