package master.beans;

public class Facultes {
	private int id;
	private String nom;
	private String surnom;
	private String logo;
	private Universite uni;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSurnom() {
		return surnom;
	}
	public void setSurnom(String surnom) {
		this.surnom = surnom;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Universite getUni() {
		return uni;
	}
	public void setUni(Universite uni) {
		this.uni = uni;
	}
}
