package master.dao.interfaces;

import java.util.List;

import master.beans.CloseMaster;
import master.beans.DocsBean;
import master.beans.Etudiant;
import master.beans.MBoard;
//import master.beans.EtudiantListe;
import master.beans.Master;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.mhdi.pub.EtudiantListe;
import master.mhdi.pub.Publication;

public interface MasterDao {
    public static final int MASTER_CARDS_PAGE_SIZE = 10; // Number of records per page (for the page of masterCards)

	List<Master> getAllMasters();
//	List<Master> getMastersByIdFac(int id_fac);
	Master getMasterById(int id);
	List<Master> getMastersByidrespo(int id_respo);
	boolean addMaster(Master master) ;
	List<MasterCard> getMasterCardsByRespoId(int id_respo);
	//List<EtudiantListe> getMasterEtudiantsListe(int id_master, String type);
	MasterCard getMasterCardById(int id_master);
	boolean deleteMaster(int id_master);
	public void inscrire(DocsBean dc) throws EtudiantDaoException;
	public boolean checkEnrollement(int etudiantId, int idMaster) throws EtudiantDaoException;
	public List<MasterCard> getMasterCardsByPage(int pageNumber) ;
	public int getTotalMasterCardsRecords() ;
	public List<MasterCard> getMasterCardsByIdEtudiant(int idEtudiant) ;
	public List<MasterCard> getMasterCardsByFacId(int idFac) ;
	public List<MasterCard> searchMasters(String keyword) ;
	public int getMasterIdFromToken(String token) throws EtudiantDaoException;
	public String getTokenFromIdMaster(int idMaster) throws EtudiantDaoException;
	public List<MasterCard> getAllMasterCards() ;
	public List<MasterCard> getNonInscritMasterCards(int idEtudiant) ;
	public List<CloseMaster> getCloseMasters(int idEtudiant) ;
	//get leaderBoard informations
	public List<MBoard> getBoardInfo(int idEtudiant) ;
	//get les mastercards dont l'etudiant est preselectioné
	public List<MasterCard> getPresMasterCards(int idEtudiant);
	//grabber les mastercards dont l'etudiant est affiché dans leur liste finale 
	public List<MasterCard> getLfMasterCards(int idEtudiant);
	//grabber les mastercards dont l'etudiant est affiché dans leur liste d'attente 
	public List<MasterCard> getLaMasterCards(int idEtudiant);
	////mhdi publication stuff
	public Publication getPublication(int id_master,String type_pub) ;
	public List<EtudiantListe> getMasterEtudiantsListe(int id_master,String ListType) ;
}
