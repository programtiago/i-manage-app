package com.devtiago.i_manage_app.backend.utils;

import java.security.SecureRandom;

public class PasswordUserGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final int DEFAULT_NUM_DIGITS = 3;

    /**
     * Generates a password based on full name plus random digits
     * Example: "Tiago Filipe Ferreira da Silva" -> "TSD289"
     * @param fullName Employee
     * @return generated password
     */
    public static String generateFromName(String fullName){
        if (fullName == null || fullName.isBlank()){
            throw new IllegalArgumentException("Full name cannot be empty");
        }

        StringBuilder initials = new StringBuilder();
        String[] parts = fullName.trim().split("\\s+");
        for (String part : parts){
            if (!part.isEmpty()){
                initials.append(Character.toLowerCase(part.charAt(0)));
            }
        }

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < DEFAULT_NUM_DIGITS; i++){
            digits.append(random.nextInt(10));
        }

        return initials + digits.toString();
    }
}
