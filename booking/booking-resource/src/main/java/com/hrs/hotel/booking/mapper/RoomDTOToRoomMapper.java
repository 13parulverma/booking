package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.Room;
import com.hrs.hotel.booking.persistence.model.RoomDTO;
/**
 * Mapper class responsible for mapping RoomDTO to Room.
 */
public class RoomDTOToRoomMapper {

    /**
     * Maps a RoomDTO to a Room.
     *
     * @param roomDTO the room DTO
     * @return the mapped room
     */
    public Room map(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setPrice(roomDTO.getPrice());
        room.setAvailability(roomDTO.getAvailability());
        return room;
    }
}