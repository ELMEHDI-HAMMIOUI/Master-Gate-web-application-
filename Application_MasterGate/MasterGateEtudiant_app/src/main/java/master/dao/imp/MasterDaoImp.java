package master.dao.imp;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import master.beans.CloseMaster;
import master.beans.DocsBean;
import master.beans.Etudiant;
//import master.beans.EtudiantListe;
import master.beans.FilieresLicense;
import master.beans.MBoard;
import master.beans.Master;
import master.beans.MasterCard;
import master.beans.MasterConditions;
import master.beans.Responsable;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FilieresLicenseDao;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;
import master.mhdi.pub.EtudiantListe;
import master.mhdi.pub.Publication;
import master.utils.DatesChecker;

public class MasterDaoImp implements MasterDao{
    
	@Override
	public List<Master> getAllMasters() {
	    
	    return null;
	}

//	@Override
//	public List<Master> getMastersByIdFac(int id_fac) {
//		return null;
//	}

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
	        	condition.setMax_annee_etude(rs.getInt("MAX_ANNEE_ETUDE"));
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
	        	master.setDocsString(rs.getString("DOCS_STRING"));
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
		        String insertMasterCondiionQuery = "INSERT INTO MASTER_CONDITION_V VALUES (?,?,?,?,?,?,?,?,?,?,?,0,?,?,?,?)";
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
	        String sql = "Select * from MASTER_CARD_v where ID_MASTER = ?";
	        ps = connection.prepareStatement(sql);
	        ps.setInt(1,id_master);
	        rs=ps.executeQuery();
	        if(rs.next()) {
	        	masterCard.setId_master(rs.getInt("ID_MASTER"));//
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
	        	if(! DatesChecker.checkDateFinInsc( rs.getString("D_FIN_INSCRIPTION") ) ) {
	        		masterCard.setExpired(true);
	        	}
	        	if( DatesChecker.isMasterNew( rs.getString("D_DEBUT_INSCRIPTION") ) ) {
	        		masterCard.setNewMaster(true);
	        	}
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
	
	//pour recupérer La liste des etudiants (inscris preselectionnés admis du master
	@Override 
	/*public List<EtudiantListe> getMasterEtudiantsListe(int id_master,String ListType) {
	    Connection connexion = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    List<EtudiantListe> etudiantsListe = new ArrayList<EtudiantListe>();
	    EtudiantListe etudiantListe;
        String sql = "";
        switch(ListType) {
		  case "inscription":
			  sql="SELECT * FROM INSCRIPTION_ETUDIANT_MASTER_V WHERE ID_MASTER = ?";
			  break;
			  
		  case "preselection":
			  sql="SELECT * FROM PRESELECTION_ETUDIANT_MASTER_V WHERE ID_MASTER = ?";
			  break;
		  case "finale":
			  
			  sql="SELECT * FROM LISTE_FINALE_ETUDIANT_MASTER_v WHERE ID_MASTER = ?";
			  break;
		  case "attente":
			  sql="SELECT * FROM LISTE_ATTENTE_ETUDIANT_MASTER_v WHERE ID_MASTER = ?";
			  break;
		  default: 
			  sql="SELECT * FROM INSCRIPTION_ETUDIANT_MASTER_V WHERE ID_MASTER = ?";
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
	            etudiantsListe.add(etudiantListe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
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
	    return etudiantsListe;
	}
	*/
	//@Override 
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
	
	//s'inscrire au master

public void inscrire(DocsBean dc) throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe) {
		sqe.printStackTrace();
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");	
	}
	
	//function core
	try {
		//adding docs in docs table
		//OraFactory.getDocsDao().addDocs(dc);
		//adding inscription in inscription table
		//Query things
		pst = conn.prepareStatement("INSERT INTO inscription_v (id_master, id_Etudiant) VALUES(?, ?)");
		pst.setInt(1, dc.getIdMaster());
		pst.setInt(2, dc.getIdEtudiant());
		int rowsAffected = pst.executeUpdate() ;
		System.out.print("rows affected: "+ rowsAffected);
		//si aucune ligne n'est affecté alors faire un rollback puis fermer la connection et sortir de la methode
		if(rowsAffected < 1) {
			//annuler la transaction
			try {
				if(conn != null)  conn.rollback();
			}catch(SQLException sqe4) {}
			
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
			}catch(SQLException sqe5) {
				sqe5.printStackTrace();
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Echec de l'inscription, Un problème est survenu lors de la connection avec la Base");
			
		}	
			
		//si tout est passé correctement alors valider la transaction, fermer la connection 
		try {
			conn.commit();
		}catch(SQLException sqe6) {
			sqe6.printStackTrace();
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}

	}catch(SQLException sqe) {
		sqe.printStackTrace();
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	//close connections
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe) {
			sqe.printStackTrace();
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}			
	}

}

	//this function whill check if the etudiant is already enrolled in the master

public boolean checkEnrollement(int etudiantId, int idMaster) throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base dans getProfile");
	}
	
	try {
		pst = conn.prepareStatement("select id_inscription from inscription where id_master = ? and id_etudiant= ?");
		pst.setInt(1, idMaster);
		pst.setInt(2, etudiantId);
		//get the result
		rs = pst.executeQuery();
		if(rs.next()) return true; //the etudiant is already enrolled
		
	}catch(SQLException sqe7) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base dans getPr");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base dans getP");
		}			
	}
	
	return false;
	
}

//MASTER_CARDS_PAGE_SIZE
	//get all masterCards
public List<MasterCard> getMasterCardsByPage(int pageNumber) {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
    MasterCard masterCard=new MasterCard();
    List<MasterCard> masterCards =new ArrayList<MasterCard>();
    int offset = (pageNumber - 1) * MASTER_CARDS_PAGE_SIZE;
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "SELECT id_master FROM ("
        		+ "    SELECT a.*, ROWNUM rnum FROM ("
        		+ "        SELECT * FROM master_card_v WHERE to_date(d_debut_inscription, 'yyyy-mm-dd')  <= sysdate order by D_FIN_INSCRIPTION asc"
        		+ "    ) a WHERE ROWNUM <= ?"
        		+ ") WHERE rnum > ?" ;
        
        ps = connection.prepareStatement(sql);
        ps.setInt(1, offset + MASTER_CARDS_PAGE_SIZE);
        ps.setInt(2, offset);
        
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

//count number of masters (all masters)
public int getTotalMasterCardsRecords() {
    int totalRecords = 0;
    String sql = "SELECT COUNT(*) FROM master_card_v WHERE to_date(d_debut_inscription, 'yyyy-mm-dd') <= sysdate ";

    try (Connection connection = OraFactory.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            totalRecords = rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalRecords;
}


//getting all masters belonging to a specific etudiant(dont il est inscrit)
public List<MasterCard> getMasterCardsByIdEtudiant(int idEtudiant) {
Connection connection = null;
PreparedStatement ps=null;
ResultSet rs=null;    	
MasterCard masterCard=new MasterCard();
List<MasterCard> masterCards =new ArrayList<MasterCard>();

    try {
    connection = OraFactory.getConnection();
    //String sql = "select id_master from INSCRIPTION_ETUDIANT_MASTER_v where id_etudiant = ?";
    String sql = "select id_master from inscription where id_etudiant = ?";
    ps = connection.prepareStatement(sql);
    ps.setInt(1, idEtudiant);
    
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

//getting mastercards by faculte id
//getting all masters belonging to a specific etudiant
public List<MasterCard> getMasterCardsByFacId(int idFac) {
Connection connection = null;
PreparedStatement ps=null;
ResultSet rs=null;    	
MasterCard masterCard=new MasterCard();
List<MasterCard> masterCards =new ArrayList<MasterCard>();

  try {
  connection = OraFactory.getConnection();
  String sql = "select id_master from master where ID_RESPONSABLE = (select ID_RESPONSABLE from responsable where ID_FACULTE = ?) AND to_date(d_debut_inscription, 'yyyy-mm-dd') <= sysdate";
  ps = connection.prepareStatement(sql);
  ps.setInt(1, idFac);
  
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


public List<MasterCard> searchMasters(String keyword) {
Connection connection = null;
PreparedStatement ps=null;
ResultSet rs=null;    	
MasterCard masterCard=new MasterCard();
List<MasterCard> masterCards =new ArrayList<MasterCard>();

  try {
  connection = OraFactory.getConnection();
  String sql = "select m.ID_MASTER from master m " +
          "join responsable using(id_responsable) " +
          "join faculte using(id_faculte) " +
          "join universite using(id_universite) " +
          "where lower(nom_fac) like ? " +
          "OR lower(SPECIALITE) like ? " +
          "OR lower(NOM_COORDINATEUR) like ? " +
          "OR lower(SURNOM_FAC) like ? " +
          "OR lower(universite.ville) like ? " +
          "OR lower(universite.SURNOM_UNI) like ? " +
          "OR lower(universite.nom_uni) like ? "+
  		  "AND to_date(m.d_debut_inscription, 'yyyy-mm-dd') <= sysdate";

keyword = "%" + keyword.toLowerCase() + "%"; 

  ps = connection.prepareStatement(sql);
  ps.setString(1, keyword);
  ps.setString(2, keyword);
  ps.setString(3, keyword);
  ps.setString(4, keyword);
  ps.setString(5, keyword);
  ps.setString(6, keyword);
  ps.setString(7, keyword);
  
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

//todo: this EtudiantDaoException should be changed to the masterException 
public int getMasterIdFromToken(String token) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int idMaster = 0;
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");	
	}
	
	//function core
	try {
		//Query things
		pst = conn.prepareStatement(" SELECT ID_MASTER FROM master_tokenmap WHERE token = ?");
		pst.setString(1, token);
		
		rs = pst.executeQuery();
		
		if(rs.next()) {
			idMaster = rs.getInt("ID_MASTER");
		}

	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	//close connections
	 finally {
	        // Close the ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException sqe) {
	                throw new EtudiantDaoException("Un problème est survenu lors de la fermeture du ResultSet");
	            }
	        }
	        // Close the PreparedStatement
	        if (pst != null) {
	            try {
	                pst.close();
	            } catch (SQLException sqe) {
	                throw new EtudiantDaoException("Un problème est survenu lors de la fermeture du PreparedStatement");
	            }
	        }
	        // Close the Connection
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException sqe) {
	                throw new EtudiantDaoException("Un problème est survenu lors de la fermeture de la connexion");
	            }
	        }
	    }


	return idMaster;

}

public String getTokenFromIdMaster(int idMaster) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String token = null;
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");	
	}
	
	//function core
	try {
		//Query things
		pst = conn.prepareStatement(" SELECT token FROM master_tokenmap WHERE id_master = ?");
		pst.setInt(1, idMaster);
		
		rs = pst.executeQuery();
		
		if(rs.next()) {
			token = rs.getString("token");
		}

	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	//close connections
	 finally {
	        // Close the ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException sqe) {
	                throw new EtudiantDaoException("Un problème est survenu lors de la fermeture du ResultSet");
	            }
	        }
	        // Close the PreparedStatement
	        if (pst != null) {
	            try {
	                pst.close();
	            } catch (SQLException sqe) {
	                throw new EtudiantDaoException("Un problème est survenu lors de la fermeture du PreparedStatement");
	            }
	        }
	        // Close the Connection
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException sqe) {
	                throw new EtudiantDaoException("Un problème est survenu lors de la fermeture de la connexion");
	            }
	        }
	    }


	return token;

}

		

public List<MasterCard> getAllMasterCards() {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
    MasterCard masterCard=new MasterCard();
    List<MasterCard> masterCards =new ArrayList<MasterCard>();
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "SELECT id_master FROM master_card_v WHERE to_date(d_debut_inscription, 'yyyy-mm-dd')  <= sysdate order by D_FIN_INSCRIPTION asc";
        
        ps = connection.prepareStatement(sql);        
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



//grabber les masterCards que l'etudiant n'ai pas encore inscrit dans eux
public List<MasterCard> getNonInscritMasterCards(int idEtudiant) {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
    MasterCard masterCard=new MasterCard();
    List<MasterCard> masterCards =new ArrayList<MasterCard>();
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "select id_master from master_card_v where id_master not in ("
        			+ " 	select id_master from inscription where id_etudiant = ?"
        			+ " ) AND to_date(d_debut_inscription, 'yyyy-mm-dd')  <= sysdate order by D_FIN_INSCRIPTION asc ";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        
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


//get all masters that are close to date de fin d'inscription 
public List<CloseMaster> getCloseMasters(int idEtudiant) {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
    List<CloseMaster> closemrs =new ArrayList<CloseMaster>();
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "select id_master,"
        		+ "       'Master ' || SURNOM_FAC || ' ' || VILLE \"master_name\", " //master_name
        		+ "       to_char( to_date(D_FIN_INSCRIPTION, 'yyyy-mm-dd'), 'dd mon yyyy' ) \"D_FIN_INSCRIPTION\", " //d_fin_inscription formated
        		+ "       floor( to_date(D_FIN_INSCRIPTION, 'yyyy-mm-dd') - sysdate ) \"time_left\" " //time left
        		+ "       from master_card_v where "
        		+ "            id_master not in (select id_master from inscription where id_etudiant = ?) and " //not inscrit
        		+ "            floor( to_date(D_FIN_INSCRIPTION, 'yyyy-mm-dd') - sysdate ) between 0 and 3 ";//range between 0(today is the last day) and 3 (three days before the date_fin)
        ps = connection.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        
        rs=ps.executeQuery();
        while(rs.next()) {
            CloseMaster cm =new CloseMaster();
        	cm.setIdMaster(rs.getInt("id_master"));
        	cm.setMasterName(rs.getString("master_name"));
        	cm.setDateFin(rs.getString("D_FIN_INSCRIPTION"));
        	cm.setTimeLeft(rs.getInt("time_left"));

        	closemrs.add(cm);
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

	return closemrs;
}


//get leaderBoard informations
public List<MBoard> getBoardInfo(int idEtudiant) {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
    List<MBoard> closemrs =new ArrayList<MBoard>();
    int insc_time_left, pres_time_left, conc_time_left;
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "select 'Master ' || SURNOM_FAC || ' ' || VILLE \"master_name\", "
        		+ "D_INSCRIPTION, id_master, id_ps, id_la, id_lf,"
        		+ "floor( to_date(D_FIN_INSCRIPTION, 'yyyy-mm-dd') - sysdate) \"insc_time_left\", " //temps restants avant la fin d'inscription
        		+ "floor( to_date(D_aff_preselection, 'yyyy-mm-dd') - sysdate) \"pres_time_left\", "//temps restants avant l'affichage de preselection
        		+ "floor( to_date(D_aff_resultat_concours, 'yyyy-mm-dd') - sysdate) \"conc_time_left\" " //temps restants avant l'affichage de concours
        		+ "from inscription left join "
        		+ "master_card_v using(id_master) left join "
        		+ "preselection using(id_inscription) left join "
        		+ "liste_finale using(id_inscription) left join "
        		+ "liste_attente using(id_inscription) "
        		+ "where id_etudiant = ?";
        
        ps = connection.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        
        rs=ps.executeQuery();
        while(rs.next()) {
        	MBoard mb =new MBoard();
        	mb.setIdMaster(rs.getInt("id_master"));
        	mb.setDateInsc(rs.getString("D_INSCRIPTION"));
        	mb.setMasterName(rs.getString("master_name"));
        	mb.setInsc_time_left(rs.getInt("insc_time_left"));
        	mb.setPres_time_left(rs.getInt("pres_time_left"));
        	mb.setConc_time_left(rs.getInt("conc_time_left"));

        	if(rs.getInt("id_ps") == 0) {mb.setPs(false);}
        	else {mb.setPs(true);}
        	if(rs.getInt("id_la") == 0) {mb.setLa(false);}
        	else {mb.setLa(true);}
        	if(rs.getInt("id_lf") == 0) {mb.setLf(false);}
        	else {mb.setLf(true);}
        	
        	closemrs.add(mb);
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

	return closemrs;
}


//get les mastercards dont l'etudiant est preselectioné
public List<MasterCard> getPresMasterCards(int idEtudiant){
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
    MasterCard masterCard=new MasterCard();
    List<MasterCard> masterCards =new ArrayList<MasterCard>();
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "SELECT id_master FROM inscription JOIN preselection USING (id_inscription) WHERE id_etudiant = ?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        
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


//grabber les mastercards dont l'etudiant est affiché dans leur liste finale 
public List<MasterCard> getLfMasterCards(int idEtudiant){
	Connection connection = null;
	PreparedStatement ps=null;
	ResultSet rs=null;    	
	MasterCard masterCard=new MasterCard();
	List<MasterCard> masterCards =new ArrayList<MasterCard>();
  
	  try {
	      connection = OraFactory.getConnection();
	      String sql = "SELECT id_master FROM inscription JOIN liste_finale USING (id_inscription) WHERE id_etudiant = ?";
	      ps = connection.prepareStatement(sql);
	      ps.setInt(1, idEtudiant);
	      
	      rs=ps.executeQuery();
	      while(rs.next()) {
	      	masterCard=getMasterCardById(rs.getInt("ID_MASTER"));
	      	masterCards.add(masterCard);
	      }
      
	  }catch (SQLException e) {
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




//grabber les mastercards dont l'etudiant est affiché dans leur liste d'attente 
public List<MasterCard> getLaMasterCards(int idEtudiant){
	Connection connection = null;
	PreparedStatement ps=null;
	ResultSet rs=null;    	
	MasterCard masterCard=new MasterCard();
	List<MasterCard> masterCards =new ArrayList<MasterCard>();

	  try {
	      connection = OraFactory.getConnection();
	      String sql = "SELECT id_master FROM inscription JOIN liste_attente USING (id_inscription) WHERE id_etudiant = ?";
	      ps = connection.prepareStatement(sql);
	      ps.setInt(1, idEtudiant);
	      
	      rs=ps.executeQuery();
	      while(rs.next()) {
	      	masterCard=getMasterCardById(rs.getInt("ID_MASTER"));
	      	masterCards.add(masterCard);
	      }
    
	  }catch (SQLException e) {
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

//////////////////////////mhdi import publication stuff/////////////////
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


@Override 
public List<EtudiantListe> getMasterEtudiantsListe(int id_master,String ListType) {
    Connection connexion = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    List<EtudiantListe> etudiantsListe = new ArrayList<EtudiantListe>();
    EtudiantListe etudiantListe;
    String sql = "";
    switch(ListType) {
	  case "preselection":
		  sql="SELECT * FROM LISTE_PRESELECTION_v WHERE ID_MASTER = ? ORDER BY SCORE desc";
		  break;
	  case "finale":
		  sql="SELECT * FROM LISTE_FINALE_ETUDIANT_v WHERE ID_MASTER = ? ORDER BY SCORE desc";
		  break;
	  case "attente":
		  sql="SELECT * FROM LISTE_ATTENTE_ETUDIANT_v WHERE ID_MASTER = ? ORDER BY SCORE desc";
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



}
