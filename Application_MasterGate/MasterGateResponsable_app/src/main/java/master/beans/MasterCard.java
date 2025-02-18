package master.beans;

public class MasterCard {
	private int id_master;
  	private String specialite ;
  	private String nom_coordinateur ;
  	private String d_debut_inscription ; 
  	private String d_fin_inscription    ;
  	private String d_aff_preselection ;
  	private String d_concours ;
  	private String d_aff_resultat_concours;
  	private String lieu_concours;
  	private int id_fac;
  	private String nom_fac;
	private String surnom_fac;
	private int id_uni;
	private String nom_uni;
	private String ville;
	private String surnom_uni; 
	private String web_site_uni; 
	private String logo_uni;
	private int id_respo;	
	private String condition_str;
	private String document;
	public String getCondition_str() {
		return condition_str;
	}
	public void setCondition_str(String condition_str) {
		this.condition_str = condition_str;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public int getId_master() {
		return id_master;
	}
	public void setId_master(int id_master) {
		this.id_master = id_master;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getD_debut_inscription() {
		return d_debut_inscription;
	}
	public void setD_debut_inscription(String d_debut_inscription) {
		this.d_debut_inscription = d_debut_inscription;
	}
	public String getD_fin_inscription() {
		return d_fin_inscription;
	}
	public void setD_fin_inscription(String d_fin_inscription) {
		this.d_fin_inscription = d_fin_inscription;
	}
	public String getD_aff_preselection() {
		return d_aff_preselection;
	}
	public void setD_aff_preselection(String d_aff_preselection) {
		this.d_aff_preselection = d_aff_preselection;
	}
	public String getD_concours() {
		return d_concours;
	}
	public void setD_concours(String d_concours) {
		this.d_concours = d_concours;
	}
	public String getD_aff_resultat_concours() {
		return d_aff_resultat_concours;
	}
	public void setD_aff_resultat_concours(String d_aff_resultat_concours) {
		this.d_aff_resultat_concours = d_aff_resultat_concours;
	}
	public int getId_fac() {
		return id_fac;
	}
	public void setId_fac(int id_fac) {
		this.id_fac = id_fac;
	}
	public String getNom_fac() {
		return nom_fac;
	}
	public void setNom_fac(String nom_fac) {
		this.nom_fac = nom_fac;
	}
	public String getSurnom_fac() {
		return surnom_fac;
	}
	public void setSurnom_fac(String surnom_fac) {
		this.surnom_fac = surnom_fac;
	}
	public int getId_uni() {
		return id_uni;
	}
	public void setId_uni(int id_uni) {
		this.id_uni = id_uni;
	}
	public String getNom_uni() {
		return nom_uni;
	}
	public void setNom_uni(String nom_uni) {
		this.nom_uni = nom_uni;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getSurnom_uni() {
		return surnom_uni;
	}
	public void setSurnom_uni(String surnom_uni) {
		this.surnom_uni = surnom_uni;
	}
	public String getWeb_site_uni() {
		return web_site_uni;
	}
	public void setWeb_site_uni(String web_site_uni) {
		this.web_site_uni = web_site_uni;
	}
	public String getLogo_uni() {
		return logo_uni;
	}
	public void setLogo_uni(String logo_uni) {
		this.logo_uni = logo_uni;
	}
	public int getId_respo() {
		return id_respo;
	}
	public void setId_respo(int id_respo) {
		this.id_respo = id_respo;
	}
	public String getNom_coordinateur() {
		return nom_coordinateur;
	}
	public void setNom_coordinateur(String nom_coordinateur) {
		this.nom_coordinateur = nom_coordinateur;
	}
	public String getLieu_concours() {
		return lieu_concours;
	}
	public void setLieu_concours(String lieu_concours) {
		this.lieu_concours = lieu_concours;
	}

}
