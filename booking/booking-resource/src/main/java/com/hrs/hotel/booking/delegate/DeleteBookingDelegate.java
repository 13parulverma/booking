package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.model.DeleteResponse;
import com.hrs.hotel.booking.model.User;
import com.hrs.hotel.booking.service.DeleteBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
/**
 * Delegate class responsible for handling the deletion of bookings.
 */
@Component
public class DeleteBookingDelegate {

    private final Logger logger = LoggerFactory.getLogger(DeleteBookingDelegate.class);
    private final DeleteBookingService deleteBookingService;

    /**
     * Constructor for DeleteBookingDelegate with constructor injection.
     *
     * @param deleteBookingService the service to delete bookings
     */
    public DeleteBookingDelegate(DeleteBookingService deleteBookingService) {
        this.deleteBookingService = deleteBookingService;
    }

    /**
     * Executes the deletion of a booking with the given ID.
     *
     * @param id the ID of the booking to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    public ResponseEntity<DeleteResponse> execute(Long id) {
        logger.debug("Deleting booking with ID: {}", id);
        deleteBookingService.deleteBooking(id);
        String message = "Booking with ID " + id + " has been deleted successfully";
        DeleteResponse deleteResponse = new DeleteResponse(message);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}