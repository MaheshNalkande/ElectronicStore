package com.project.ecom.service.impl;

import com.project.ecom.dtos.UserDto;
import com.project.ecom.entities.User;
import com.project.ecom.exceptions.ResourceNotFoundException;
import com.project.ecom.repository.UserRepository;
import com.project.ecom.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    // Create User
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        return entityToDto(savedUser);
    }

    // Update User
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updatedUser = userRepository.save(user);
        return entityToDto(updatedUser);
    }

    // Delete User
    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));

        userRepository.delete(user);
    }

    // Get All Users (simple list)
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Get All Users with pagination and sorting
    @Override
    public Page<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserDto> userDtos = userPage
                .getContent()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtos, pageable, userPage.getTotalElements());
    }

    // Get User by ID
    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));
        return entityToDto(user);
    }

    // Get User by Email
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given email !!"));
        return entityToDto(user);
    }

    // Search Users by Keyword
    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        return users.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Mapping Methods
    private User dtoToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    private UserDto entityToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
