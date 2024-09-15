package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.mapper.HotelDTOToHotelMapper;
import com.hrs.hotel.booking.mapper.HotelToHotelDTOMapper;
import com.hrs.hotel.booking.model.Hotel;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.service.UpdateHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Delegate class responsible for handling the updating of hotels.
 */
@Component
public class UpdateHotelDelegate {
    private final UpdateHotelService updateHotelService;
    private final HotelToHotelDTOMapper hotelToHotelDTOMapper;
    private final HotelDTOToHotelMapper hotelDTOToHotelMapper;
    private final Logger logger = LoggerFactory.getLogger(UpdateHotelDelegate.class);

    /**
     * Constructor for UpdateHotelDelegate with constructor injection.
     *
     * @param updateHotelService the service to update hotels
     * @param hotelToHotelDTOMapper the mapper to convert hotel requests to hotel DTOs
     * @param hotelDTOToHotelMapper the mapper to convert hotel DTOs to hotel responses
     */
    public UpdateHotelDelegate(UpdateHotelService updateHotelService,
                               HotelToHotelDTOMapper hotelToHotelDTOMapper,
                               HotelDTOToHotelMapper hotelDTOToHotelMapper) {
        this.updateHotelService = updateHotelService;
        this.hotelToHotelDTOMapper = hotelToHotelDTOMapper;
        this.hotelDTOToHotelMapper = hotelDTOToHotelMapper;
    }

    /**
     * Executes the updating of a hotel with the given ID.
     *
     * @param id the ID of the hotel to be updated
     * @param updateHotel the hotel request with updated details
     * @return a ResponseEntity containing the updated hotel response
     */
    public ResponseEntity<Hotel> execute(Long id, Hotel updateHotel) {
        logger.debug("Starting update for hotel with ID: {}", id);

        logger.debug("Mapping updateHotel request to HotelDTO");
        HotelDTO hotelDTO = hotelToHotelDTOMapper.map(updateHotel);

        logger.debug("Calling UpdateHotelService to update hotel");
        HotelDTO updatedHotelDTO = updateHotelService.updateHotel(id, hotelDTO);

        logger.debug("Mapping updated HotelDTO to updateHotel response");
        Hotel updateHotelResponse = hotelDTOToHotelMapper.map(updatedHotelDTO);

        logger.debug("Successfully updated hotel with ID: {}", id);
        return new ResponseEntity<>(updateHotelResponse, HttpStatus.OK);
    }
}