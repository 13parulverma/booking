package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.model.DeleteResponse;
import com.hrs.hotel.booking.service.DeleteHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
/**
 * Delegate class responsible for handling the deletion of hotels.
 */
@Component
public class DeleteHotelDelegate {

    private final Logger logger = LoggerFactory.getLogger(DeleteHotelDelegate.class);
    private final DeleteHotelService deleteHotelService;

    /**
     * Constructor for DeleteHotelDelegate with constructor injection.
     *
     * @param deleteHotelService the service to delete hotels
     */
    public DeleteHotelDelegate(DeleteHotelService deleteHotelService) {
        this.deleteHotelService = deleteHotelService;
    }

    /**
     * Executes the deletion of a hotel with the given ID.
     *
     * @param id the ID of the hotel to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    public ResponseEntity<DeleteResponse> execute(Long id) {
        logger.debug("Deleting hotel with ID: {}", id);
        deleteHotelService.deleteHotel(id);
        String message = "Hotel with ID " + id + " has been deleted successfully";
        DeleteResponse deleteResponse = new DeleteResponse(message);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}