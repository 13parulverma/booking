package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Service class responsible for handling the fetching of users.
 */
@Service
public class FetchUserService {
    /**
     * Logger for logging FetchUserService events.
     */
    private final Logger logger = LoggerFactory.getLogger(FetchUserService.class);

    /**
     * Repository for handling user persistence.
     */
    private final UserRepository userRepository;

    /**
     * Constructor for FetchUserService with constructor injection.
     *
     * @param userRepository the repository to handle user persistence
     */
    @Autowired
    public FetchUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetches a user by the given ID.
     *
     * @param id the ID of the user to be fetched
     * @return an Optional containing the fetched user DTO if found, otherwise empty
     * @throws EntityNotFoundException if there is an error while fetching the user
     */
    public Optional<UserDTO> fetchUserById(Long id) {
        Optional<UserDTO> userDTOOptional;
        try {
            logger.debug("Fetching user with ID: {}", id);
            userDTOOptional = userRepository.findById(id);
        } catch (Exception ex) {
            logger.error("Error while fetching user with ID: {}", id, ex);
            throw new EntityNotFoundException("Error while fetching user with ID: " + id, ex);
        }
        return userDTOOptional;
    }

    /**
     * Fetches all users.
     *
     * @return a list of all user DTOs
     * @throws EntityNotFoundException if there is an error while fetching the users
     */
    public List<UserDTO> fetchAllUsers() {
        try {
            logger.debug("Fetching all users");
            return userRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error while fetching all users", ex);
            throw new EntityNotFoundException("Error while fetching users", ex);
        }
    }
}