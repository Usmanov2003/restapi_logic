package com.example.rest_apis.services;

import com.example.rest_apis.payloads.UserDto;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user) throws InterruptedException;

    UserDto createUser(UserDto user);

    UserDto updateUser(@Valid UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);


}
