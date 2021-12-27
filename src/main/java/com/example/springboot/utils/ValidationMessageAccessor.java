package com.example.springboot.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
public class ValidationMessageAccessor {

    private final MessageSource messageSource;

    ValidationMessageAccessor(@Qualifier("validationMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(Locale locale, String key, Object... parameter) {

        if (Objects.isNull(locale)) {
            return messageSource.getMessage(key, parameter, ProjectConstants.RUSSIAN_LOCALE);
        }
        return messageSource.getMessage(key, parameter, locale);
    }
}