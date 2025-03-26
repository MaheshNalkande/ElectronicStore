package com.project.ecom.service.impl;

import com.project.ecom.dtos.UserDto;
import com.project.ecom.entities.User;
import com.project.ecom.repository.UserRepository;
import com.project.ecom.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    // Create User
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        User user = mapper.map(userDto, User.class); // Convert DTO → Entity
        User savedUser = userRepository.save(user); // Save to DB
        return mapper.map(savedUser, UserDto.class); // Convert back to DTO
    }

    // Update User
    public UserDto updateUser(UserDto userDto, String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDto.getName()); // Assuming UserDto has getName()
            user.setEmail(userDto.getEmail()); // Update fields
            User updatedUser = userRepository.save(user);
            return mapper.map(updatedUser, UserDto.class);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    // Delete User
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    // Get All Users
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    // Get User by ID
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return mapper.map(user, UserDto.class);
    }

    // Get User by Email
    public UserDto getUserByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        return mapper.map(user, UserDto.class);
    }

    // Search Users by Keyword
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        return users.stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
