package master.beans;

import jakarta.servlet.http.Part;
import master.beans.exception.EtudiantBeansException;

public class Etudiant {
		int id;
		private String nom;
		private String prenom;
		private String dateNaissance;
		private String sexe;
		private int tel;
		private String email;
		private String password;
		private String nationalite;
		private String cin;
		private String massar;
		private String dateBac;
		private String dateGraduation;
		private String datePremiereInscription;

		private Part photo;
		private Part docs;
		private String FilBac;
		
		private int idFaculte;
		private int idFilLicense;
		//la faculte et la filiere sous forme de string vont nous servir pour l'affichage sans avoir les stocker comme des beans, ni creer une methode pour les grabber by id
		private String faculteNom;
		private String filLicenseNom;
		
		private float moyBac;
		private float moyLicense;
		private float noteS1;
		private float noteS2;
		private float noteS3;
		private float noteS4;
		private float noteS5;
		private float noteS6;
		
		//constructeur 
	    public Etudiant() {

	    }
	    
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
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getDateNaissance() {
			return dateNaissance;
		}
		public void setDateNaissance(String dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
		public String getSexe() {
			return sexe;
		}
		public void setSexe(String sexe) {
			this.sexe = sexe;
		}
		public int getTel() {
			return tel;
		}
		public void setTel(int tel) {
			this.tel = tel;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNationalite() {
			return nationalite;
		}
		public void setNationalite(String nationalite) {
			this.nationalite = nationalite;
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
		public String getDateGraduation() {
			return dateGraduation;
		}
		public void setDateGraduation(String dateGraduation) {
			this.dateGraduation = dateGraduation;
		}
		public Part getPhoto() {
			return photo;
		}
		public void setPhoto(Part photo) throws EtudiantBeansException{
			if( photo.getContentType().equals("image/jpeg") || photo.getContentType().equals("image/png") || photo.getContentType().equals("image/webp")) {
				this.photo = photo;
			}
			else {
				throw new EtudiantBeansException("Le type de la photo doit être: .jpeg ou .png ou .webp");
			}
			
		}
		public Part getDocs() {
			return docs;
		}
		public void setDocs(Part docs) throws EtudiantBeansException{
			if( docs.getContentType().equals("application/pdf") ) {
				this.docs = docs;
			}
			else {
				throw new EtudiantBeansException("Le type du document doit être: .pdf");
			}
					}
		public String getDateBac() {
			return dateBac;
		}
		public void setDateBac(String dateBac) {
			this.dateBac = dateBac;
		}
		
		public String getDatePremiereInscription() {
			return datePremiereInscription;
		}

		public void setDatePremiereInscription(String datePremiereInscription) {
			this.datePremiereInscription = datePremiereInscription;
		}
		public int getIdFaculte() {
			return idFaculte;
		}
		public void setIdFaculte(int idFaculte) {
			this.idFaculte = idFaculte;
		}
		public String getFilBac() {
			return FilBac;
		}
		public void setFilBac(String FilBac) {
			this.FilBac = FilBac;
		}
		public int getIdFilLicense() {
			return idFilLicense;
		}
		public void setIdFilLicense(int idFilLicense) {
			this.idFilLicense = idFilLicense;
		}
		//
		public String getFaculteNom() {
			return faculteNom;
		}
		public void setFaculteNom(String faculteNom) {
			this.faculteNom = faculteNom;
		}
		public String getFilLicenseNom() {
			return filLicenseNom;
		}
		public void setFilLicenseNom(String filLicenseNom) {
			this.filLicenseNom = filLicenseNom;
		}
		//
		public float getMoyBac() {
			return moyBac;
		}
		public void setMoyBac(float moyBac) {
			this.moyBac = moyBac;
		}
		public float getMoyLicense() {
			return moyLicense;
		}
		public void setMoyLicense(float moyLicense) {
			this.moyLicense = moyLicense;
		}
		public float getNoteS1() {
			return noteS1;
		}
		public void setNoteS1(float noteS1) {
			this.noteS1 = noteS1;
		}
		public float getNoteS2() {
			return noteS2;
		}
		public void setNoteS2(float noteS2) {
			this.noteS2 = noteS2;
		}
		public float getNoteS3() {
			return noteS3;
		}
		public void setNoteS3(float noteS3) {
			this.noteS3 = noteS3;
		}
		public float getNoteS4() {
			return noteS4;
		}
		public void setNoteS4(float noteS4) {
			this.noteS4 = noteS4;
		}
		public float getNoteS5() {
			return noteS5;
		}
		public void setNoteS5(float noteS5) {
			this.noteS5 = noteS5;
		}
		public float getNoteS6() {
			return noteS6;
		}
		public void setNoteS6(float noteS6) {
			this.noteS6 = noteS6;
		}

	}


