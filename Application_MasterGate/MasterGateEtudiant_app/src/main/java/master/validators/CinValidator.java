package master.validators;

import java.util.regex.Pattern;

public class CinValidator {

    // Définir le motif regex pour le CNIE : 1 à 3 lettres suivies d'au moins 5 chiffres
    private static final String CNIE_PATTERN = "^[A-Z]{1,3}\\d{5,}$";

    // Méthode pour valider un CNIE et retourner un booléen
    public static boolean isValidCNIE(String cnie) {
        return getCNIEValidationMessage(cnie).equals("Le CNIE est valide.");
    }

    // Méthode pour valider un CNIE et retourner un message d'erreur si invalide
    public static String getCNIEValidationMessage(String cnie) {
        if (cnie == null || cnie.isEmpty()) {
            return "Le CNIE ne peut pas être vide.";
        }

        if (!Pattern.matches(CNIE_PATTERN, cnie)) {
            return "Le CNIE doit contenir de 1 à 3 lettres majuscules suivies d'au moins 5 chiffres.";
        }

        if (cnie.length() < 7) {
            return "Le CNIE doit comporter au moins 7 caractères.";
        }

        return "Le CNIE est valide.";
    }

}
