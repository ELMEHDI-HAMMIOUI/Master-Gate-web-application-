package master.validators;

public class NoteValidator {

    // Méthode pour valider une note
    public static boolean isValidNote(float note) {
        // Spécifier la plage valide des notes (par exemple, entre 0 et 20)
        float minNote = 0.0f;
        float maxNote = 20.0f;

        // Vérifier si la note est dans la plage valide
        return note >= minNote && note <= maxNote;
    }

    // Méthode pour obtenir un message de validation de la note
    public static String getNoteValidationMessage(float note) {
        // Spécifier la plage valide des notes (par exemple, entre 0 et 20)
        float minNote = 0.0f;
        float maxNote = 20.0f;

        // Vérifier si la note est dans la plage valide
        if (note < minNote || note > maxNote) {
            return "La note doit être comprise entre " + minNote + " et " + maxNote + ".";
        }

        // La note est valide
        return "La note est valide.";
    }

}
