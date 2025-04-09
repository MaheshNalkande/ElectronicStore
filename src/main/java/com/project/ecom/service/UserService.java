package com.project.ecom.service;

import com.project.ecom.dtos.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    // Create a new user
    UserDto createUser(UserDto userDto);

    // Update existing user
    UserDto updateUser(UserDto userDto, String userId);

    // Delete user by ID
    void deleteUser(String userId);

    // Get all users (non-paginated)
    List<UserDto> getAllUsers();

    // Get all users with pagination and sorting
    Page<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    // Get user by ID
    UserDto getUserById(String userId);

    // Get user by email
    UserDto getUserByEmail(String email);

    // Search users by keyword in name
    List<UserDto> searchUser(String keyword);
}
