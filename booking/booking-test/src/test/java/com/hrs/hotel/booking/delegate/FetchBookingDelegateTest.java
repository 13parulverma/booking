// Test class for FetchBookingDelegate
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Test class for FetchBookingDelegate.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
class FetchBookingDelegateTest extends JsonFileReader {

    @Autowired
    private FetchBookingDelegate fetchBookingDelegate;

    @MockBean
    private BookingRepository bookingRepository;


    /**
     * Tests the positive scenario for fetching a booking.
     *
     * @throws IOException if there is an error reading the JSON files
     */
    @Test
    void testFetchBooking_PositiveScenario() throws IOException {
        // Read input data from JSON files
        Booking expectedBooking = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\responses\\fetchBookingExpectedResponse.json", Booking.class);
        BookingDTO bookingDTO = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\DTO\\BookingDTO.json", BookingDTO.class);

        // Mock the BookingRepository.findById method
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(bookingDTO));

        // Act
        ResponseEntity<Booking> responseEntity = fetchBookingDelegate.execute(1L);

        // Assert
        Booking fetchBookingResponse = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(fetchBookingResponse);
        assertNotNull(fetchBookingResponse.getBookingId());
        assertEquals(expectedBooking.getBookingId(), fetchBookingResponse.getBookingId());
    }

    /**
     * Tests the scenario where the booking to be fetched is not found.
     *
     * @throws EntityNotFoundException if the booking is not found
     */
    @Test
    void testFetchBooking_BookingNotFound() throws EntityNotFoundException {
        // Mock the BookingRepository.findById method to return empty
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Act
        try {
            ResponseEntity<Booking> responseEntity = fetchBookingDelegate.execute(1L);
        } catch (EntityNotFoundException ex) {
            // Assert
            assertEquals("Booking with ID: 1 not found", ex.getMessage());
        }
    }
}