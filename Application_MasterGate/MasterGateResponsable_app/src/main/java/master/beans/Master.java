package master.beans;

import java.util.List;

public class Master {
	  	private int id ;
	  	private String specialite ;
	  	private String nom_coordinateur ;
	  	private String d_debut_inscription ; 
	  	private String d_fin_inscription    ;
	  	private String d_aff_preselection ;
	  	private String d_concours ;
	  	private String d_aff_resultat_concours; 
	  	private String lieu_concours ;
	  	private String information ;
	  	private Responsable respo ;
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

		private MasterConditions conditions;
	  	private List<FilieresLicense> filieresAdmet;
	  	public int getId() {
	        return id;
	    }

	    public void setId(int id_master) {
	        this.id = id_master;
	    }

	    public String getSpecialite() {
	        return specialite;
	    }

	    public void setSpecialite(String specialite) {
	        this.specialite = specialite;
	    }

	    public String getNom_coordinateur() {
	        return nom_coordinateur;
	    }

	    public void setNom_coordinateur(String nom_coordinateur) {
	        this.nom_coordinateur = nom_coordinateur;
	    }

	    // Getter and Setter for debut_inscription
	    public String getD_debut_inscription() {
	        return d_debut_inscription;
	    }

	    public void setD_debut_inscription(String debut_inscription) {
	        this.d_debut_inscription = debut_inscription;
	    }

	    public String getD_fin_inscription() {
	        return d_fin_inscription;
	    }

	    public void setD_fin_inscription(String fin_inscription) {
	        this.d_fin_inscription = fin_inscription;
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

	    public String getLieu_concours() {
	        return lieu_concours;
	    }

	    public void setLieu_concours(String lieu_concours) {
	        this.lieu_concours = lieu_concours;
	    }

	    public String getInformation() {
	        return information;
	    }

	    public void setInformation(String information) {
	        this.information = information;
	    }

	    public Responsable getRespo() {
	        return respo;
	    }

	    public void setRespo(Responsable respo) {
	        this.respo = respo;
	    }


		public MasterConditions getConditions() {
			return conditions;
		}

		public void setConditions(MasterConditions conditions) {
			this.conditions = conditions;
		}

		

		public List<FilieresLicense> getFilieresAdmet() {
			return filieresAdmet;
		}

		public void setFilieresAdmet(List<FilieresLicense> filieresAdmet) {
			this.filieresAdmet = filieresAdmet;
		}

}
