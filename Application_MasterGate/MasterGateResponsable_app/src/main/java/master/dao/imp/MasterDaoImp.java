package master.dao.imp;

import java.sql.Connection;     
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import master.beans.EtudiantListe;
import master.beans.FilieresLicense;
import master.beans.Master;
import master.beans.MasterCard;
import master.beans.MasterConditions;
import master.beans.Publication;
import master.beans.Responsable;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FilieresLicenseDao;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;

public class MasterDaoImp implements MasterDao{


	@Override
	public Master getMasterById(int id_master) {
		ResponsableDao RDO=OraFactory.getResponsableDao();
		FilieresLicenseDao FilDao = OraFactory.getFilieresLicenseDao();
		Connection connection = null;
		PreparedStatement ps=null;
	    ResultSet rs=null;
    	Responsable respo =new Responsable();
    	int id_respo=0;
    	MasterConditions condition=new MasterConditions();
    	int id_condition=0;
    	Master master =new Master();
    	List<FilieresLicense>  filiereAdmet=new ArrayList<FilieresLicense>();
    	
 	    try {
	        connection = OraFactory.getConnection();
	        String sql = "Select * from MASTER_CONDITION_V where ID_MASTER = ?";

	        ps = connection.prepareStatement(sql);
	        ps.setInt(1,id_master);
	        rs=ps.executeQuery();
	        if(rs.next()) {
	        	//getting reso id the get respoById
	        	id_respo=rs.getInt("ID_RESPONSABLE");
	        	respo = RDO.getRespoByID(id_respo);
	        	//geting filieres amdet
	        	filiereAdmet=FilDao.getFiliereAdmetByMasterId(id_master);
	        	
	        	
	        	//creating the conditions
	        	condition.setId_condition(rs.getInt("ID_CONDITION"));
	        	condition.setNote_min_semestre(rs.getDouble("NOTE_MIN_SEMESTRE"));
	        	condition.setNote_seuil(rs.getDouble("NOTE_SEUIL"));
	        	condition.setMax_age(rs.getInt("MAX_AGE"));
	        	condition.setMax_annee_etude(id_condition);
	        	//now we have responsable , conditions , filiereAdmet 
	        	//creating the master 
	        	master.setId(rs.getInt("ID_MASTER"));
	        	master.setSpecialite(rs.getString("SPECIALITE"));
	        	master.setNom_coordinateur(rs.getString("NOM_COORDINATEUR"));
	        	master.setD_debut_inscription(rs.getString("D_DEBUT_INSCRIPTION"));
	        	master.setD_fin_inscription(rs.getString("D_FIN_INSCRIPTION"));
	        	master.setD_aff_preselection(rs.getString("D_AFF_PRESELECTION"));
	        	master.setD_concours(rs.getString("D_CONCOURS"));
	        	master.setD_aff_resultat_concours(rs.getString("D_AFF_RESULTAT_CONCOURS"));
	        	master.setLieu_concours(rs.getString("LIEU_CONCOURS"));
	        	master.setInformation(rs.getString("INFORMATION"));
	        	master.setRespo(respo);
	        	master.setConditions(condition);
	        	master.setFilieresAdmet(filiereAdmet);
	        }
	    } catch (SQLException e) {
	        // Handle database errors
	        e.printStackTrace(); // Consider using a logger instead of printing stack trace
	    }
 	    finally {
	        
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(rs !=null ) {
	        	try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
        }
		return master;
	}

	@Override
	public List<Master> getMastersByidrespo(int id_respo) {
		Connection connection = null;
		PreparedStatement ps=null;//pour select from master
	    ResultSet rs=null;    	
	    List<Master> masters =new ArrayList<Master>();
 	    try {
	        connection = OraFactory.getConnection();
	        String sql = "Select ID_MASTER from MASTER_CONDITION_V where ID_RESPONSABLE = ?";
	        ps = connection.prepareStatement(sql);
	        ps.setInt(1,id_respo);
	        rs=ps.executeQuery();
	        while(rs.next()) {
	        	masters.add(getMasterById(rs.getInt("ID_MASTER")));
	        	//we get the id master the we getMaster by id then we add the master to ListMasters
	        }
 	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
 	    finally {
	        
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(rs !=null ) {
	        	try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
        }

		return masters;
	}
	

	@Override
	public boolean addMaster(Master master)  {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    Statement s = null;
	    ResultSet rs=null;
	    
	    boolean isAffected = false;
	    int rowsAffected_Master_Condition=0 ;
	    int rowsAffected_master_FAD=0;
	    int id_master=0;
	    try {
	        // Get database connection
	        connection = OraFactory.getConnection();
	        connection.setAutoCommit(false);//to rollback the insert on master_condition dans le cas d'echec en inssertion dans master_filere
	        //Stocker id du master pour la reutiliser dan s insert de filere admet
	        s=connection.createStatement();
	        rs=s.executeQuery("SELECT SQC_MASTER.NEXTVAL  FROM DUAL");
	        if(rs.next()) {
	        	id_master=rs.getInt(1);
	        	master.setId(id_master);

		        // Prepare SQL statement for insertion into MASTER_CONDITION_V view
		        String insertMasterCondiionQuery = "INSERT INTO MASTER_CONDITION_V"
		        		+ "   (ID_MASTER, SPECIALITE, NOM_COORDINATEUR, D_DEBUT_INSCRIPTION,"
		        		+ "    D_FIN_INSCRIPTION, \r\n"
		        		+ "    D_AFF_PRESELECTION, D_CONCOURS, D_AFF_RESULTAT_CONCOURS, LIEU_CONCOURS, \r\n"
		        		+ "    INFORMATION, ID_RESPONSABLE, ID_CONDITION, MAX_AGE, MAX_ANNEE_ETUDE, \r\n"
		        		+ "    NOTE_MIN_SEMESTRE, NOTE_SEUIL,CONDITION,DOCS_STRING)"
		        		+ "    VALUES (?,?,?,?,?,?,?,?,?,?,?,0,?,?,?,?,?,?)";
		        ps = connection.prepareStatement(insertMasterCondiionQuery);
		       //a trigger manage the primary keys 
		        ps.setInt(1,    master.getId());
		        ps.setString(2, master.getSpecialite());
		        ps.setString(3,   master.getNom_coordinateur());	
		        ps.setDate(4, java.sql.Date.valueOf(master.getD_debut_inscription()));
		        ps.setDate(5, java.sql.Date.valueOf(master.getD_fin_inscription()));
		        ps.setDate(6, java.sql.Date.valueOf(master.getD_aff_preselection()));
		        ps.setDate(7, java.sql.Date.valueOf(master.getD_concours()));
		        ps.setDate(8, java.sql.Date.valueOf(master.getD_aff_resultat_concours()));
		        ps.setString(9, master.getLieu_concours());
		        ps.setString(10,master.getInformation());
		        ps.setInt(11,   master.getRespo().getId());
		        ps.setInt(12,   master.getConditions().getMax_age());
		        ps.setInt(13,   master.getConditions().getMax_annee_etude());
		        ps.setDouble(14,master.getConditions().getNote_min_semestre());
		        ps.setDouble(15,master.getConditions().getNote_seuil());
		        ps.setString(16,master.getCondition_str());
		        ps.setString(17,master.getDocument());
		        //set number of rows affected on the variable to see if we can continue the insertio of filiere admet
		        rowsAffected_Master_Condition = ps.executeUpdate();
		        
		        if(rowsAffected_Master_Condition>0) {
		        	// we reuse the preparetatment thats why we should colose it and re open it
			        if (ps != null) {try { ps.close();} catch (SQLException e) {e.printStackTrace();}}
			        // Check if rows were affected to continue the inserting filiere admet 
			            String insertFiliereAdmetQuery ="INSERT INTO MASTER_FILIER_ADMET_V (id_master,ID_FIL_LICENCE) values (?,?)";
			            ps = connection.prepareStatement(insertFiliereAdmetQuery);
			            for(FilieresLicense f : master.getFilieresAdmet()) {
			            	ps.setInt(1, master.getId());
			            	ps.setInt(2, f.getId_filiere());
			            	rowsAffected_master_FAD+=ps.executeUpdate();
			            }
			            if(rowsAffected_master_FAD == master.getFilieresAdmet().size()) {
			            	connection.commit();
			            	isAffected=true;
			            }
			            else {
			            	connection.rollback();
			            	isAffected=false;
			            }
			        }else {
			        	connection.rollback();
		            	isAffected=false;
		        }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return isAffected;
	}
	@Override
	public List<MasterCard> getMasterCardsByRespoId(int id_respo) {
		Connection connection = null;
		PreparedStatement ps=null;
	    ResultSet rs=null;    	
	    MasterCard masterCard=new MasterCard();
	    List<MasterCard> masterCards =new ArrayList<MasterCard>();
 	    try {
	        connection = OraFactory.getConnection();
	        String sql = "Select  ID_MASTER from MASTER_CARD_v where ID_RESPONSABLE = ?";
	        ps = connection.prepareStatement(sql);
	        ps.setInt(1,id_respo);
	        rs=ps.executeQuery();
	        while(rs.next()) {
	        	masterCard=getMasterCardById(rs.getInt("ID_MASTER"));
	        	masterCards.add(masterCard);
	        }
 	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
 	    finally {
	        
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(rs !=null ) {
	        	try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
        }

		return masterCards;
	}
	
	@Override
	public MasterCard getMasterCardById(int id_master) {
		Connection connection = null;
		PreparedStatement ps=null;
	    ResultSet rs=null;    	
	    MasterCard masterCard=new MasterCard();;
 	    try {
	        connection = OraFactory.getConnection();
	        String sql = "Select * from MASTER_CARD_v where id_master = ?";
	        ps = connection.prepareStatement(sql);
	        ps.setInt(1,id_master);
	        rs=ps.executeQuery();
	        if(rs.next()) {
	        	masterCard.setId_master(rs.getInt("ID_MASTER"));
	        	masterCard.setSpecialite(rs.getString("SPECIALITE"));
	        	masterCard.setNom_coordinateur(rs.getString("nom_coordinateur"));
	        	masterCard.setD_debut_inscription((rs.getString("D_DEBUT_INSCRIPTION")));
	        	masterCard.setD_fin_inscription(rs.getString("D_FIN_INSCRIPTION"));
	        	masterCard.setD_aff_preselection(rs.getString("D_AFF_PRESELECTION"));
	        	masterCard.setD_concours(rs.getString("D_CONCOURS"));
	        	masterCard.setD_aff_resultat_concours(rs.getString("D_AFF_RESULTAT_CONCOURS"));
	        	masterCard.setId_respo(rs.getInt("ID_RESPONSABLE"));
	        	masterCard.setId_fac(rs.getInt("ID_FACULTE"));
	        	masterCard.setNom_fac(rs.getString("NOM_FAC"));
	        	masterCard.setSurnom_fac(rs.getString("SURNOM_FAC"));
	        	masterCard.setId_uni(rs.getInt("ID_UNIVERSITE"));
	        	masterCard.setNom_uni(rs.getString("NOM_UNI"));
	        	masterCard.setVille(rs.getString("VILLE"));
	        	masterCard.setSurnom_uni(rs.getString("SURNOM_UNI"));
	        	masterCard.setWeb_site_uni(rs.getString("WEB_SITE"));
	        	masterCard.setLogo_uni(rs.getString("LOGO_UNI"));
	        	masterCard.setCondition_str(rs.getString("CONDITION"));
	        	masterCard.setDocument(rs.getString("DOCS_STRING"));  	
	        }
 	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
 	    finally {
	        
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(rs !=null ) {
	        	try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
        }

		return masterCard;
	}
	@Override
	public int getMasterStatus(int id_master) {
	    Connection connexion = null;
	    ResultSet rs0 = null;
	    PreparedStatement ps0 = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    int status = 0;
	    String sql0="select TYPE_PUB from publication where id_master=? and TYPE_PUB='FIN' ";
	    String sql="select count(*) from publication where id_master=?";
 	    try {
 	    	
 	    	connexion = OraFactory.getConnection();
 	    	ps0=connexion.prepareStatement(sql0);
	        ps0.setInt(1,id_master);
	        rs0=ps0.executeQuery();
	        if(rs0.next()) {
	        	status=99;
	        }else {
	 	    	ps=connexion.prepareStatement(sql);
		        ps.setInt(1,id_master);
		        rs=ps.executeQuery();
		        if(rs.next()) {
		        	status=rs.getInt(1);
		        }
	        }
 	    	
 	    	
	        
    	} catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	try {        	
        		if(connexion!=null) {connexion.close();}
	        	if(ps0!=null) {ps0.close();}
	        	if(rs0!=null) {rs0.close();}
	        	if(ps!=null) {ps.close();}
	        	if(rs!=null) {rs.close();}
        	} catch (SQLException e) {
                e.printStackTrace();
            }
        }
	    return status;
	}
	
	//pour recupérer La liste des etudiants (inscris preselectionnés admis du master
	@Override 
	public List<EtudiantListe> getMasterEtudiantsListe(int id_master,String ListType) {
	    Connection connexion = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    List<EtudiantListe> etudiantsListe = new ArrayList<EtudiantListe>();
	    EtudiantListe etudiantListe;
        String sql = "";
        switch(ListType) {
		  case "inscriptionEtudedossier":
			  sql="SELECT * FROM INSCRIPTION_ETUDIANT_MASTER_V WHERE ID_MASTER = ? and ID_INSCRIPTION not in (select ID_INSCRIPTION from TMP_PRESELECTION ) and ID_INSCRIPTION not in (select ID_INSCRIPTION from Rejected )";
			  break;
		  case "inscription":
			  sql="SELECT * FROM INSCRIPTION_ETUDIANT_MASTER_V WHERE ID_MASTER = ?";
			  break;	  
		  case "preselection":
			  sql="SELECT * FROM LISTE_PRESELECTION_v WHERE ID_MASTER = ? ORDER BY SCORE desc  NULLS LAST";
			  break;
		  case "finale":
			  sql="SELECT * FROM LISTE_FINALE_ETUDIANT_v WHERE ID_MASTER = ? ORDER BY SCORE desc  NULLS LAST";
			  break;
		  case "attente":
			  sql="SELECT * FROM LISTE_ATTENTE_ETUDIANT_v WHERE ID_MASTER = ? ORDER BY SCORE desc  NULLS LAST";
			  break;
		  case "tmp_preselection":
			  sql="SELECT * FROM LISTE_TMP_PRESELECTION_v WHERE ID_MASTER = ? ORDER BY SCORE desc  NULLS LAST";
			  break;
		  case "tmp_finale":
			  sql="SELECT * FROM   LISTE_TMP_FINALE_ETUDIANT_v  WHERE ID_MASTER = ? ORDER BY SCORE desc  NULLS LAST";
			  break;
		  case "tmp_attente":
			  sql="SELECT * FROM   LISTE_TMP_ATTENTE_ETUDIANT_v  WHERE ID_MASTER = ? ORDER BY SCORE desc  NULLS LAST";
			  break;
		  case "tmp_convocation":
			  sql="SELECT * from tmp_convocation_v where id_master=? and id_inscription not in (select id_inscription from convocation) order by score desc  NULLS LAST";
			  break;
		  case "rejected":
			  sql="SELECT * from LISTE_Rejected_v where id_master=? ";
			  break;
		  case "convocation":
			  sql="SELECT * from convocation_v where id_master=? and id_inscription not in (select id_inscription from LISTE_TMP_FINALE_ETUDIANT_v) order by score desc NULLS LAST";
			  break;
		  case "convocationR":
			  sql="SELECT * from convocation_v where id_master=? and id_inscription  in (select id_inscription from LISTE_TMP_FINALE_ETUDIANT_v) order by score desc NULLS LAST";
			  break;
		  case "convocationF":
			  sql="SELECT * from convocation_v where id_master=? and id_inscription not in (select id_inscription from LISTE_TMP_FINALE_ETUDIANT_v) order by score desc NULLS LAST";
			  break;
		  default: 
			  
        }
	    try {
	        connexion = OraFactory.getConnection();
	        ps = connexion.prepareStatement(sql);
	        ps.setInt(1, id_master);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            etudiantListe = new EtudiantListe();
	            etudiantListe.setId(rs.getInt("ID_ETUDIANT"));
	            etudiantListe.setNom(rs.getString("NOM"));
	            etudiantListe.setPrenom(rs.getString("PRENOM"));
	            etudiantListe.setCin(rs.getString("CIN"));
	            etudiantListe.setMassar(rs.getString("MASSAR"));
	           if(ListType.equals("preselection") || ListType.equals("tmp_finale") || ListType.equals("tmp_attente")  || ListType.equals("attente") || ListType.equals("finale") || ListType.equals("convocation") || ListType.equals("tmp_convocation") || ListType.equals("tmp_preselection")) {
	        	   etudiantListe.setScore(rs.getDouble("SCORE"));
	           }
	            etudiantsListe.add(etudiantListe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (rs != null) {rs.close();}
		        if (ps != null) { ps.close(); }
		        if (connexion != null) {connexion.close();}
	        } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return etudiantsListe;
	}
	
	@Override 
	public boolean deleteMaster(int id_master) {
		 Connection connexion = null;
		 PreparedStatement ps=null;
		 int rowAffected; 
		 boolean isDelete=false;
		 try {
			connexion = OraFactory.getConnection();
	        String sql = "DELETE FROM MASTER_CARD_V WHERE ID_MASTER = ?";
	        ps = connexion.prepareStatement(sql);
	        ps.setInt(1,id_master);
	        rowAffected=ps.executeUpdate();
	        if(rowAffected>0) {
	        	isDelete=true;
	        }
			}catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (ps != null) {
		            try {
		                ps.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (connexion != null) {
		            try {
		                connexion.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        
		    }
		 return isDelete;
	}
	@Override 
	public MasterConditions getMasterConditionByMasterId(int id_master) {
		Connection connexion = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    MasterConditions condition=null;
	    String sql="select ID_CONDITION, MAX_AGE, MAX_ANNEE_ETUDE, NOTE_MIN_SEMESTRE, NOTE_SEUIL from master_condition_v where id_master=?";
	    try {
	        connexion = OraFactory.getConnection();
	        ps = connexion.prepareStatement(sql);
	        ps.setInt(1, id_master);
	        rs = ps.executeQuery();
	        if(rs.next()) {
	        	condition=new MasterConditions();
	        	condition.setId_condition(rs.getInt(1));
	        	condition.setMax_age(rs.getInt(2));
	        	condition.setMax_annee_etude(rs.getInt(3));
	        	condition.setNote_min_semestre(rs.getDouble(4));
	        	condition.setNote_seuil(rs.getDouble(5));
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return condition;
	}
	@Override 
	public int getIdInscription(int id_etudiant,int id_master) {
		Connection connexion = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    String sql="select ID_INSCRIPTION  from INSCRIPTION where id_etudiant=? and id_master=?";
	    int id_inscri=-1;
	    try {
	        connexion = OraFactory.getConnection();
	        ps = connexion.prepareStatement(sql);
	        ps.setInt(1, id_etudiant);
	        ps.setInt(2, id_master);
	        rs = ps.executeQuery();
	        if(rs.next()) {
	        	id_inscri=rs.getInt(1);
	        }
        }catch (SQLException e) {
		        e.printStackTrace();
        } finally {
	        if (ps != null) {
	            try {
                ps.close();
	            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (connexion != null) {
		            try {
		                connexion.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
	    }
	    return id_inscri;
	}
	
	@Override 
	public boolean AddPreInscriprion(int id_etudiant,int id_master ) {
		int id_insc=getIdInscription(id_etudiant,id_master);
		Connection connexion = null;
		int rowAffected=0;
		boolean isAdded=false;
		PreparedStatement ps = null;
	    String sql="insert into TMP_Preselection (ID_PS,ID_INSCRIPTION,id_master) values (SQC_PRESELECTION.nextval,?,?)";
	    try {
	        connexion = OraFactory.getConnection();
	        ps = connexion.prepareStatement(sql);
	        ps.setInt(1, id_insc);
	        ps.setInt(2, id_master);
	        rowAffected = ps.executeUpdate();
	        if(rowAffected>0) {
	        	isAdded=true;
	        }
	    	
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null) {
	            try {
	            ps.close();
	            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (connexion != null) {
		            try {
		                connexion.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
	    }
	    return isAdded;
	}
	@Override
	public boolean deleteFromList(int id_etudiant,int id_master,String listType )  {	
		int id_insc=getIdInscription(id_etudiant,id_master);
		Connection connexion = null;
		int rowAffected=0;
		boolean isDeleted=false;
		PreparedStatement ps = null;

		 String sql="";
		switch(listType) {
		  case "inscription":
			  sql="insert into rejected values(?)";
			  break;
			  
		  case "preselection":
			  sql="delete from  PRESELECTION where id_inscription=?";
			  break;
		  case "finale":
			  
			  sql="delete from  LISTE_FINALE where id_inscription=?";
			  break;
		  case "attente":
			  sql="delete from  LISTE_ATTENTE where id_inscription=?";
			  break;
		  case "tmp_preselection":
			  sql="delete from   TMP_PRESELECTION where id_inscription=?";
			  break;
		  case "tmp_finale":
			  sql="delete from  TMP_LISTE_FINALE where id_inscription=?";
			  break;
		  case "tmp_attente":
			  sql="delete from  TMP_LISTE_ATTENTE where id_inscription=?";
			  break;
		  case "tmp_convocation":
			  sql="delete from  tmp_convocation where id_inscription=?";
			  break;
		  case "convocation":
			  sql="delete from  convocation where id_inscription=?";
			  break;
			  

		  default: 
			  break;
		}
	    try {
	        connexion = OraFactory.getConnection();
	        	if(listType.equals("preselection")) {
	        		if(deleteFromList(id_etudiant,id_master,"tmp_finale")) {
	        		}else {
	        			deleteFromList(id_etudiant,id_master,"tmp_attente");
	        		}
	        	}else if(listType.equals("tmp_finale") || listType.equals("convocation") ) {
	        			ResultSet rs=null;
	        			int id_etdAtt=0;
	        			PreparedStatement ps2 = null;
	        			PreparedStatement ps3 = null;

	        			String sql2="\r\n"
	        					+ "SELECT id_etudiant \r\n"
	        					+ "from LISTE_TMP_ATTENTE_ETUDIANT_V \r\n"
	        					+ "where id_master=?\r\n"
	        					+ "order by score desc NULLS LAST FETCH FIRST  ROW ONLY";
	        			ps2 = connexion.prepareStatement(sql2);
	        			ps2.setInt(1, id_master);
	    	 	        rs = ps2.executeQuery();
	        		
	    	 	        if(rs.next()) {
	    	 	        	id_etdAtt=rs.getInt("id_etudiant");
	    	 	        
		    	 	        	if(id_etdAtt!=0) {
			    	 	        	int id_inscForConv = getIdInscription(id_etdAtt,id_master);
			    	 	        	String sql3="insert into tmp_convocation values (SQC_convocation.nextval,?)";
				        			ps3 = connexion.prepareStatement(sql3);
				        			ps3.setInt(1, id_inscForConv);
				        			rowAffected=ps3.executeUpdate();
				        			if(rowAffected>0) {
				        		      	deleteFromList(id_etdAtt,id_master,"tmp_attente");
				        			}
		    	 	        	}
	    	 	        }
		    	 	        
		    	 	   	try {
	        				if (rs != null)  { rs.close();} 
	        				if (ps2 != null)  { ps2.close();} 
	        				if (ps3 != null)  { ps3.close();} 
	        	    	}catch (SQLException e) { e.printStackTrace();}
		    	 	   	
		        	}
	        	
	        	ps = connexion.prepareStatement(sql);
	 	        ps.setInt(1, id_insc);
	 	        rowAffected = ps.executeUpdate();
	        	if(rowAffected>0) {isDeleted=true;}
	        	
	    	
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps != null)  { ps.close();} 
		        
			    if (connexion != null) {connexion.close();}		       
	    	}catch (SQLException e) { e.printStackTrace();}
        }
	    return isDeleted;
    }
	@Override 
	public boolean publierPreselection(int id_master,String titre,String sous_titre) {
		Connection connexion = null;
		int rowAffected=0;
		boolean isAdded=false;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
	    String sql1="insert into PRESELECTION (ID_PS, ID_INSCRIPTION, SCORE) \r\n"
	    		+ "    select ID_PS, ID_INSCRIPTION, SCORE \r\n"
	    		+ "    from TMP_Preselection \r\n"
	    		+ "    where ID_MASTER = ?";
	    String sql2="insert into publication values (\r\n"
	    		+ "SQC_PUBLICATION.nextval,?,to_date(sysdate,'dd-mm-yy'),'PRE',? , ?\r\n"
	    		+ ")";
	    try {
	        connexion = OraFactory.getConnection();
	        ps1 = connexion.prepareStatement(sql1);
	        ps1.setInt(1, id_master);
	        rowAffected = ps1.executeUpdate();
	        if(rowAffected>0) {
		        ps2 = connexion.prepareStatement(sql2);
		        ps2.setInt(1, id_master);
		        ps2.setString(2, titre);
		        ps2.setString(3, sous_titre);
		        rowAffected = ps2.executeUpdate();
		        if(rowAffected>0)
	        	isAdded=true;
	        }
	    	
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	     
	            try {
	               if (ps1 != null) {
	            	   ps1.close();
	               }
	               if (ps2 != null) {
	            	   ps2.close();
	               }
	               if (connexion != null) {

		                connexion.close();
	                }
		           
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	    }
	    return isAdded;	
	}
	
	@Override
	public boolean saveScore(double score,int id_etudiant,int id_master) {
		boolean isSaved=false;
		Connection connexion = null;
		int rowAffected=0;
		int id_insc=getIdInscription(id_etudiant,id_master);
		if(id_insc==0) {return false;}
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
	    String sql1="update PRESELECTION set score=? where id_inscription=?";
	    String sql2="update TMP_PRESELECTION set score=? where id_inscription=?";
	    try {
	        connexion = OraFactory.getConnection();
	        ps1 = connexion.prepareStatement(sql1);
	        ps1.setDouble(1, score);
	        ps1.setInt(2, id_insc);
	        rowAffected = ps1.executeUpdate();
	        if(rowAffected>0) {
	        	ps2 = connexion.prepareStatement(sql2);
	        	ps2.setDouble(1, score);
	        	ps2.setInt(2, id_insc);
	 	        rowAffected = ps2.executeUpdate();
	 	        if(rowAffected>0)
	        	isSaved=true;
	        }
	    	
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps1 != null) {ps1.close(); }
		        if (ps2 != null) {ps1.close(); }
		        if (connexion != null) { connexion.close();}
	    	}catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    return isSaved;
	}
	@Override
	//selectioné les top 60 etudiants et inserer 30 30 liste finale , liste att
	public boolean generateListeFA(int id_master ,int Nfinale,int Nattente ) {
		boolean areGenerated=false;
		Connection connexion = null;
		int rowAffected=0;
		PreparedStatement ps0 = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		String sql0="delete from TMP_LISTE_FINALE WHERE id_master=?";
		String sql1="delete from TMP_LISTE_ATTENTE WHERE id_master=?";
		//sql1 to insert in 30 etudiant dans liste finale 
	    String sql2="    INSERT INTO TMP_LISTE_FINALE \r\n"
	    		+ "    SELECT SQC_liste_finale.nextval, ID_INSCRIPTION,id_master\r\n"
	    		+ "    from(\r\n"
	    		+ "        SELECT ID_INSCRIPTION,id_master\r\n"
	    		+ "        FROM PRESELECTION_ETUDIANT_MASTER_V\r\n"
	    		+ "        where id_master =?\r\n"
	    		+ "        ORDER BY score DESC  NULLS LAST\r\n"
	    		+ "        FETCH FIRST ? ROWS ONLY\r\n"
	    		+ "    )";
		//sql2 to insert in 30 etudiant dans liste ATTente 
	    String sql3=" INSERT INTO TMP_LISTE_ATTENTE\r\n"
	    		+ "    SELECT SQC_LISTE_ATTENTE.nextval, ID_INSCRIPTION ,id_master\r\n"
	    		+ "    FROM (\r\n"
	    		+ "        SELECT ID_INSCRIPTION,id_master\r\n"
	    		+ "        FROM PRESELECTION_ETUDIANT_MASTER_V\r\n"
	    		+ "        where id_master =? AND ID_INSCRIPTION not in (SELECT id_inscription from TMP_LISTE_FINALE where id_master=?)\r\n"
	    		+ "        ORDER BY score DESC  NULLS LAST\r\n"
	    		+ "        FETCH FIRST ? ROWS ONLY\r\n"
	    		+ "    )";
	    try {
	        connexion = OraFactory.getConnection();
	        ps0=connexion.prepareStatement(sql0);
	        ps0.setInt(1, id_master);
	        ps0.executeUpdate();
	        
	        ps1=connexion.prepareStatement(sql1);
	        ps1.setInt(1, id_master);
	        ps1.executeUpdate();
	        
	        ps2 = connexion.prepareStatement(sql2);
	        ps2.setDouble(1, id_master );
	        ps2.setDouble(2, Nfinale );
	        rowAffected = ps2.executeUpdate();
	        if(rowAffected>0) {
	        	ps3 = connexion.prepareStatement(sql3);
		        ps3.setDouble(1, id_master );
		        ps3.setDouble(2, id_master );
		        ps3.setDouble(3, Nattente );
		        rowAffected = ps3.executeUpdate();
		        areGenerated=true;
	        }


	        
	    	
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps0 != null) {ps0.close();}
		        if (ps1 != null) {ps1.close();}
		        if (ps3 != null) {ps3.close();}
		        if (ps2 != null) {ps2.close();}
		        if (connexion != null) {connexion.close();}
	        } catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    return areGenerated;
	}
	@Override
	public int getSizeOf(int id_master,String listType) {
		int size=-1;
		Connection connexion = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		String sql="";
		switch(listType) {
		  case "tmp_finale":
			  sql="SELECT count(*) FROM TMP_LISTE_FINALE where id_master=?";
			  break;
		  case "tmp_attente":
			  sql="SELECT count(*) FROM TMP_LISTE_ATTENTE where id_master=?";
			  break;
		  default:
		} 
		try {
			connexion = OraFactory.getConnection();
			ps=connexion.prepareStatement(sql);
			ps.setInt(1, id_master);
			rs=ps.executeQuery();
			if(rs.next()) {
				size=rs.getInt(1);
			}
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (connexion != null) {connexion.close();}
	    		if (ps != null) {ps.close();}
	    		if (rs != null) {rs.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    	
	    }
		return size;
	}
	@Override
	public boolean publierListeFA(int id_master,String titre,String sous_titre) {
		Connection connexion = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		String sql1="insert into LISTE_FINALE select ID_LF,ID_INSCRIPTION from TMP_LISTE_FINALE where id_master=?";
		String sql2="insert into LISTE_ATTENTE select ID_LA,ID_INSCRIPTION from TMP_LISTE_ATTENTE where id_master=?";
		String sql3="insert into publication values (\r\n"
					+ "SQC_PUBLICATION.nextval,?,to_date(sysdate,'dd-mm-yy'),'LFA',?,?\r\n"
					+ ")";
		try {
			connexion = OraFactory.getConnection();
	        ps1=connexion.prepareStatement(sql1);
	        ps1.setInt(1, id_master);
	        ps1.executeUpdate();
	        ps2=connexion.prepareStatement(sql2);
	        ps2.setInt(1, id_master);
	        ps2.executeUpdate();
	        ps3=connexion.prepareStatement(sql3);
	        ps3.setInt(1, id_master);
	        ps3.setString(2, titre);
	        ps3.setString(3, sous_titre);
	        ps3.executeUpdate();
	        
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps1 != null) {ps1.close();}
		        if (ps2 != null) {ps2.close();}
		        if (ps3 != null) {ps3.close();}
		        if (connexion != null) {connexion.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } return true;
	}
	@Override
	public boolean convocate(int id_master,String message,int status) {
		Connection connexion = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		boolean areConvocated=false;
		int rowAffected=0;
		String sql="insert into convocation  (\r\n"
				+ "SELECT id_convocation,id_inscription \r\n"
				+ "from tmp_convocation_v \r\n"
				+ "where id_master=? \r\n"
				+ "and id_inscription not in (select id_inscription from convocation_v ) )";
		
		String sql2="insert into publication values (\r\n"
	    		+ "SQC_PUBLICATION.nextval,?,to_date(sysdate,'dd-mm-yy'),?,? ,null\r\n"
	    		+ ")";
		try {
			connexion = OraFactory.getConnection();
	        ps=connexion.prepareStatement(sql);
	        ps.setInt(1, id_master);
	        rowAffected=ps.executeUpdate();
	        if(rowAffected>0) {
		        ps2 = connexion.prepareStatement(sql2);
		        ps2.setInt(1, id_master);
		        ps2.setString(2, "co"+(status-1));
		        ps2.setString(3, message);
		        rowAffected = ps2.executeUpdate();
		        if(rowAffected>0)
		        	areConvocated=true;
	        }
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps != null) {ps.close();}
		        if (connexion != null) {connexion.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } 
		return areConvocated;
	}
	
	@Override
	public String getEmailEtudiantByIdEtudiant(int id_etudiant) {
		Connection connexion = null;
		PreparedStatement ps = null;
		String email="";
		ResultSet rs=null;
		String sql="select email from etudiant where id_etudiant=?";

		try {
			connexion = OraFactory.getConnection();
	        ps=connexion.prepareStatement(sql);
	        ps.setInt(1, id_etudiant);
	        rs=ps.executeQuery();
	        if(rs.next()) {
	        	email=rs.getString("email");
	        }
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps != null) {ps.close();}
		        if(rs != null) {rs.close();}
		        if (connexion != null) {connexion.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } 
		return email;
	}
	@Override
	public boolean ajouteAuListFinale(int id_etudiant,int id_master) {
		int id_insc = getIdInscription(id_etudiant,id_master);
		Connection connexion = null;
		PreparedStatement ps = null;
		int rowAffected=0;
		boolean isAdded=false;
		String sql ="INSERT INTO TMP_liste_finale values (SQC_LISTE_FINALE.nextval,?,?)";
		try {
			connexion = OraFactory.getConnection();
	        ps=connexion.prepareStatement(sql);
	        ps.setInt(1, id_insc);
	        ps.setInt(2, id_master);
	        rowAffected=ps.executeUpdate();
	        if(rowAffected>0) {
	        	isAdded=true;
	        }
        }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps != null) {ps.close();}
		        if (connexion != null) {connexion.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } 
		return isAdded;
	}
	@Override
	public boolean terminerMaster(int id_master) {
		Connection connexion = null;
		PreparedStatement ps = null;
		int rowAffected=0;
		boolean isEnded=false;
		String sql ="insert into publication values (\r\n"
	    		+ "SQC_PUBLICATION.nextval,?,to_date(sysdate,'dd-mm-yy'),'FIN',null , null\r\n"
	    		+ ")";
		try {
			connexion = OraFactory.getConnection();
	        ps=connexion.prepareStatement(sql);
	        ps.setInt(1, id_master);
	        rowAffected=ps.executeUpdate();
	        if(rowAffected>0) {
	        	isEnded=true;
	        }
        }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps != null) {ps.close();}
		        if (connexion != null) {connexion.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } 
		return isEnded;
	}
	@Override
	public Publication getPublication(int id_master,String type_pub) {
		Connection connexion = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Publication pub=new Publication();
		String sql ="select * from Publication_v where id_master=? and type_pub=?";
		try {
			connexion = OraFactory.getConnection();
	        ps=connexion.prepareStatement(sql);
	        ps.setInt(1, id_master);
	        ps.setString(2, type_pub);
	        rs=ps.executeQuery();
	        if(rs.next()) {
	        	pub.setId_publication(rs.getInt("id_publication"));
	        	pub.setId_master(rs.getInt("id_master"));
	        	pub.setType_pub(rs.getString("type_pub"));
	        	pub.setDate_publication(rs.getString("date_publication"));
	        	pub.setTitre(rs.getString("titre"));
	        	pub.setSous_titre(rs.getString("sous_titre"));
	        	pub.setNom_uni(rs.getString("NOM_UNI"));
	        	pub.setNom_fac(rs.getString("NOM_FAC"));
	        	pub.setVille(rs.getString("VILLE"));
	        	pub.setLogo_uni(rs.getString("LOGO_UNI"));
	        }
        }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	try {
		        if (ps != null) {ps.close();}
		        if (rs != null) {rs.close();}
		        if (connexion != null) {connexion.close();}
	    	}catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } 
		return pub;
	}
}