package com.hrs.hotel.booking.exception;

/**
 * Custom exception class for handling cases where a booking is not found.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}