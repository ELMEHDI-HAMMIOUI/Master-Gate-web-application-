package master.dao.interfaces;
import master.beans.Etudiant;
import master.beans.Inscription;
import master.dao.exception.EtudiantDaoException;

public interface EtudiantDao {
	public void addEtudiant(Etudiant e) throws EtudiantDaoException;
	public boolean checkEtudiant(Etudiant e) throws EtudiantDaoException;//true if it's exist (if is there)
	public Etudiant getProfileById(int etudiantId) throws EtudiantDaoException;//retourne les info de l'etudiant pour les afficher dans le menu du profile
	//public Blob getBlobedDocByEtudiantId(int etudiantId) throws EtudiantDaoException;
	//public Blob getBlobedImgByEtudiantId(int etudiantId) throws EtudiantDaoException;
	public int verifyLogin(Etudiant e) throws EtudiantDaoException;
	public Inscription getInsc(int idEtudiant, int idMaster) throws EtudiantDaoException;
	public String storeToken(int idEtudiant) throws EtudiantDaoException;
	public int getEtudiantIdFromToken(String token) throws EtudiantDaoException;
	public void deleteTokenMapEntry(String token) throws EtudiantDaoException;
	public void deleteEtudiant(int idEtudiant) throws EtudiantDaoException;
	//update all etudiant info (the etudiant bean must have the idEtudiant, because im updating the column with the idEtudiant)
	public void updateEtudiantAll(Etudiant e) throws EtudiantDaoException ;
	//update custom etudiant info,(the email, password, telephone, photo) (the etudiant bean must have the idEtudiant, because im updating the column with the idEtudiant)
	public void updateEtudiantCustom(Etudiant e) throws EtudiantDaoException ;
	//retourne le nombre de masters auxquels un étudiant a été inscrit.
	public int getEnrolledMastersNumber(int idEtudiant) throws EtudiantDaoException;
}
