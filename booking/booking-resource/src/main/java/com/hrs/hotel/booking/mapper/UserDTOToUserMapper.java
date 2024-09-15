package com.hrs.hotel.booking.mapper;

import com.hrs.hotel.booking.model.User;
import com.hrs.hotel.booking.persistence.model.UserDTO;
/**
 * Mapper class responsible for mapping UserDTO to User.
 */
public class UserDTOToUserMapper {

    /**
     * Maps a UserDTO to a User.
     *
     * @param userDTO the user DTO
     * @return the mapped user
     */
    public User map(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        return user;
    }
}