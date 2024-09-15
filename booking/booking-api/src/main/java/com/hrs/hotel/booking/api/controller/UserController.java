package com.hrs.hotel.booking.api.controller;

import com.hrs.hotel.booking.delegate.CreateUserDelegate;
import com.hrs.hotel.booking.delegate.DeleteUserDelegate;
import com.hrs.hotel.booking.delegate.FetchUserDelegate;
import com.hrs.hotel.booking.delegate.UpdateUserDelegate;
import com.hrs.hotel.booking.model.DeleteResponse;
import com.hrs.hotel.booking.model.User;

import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Inject
    CreateUserDelegate createUserDelegate;
    @Inject
    UpdateUserDelegate updateUserDelegate;
    @Inject
    FetchUserDelegate fetchUserDelegate;
    @Inject
    DeleteUserDelegate deleteUserDelegate;

    /**
     * Creates a new user.
     *
     * @param createUser the user request
     * @return a ResponseEntity containing the created user response
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User createUser) {
        return createUserDelegate.execute(createUser);
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to be updated
     * @param updateUser the user request with updated details
     * @return a ResponseEntity containing the updated user response
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        return updateUserDelegate.execute(id, updateUser);
    }

    /**
     * Fetches a user by their ID.
     *
     * @param id the ID of the user to be fetched
     * @return a ResponseEntity containing the fetched user response
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> fetchUser(@PathVariable Long id) {
        return fetchUserDelegate.execute(id);
    }

    /**
     * Fetches all users.
     *
     * @return a ResponseEntity containing the list of fetched user responses
     */
    @GetMapping("")
    public ResponseEntity<List<User>> fetchUser() {
        return fetchUserDelegate.execute();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable Long id) {
        return deleteUserDelegate.execute(id);
    }
}