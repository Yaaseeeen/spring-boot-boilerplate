package com.example.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserControllerAdvice {


    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    ResponseEntity<ApiExceptionResponse> handleUserNotFoundException(
            UserNotFoundException exception
    ) {
        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String onMethodArgumentNotValidException(
            MissingServletRequestParameterException e
    ) {
        return e.getMessage();
    }

}
