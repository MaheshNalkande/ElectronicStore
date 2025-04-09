package com.project.ecom.controller;

import com.project.ecom.dtos.API_Response_MSG;
import com.project.ecom.dtos.UserDto;
import com.project.ecom.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId,
                                              @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<API_Response_MSG> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        API_Response_MSG message = API_Response_MSG.builder()
                .message("User deleted successfully.")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
        UserDto user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Search users by keyword
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUsers(@PathVariable String keyword) {
        List<UserDto> users = userService.searchUser(keyword);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
