package master.validators;

import java.util.regex.Pattern;

public class PasswordValidator {

    // Define regex patterns for the criteria
    private static final String LOWERCASE_PATTERN = ".*[a-z].*";
    private static final String UPPERCASE_PATTERN = ".*[A-Z].*";
    private static final String DIGIT_PATTERN = ".*\\d.*";
    private static final String SPECIAL_CHARACTER_PATTERN = ".*[!@#$%^&*].*";
    private static final int MIN_LENGTH = 8;

    // Method to validate a password and return a boolean
    public static boolean isValidPassword(String password) {
        return getPasswordValidationMessage(password).equals("Le mot de passe est valide.");
    }

    // Method to validate a password and return an error message if invalid
    public static String getPasswordValidationMessage(String password) {
        if (password == null || password.length() < MIN_LENGTH) {
            return "Le mot de passe doit comporter au moins " + MIN_LENGTH + " caractères.";
        }

        if (!Pattern.matches(LOWERCASE_PATTERN, password)) {
            return "Le mot de passe doit contenir au moins une lettre minuscule.";
        }

        if (!Pattern.matches(UPPERCASE_PATTERN, password)) {
            return "Le mot de passe doit contenir au moins une lettre majuscule.";
        }

        if (!Pattern.matches(DIGIT_PATTERN, password)) {
            return "Le mot de passe doit contenir au moins un chiffre.";
        }

        if (!Pattern.matches(SPECIAL_CHARACTER_PATTERN, password)) {
            return "Le mot de passe doit contenir au moins un caractère spécial (parmi !@#$%^&*).";
        }

        return "Le mot de passe est valide.";
    }


}
