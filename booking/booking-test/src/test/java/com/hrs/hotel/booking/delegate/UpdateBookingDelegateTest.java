package com.hrs.hotel.booking.delegate;


import com.hrs.hotel.booking.config.TestConfiguration;
import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.repository.BookingRepository;
import com.hrs.hotel.booking.util.JsonFileReader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Tests the positive scenario for updating a booking.
 *
 * @throws IOException if there is an error reading the JSON files
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})

class UpdateBookingDelegateTest extends JsonFileReader {


    @Autowired
    private UpdateBookingDelegate updateBookingDelegate;

    @MockBean
    private BookingRepository bookingRepository;


    /**
     * Tests the positive scenario for updating a booking.
     *
     * @throws IOException if there is an error reading the JSON files
     */
    @Test
    void testUpdateBooking_PositiveScenario() throws IOException {
        // Read input data from JSON files
        Booking updateBookingRequest = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\requests\\updateBookingRequest.json", Booking.class);
        Booking expectedBooking = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\responses\\updateBookingExpectedResponse.json", Booking.class);
        BookingDTO bookingDTO = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\DTO\\BookingDTO.json", BookingDTO.class);
        BookingDTO updatedBookingDTO = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\DTO\\BookingDTO_updated.json", BookingDTO.class);

        // Mock the BookingRepository.findById method
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(bookingDTO));
        when(bookingRepository.save(any(BookingDTO.class))).thenReturn(updatedBookingDTO);
        // Act
        ResponseEntity<Booking> responseEntity = updateBookingDelegate.execute(1L, updateBookingRequest);

        // Assert
        Booking UpdateBookingResponse = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(UpdateBookingResponse);
        assertNotNull(UpdateBookingResponse.getBookingId());
        assertEquals(expectedBooking.getBookingId(), UpdateBookingResponse.getBookingId());
    }

    /**
     * Tests the scenario where the booking to be updated is not found.
     *
     * @throws IOException if there is an error reading the JSON files
     * @throws EntityNotFoundException if the booking is not found
     */
    @Test
    void testUpdateBooking_BookingNotFound() throws IOException,EntityNotFoundException {
        // Mock the BookingRepository.findById method to return empty
        Booking updateBookingRequest = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\requests\\createBookingRequest.json", Booking.class);
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Act
        try {
            ResponseEntity<Booking> responseEntity = updateBookingDelegate.execute(1L, updateBookingRequest);
        } catch (EntityNotFoundException ex) {
            // Assert
            assertEquals("Cannot update booking, because booking with ID: 1 not found", ex.getMessage());

        }
    }
}
