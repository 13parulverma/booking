package com.hrs.hotel.booking.persistence.repository;

import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.model.HotelDTO;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing HotelDTO entities.
 * This interface extends CouchbaseRepository to provide CRUD operations for HotelDTO.
 */
@Repository
public interface HotelRepository extends CouchbaseRepository<HotelDTO, Long> {
    List<HotelDTO> findAll();

}