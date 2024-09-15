package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.Booking;
import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.model.Room;
import com.hrs.hotel.booking.persistence.model.RoomDTO;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Mapper class responsible for mapping Booking to BookingDTO.
 */
public class BookingToBookingDTOMapper {

    /**
     * Maps a Booking to a BookingDTO.
     *
     * @param createBooking the booking request
     * @return the mapped booking DTO
     */
    public BookingDTO map(Booking createBooking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(createBooking.getBookingId());
        bookingDTO.setUserId(createBooking.getUserId());
        bookingDTO.setUserName(createBooking.getUserName());
        bookingDTO.setHotelName(createBooking.getHotelName());
        bookingDTO.setCheckInDate(createBooking.getCheckInDate());
        bookingDTO.setCheckOutDate(createBooking.getCheckOutDate());
        bookingDTO.setPayment(createBooking.getPayment());
        bookingDTO.setRoom(mapRooms(createBooking.getRoom()));
        return bookingDTO;
    }

    /**
     * Maps a list of Room objects to a list of RoomDTO objects.
     *
     * @param rooms the list of rooms
     * @return the list of mapped room DTOs
     */
    private List<RoomDTO> mapRooms(List<Room> rooms) {
        return rooms.stream()
                .map(this::mapRoom)
                .collect(Collectors.toList());
    }

    /**
     * Maps a Room object to a RoomDTO object.
     *
     * @param room the room
     * @return the mapped room DTO
     */
    private RoomDTO mapRoom(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setAvailability(room.getAvailability());
        return roomDTO;
    }
}