package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.exception.EntityNotFoundException;
import com.hrs.hotel.booking.mapper.UserDTOToUserMapper;
import com.hrs.hotel.booking.model.User;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.service.FetchUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Delegate class responsible for handling the fetching of users.
 */
@Component
public class FetchUserDelegate {
    private final FetchUserService fetchUserService;
    private final UserDTOToUserMapper userDTOToUserMapper;
    private final Logger logger = LoggerFactory.getLogger(FetchUserDelegate.class);

    /**
     * Constructor for FetchUserDelegate with constructor injection.
     *
     * @param fetchUserService the service to fetch users
     * @param userDTOToUserMapper the mapper to convert user DTOs to user responses
     */
    public FetchUserDelegate(FetchUserService fetchUserService,
                             UserDTOToUserMapper userDTOToUserMapper) {
        this.fetchUserService = fetchUserService;
        this.userDTOToUserMapper = userDTOToUserMapper;
    }

    /**
     * Executes the fetching of a user with the given ID.
     *
     * @param id the ID of the user to be fetched
     * @return a ResponseEntity containing the fetched user response
     * @throws EntityNotFoundException if the user with the given ID is not found
     */
    public ResponseEntity<User> execute(Long id) {
        logger.debug("Fetching user with ID: {}", id);
        Optional<UserDTO> userDTO = fetchUserService.fetchUserById(id);
        if(userDTO.isEmpty()){
            logger.error("User with ID: {} not found", id);
            throw new EntityNotFoundException("User with ID: "+id+" not found");
        }
        User fetchUser = userDTOToUserMapper.map(userDTO.get());
        logger.debug("Successfully fetched user with ID: {}", id);
        return new ResponseEntity<>(fetchUser, HttpStatus.OK);
    }

    /**
     * Executes the fetching of all users.
     *
     * @return a ResponseEntity containing the list of fetched user responses
     */
    public ResponseEntity<List<User>> execute() {
        logger.debug("Fetching all users");
        List<UserDTO> userDTOs = fetchUserService.fetchAllUsers();

        List<User> fetchUsers = mapUserDTOListToUserList(userDTOs);
        logger.debug("Successfully fetched all users");
        return new ResponseEntity<>(fetchUsers, HttpStatus.OK);
    }

    /**
     * Maps a list of UserDTOs to a list of Users.
     *
     * @param userDTOs the list of UserDTOs to be mapped
     * @return the list of mapped Users
     */
    private List<User> mapUserDTOListToUserList(List<UserDTO> userDTOs) {
        logger.debug("Mapping UserDTO list to User list");
        return userDTOs.stream()
                .map(userDTOToUserMapper::map)
                .collect(Collectors.toList());
    }
}