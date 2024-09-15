package com.hrs.hotel.booking.config;

import com.hrs.hotel.booking.persistence.repository.BookingRepository;
import com.hrs.hotel.booking.persistence.repository.HotelRepository;
import com.hrs.hotel.booking.persistence.repository.UserRepository;
import com.hrs.hotel.booking.service.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuration class for defining service beans.
 */
@AutoConfiguration
public class ServiceConfig {
    @Bean
    CreateBookingService createBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        return new CreateBookingService(fetchBookingService, bookingRepository);
    }

    @Bean
    UpdateBookingService updateBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        return new UpdateBookingService(fetchBookingService, bookingRepository);
    }

    @Bean
    FetchBookingService fetchBookingService(BookingRepository bookingRepository) {
        return new FetchBookingService(bookingRepository);
    }

    @Bean
    DeleteBookingService deleteBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        return new DeleteBookingService(fetchBookingService, bookingRepository);
    }


    @Bean
    CreateHotelService createHotelService(FetchHotelService fetchHotelService, HotelRepository hotelRepository) {
        return new CreateHotelService(fetchHotelService, hotelRepository);
    }
    @Bean
    DeleteHotelService deleteHotelService(FetchHotelService fetchHotelService, HotelRepository hotelRepository) {
        return new DeleteHotelService(fetchHotelService, hotelRepository);
    }

    @Bean
    FetchHotelService fetchHotelService(HotelRepository hotelRepository) {
        return new FetchHotelService(hotelRepository);
    }

    @Bean
    UpdateHotelService updateHotelService(FetchHotelService fetchHotelService, HotelRepository hotelRepository) {
        return new UpdateHotelService(fetchHotelService, hotelRepository);
    }

    @Bean
    CreateUserService createUserService(FetchUserService fetchUserService, UserRepository userRepository) {
        return new CreateUserService(fetchUserService, userRepository);
    }

    @Bean
    DeleteUserService deleteUserService(FetchUserService fetchUserService, UserRepository userRepository) {
        return new DeleteUserService(fetchUserService, userRepository);
    }

    @Bean
    FetchUserService fetchUserService(UserRepository userRepository) {
        return new FetchUserService(userRepository);
    }

    @Bean
    UpdateUserService updateUserService(FetchUserService fetchUserService, UserRepository userRepository) {
        return new UpdateUserService(fetchUserService, userRepository);
    }
}