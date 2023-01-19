package com.task.sie.app.tasksie.utils;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import liquibase.repackaged.org.apache.commons.text.RandomStringGenerator;

public class GeneratePassword {
    public static String generateRandomSpecialCharacters(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}|;:,<.>/?";
        return RandomStringUtils.random( length, characters );
    }
}
