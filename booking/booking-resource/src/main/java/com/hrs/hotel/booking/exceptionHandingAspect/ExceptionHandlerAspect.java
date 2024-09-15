package com.hrs.hotel.booking.exceptionHandingAspect;

import com.hrs.hotel.booking.exception.EntityAlreadyExistsException;
import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * Aspect for handling exceptions globally across the application.
 * Provides specific handlers for different types of exceptions.
 */
@RestControllerAdvice
public class ExceptionHandlerAspect {

    /**
     * Handles EntityNotFoundException and returns a 404 Not Found response.
     *
     * @param ex the EntityNotFoundException thrown
     * @return a ResponseEntity containing a CustomErrorResponse with the exception message
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomErrorResponse error = new CustomErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles EntityAlreadyExistsException and returns a 409 Conflict response.
     *
     * @param ex the EntityAlreadyExistsException thrown
     * @return a ResponseEntity containing a CustomErrorResponse with the exception message
     */
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        CustomErrorResponse error = new CustomErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handles CouchbaseException and returns a 500 Internal Server Error response.
     *
     * @param ex the CouchbaseException thrown
     * @return a ResponseEntity containing a CustomErrorResponse with the exception message
     */
    @ExceptionHandler(CouchbaseException.class)
    public ResponseEntity<CustomErrorResponse> handleCouchbaseException(CouchbaseException ex) {
        CustomErrorResponse error = new CustomErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles generic exceptions and returns a 500 Internal Server Error response.
     *
     * @param ex the Exception thrown
     * @return a ResponseEntity containing a CustomErrorResponse with the exception message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex) {
        CustomErrorResponse error = new CustomErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}