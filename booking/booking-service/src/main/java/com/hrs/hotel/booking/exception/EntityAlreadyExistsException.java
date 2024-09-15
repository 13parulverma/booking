package com.hrs.hotel.booking.exception;

/**
 * Custom exception class for handling cases where an entity already exists.
 */
public class EntityAlreadyExistsException extends RuntimeException{

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
