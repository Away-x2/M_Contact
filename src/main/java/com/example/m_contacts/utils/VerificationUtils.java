package com.example.m_contacts.utils;

import java.util.regex.Pattern;

public class VerificationUtils {
    public static boolean verifyName(String name) {
        // Regular expression pattern for name validation
        String nameRegex = "^[A-Za-z\\s]+$";
        return Pattern.matches(nameRegex, name);
    }

    public static boolean verifyEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        return Pattern.matches(emailRegex, email);
    }


    public static boolean verifyPassword(String password) {
        // Regular expression pattern for password validation
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$";
        return Pattern.matches(passwordRegex, password);
    }
    public static boolean verifyNumber(String number) {
        String regex = "-?\\d+(\\.\\d+)?";
        return number.matches(regex);
    }

}
