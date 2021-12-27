package com.example.springboot.security.service;

import com.example.springboot.exceptions.UserNotFoundException;
import com.example.springboot.model.Phone;
import com.example.springboot.model.User;
import com.example.springboot.model.UserRole;
import com.example.springboot.model.dto.UserDto;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.security.dto.AuthenticatedUserDto;
import com.example.springboot.security.dto.RegistrationResponse;
import com.example.springboot.security.mapper.UserMapper;
import com.example.springboot.service.UserValidationService;
import com.example.springboot.utils.ExceptionMessageAccessor;
import com.example.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";
    private static final String USER_NOT_FOUND = "user_not_found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserValidationService userValidationService;
    private final GeneralMessageAccessor generalMessageAccessor;
    private final ExceptionMessageAccessor exceptionMessageAccessor;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public RegistrationResponse registration(UserDto dto) {
        List<Phone> phones = userValidationService.validateUser(dto);
        final var user = UserMapper.INSTANCE.convertToUser(dto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);
        user.setPhones(phones);
        phones.forEach(phone -> phone.setUser(user));
        userRepository.save(user);
        final var registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, dto.getUsername());

        log.info("{} registered successfully!", dto.getUsername());

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {
        final var user = findByUsername(username);
        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }

    public User findById(Long id) {
        final var userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        log.warn("Not found user with id {}!", id);

        final var existsUser = exceptionMessageAccessor.getMessage(null, USER_NOT_FOUND);
        throw new UserNotFoundException(existsUser);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        final var user = findById(id);
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
        log.info("User with id {} updated successfully!", id);
        return userDto;
    }


    @Override
    public void delete(Long id) {
        final var user = findById(id);
        userRepository.delete(user);
        log.info("User with id {} deleted successfully!", id);
    }


    @Override
    public UserDto getById(Long id) {
        final var user = findById(id);
        final var userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        log.info("Get user with id {}!", id);
        return userDto;
    }

    @Override
    public List<UserDto> getAll() {
        final var userDto = new UserDto();
        final List<UserDto> list = new ArrayList<>();
        userRepository.findAll().forEach(user ->
        {
            BeanUtils.copyProperties(user, userDto);
            list.add(userDto);
        });
        log.info("Get list of users {}!", list);
        return list;
    }
}
