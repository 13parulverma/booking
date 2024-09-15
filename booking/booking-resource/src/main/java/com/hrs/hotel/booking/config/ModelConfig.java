package com.hrs.hotel.booking.config;

import com.hrs.hotel.booking.model.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuration class for creating model beans.
 * This class defines beans for various models used in the application.
 */
@AutoConfiguration
public class ModelConfig {


    @Bean
    public Booking createBooking() {
        return new Booking();
    }


    @Bean
    public Hotel createHotel() {
        return new Hotel();
    }

    @Bean
    public Room createRoom() {
        return new Room();
    }

    @Bean
    public User createUser() {
        return new User();
    }
}
