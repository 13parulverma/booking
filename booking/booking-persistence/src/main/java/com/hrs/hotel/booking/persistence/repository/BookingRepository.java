package com.hrs.hotel.booking.persistence.repository;

import com.hrs.hotel.booking.persistence.model.BookingDTO;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository class responsible for handling CRUD operations for BookingDTO.
 */

@Repository
public interface BookingRepository extends CouchbaseRepository<BookingDTO, Long> {
    List<BookingDTO> findAll();
}