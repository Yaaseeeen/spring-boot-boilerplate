package com.example.springboot.controller;

import com.example.springboot.model.dto.UserDto;
import com.example.springboot.security.dto.RegistrationResponse;
import com.example.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody UserDto dto) {

        final RegistrationResponse registrationResponse = userService.registration(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

}
