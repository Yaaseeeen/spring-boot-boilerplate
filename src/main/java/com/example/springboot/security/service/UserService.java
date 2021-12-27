package com.example.springboot.security.service;

import com.example.springboot.model.User;
import com.example.springboot.model.dto.UserDto;
import com.example.springboot.security.dto.AuthenticatedUserDto;
import com.example.springboot.security.dto.RegistrationResponse;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    RegistrationResponse registration(UserDto dto);

    AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

    UserDto update(Long id, UserDto userDto);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();

    User findById(Long id);
}
