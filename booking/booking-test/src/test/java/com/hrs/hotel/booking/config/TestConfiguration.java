package com.hrs.hotel.booking.config;

import com.hrs.hotel.booking.delegate.CreateBookingDelegate;
import com.hrs.hotel.booking.delegate.DeleteBookingDelegate;
import com.hrs.hotel.booking.delegate.FetchBookingDelegate;
import com.hrs.hotel.booking.delegate.UpdateBookingDelegate;
import com.hrs.hotel.booking.mapper.BookingDTOToBookingMapper;
import com.hrs.hotel.booking.mapper.BookingToBookingDTOMapper;
import com.hrs.hotel.booking.persistence.repository.BookingRepository;
import com.hrs.hotel.booking.service.CreateBookingService;
import com.hrs.hotel.booking.service.DeleteBookingService;
import com.hrs.hotel.booking.service.FetchBookingService;
import com.hrs.hotel.booking.service.UpdateBookingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    CreateBookingDelegate createBookingDelegate(CreateBookingService createBookingService,
                                                BookingToBookingDTOMapper bookingToBookingDTOMapper,
                                                BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        return new CreateBookingDelegate(createBookingService, bookingToBookingDTOMapper, bookingDTOToBookingMapper);
    }
    @Bean
    public BookingToBookingDTOMapper bookingDTOMapper() {
        return new BookingToBookingDTOMapper();
    }
    @Bean
    public BookingDTOToBookingMapper bookingMapper() {
        return new BookingDTOToBookingMapper();
    }

    @Bean
    CreateBookingService createBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        return new CreateBookingService(fetchBookingService, bookingRepository);
    }

    @Bean
    FetchBookingDelegate fetchBookingDelegate(FetchBookingService fetchBookingService,
                                              BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        return new FetchBookingDelegate(fetchBookingService, bookingDTOToBookingMapper);
    }
    @Bean
    FetchBookingService fetchBookingService(BookingRepository bookingRepository) {
        return new FetchBookingService(bookingRepository);
    }

    @Bean
    UpdateBookingDelegate updateBookingDelegate(UpdateBookingService updateBookingService,
                                                BookingToBookingDTOMapper bookingToBookingDTOMapper,
                                                BookingDTOToBookingMapper bookingDTOToBookingMapper) {
        return new UpdateBookingDelegate(updateBookingService, bookingToBookingDTOMapper, bookingDTOToBookingMapper);
    }
    @Bean
    UpdateBookingService updateBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        return new UpdateBookingService(fetchBookingService, bookingRepository);
    }

    @Bean
    DeleteBookingDelegate deleteBookingDelegate(DeleteBookingService deleteBookingService) {
        return new DeleteBookingDelegate(deleteBookingService);
    }
    @Bean
    DeleteBookingService deleteBookingService(FetchBookingService fetchBookingService, BookingRepository bookingRepository) {
        return new DeleteBookingService(fetchBookingService, bookingRepository);
    }

}
