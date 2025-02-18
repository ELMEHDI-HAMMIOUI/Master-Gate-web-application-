package master.validators;

public class PhoneNumberValidator {

    // Method to return error message
    public static String getPhoneNumberValidationMessage(String phoneNumber) {
        // Check if the phone number is exactly 10 digits long
        if (phoneNumber == null) {
            return "Erreur: Le numéro de téléphone ne doit pas être nul.";
        }

        if (phoneNumber.length() != 10) {
            return "Erreur: Le numéro de téléphone doit comporter exactement 10 chiffres.";
        }

        // Check if the phone number consists of only digits
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return "Erreur: Le numéro de téléphone ne doit contenir que des chiffres.";
            }
        }

        // Check if the phone number starts with "06" or "07"
        if (!phoneNumber.startsWith("06") && !phoneNumber.startsWith("07")) {
            return "Erreur: Le numéro de téléphone doit commencer par '06' ou '07'.";
        }

        // If all checks pass, the phone number is valid
        return "Le numéro de téléphone est valide.";
    }
    
    // Method to validate an phone number, return a boolean
    public static boolean isValidPhone(String phoneNumber) {
        return getPhoneNumberValidationMessage(phoneNumber).equals("Le numéro de téléphone est valide.");
    }
    
    
}