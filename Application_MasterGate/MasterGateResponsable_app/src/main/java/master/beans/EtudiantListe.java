package master.beans;

public class EtudiantListe {
	private int id;
	private String nom;
	private String prenom;
	private String cin;
	private String massar;
	private double score;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id_etudiant) {
		this.id= id_etudiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getMassar() {
		return massar;
	}
	public void setMassar(String massar) {
		this.massar = massar;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
