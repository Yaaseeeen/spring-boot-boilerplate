package com.example.springboot.service;

import com.example.springboot.exceptions.RegistrationException;
import com.example.springboot.model.Phone;
import com.example.springboot.model.dto.UserDto;
import com.example.springboot.repository.PhoneRepository;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.utils.ExceptionMessageAccessor;
import com.example.springboot.utils.ValidationMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

    private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";
    private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";
    private static final String PHONE_NUMBER_ALREADY_EXISTS = "phone_number_already_exists";
    private static final String PHONE_NUMBER_IS_NOT_VALID = "phone_is_not_valid";
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final ExceptionMessageAccessor exceptionMessageAccessor;
    private final ValidationMessageAccessor validationMessageAccessor;

    public List<Phone> validateUser(UserDto dto) {
        final var email = dto.getEmail();
        final var username = dto.getUsername();
        final var phones = dto.getPhones();

        checkEmail(email);
        checkUsername(username);
        return checkPhonesAndGet(phones);
    }

    private void checkUsername(String username) {
        final var existsByUsername = userRepository.existsByUsername(username);

        if (existsByUsername) {

            log.warn("{} is already being used!", username);

            final var existsUsername = exceptionMessageAccessor.getMessage(null, USERNAME_ALREADY_EXISTS);
            throw new RegistrationException(existsUsername);
        }

    }

    private void checkEmail(String email) {
        final var existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            log.warn("{} is already being used!", email);
            final var existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }
    }

    @Transactional
    List<Phone> checkPhonesAndGet(List<Phone> phones) {
        List<Phone> phonesList = new ArrayList<>();
        phones.forEach(phone -> {
            phonesList.add(checkPhoneNumberAndGet(phone));
        });
        return phonesList;
    }

    private Phone checkPhoneNumberAndGet(Phone phone) {
        var phoneNumber = phone.getValue();
        StringBuilder sb = new StringBuilder();
        if (phoneNumber.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")) {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (Character.isDigit(phoneNumber.charAt(i)))
                    sb.append(phoneNumber.charAt(i));
            }
            if (sb.length() > 10) {
                sb.deleteCharAt(0);
            }
            String number = sb.toString();
            if (phoneRepository.findByValue(number) != null) {
                final var existsPhoneNumber = exceptionMessageAccessor.getMessage(null, PHONE_NUMBER_ALREADY_EXISTS);
                throw new RegistrationException(existsPhoneNumber);
            }
            return new Phone(number);
        }
        log.warn("invalid phone number {}", phoneNumber);
        final var invalidPhoneNumber = validationMessageAccessor.getMessage(null, PHONE_NUMBER_IS_NOT_VALID);
        throw new RegistrationException(invalidPhoneNumber);
    }
}
