package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.model.RoomDTO;
import com.hrs.hotel.booking.model.Room;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Mapper class responsible for mapping BookingDTO to Booking.
 */
public class BookingDTOToBookingMapper {

    /**
     * Maps a BookingDTO to a Booking.
     *
     * @param bookingDTO the booking DTO
     * @return the mapped booking response
     */
    public Booking map(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setUserId(bookingDTO.getUserId());
        booking.setUserName(bookingDTO.getUserName());
        booking.setHotelName(bookingDTO.getHotelName());
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setPayment(bookingDTO.getPayment());
        booking.setRoom(mapRoomList(bookingDTO.getRoom()));
        return booking;
    }

    /**
     * Maps a list of RoomDTO objects to a list of Room objects.
     *
     * @param roomDTOs the list of room DTOs
     * @return the list of mapped rooms
     */
    private List<Room> mapRoomList(List<RoomDTO> roomDTOs) {
        return roomDTOs.stream()
                .map(this::mapRoom)
                .collect(Collectors.toList());
    }

    /**
     * Maps a RoomDTO object to a Room object.
     *
     * @param roomDTO the room DTO
     * @return the mapped room
     */
    private Room mapRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomType(roomDTO.getRoomType());
        room.setPrice(roomDTO.getPrice());
        return room;
    }
}