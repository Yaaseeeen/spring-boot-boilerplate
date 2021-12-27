package com.example.springboot.service;

import com.example.springboot.model.dto.UserDto;

import java.util.List;


public interface UserService12 {
    //todo
//    UserDto create(@Valid UserDto userDto);
    UserDto update(Long id, UserDto userDto);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();
}
