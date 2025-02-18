package master.dao.interfaces;

import java.util.List;

import master.beans.EtudiantListe;
import master.beans.Master;
import master.beans.MasterCard;
import master.beans.MasterConditions;
import master.beans.Publication;

public interface MasterDao {
	Master getMasterById(int id);
	List<Master> getMastersByidrespo(int id_respo);
	boolean addMaster(Master master) ;
	List<MasterCard> getMasterCardsByRespoId(int id_respo);
	MasterCard getMasterCardById(int id_master);
	boolean deleteMaster(int id_master);
	List<EtudiantListe> getMasterEtudiantsListe(int id_master, String ListType);
	MasterConditions getMasterConditionByMasterId(int id_master);
	int getIdInscription(int id_etudiant, int id_master);
	boolean AddPreInscriprion(int id_etudiant,int id_master);
	boolean deleteFromList(int id_etudiant, int id_master, String listType);
	boolean publierPreselection(int id_master, String titre,String sous_titre);
	boolean publierListeFA(int id_master, String titre,String sous_titre);
	boolean saveScore(double score, int id_etudiant, int id_master);
	boolean generateListeFA(int id_master, int Nfinale, int Nattente);
	int getSizeOf(int id_master, String listType);
	int getMasterStatus(int id_master);
	boolean convocate(int id_master,String message,int status);
	String getEmailEtudiantByIdEtudiant(int id_etudiant);
	boolean ajouteAuListFinale(int id_etudiant,int id_master);
	boolean terminerMaster(int id_master);
	Publication getPublication(int id_master, String type_pub);
}
