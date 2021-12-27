//package com.example.springboot.service;
//
//
//import com.example.springboot.exceptions.UserNotFoundException;
//import com.example.springboot.model.User;
//import com.example.springboot.model.dto.UserDto;
//import com.example.springboot.repository.UserRepository;
//import com.example.springboot.security.service.UserService;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.BeanUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.annotation.Validated;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Validated
//public class UserServiceImpl12 implements UserService12 {
//
//    private final UserRepository userRepository;
//
//
////    @Override
////    public UserDto create(UserDto userDto) {
////        User user = new User();
////        BeanUtils.copyProperties(userDto, user);
////        userRepository.save(user);
////        return userDto;
////    }
//
////    @Override
////    public UserDto update(Long id, UserDto userDto) {
////        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден!", HttpStatus.NOT_FOUND));
////        BeanUtils.copyProperties(userDto, user);
////        userRepository.save(user);
////        return userDto;
////    }
////
////
////    @Override
////    public void delete(Long id) {
////        User user = userRepository.findById(id).orElseThrow(
////                () -> new UserNotFoundException("Пользователь не найден!", HttpStatus.NOT_FOUND));
////        userRepository.delete(user);
////    }
////
////    @Override
////    public UserDto getById(Long id) {
////        System.out.println();
////        System.out.println("id********** " +id);
////        System.out.println();
////        User user = userRepository.findById(id).orElseThrow(() ->
////                new UserNotFoundException("Пользователь не найден!", HttpStatus.NOT_FOUND));
////        UserDto userDto = new UserDto();
////        BeanUtils.copyProperties(user, userDto);
////        return userDto;
////    }
//
//    @Override
//    public List<UserDto> getAll() {
//        UserDto userDto = new UserDto();
//        List<UserDto> list = new ArrayList<>();
//        userRepository.findAll().forEach(user ->
//        {
//            System.out.println("find-------------" +list);
//            BeanUtils.copyProperties(user, userDto);
//            list.add(userDto);
//        });
//        System.out.println("list-------------" +list);
//        return list;
//    }
//}
