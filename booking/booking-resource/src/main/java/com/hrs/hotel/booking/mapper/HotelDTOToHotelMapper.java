package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.Hotel;
import com.hrs.hotel.booking.model.Room;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import com.hrs.hotel.booking.persistence.model.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Mapper class responsible for mapping HotelDTO to Hotel.
 */
public class HotelDTOToHotelMapper {

    @Autowired
    private RoomDTOToRoomMapper roomDTOToRoomMapper;

    /**
     * Maps a HotelDTO to a Hotel.
     *
     * @param hotelDTO the hotel DTO
     * @return the mapped hotel
     */
    public Hotel map(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelDTO.getHotelId());
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelAddress(hotelDTO.getHotelAddress());
        hotel.setHotelCity(hotelDTO.getHotelCity());
        hotel.setHotelCountry(hotelDTO.getHotelCountry());
        hotel.setHotelZipCode(hotelDTO.getHotelZipCode());
        hotel.setHotelPhoneNumber(hotelDTO.getHotelPhoneNumber());
        hotel.setHotelEmail(hotelDTO.getHotelEmail());
        hotel.setRooms(roomListDTOToRoomListMapper(hotelDTO.getRooms()));

        return hotel;
    }

    /**
     * Maps a list of RoomDTO objects to a list of Room objects.
     *
     * @param roomDTOs the list of room DTOs
     * @return the list of mapped rooms
     */
    private List<Room> roomListDTOToRoomListMapper(List<RoomDTO> roomDTOs) {
        if (roomDTOs.isEmpty()) {
            return new ArrayList<>();
        }

        return roomDTOs.stream()
                .map(roomDTOToRoomMapper::map)
                .toList();
    }
}