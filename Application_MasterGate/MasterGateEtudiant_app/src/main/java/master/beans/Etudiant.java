package master.beans;

import jakarta.servlet.http.Part;
import master.beans.exception.EtudiantBeansException;
import master.validators.*;

public class Etudiant {
		int id;
		private String nom;
		private String prenom;
		private String dateNaissance;
		private String sexe;
		private String tel;
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
		
		
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) throws EtudiantBeansException {
			if(PhoneNumberValidator.isValidPhone(tel)) {
				this.tel = tel;
			}else {
				throw new EtudiantBeansException(PhoneNumberValidator.getPhoneNumberValidationMessage(tel));	
			}

		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) throws EtudiantBeansException {
			if(EmailValidator.isValidEmail(email)) {
				this.email = email;
			}else {
				throw new EtudiantBeansException(EmailValidator.getEmailValidationMessage(email));	
			}
		}
		
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) throws EtudiantBeansException {
			
			if(PasswordValidator.isValidPassword(password)) {
				this.password = password;
			}else {
				throw new EtudiantBeansException(PasswordValidator.getPasswordValidationMessage(password));	
			}
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
		public void setCin(String cin) throws EtudiantBeansException {
			if(CinValidator.isValidCNIE(cin)) {
				this.cin = cin;
			}else {
				throw new EtudiantBeansException(CinValidator.getCNIEValidationMessage(cin));	
			}
		}
		
		public String getMassar() {
			return massar;
		}
		public void setMassar(String massar) throws EtudiantBeansException {
		       if (massar.length() <= 15) {
					this.massar = massar;
		        }
		       else {
		    	   throw new EtudiantBeansException("Le code Massar ne doit pas depasser 15 caractères.");
		       }
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
		public float getMoyBac()  {
			return moyBac;

		}
		public void setMoyBac(float moyBac) throws EtudiantBeansException{
			if(NoteValidator.isValidNote(moyBac)) {
				this.moyBac = moyBac;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(moyBac));
			}
		}
		
		public float getMoyLicense() {
			return moyLicense;
		}
		public void setMoyLicense(float moyLicense) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(moyLicense)) {
				this.moyLicense = moyLicense;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(moyLicense));
			}
			
		}
		public float getNoteS1() {
			return noteS1;

		}
		public void setNoteS1(float noteS1) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(noteS1)) {
				this.noteS1 = noteS1;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(noteS1));
			}
		}
		public float getNoteS2()  {
			return noteS2;

		}
		public void setNoteS2(float noteS2) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(noteS2)) {
				this.noteS2 = noteS2;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(noteS2));
			}
		}
		
		public float getNoteS3() {
			return noteS3;

		}
		public void setNoteS3(float noteS3) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(noteS3)) {
				this.noteS3 = noteS3;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(noteS3));
			}
		}
		
		public float getNoteS4() {
			return noteS4;

		}
		public void setNoteS4(float noteS4) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(noteS4)) {
				this.noteS4 = noteS4;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(noteS4));
			}
		}
		public float getNoteS5()  {
			return noteS5;

		}
		public void setNoteS5(float noteS5) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(noteS5)) {
				this.noteS5 = noteS5;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(noteS5));
			}
		}
		public float getNoteS6() {
			return noteS6;

		}
		public void setNoteS6(float noteS6) throws EtudiantBeansException {
			if(NoteValidator.isValidNote(noteS6)) {
				this.noteS6 = noteS6;
			}
			else {
				throw new EtudiantBeansException(NoteValidator.getNoteValidationMessage(noteS6));
			}
		}

	}


