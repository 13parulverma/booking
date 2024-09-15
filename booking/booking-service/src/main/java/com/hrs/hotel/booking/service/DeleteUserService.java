package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Service class responsible for handling the deletion of users.
 */
@Service
public class DeleteUserService {
    /**
     * Service for fetching users.
     */
    private final FetchUserService fetchUserService;

    /**
     * Repository for handling user persistence.
     */
    private final UserRepository userRepository;

    /**
     * Logger for logging DeleteUserService events.
     */
    private final Logger logger = LoggerFactory.getLogger(DeleteUserService.class);

    /**
     * Constructor for DeleteUserService with constructor injection.
     *
     * @param fetchUserService the service to fetch users
     * @param userRepository the repository to handle user persistence
     */
    public DeleteUserService(FetchUserService fetchUserService, UserRepository userRepository) {
        this.fetchUserService = fetchUserService;
        this.userRepository = userRepository;
    }

    /**
     * Deletes a user with the given ID.
     *
     * @param id the ID of the user to be deleted
     * @throws EntityNotFoundException if the user is not found
     * @throws CouchbaseException if there is an error while deleting the user
     */
    public void deleteUser(Long id) {
        Optional<UserDTO> fetchedUser;
        try {
            fetchedUser = fetchUserService.fetchUserById(id);
        } catch (Exception ex) {
            logger.error("Error while fetching user with ID: {} in couchbase", id);
            throw new CouchbaseException("Error while fetching user with ID: " + id + " in couchbase");
        }
        if (fetchedUser.isPresent()) {
            try {
                logger.debug("Deleting user with ID: {}", id);
                userRepository.deleteById(id);
                logger.debug("Deleted user with ID: {}", id);
            } catch (Exception ex) {
                logger.error("Error while deleting user with ID: {} in couchbase", id);
                throw new CouchbaseException("Error while deleting user with ID: " + id + " in couchbase");
            }
        } else {
            logger.debug("Cannot delete user, because user with ID: {} not found", id);
            throw new EntityNotFoundException("Cannot delete user, because user with ID: " + id + " not found");
        }
    }
}