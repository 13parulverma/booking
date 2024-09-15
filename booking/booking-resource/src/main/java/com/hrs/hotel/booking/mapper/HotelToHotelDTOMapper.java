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
 * Mapper class responsible for mapping Hotel to HotelDTO.
 */
public class HotelToHotelDTOMapper {

    @Autowired
    private RoomToRoomDTOMapper roomToRoomDTOMapper;

    /**
     * Maps a Hotel to a HotelDTO.
     *
     * @param hotel the hotel
     * @return the mapped hotel DTO
     */
    public HotelDTO map(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelId(hotel.getHotelId());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setHotelAddress(hotel.getHotelAddress());
        hotelDTO.setHotelCity(hotel.getHotelCity());
        hotelDTO.setHotelCountry(hotel.getHotelCountry());
        hotelDTO.setHotelZipCode(hotel.getHotelZipCode());
        hotelDTO.setHotelPhoneNumber(hotel.getHotelPhoneNumber());
        hotelDTO.setHotelEmail(hotel.getHotelEmail());
        hotelDTO.setRooms(roomListToRoomDTOListMapper(hotel.getRooms()));

        return hotelDTO;
    }

    /**
     * Maps a list of Room objects to a list of RoomDTO objects.
     *
     * @param rooms the list of rooms
     * @return the list of mapped room DTOs
     */
    private List<RoomDTO> roomListToRoomDTOListMapper(List<Room> rooms) {
        if (rooms.isEmpty()) {
            return new ArrayList<>();
        }

        return rooms.stream()
                .map(roomToRoomDTOMapper::map)
                .toList();
    }
}