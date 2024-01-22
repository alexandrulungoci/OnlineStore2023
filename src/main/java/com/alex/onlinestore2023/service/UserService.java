package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.UserDto;

import java.util.List;

public interface UserService {

    void addUser(UserDto userDto);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    void updateUser(UserDto userDto);

    void deleteUser(Long id);
}
