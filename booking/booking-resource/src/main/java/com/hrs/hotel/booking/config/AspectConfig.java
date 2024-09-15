package com.hrs.hotel.booking.config;

import com.hrs.hotel.booking.exceptionHandingAspect.ExceptionHandlerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for aspect-oriented programming.
 * This class defines beans for various aspects used in the application.
 */
@Configuration
public class AspectConfig {
    @Bean
    public ExceptionHandlerAspect exceptionHandlerAspect() {
        return new ExceptionHandlerAspect();
    }
}
