package master.dao.interfaces;

import java.util.List;
import master.beans.Facultes;
import master.beans.Universite;
import master.dao.exception.EtudiantDaoException;

public interface FacultesDao {
    public static final int FACULTES_CARDS_PAGE_SIZE = 8; // Number of records per page (for the page of facultes)

	public List<Facultes> getAllFacultes() throws EtudiantDaoException;
	public Universite getUniByFacId(int facId) throws EtudiantDaoException;
	public Facultes getFaculteById(int idFac) throws EtudiantDaoException;
	public int getTotalFacsRecords() ;
	public int getTotalFacsRecordsHavingMaster() ;
	public List<Facultes> getFacsByPage(int pageNumber) throws EtudiantDaoException ;
	public List<Facultes> searchFacs(String keyword) throws EtudiantDaoException;
	public List<Facultes> getFacsHavingMasterByPage(int pageNumber) throws EtudiantDaoException ;
	public List<Universite> getAllUnis() throws EtudiantDaoException;
	public List<String> getAllCities() throws EtudiantDaoException;
	public List<String> getAllSpecialities() throws EtudiantDaoException;
}
