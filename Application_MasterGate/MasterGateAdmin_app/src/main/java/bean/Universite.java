package bean;

public class Universite {
		private int id;
		private String nom ;
		private String ville;
		private String surnom; 
		private String web_site; 
		private String logo ;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id= id;
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
		public String getVille() {
			return ville;
		}
		public void setVille(String ville) {
			this.ville = ville;
		}
		public String getWeb_site() {
			return web_site;
		}
		public void setWeb_site(String web_site) {
			this.web_site = web_site;
		}
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
	
}
