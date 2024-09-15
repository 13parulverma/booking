package com.hrs.hotel.booking.persistence.repository;

import com.hrs.hotel.booking.persistence.model.BookingDTO;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing UserDTO entities.
 * This interface extends CouchbaseRepository to provide CRUD operations for UserDTO.
 */
@Repository
public interface UserRepository extends CouchbaseRepository<UserDTO, Long> {
    List<UserDTO> findAll();

}
