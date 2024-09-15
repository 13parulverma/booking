package com.hrs.hotel.booking.config;


import com.hrs.hotel.booking.delegate.*;
import com.hrs.hotel.booking.mapper.*;
import com.hrs.hotel.booking.service.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuration class for resource delegates.
 * This class defines beans for various delegates used in the application.
 */
@AutoConfiguration
public class ResourceConfig {
    @Bean
    CreateBookingDelegate createBookingDelegate(CreateBookingService createBookingService,
                                                BookingToBookingDTOMapper bookingToBookingDTOMapper,
                                                BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        return new CreateBookingDelegate(createBookingService, bookingToBookingDTOMapper, bookingDTOToBookingMapper);
    }

    @Bean
    DeleteBookingDelegate deleteBookingDelegate(DeleteBookingService deleteBookingService) {
        return new DeleteBookingDelegate(deleteBookingService);
    }

    @Bean
    FetchBookingDelegate fetchBookingDelegate(FetchBookingService fetchBookingService,
                                              BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        return new FetchBookingDelegate(fetchBookingService, bookingDTOToBookingMapper);
    }

    @Bean
    UpdateBookingDelegate updateBookingDelegate(UpdateBookingService updateBookingService,
                                                BookingToBookingDTOMapper bookingToBookingDTOMapper,
                                                BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        return new UpdateBookingDelegate(updateBookingService, bookingToBookingDTOMapper, bookingDTOToBookingMapper);
    }

    @Bean
    CreateHotelDelegate createHotelDelegate(CreateHotelService createHotelService,
                                            HotelToHotelDTOMapper hotelToHotelDTOMapper,
                                            HotelDTOToHotelMapper hotelDTOToHotelMapper) {
        return new CreateHotelDelegate(createHotelService, hotelToHotelDTOMapper, hotelDTOToHotelMapper);
    }

    @Bean
    DeleteHotelDelegate deleteHotelDelegate(DeleteHotelService deleteHotelService) {
        return new DeleteHotelDelegate(deleteHotelService);
    }

    @Bean
    FetchHotelDelegate fetchHotelDelegate(FetchHotelService fetchHotelService,
                                          HotelDTOToHotelMapper hotelDTOToHotelMapper) {
        return new FetchHotelDelegate(fetchHotelService, hotelDTOToHotelMapper);
    }

    @Bean
    UpdateHotelDelegate updateHotelDelegate(UpdateHotelService updateHotelService,
                                            HotelToHotelDTOMapper hotelToHotelDTOMapper,
                                            HotelDTOToHotelMapper hotelDTOToHotelMapper) {
        return new UpdateHotelDelegate(updateHotelService, hotelToHotelDTOMapper, hotelDTOToHotelMapper);
    }

    @Bean
    CreateUserDelegate createUserDelegate(CreateUserService createUserService,
                                          UserToUserDTOMapper userToUserDTOMapper,
                                          UserDTOToUserMapper userDTOToUserMapper) {
        return new CreateUserDelegate(createUserService, userToUserDTOMapper, userDTOToUserMapper);
    }

    @Bean
    DeleteUserDelegate deleteUserDelegate(DeleteUserService deleteUserService) {
        return new DeleteUserDelegate(deleteUserService);
    }

    @Bean
    FetchUserDelegate fetchUserDelegate(FetchUserService fetchUserService,
                                        UserDTOToUserMapper userDTOToUserMapper) {
        return new FetchUserDelegate(fetchUserService, userDTOToUserMapper);
    }

    @Bean
    UpdateUserDelegate updateUserDelegate(UpdateUserService updateUserService,
                                          UserToUserDTOMapper userToUserDTOMapper,
                                          UserDTOToUserMapper userDTOToUserMapper) {
        return new UpdateUserDelegate(updateUserService, userToUserDTOMapper, userDTOToUserMapper);
    }

}