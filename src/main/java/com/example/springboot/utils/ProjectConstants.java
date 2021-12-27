package com.example.springboot.utils;

import java.util.Locale;

public final class ProjectConstants {


    public static final String DEFAULT_ENCODING = "UTF-8";

    public static final String PROJECT_BASE_PACKAGE = "com.example.springboot";

    public static final Locale RUSSIAN_LOCALE = new Locale.Builder().setLanguage("ru").setRegion("RU").build();

    private ProjectConstants() {

        throw new UnsupportedOperationException();
    }

}
