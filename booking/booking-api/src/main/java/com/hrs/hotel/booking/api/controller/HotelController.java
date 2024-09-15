package com.hrs.hotel.booking.api.controller;

import com.hrs.hotel.booking.delegate.CreateHotelDelegate;
import com.hrs.hotel.booking.delegate.DeleteHotelDelegate;
import com.hrs.hotel.booking.delegate.FetchHotelDelegate;
import com.hrs.hotel.booking.delegate.UpdateHotelDelegate;
import com.hrs.hotel.booking.model.DeleteResponse;
import com.hrs.hotel.booking.model.Hotel;

import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * REST controller for managing hotels.
 */
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Inject
    private CreateHotelDelegate createHotelDelegate;

    @Inject
    private UpdateHotelDelegate updateHotelDelegate;

    @Inject
    private FetchHotelDelegate fetchHotelDelegate;

    @Inject
    private DeleteHotelDelegate deleteHotelDelegate;

    /**
     * Creates a new hotel.
     *
     * @param createHotel the hotel request
     * @return a ResponseEntity containing the created hotel response
     */
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel createHotel) {
        return createHotelDelegate.execute(createHotel);
    }

    /**
     * Updates an existing hotel.
     *
     * @param id the ID of the hotel to be updated
     * @param updateHotel the hotel request with updated details
     * @return a ResponseEntity containing the updated hotel response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel updateHotel) {
        return updateHotelDelegate.execute(id, updateHotel);
    }

    /**
     * Fetches a hotel by its ID.
     *
     * @param id the ID of the hotel to be fetched
     * @return a ResponseEntity containing the fetched hotel response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> fetchHotel(@PathVariable Long id) {
        return fetchHotelDelegate.execute(id);
    }

    /**
     * Fetches all hotels.
     *
     * @return a ResponseEntity containing the list of all hotels
     */
    @GetMapping("")
    public ResponseEntity<List<Hotel>> fetchHotels() {
        return fetchHotelDelegate.execute();
    }

    /**
     * Deletes a hotel by its ID.
     *
     * @param id the ID of the hotel to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteHotel(@PathVariable Long id) {
        return deleteHotelDelegate.execute(id);
    }
}