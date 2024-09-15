package com.hrs.hotel.booking.api.controller;

import com.hrs.hotel.booking.delegate.CreateBookingDelegate;
import com.hrs.hotel.booking.delegate.DeleteBookingDelegate;
import com.hrs.hotel.booking.delegate.FetchBookingDelegate;
import com.hrs.hotel.booking.delegate.UpdateBookingDelegate;
import com.hrs.hotel.booking.model.Booking;

import com.hrs.hotel.booking.model.DeleteResponse;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
/**
 * REST controller for managing bookings.
 */

/**
 * TODO: SpringSecurity Implementation
 */
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Inject
    CreateBookingDelegate createBookingDelegate;
    @Inject
    UpdateBookingDelegate updateBookingDelegate;
    @Inject
    FetchBookingDelegate fetchBookingDelegate;
    @Inject
    DeleteBookingDelegate deleteBookingDelegate;

    /**
     * Creates a new booking.
     *
     * @param createBooking the booking request
     * @return a ResponseEntity containing the created booking response
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking createBooking) {
        return createBookingDelegate.execute(createBooking);
    }

    /**
     * Updates an existing booking.
     *
     * @param id the ID of the booking to be updated
     * @param updateBooking the booking request with updated details
     * @return a ResponseEntity containing the updated booking response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updateBooking) {
        return updateBookingDelegate.execute(id, updateBooking);
    }

    /**
     * Fetches a booking by its ID.
     *
     * @param id the ID of the booking to be fetched
     * @return a ResponseEntity containing the fetched booking response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> fetchBooking(@PathVariable Long id) {
        return fetchBookingDelegate.execute(id);
    }

    /**
     * Fetches all bookings.
     *
     * @return a ResponseEntity containing the list of all bookings
     */
    @GetMapping("")
    public ResponseEntity<List<Booking>> fetchBooking() {
        return fetchBookingDelegate.execute();
    }

    /**
     * Deletes a booking by its ID.
     *
     * @param id the ID of the booking to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> cancelBooking(@PathVariable Long id) {
        return deleteBookingDelegate.execute(id);
    }
}