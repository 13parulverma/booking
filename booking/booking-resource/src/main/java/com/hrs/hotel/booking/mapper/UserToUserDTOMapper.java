package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.User;
import com.hrs.hotel.booking.persistence.model.UserDTO;
/**
 * Mapper class responsible for mapping User to UserDTO.
 */
public class UserToUserDTOMapper {

    /**
     * Maps a User to a UserDTO.
     *
     * @param user the user
     * @return the mapped user DTO
     */
    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        return userDTO;
    }
}