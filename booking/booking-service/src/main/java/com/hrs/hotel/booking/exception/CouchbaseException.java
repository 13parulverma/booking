package com.hrs.hotel.booking.exception;

/**
 * Custom exception class for handling Couchbase-related errors.
 */
public class CouchbaseException extends RuntimeException{
    public CouchbaseException(String message) {
        super(message);
    }
}
