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
 * Service class responsible for handling the updating of users.
 */
@Service
public class UpdateUserService {
    private final Logger logger = LoggerFactory.getLogger(UpdateUserService.class);
    private final FetchUserService fetchUserService;
    private final UserRepository userRepository;

    /**
     * Constructor for UpdateUserService with constructor injection.
     *
     * @param fetchUserService the service to fetch users
     * @param userRepository the repository to handle user persistence
     */
    public UpdateUserService(FetchUserService fetchUserService, UserRepository userRepository) {
        this.fetchUserService = fetchUserService;
        this.userRepository = userRepository;
    }

    /**
     * Updates a user with the given ID.
     *
     * @param id the ID of the user to be updated
     * @param userDTO the user DTO with updated details
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<UserDTO> fetchedUser;
        try {
            fetchedUser = fetchUserService.fetchUserById(id);
        } catch (Exception ex) {
            logger.error("Error while fetching user with ID: {} in couchbase", id);
            throw new CouchbaseException("Error while fetching user with ID: " + id + " in couchbase");
        }
        if (fetchedUser.isEmpty()) {
            logger.debug("Cannot update user, because user with ID: {} not found", id);
            throw new EntityNotFoundException("Cannot update user, because user with ID: " + id + " not found");
        } else {
            try {
                logger.debug("Updating user with ID: {}", id);
                userDTO.setUserId(fetchedUser.get().getUserId());
                UserDTO updatedUser = userRepository.save(userDTO);
                logger.debug("Updated user with ID: {}", id);
                return updatedUser;
            } catch (Exception ex) {
                logger.error("Error while updating user with ID: {} in couchbase", id);
                throw new CouchbaseException("Error while updating user with ID: " + id + " in couchbase");
            }
        }
    }
}