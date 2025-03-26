package com.project.ecom.service;

import com.project.ecom.dtos.UserDto;
import com.project.ecom.entities.User;

import java.util.List;

public interface UserService {
    // create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);



    //delete
    void deleteUser(String userId);

    //get all users
    List<UserDto> getAllUsers();


    //get single user using id/email
    UserDto getUserById(String userId);
    UserDto getUserByEmail(String userEmail);


    // search user

    List<UserDto> searchUser(String keyword);



}