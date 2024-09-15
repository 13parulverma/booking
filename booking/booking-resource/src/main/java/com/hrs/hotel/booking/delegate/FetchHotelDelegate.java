package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.mapper.HotelDTOToHotelMapper;
import com.hrs.hotel.booking.model.Hotel;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.service.FetchHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Delegate class responsible for handling the fetching of hotels.
 */
@Component
public class FetchHotelDelegate {
    private final FetchHotelService fetchHotelService;
    private final HotelDTOToHotelMapper hotelDTOToHotelMapper;
    private final Logger logger = LoggerFactory.getLogger(FetchHotelDelegate.class);

    /**
     * Constructor for FetchHotelDelegate with constructor injection.
     *
     * @param fetchHotelService the service to fetch hotels
     * @param hotelDTOToHotelMapper the mapper to convert hotel DTOs to hotel responses
     */
    public FetchHotelDelegate(FetchHotelService fetchHotelService,
                              HotelDTOToHotelMapper hotelDTOToHotelMapper) {
        this.fetchHotelService = fetchHotelService;
        this.hotelDTOToHotelMapper = hotelDTOToHotelMapper;
    }

    /**
     * Executes the fetching of a hotel with the given ID.
     *
     * @param id the ID of the hotel to be fetched
     * @return a ResponseEntity containing the fetched hotel response
     * @throws EntityNotFoundException if the hotel with the given ID is not found
     */
    public ResponseEntity<Hotel> execute(Long id) {
        logger.debug("Fetching hotel with ID: {}", id);
        Optional<HotelDTO> hotelDTO = fetchHotelService.fetchHotelById(id);
        if(hotelDTO.isEmpty()){
            logger.error("Hotel with ID: {} not found", id);
            throw new EntityNotFoundException("Hotel with ID: "+id+" not found");
        }
        Hotel fetchHotel = hotelDTOToHotelMapper.map(hotelDTO.get());
        logger.debug("Successfully fetched hotel with ID: {}", id);

        return new ResponseEntity<>(fetchHotel, HttpStatus.OK);
    }

    /**
     * Executes the fetching of all hotels.
     *
     * @return a ResponseEntity containing the list of fetched hotel responses
     */
    public ResponseEntity<List<Hotel>> execute() {
        logger.debug("Fetching all hotels");
        List<HotelDTO> hotelDTOs = fetchHotelService.fetchAllHotels();

        List<Hotel> fetchHotels = mapHotelDTOListToHotelList(hotelDTOs);
        logger.debug("Successfully fetched all hotels");

        return new ResponseEntity<>(fetchHotels, HttpStatus.OK);
    }

    /**
     * Maps a list of HotelDTOs to a list of Hotels.
     *
     * @param hotelDTOs the list of HotelDTOs to be mapped
     * @return the list of mapped Hotels
     */
    private List<Hotel> mapHotelDTOListToHotelList(List<HotelDTO> hotelDTOs) {
        logger.debug("Mapping HotelDTO list to Hotel list");

        return hotelDTOs.stream()
                .map(hotelDTOToHotelMapper::map)
                .collect(Collectors.toList());
    }
}