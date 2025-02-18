package master.validators;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class EmailValidator {

    // Define the regex pattern for a valid email address
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    // Method to validate an email address and return a boolean
    public static boolean isValidEmail(String email) {
        return getEmailValidationMessage(email).equals("L'email est valide.");
    }

    // Method to validate an email address and return an error message if invalid
    public static String getEmailValidationMessage(String email) {
        if (email == null || email.isEmpty()) {
            return "L'email ne peut pas être vide.";
        }

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return "L'email n'est pas valide.";
        }

        return "L'email est valide.";
    }
}