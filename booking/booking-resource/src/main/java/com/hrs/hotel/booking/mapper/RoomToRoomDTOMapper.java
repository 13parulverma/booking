package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.Room;
import com.hrs.hotel.booking.persistence.model.RoomDTO;
/**
 * Mapper class responsible for mapping Room to RoomDTO.
 */
public class RoomToRoomDTOMapper {

    /**
     * Maps a Room to a RoomDTO.
     *
     * @param room the room
     * @return the mapped room DTO
     */
    public RoomDTO map(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setAvailability(room.getAvailability());
        return roomDTO;
    }
}