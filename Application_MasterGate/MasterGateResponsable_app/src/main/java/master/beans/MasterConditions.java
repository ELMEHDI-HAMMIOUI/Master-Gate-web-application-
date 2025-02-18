package master.beans;

public class MasterConditions {
	private int id_condition ;
	private int max_age ;
	private int max_annee_etude;
    private double note_min_semestre ;
    private double note_seuil ;
    
    public int getId_condition() {
        return id_condition;
    }

    public void setId_condition(int id_condition) {
        this.id_condition = id_condition;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public int getMax_annee_etude() {
        return max_annee_etude;
    }

    public void setMax_annee_etude(int max_annee_etude) {
        this.max_annee_etude = max_annee_etude;
    }

    public double getNote_min_semestre() {
        return note_min_semestre;
    }

    public void setNote_min_semestre(double note_min_semestre) {
        this.note_min_semestre = note_min_semestre;
    }

    public double getNote_seuil() {
        return note_seuil;
    }

    public void setNote_seuil(double note_seuil) {
        this.note_seuil = note_seuil;
    }
}
