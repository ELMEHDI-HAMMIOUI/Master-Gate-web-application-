package master.beans;

import jakarta.servlet.http.Part;
import master.beans.exception.EtudiantBeansException;

public class DocsBean {
	private Part docs;
	private int idMaster;
	private int idEtudiant;
	
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
	
	public int getIdMaster() {
		return idMaster;
	}
	public void setIdMaster(int idMaster) {
		this.idMaster = idMaster;
	}
	public int getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
}
