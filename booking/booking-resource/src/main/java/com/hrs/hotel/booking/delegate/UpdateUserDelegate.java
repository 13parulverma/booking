package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.mapper.UserDTOToUserMapper;
import com.hrs.hotel.booking.mapper.UserToUserDTOMapper;
import com.hrs.hotel.booking.model.User;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.service.UpdateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Delegate class responsible for handling the updating of users.
 */
@Component
public class UpdateUserDelegate {
    private final UpdateUserService updateUserService;
    private final UserToUserDTOMapper userToUserDTOMapper;
    private final UserDTOToUserMapper userDTOToUserMapper;
    private final Logger logger = LoggerFactory.getLogger(UpdateUserDelegate.class);

    /**
     * Constructor for UpdateUserDelegate with constructor injection.
     *
     * @param updateUserService the service to update users
     * @param userToUserDTOMapper the mapper to convert user requests to user DTOs
     * @param userDTOToUserMapper the mapper to convert user DTOs to user responses
     */
    public UpdateUserDelegate(UpdateUserService updateUserService,
                              UserToUserDTOMapper userToUserDTOMapper,
                              UserDTOToUserMapper userDTOToUserMapper) {
        this.updateUserService = updateUserService;
        this.userToUserDTOMapper = userToUserDTOMapper;
        this.userDTOToUserMapper = userDTOToUserMapper;
    }

    /**
     * Executes the updating of a user with the given ID.
     *
     * @param id the ID of the user to be updated
     * @param updateUser the user request with updated details
     * @return a ResponseEntity containing the updated user response
     */
    public ResponseEntity<User> execute(Long id, User updateUser) {
        logger.debug("Starting update for user with ID: {}", id);

        logger.debug("Mapping updateUser request to UserDTO");
        UserDTO userDTO = userToUserDTOMapper.map(updateUser);

        logger.debug("Calling UpdateUserService to update user");
        UserDTO updatedUserDTO = updateUserService.updateUser(id, userDTO);

        logger.debug("Mapping updated UserDTO to updateUser response");
        User updateUserResponse = userDTOToUserMapper.map(updatedUserDTO);

        logger.debug("Successfully updated user with ID: {}", id);
        return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    }
}