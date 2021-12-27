package com.example.springboot.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//todo rename to validation exeption
public class RegistrationException extends RuntimeException {

    private final String errorMessage;

}
