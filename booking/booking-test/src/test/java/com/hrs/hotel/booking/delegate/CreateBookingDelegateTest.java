// Test class for CreateBookingDelegate
package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.config.TestConfiguration;
import com.hrs.hotel.booking.exception.EntityAlreadyExistsException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Test class for CreateBookingDelegate.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
class CreateBookingDelegateTest extends JsonFileReader {

    @Autowired
    private CreateBookingDelegate createBookingDelegate;// = new CreateBookingDelegate(createBookingService, bookingToBookingDTOMapper, bookingDTOToBookingMapper);

    @MockBean
    private BookingRepository bookingRepository;

    /**
     * Tests the positive scenario for creating a booking.
     *
     * @throws IOException if there is an error reading the JSON files
     */
    @Test
    public void testCreateBooking_PositiveScenario() throws IOException {
        System.out.println(bookingRepository);

        // Read input data from JSON files
        Booking createBooking = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\requests\\createBookingRequest.json", Booking.class);
        Booking expectedBooking = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\responses\\createBookingExpectedResponse.json", Booking.class);
        BookingDTO bookingDTO = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\DTO\\BookingDTO.json", BookingDTO.class);

        // Mock the BookingRepository.save method
        when(bookingRepository.save(any(BookingDTO.class))).thenReturn(bookingDTO);
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Booking> responseEntity = createBookingDelegate.execute(createBooking);
        Booking bookingResponse = responseEntity.getBody();

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBookingId());
        assertEquals(expectedBooking.getBookingId(), bookingResponse.getBookingId());

    }

    /**
     * Tests the scenario where a booking already exists.
     *
     * @throws IOException if there is an error reading the JSON files
     * @throws EntityAlreadyExistsException if the booking already exists
     */
    @Test
    public void testCreateBooking_BookingAlreadyExists() throws IOException, EntityAlreadyExistsException {
        // Read input data from JSON files
        Booking booking = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\requests\\createBookingRequest.json", Booking.class);
        BookingDTO existingBookingDTO = readJsonFile("C:\\RTB\\booking\\booking-test\\src\\test\\resources\\DTO\\BookingDTO.json", BookingDTO.class);

        // Mock the BookingRepository.findById method to return an existing BookingDTO
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(existingBookingDTO));
        // Act
        try{
            ResponseEntity<Booking> responseEntity = createBookingDelegate.execute(booking);
        }catch (EntityAlreadyExistsException ex){
            // Assert
            assertEquals("Booking with ID: 1 already exists", ex.getMessage());
        }
    }

}