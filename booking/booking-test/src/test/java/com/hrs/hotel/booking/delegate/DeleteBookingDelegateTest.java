package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.config.TestConfiguration;
import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.model.DeleteResponse;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Test class for DeleteBookingDelegate.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
class DeleteBookingDelegateTest extends JsonFileReader {


    @Autowired
    private DeleteBookingDelegate deleteBookingDelegate;

    @MockBean
    private BookingRepository bookingRepository;


    /**
     * Tests the positive scenario for deleting a booking.
     *
     * @throws IOException if there is an error reading the JSON files
     */
    @Test
    void testDeleteBooking_PositiveScenario() throws IOException {
        // Read input data from JSON files
        DeleteResponse expectedDeleteResponse = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\responses\\deleteBookingResponse.json", DeleteResponse.class);
        BookingDTO bookingDTO = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\DTO\\BookingDTO.json", BookingDTO.class);

        // Mock the BookingRepository.findById method
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(bookingDTO));
        doNothing().when(bookingRepository).deleteById(anyLong());
        // Act
        ResponseEntity<DeleteResponse> responseEntity = deleteBookingDelegate.execute(1L);

        // Assert
        DeleteResponse deleteBookingResponse = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(deleteBookingResponse);
        assertNotNull(deleteBookingResponse.getMessage());
        assertEquals(expectedDeleteResponse.getMessage(), deleteBookingResponse.getMessage());
    }

    /**
     * Tests the scenario where the booking to be deleted is not found.
     *
     * @throws IOException if there is an error reading the JSON files
     * @throws EntityNotFoundException if the booking is not found
     */
    @Test
    void testUpdateBooking_BookingNotFound() throws IOException, EntityNotFoundException {
        // Mock the BookingRepository.findById method to return empty
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Act
        try {
            ResponseEntity<DeleteResponse> responseEntity = deleteBookingDelegate.execute(1L);
        } catch (EntityNotFoundException ex) {
            // Assert
            assertEquals("Cannot delete booking, because booking with ID: 1 not found", ex.getMessage());
        }
    }
}
