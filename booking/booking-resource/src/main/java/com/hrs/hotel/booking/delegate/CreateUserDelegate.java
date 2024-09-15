package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.mapper.UserDTOToUserMapper;
import com.hrs.hotel.booking.mapper.UserToUserDTOMapper;
import com.hrs.hotel.booking.model.User;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.service.CreateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Delegate class responsible for handling the creation of users.
 */
@Component
public class CreateUserDelegate {

    private final CreateUserService createUserService;
    private final UserToUserDTOMapper userToUserDTOMapper;
    private final UserDTOToUserMapper userDTOToUserMapper;

    private final Logger logger = LoggerFactory.getLogger(CreateUserDelegate.class);

    /**
     * Constructor for CreateUserDelegate with constructor injection.
     *
     * @param createUserService the service to create users
     * @param userToUserDTOMapper the mapper to convert user requests to user DTOs
     * @param userDTOToUserMapper the mapper to convert user DTOs to user responses
     */
    public CreateUserDelegate(CreateUserService createUserService,
                              UserToUserDTOMapper userToUserDTOMapper,
                              UserDTOToUserMapper userDTOToUserMapper) {
        this.createUserService = createUserService;
        this.userToUserDTOMapper = userToUserDTOMapper;
        this.userDTOToUserMapper = userDTOToUserMapper;
    }

    /**
     * Executes the creation of a new user.
     *
     * @param createUser the user request
     * @return a ResponseEntity containing the created user response
     */
    public ResponseEntity<User> execute(User createUser) {
        validateRequest(createUser);

        logger.debug("Mapping CreateUser request to UserDTO");
        UserDTO userDTO = userToUserDTOMapper.map(createUser);

        logger.debug("Calling CreateUserService");
        UserDTO createUserDTO = createUserService.createUser(userDTO);

        logger.debug("Mapping UserDTO to CreateUser response");
        User userResponse = userDTOToUserMapper.map(createUserDTO);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    /**
     * Validates the user request.
     *
     * @param createUser the user request to validate
     * @throws IllegalArgumentException if the user request is invalid
     */
    private void validateRequest(User createUser) {
        if (createUser == null) {
            throw new IllegalArgumentException("User request cannot be null");
        }
        if (createUser.getUserName() == null || createUser.getUserName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
    }
}