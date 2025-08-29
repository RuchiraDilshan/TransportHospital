package com.example.TransportHospital.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(String password) {
        if (password == null)
            return false;
        return pattern.matcher(password).matches();
    }

    public String getPasswordRequirements() {
        return "Password must contain: " +
                "• At least 8 characters\n" +
                "• At least one uppercase letter\n" +
                "• At least one lowercase letter\n" +
                "• At least one number\n" +
                "• At least one special character (@#$%^&+=!)";
    }

}
