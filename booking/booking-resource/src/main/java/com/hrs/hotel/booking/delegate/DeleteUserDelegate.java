package com.hrs.hotel.booking.delegate;

import com.hrs.hotel.booking.model.DeleteResponse;
import com.hrs.hotel.booking.persistence.model.UserDTO;
import com.hrs.hotel.booking.service.DeleteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
/**
 * Delegate class responsible for handling the deletion of users.
 */
@Component
public class DeleteUserDelegate {

    private final Logger logger = LoggerFactory.getLogger(DeleteUserDelegate.class);
    private final DeleteUserService deleteUserService;

    /**
     * Constructor for DeleteUserDelegate with constructor injection.
     *
     * @param deleteUserService the service to delete users
     */
    public DeleteUserDelegate(DeleteUserService deleteUserService) {
        this.deleteUserService = deleteUserService;
    }

    /**
     * Executes the deletion of a user with the given ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    public ResponseEntity<DeleteResponse> execute(Long id) {
        logger.debug("Deleting user with ID: {}", id);
        deleteUserService.deleteUser(id);
        String message = "User with ID " + id + " has been deleted successfully";
        DeleteResponse deleteResponse = new DeleteResponse(message);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}