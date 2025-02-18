package master.dao.interfaces;

import master.beans.Etudiant;
import master.dao.exception.EtudiantDaoException;

public interface EtudiantDao {
	public Etudiant getProfileById(int etudiantId) throws EtudiantDaoException;
}
