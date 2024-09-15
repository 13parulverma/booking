package com.hrs.hotel.booking.service;

import com.hrs.hotel.booking.exception.CouchbaseException;
import com.hrs.hotel.booking.exception.EntityAlreadyExistsException;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service class responsible for handling the creation of users.
 */
@Service
public class CreateUserService {
    /**
     * Logger for logging CreateUserService events.
     */
    private final Logger logger = LoggerFactory.getLogger(CreateUserService.class);

    /**
     * Service for fetching users.
     */
    private final FetchUserService fetchUserService;

    /**
     * Repository for handling user persistence.
     */
    private final UserRepository userRepository;

    /**
     * Constructor for CreateUserService with constructor injection.
     *
     * @param fetchUserService the service to fetch users
     * @param userRepository the repository to handle user persistence
     */
    @Autowired
    public CreateUserService(FetchUserService fetchUserService, UserRepository userRepository) {
        this.fetchUserService = fetchUserService;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param userDTO the user DTO
     * @return the persisted user DTO
     * @throws EntityAlreadyExistsException if the user already exists
     * @throws CouchbaseException if there is an error while persisting the user
     */
    public UserDTO createUser(UserDTO userDTO) {
        try {
            // Check if user already exists
            if (fetchUserService.fetchUserById(userDTO.getUserId()).isPresent()) {
                logger.debug("User with ID: {} already exists", userDTO.getUserId());
                throw new EntityAlreadyExistsException("User with ID: " + userDTO.getUserId() + " already exists");
            }

            logger.debug("Persisting user in couchbase");
            UserDTO userDTOResponse = userRepository.save(userDTO);
            logger.debug("Persisting user with ID: {}", userDTO.getUserId());
            return userDTOResponse;
        } catch (Exception ex) {
            if(ex instanceof EntityAlreadyExistsException) {
                throw ex;
            }
            else {
                logger.error("Error while persisting user in couchbase", ex);
                throw new CouchbaseException("Error while persisting user in couchbase");
            }
        }
    }
}