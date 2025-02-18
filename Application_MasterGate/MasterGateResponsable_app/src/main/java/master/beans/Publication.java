package master.beans;

public class Publication {
	private int id_publication;
	private int id_master;
	private String type_pub;
	private String date_publication;
	private String titre;
	private String sous_titre;
	private String nom_uni;
	private String nom_fac;
	private String ville;
	private String logo_uni;

	public int getId_publication() {
		return id_publication;
	}
	public void setId_publication(int id_publication) {
		this.id_publication = id_publication;
	}
	public int getId_master() {
		return id_master;
	}
	public void setId_master(int id_master) {
		this.id_master = id_master;
	}
	public String getType_pub() {
		return type_pub;
	}
	public void setType_pub(String type_pub) {
		this.type_pub = type_pub;
	}
	public String getDate_publication() {
		return date_publication;
	}
	public void setDate_publication(String date_publication) {
		this.date_publication = date_publication;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSous_titre() {
		return sous_titre;
	}
	public void setSous_titre(String sous_titre) {
		this.sous_titre = sous_titre;
	}
	public String getNom_uni() {
		return nom_uni;
	}
	public void setNom_uni(String nom_uni) {
		this.nom_uni = nom_uni;
	}
	public String getNom_fac() {
		return nom_fac;
	}
	public void setNom_fac(String nom_fac) {
		this.nom_fac = nom_fac;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getLogo_uni() {
		return logo_uni;
	}
	public void setLogo_uni(String logo_uni) {
		this.logo_uni = logo_uni;
	}

	
}
