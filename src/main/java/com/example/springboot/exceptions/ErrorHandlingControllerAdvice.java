package com.example.springboot.exceptions;

import com.example.springboot.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandlingControllerAdvice {

    private final ExceptionMessageAccessor exceptionMessageAccessor;
//    @ResponseBody
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ValidationErrorResponse onConstraintValidationException(
//            ConstraintViolationException e
//    ) {
//        final List<Violation> violations = e.getConstraintViolations().stream()
//                .map(
//                        violation -> new Violation(
//                                violation.getPropertyPath().toString(),
//                                violation.getMessage()
//                        )
//                )
//                .collect(Collectors.toList());
//        return new ValidationErrorResponse(violations);
//    }

//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ValidationErrorResponse onMethodArgumentNotValidException(
//            MethodArgumentNotValidException e
//    ) {
//        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
//                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
//                .collect(Collectors.toList());
//        return new ValidationErrorResponse(violations);
//    }

//    @ExceptionHandler({UserNotFoundException.class})
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    ResponseEntity<ApiExceptionResponse> onMethodArgumentNotValidException(
//            UserNotFoundException exception
//    ) {
//        final String existsUsername = exceptionMessageAccessor.getMessage(null, USERNAME_ALREADY_EXISTS);
//        throw new RegistrationException(existsUsername);
//    }

//    @ExceptionHandler({IncorrectParametersException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public String onMethodArgumentNotValidException(
//            IncorrectParametersException e
//    ) {
//        return e.getMessage();
//    }

//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public String onMethodArgumentNotValidException(
//            MissingServletRequestParameterException e
//    ) {
//        return e.getMessage();
//    }

}
