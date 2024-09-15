package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.mapper.HotelDTOToHotelMapper;
import com.hrs.hotel.booking.mapper.HotelToHotelDTOMapper;
import com.hrs.hotel.booking.model.Hotel;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.service.CreateHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Delegate class responsible for handling the creation of hotels.
 */
@Component
public class CreateHotelDelegate {

    private final CreateHotelService createHotelService;
    private final HotelToHotelDTOMapper hotelToHotelDTOMapper;
    private final HotelDTOToHotelMapper hotelDTOToHotelMapper;

    private final Logger logger = LoggerFactory.getLogger(CreateHotelDelegate.class);

    /**
     * Constructor for CreateHotelDelegate with constructor injection.
     *
     * @param createHotelService the service to create hotels
     * @param hotelToHotelDTOMapper the mapper to convert hotel requests to hotel DTOs
     * @param hotelDTOToHotelMapper the mapper to convert hotel DTOs to hotel responses
     */
    public CreateHotelDelegate(CreateHotelService createHotelService,
                               HotelToHotelDTOMapper hotelToHotelDTOMapper,
                               HotelDTOToHotelMapper hotelDTOToHotelMapper) {
        this.createHotelService = createHotelService;
        this.hotelToHotelDTOMapper = hotelToHotelDTOMapper;
        this.hotelDTOToHotelMapper = hotelDTOToHotelMapper;
    }

    /**
     * Executes the creation of a new hotel.
     *
     * @param createHotel the hotel request
     * @return a ResponseEntity containing the created hotel response
     */
    public ResponseEntity<Hotel> execute(Hotel createHotel) {
        validateRequest(createHotel);

        logger.debug("Mapping CreateHotel request to HotelDTO");
        HotelDTO hotelDTO = hotelToHotelDTOMapper.map(createHotel);

        logger.debug("Calling CreateHotelService");
        HotelDTO createHotelDTO = createHotelService.createHotel(hotelDTO);

        logger.debug("Mapping HotelDTO to CreateHotel response");
        Hotel hotelResponse = hotelDTOToHotelMapper.map(createHotelDTO);
        return new ResponseEntity<>(hotelResponse, HttpStatus.CREATED);
    }

    /**
     * Validates the hotel request.
     *
     * @param createHotel the hotel request to validate
     * @throws IllegalArgumentException if the hotel request is invalid
     */
    private void validateRequest(Hotel createHotel) {
        if (createHotel == null) {
            throw new IllegalArgumentException("Hotel request cannot be null");
        }
        if (createHotel.getHotelName() == null || createHotel.getHotelName().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be null or empty");
        }
    }
}