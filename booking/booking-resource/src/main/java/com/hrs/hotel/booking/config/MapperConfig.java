package com.hrs.hotel.booking.config;

import com.hrs.hotel.booking.mapper.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuration class for mapping beans.
 * This class defines beans for various mappers used in the application.
 */
@AutoConfiguration
public class MapperConfig {
    @Bean
    public BookingDTOToBookingMapper bookingMapper() {
        return new BookingDTOToBookingMapper();
    }

    @Bean
    public BookingToBookingDTOMapper bookingDTOMapper() {
        return new BookingToBookingDTOMapper();
    }

    @Bean
    public HotelDTOToHotelMapper hotelMapper() {
        return new HotelDTOToHotelMapper();
    }

    @Bean
    public HotelToHotelDTOMapper hotelDTOMapper() {
        return new HotelToHotelDTOMapper();
    }

    @Bean
    public UserDTOToUserMapper userMapper() {
        return new UserDTOToUserMapper();
    }

    @Bean
    public UserToUserDTOMapper userDTOMapper() {
        return new UserToUserDTOMapper();
    }

    @Bean
    public RoomToRoomDTOMapper roomToRoomDTOMapper() {
        return new RoomToRoomDTOMapper();
    }

    @Bean
    public RoomDTOToRoomMapper roomDTOToRoomMapper() {
        return new RoomDTOToRoomMapper();
    }
}