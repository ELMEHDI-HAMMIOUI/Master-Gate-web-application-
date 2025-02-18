package master.dao.imp;

import java.io.IOException;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import master.beans.Etudiant;
import master.beans.Facultes;
import master.beans.Inscription;
import master.beans.exception.EtudiantBeansException;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.EtudiantDao;

public class EtudiantDaoImp implements EtudiantDao {

	//if the etudiant exist then true
	public boolean checkEtudiant(Etudiant e) throws EtudiantDaoException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		//int idEtudiant = -1;
		boolean isThere = false;
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
			pst = conn.prepareStatement("SELECT id_etudiant FROM etudiant where email LIKE ? OR cin LIKE ? OR massar LIKE ?");
			pst.setString(1, e.getEmail());
			pst.setString(2, e.getCin());
			pst.setString(3, e.getMassar());
			
			rs = pst.executeQuery();
			//if any record was found then the etudiant already exist
			
				if(rs.next()) isThere = true;

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

		return isThere;

	}


public void addEtudiant(Etudiant e) throws EtudiantDaoException {
	Connection conn = null;
	PreparedStatement pst = null;
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	try {

		//pst = conn.prepareStatement("INSERT INTO Etudiant_Info_insertion (NOM, PRENOM, D_NAISS, SEXE, TELE, EMAIL, PASSWORD, NATIONALITE, CIN, MASSAR, D_LICENCE, D_BAC, FIL_BAC, MOY_LICENCE, MOY_BAC, N_S1, N_S2, N_S3, N_S4, N_S5, N_S6, DOCUMENT, PHOTO, ID_FIL_LICENCE, ID_FACULTE, d_pr_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		pst = conn.prepareStatement("INSERT INTO Etudiant_Info_insertion (NOM, PRENOM, D_NAISS, SEXE, TELE, EMAIL, PASSWORD, NATIONALITE, CIN, MASSAR, D_LICENCE, D_BAC, FIL_BAC, MOY_LICENCE, MOY_BAC, N_S1, N_S2, N_S3, N_S4, N_S5, N_S6, PHOTO, ID_FIL_LICENCE, ID_FACULTE, d_pr_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		String newPassword = e.getPassword();
		// Hash the password
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        
		pst.setString( 1, e.getNom().toUpperCase() );
		pst.setString( 2, e.getPrenom().toUpperCase() );
		pst.setDate(3, java.sql.Date.valueOf(e.getDateNaissance()));
		pst.setString(4, e.getSexe());
		pst.setString(5, e.getTel());
		pst.setString(6, e.getEmail());
		pst.setString(7, hashedPassword);
		pst.setString(8, e.getNationalite());
		pst.setString(9, e.getCin());
		pst.setString(10, e.getMassar());
		//remplissage des info accademiques
		pst.setDate(11, java.sql.Date.valueOf(e.getDateGraduation()));//D_LICENCE
		pst.setDate(12, java.sql.Date.valueOf(e.getDateBac()));
		pst.setString(13, e.getFilBac());
		pst.setFloat(14, e.getMoyLicense());
		pst.setFloat(15, e.getMoyBac());
		pst.setFloat(16, e.getNoteS1());
		pst.setFloat(17, e.getNoteS2());
		pst.setFloat(18, e.getNoteS3());
		pst.setFloat(19, e.getNoteS4());
		pst.setFloat(20, e.getNoteS5());
		pst.setFloat(21, e.getNoteS6());
		//insertion des fichiers
		try {
//	        pst.setBinaryStream(22, e.getDocs().getInputStream(), (int)  e.getDocs().getSize());
			pst.setBinaryStream(22, e.getPhoto().getInputStream(), (int)  e.getPhoto().getSize());
		}catch(IOException io) {
			throw new EtudiantDaoException("Un problème est survenu lors de la lecture des fichiers");
		}
	    //suite
	    pst.setInt(23, e.getIdFilLicense());
		pst.setInt(24, e.getIdFaculte());
		pst.setDate(25, java.sql.Date.valueOf(e.getDatePremiereInscription()));

		int rowsAffected = pst.executeUpdate() ;
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
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
			
		}	
			
		//si tout est passé correctement alors valider la transaction, fermer la connection 
		try {
			conn.commit();
		}catch(SQLException sqe6) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}
		
	}catch(SQLException sqe7) {
		sqe7.printStackTrace();
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}			
	}

	
}


public Etudiant getProfileById(int etudiantId) throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Etudiant e = new Etudiant();
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base dans getProfile");
	}
	
	try {
		pst = conn.prepareStatement("SELECT * FROM profile WHERE id_etudiant = ?");
		pst.setInt(1, etudiantId);
		//get the result
		rs = pst.executeQuery();
		rs.next();
		try {
			e.setId(rs.getInt("ID_ETUDIANT"));
			e.setNom(rs.getString("NOM"));
			e.setPrenom(rs.getString("PRENOM"));
			e.setDateNaissance(rs.getString("D_NAISS"));
			e.setSexe(rs.getString("SEXE"));
			e.setTel(rs.getString("TELE"));//tel in database should be int(number)
			e.setEmail(rs.getString("EMAIL"));
			e.setPassword(rs.getString("PASSWORD"));
			e.setNationalite(rs.getString("NATIONALITE"));
			e.setCin(rs.getString("CIN"));
			e.setMassar(rs.getString("MASSAR"));
			e.setDateGraduation(rs.getString("D_LICENCE"));
			e.setDateBac(rs.getString("D_BAC"));
			e.setFilBac(rs.getString("FIL_BAC"));
			e.setMoyLicense(rs.getFloat("MOY_LICENCE"));
			e.setMoyBac(rs.getFloat("MOY_BAC"));
			e.setNoteS1(rs.getFloat("N_S1"));
			e.setNoteS2(rs.getFloat("N_S2"));
			e.setNoteS3(rs.getFloat("N_S3"));
			e.setNoteS4(rs.getFloat("N_S4"));
			e.setNoteS5(rs.getFloat("N_S5"));
			e.setNoteS6(rs.getFloat("N_S6"));
			//on va skipper le document et la photo puisqu'on va creer une servlette specifique à ceci
			e.setFilLicenseNom(rs.getString("NOM_FIL"));
			e.setFaculteNom(rs.getString("NOM_FAC"));
			e.setDatePremiereInscription(rs.getString("d_pr_inscription"));
		}catch(EtudiantBeansException ebe) {
			throw new EtudiantDaoException("Un problème est survenu lors du retreive des donnés pour le profil");
	
		}
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
	
	return e;
	
}


//return the idEtudiant if it exist and -1 if not (it's used for login: checking email and password)
public int verifyLogin(Etudiant e) throws EtudiantDaoException {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int idEtudiant = -1;

    try {
        conn = OraFactory.getConnection();
        conn.setAutoCommit(false);
    } catch (SQLException sqe) {
        throw new EtudiantDaoException("Un problème est survenu lors de la connexion avec la Base");
    }

    try {
        // Query to fetch id_etudiant and hashed password
        pst = conn.prepareStatement("SELECT id_etudiant, password FROM etudiant WHERE email = ?");
        pst.setString(1, e.getEmail());
        rs = pst.executeQuery();
        
        if (rs.next()) {
            // Retrieve hashed password from database
            String hashedPasswordFromDB = rs.getString("password");
            
            // Verify password using BCrypt
            if (BCrypt.checkpw(e.getPassword(), hashedPasswordFromDB)) {
                idEtudiant = rs.getInt("id_etudiant");
            }
        }
    } catch (SQLException sqe) {
        throw new EtudiantDaoException("Un problème est survenu lors de la récupération des données depuis la Base");
    } finally {
        // Close connections
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException sqe) {
            throw new EtudiantDaoException("Un problème est survenu lors de la fermeture des connexions");
        }
    }

    return idEtudiant;
}
//get the inscription details
public Inscription getInsc(int idEtudiant, int idMaster) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Inscription in = new Inscription();
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
		pst = conn.prepareStatement(" SELECT ID_ETUDIANT, ID_MASTER, ID_INSCRIPTION, to_char(D_INSCRIPTION,'DD MON YYYY') \"D_INSCRIPTION\" FROM inscription WHERE id_etudiant = ? AND id_master = ?");
		pst.setInt(1, idEtudiant);
		pst.setInt(2, idMaster);
		
		rs = pst.executeQuery();
		
			if(rs.next()) {
				in.setIdEtudiant(rs.getInt("ID_ETUDIANT"));
				in.setIdMaster(rs.getInt("ID_MASTER"));
				in.setIdInsc(rs.getInt("ID_INSCRIPTION"));
				in.setDateInsc(rs.getString("D_INSCRIPTION"));
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


	return in;

}


public String storeToken(int idEtudiant) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String token = UUID.randomUUID().toString();
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
		pst = conn.prepareStatement("INSERT INTO etudiant_tokenmap(TOKEN, ID_ETUDIANT) VALUES(?, ?)");
		pst.setString(1, token);
		pst.setInt(2, idEtudiant);
		
		int rowsAffected = pst.executeUpdate();
		
		if(rowsAffected < 1) {
			//annuler la transaction
			try {
				if(conn != null)  conn.rollback();
			}catch(SQLException sqe4) {}
			
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
			}catch(SQLException sqe5) {
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
			
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

	System.out.print(token);
	return token;

}

public int getEtudiantIdFromToken(String token) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int idEtudiant = 0;
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
		pst = conn.prepareStatement(" SELECT ID_ETUDIANT FROM etudiant_tokenmap WHERE token = ?");
		pst.setString(1, token);
		
		rs = pst.executeQuery();
		
		if(rs.next()) {
			idEtudiant = rs.getInt("ID_ETUDIANT");
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


	return idEtudiant;

}

public void deleteTokenMapEntry(String token) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
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
		pst = conn.prepareStatement("DELETE FROM etudiant_tokenmap WHERE token = ?");
		pst.setString(1, token);
		
		int rowsAffected = pst.executeUpdate();
		
		if(rowsAffected < 1) {
			//annuler la transaction
			try {
				if(conn != null)  conn.rollback();
			}catch(SQLException sqe4) {}
			
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
			}catch(SQLException sqe5) {
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
			
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


}



public void deleteEtudiant(int idEtudiant) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
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
		pst = conn.prepareStatement(" DELETE FROM etudiant WHERE id_etudiant = ?");
		pst.setInt(1, idEtudiant);
		
		int rowsAffected = pst.executeUpdate() ;
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
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
			
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

}

//update all etudiant info (the etudiant bean must have the idEtudiant, because im updating the column with the idEtudiant)
public void updateEtudiantAll(Etudiant e) throws EtudiantDaoException {
	Connection conn = null;
	PreparedStatement pst = null;
	PreparedStatement ppst = null;

	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	try {

		//pst = conn.prepareStatement("INSERT INTO Etudiant_Info_insertion (NOM, PRENOM, D_NAISS, SEXE, TELE, EMAIL, PASSWORD, NATIONALITE, CIN, MASSAR, D_LICENCE, D_BAC, FIL_BAC, MOY_LICENCE, MOY_BAC, N_S1, N_S2, N_S3, N_S4, N_S5, N_S6, DOCUMENT, PHOTO, ID_FIL_LICENCE, ID_FACULTE, d_pr_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		pst = conn.prepareStatement("UPDATE etudiant SET NOM = ?, PRENOM = ?, D_NAISS = ?, SEXE = ?, TELE = ?, EMAIL = ?, "
				+ "PASSWORD = ?, NATIONALITE = ?, CIN = ?, MASSAR = ?  WHERE id_etudiant = ?" );
		ppst = conn.prepareStatement("UPDATE Info_academique SET D_LICENCE = ?, D_BAC = ?, FIL_BAC = ?, "
				+ "MOY_LICENCE = ?, MOY_BAC = ?, N_S1 = ?, N_S2 = ?, N_S3 = ?, N_S4 = ?, N_S5 = ?, N_S6 = ?, "
				+ "PHOTO = ?, ID_FIL_LICENCE = ?, ID_FACULTE = ?, d_pr_inscription = ? WHERE id_etudiant = ?");

		String newPassword = e.getPassword();
		// Hash the password
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        
		pst.setString( 1, e.getNom().toUpperCase() );
		pst.setString( 2, e.getPrenom().toUpperCase() );
		pst.setDate(3, java.sql.Date.valueOf(e.getDateNaissance()));
		pst.setString(4, e.getSexe());
		pst.setString(5, e.getTel());
		pst.setString(6, e.getEmail());
		pst.setString(7, hashedPassword);
		pst.setString(8, e.getNationalite());
		pst.setString(9, e.getCin());
		pst.setString(10, e.getMassar());
		pst.setInt(11, e.getId());

		//remplissage des info accademiques
		pst.setDate(1, java.sql.Date.valueOf(e.getDateGraduation()));//D_LICENCE
		pst.setDate(2, java.sql.Date.valueOf(e.getDateBac()));
		pst.setString(3, e.getFilBac());
		pst.setFloat(4, e.getMoyLicense());
		pst.setFloat(5, e.getMoyBac());
		pst.setFloat(6, e.getNoteS1());
		pst.setFloat(7, e.getNoteS2());
		pst.setFloat(8, e.getNoteS3());
		pst.setFloat(9, e.getNoteS4());
		pst.setFloat(10, e.getNoteS5());
		pst.setFloat(11, e.getNoteS6());
		//insertion des fichiers
		try {
//	        pst.setBinaryStream(22, e.getDocs().getInputStream(), (int)  e.getDocs().getSize());
			pst.setBinaryStream(12, e.getPhoto().getInputStream(), (int)  e.getPhoto().getSize());
		}catch(IOException io) {
			throw new EtudiantDaoException("Un problème est survenu lors de la lecture des fichiers");
		}
	    //suite
	    pst.setInt(13, e.getIdFilLicense());
		pst.setInt(14, e.getIdFaculte());
		pst.setDate(15, java.sql.Date.valueOf(e.getDatePremiereInscription()));

		//the idEtudiant for where clause
		pst.setInt(16, e.getId());

		int rowsAffected = pst.executeUpdate() ;
		int rowsAffected2 = ppst.executeUpdate() ;
		
		//si aucune ligne n'est affecté alors faire un rollback puis fermer la connection et sortir de la methode
		if(rowsAffected < 1 || rowsAffected2 < 1) {
			//annuler la transaction
			try {
				if(conn != null)  conn.rollback();
			}catch(SQLException sqe4) {}
			
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
				if(ppst != null ) ppst.close();

			}catch(SQLException sqe5) {
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
			
		}	
			
		//si tout est passé correctement alors valider la transaction, fermer la connection 
		try {
			conn.commit();
		}catch(SQLException sqe6) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}
		
	}catch(SQLException sqe7) {
		sqe7.printStackTrace();
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
			if(ppst != null ) ppst.close();

		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}			
	}

	
}



//update custom etudiant info,(the email, password, telephone, photo) (the etudiant bean must have the idEtudiant, because im updating the column with the idEtudiant)
public void updateEtudiantCustom(Etudiant e) throws EtudiantDaoException {
	Connection conn = null;
	PreparedStatement pst = null;
	PreparedStatement ppst = null;
	PreparedStatement pst3 = null;

	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	try {

		//pst = conn.prepareStatement("INSERT INTO Etudiant_Info_insertion (NOM, PRENOM, D_NAISS, SEXE, TELE, EMAIL, PASSWORD, NATIONALITE, CIN, MASSAR, D_LICENCE, D_BAC, FIL_BAC, MOY_LICENCE, MOY_BAC, N_S1, N_S2, N_S3, N_S4, N_S5, N_S6, DOCUMENT, PHOTO, ID_FIL_LICENCE, ID_FACULTE, d_pr_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		pst = conn.prepareStatement("UPDATE etudiant SET TELE = ?, EMAIL = ? WHERE id_etudiant = ?");
		pst.setString(1, e.getTel());
		pst.setString(2, e.getEmail());
		pst.setInt(3, e.getId());
	      
		//insertion de photo
		if(e.getPhoto() != null) {
			ppst = conn.prepareStatement("UPDATE Info_academique SET PHOTO = ? WHERE id_etudiant = ?");
			try {
//		        pst.setBinaryStream(22, e.getDocs().getInputStream(), (int)  e.getDocs().getSize());
				ppst.setBinaryStream(1, e.getPhoto().getInputStream(), (int)  e.getPhoto().getSize());
			}catch(IOException io) {
				throw new EtudiantDaoException("Un problème est survenu lors de la lecture des fichiers");
			}
		    
			ppst.setInt(2, e.getId());
		}

		
		
		pst3 = conn.prepareStatement("UPDATE etudiant SET password = ? WHERE id_etudiant = ?");	
		//update password if it not empty
		if(! e.getPassword().isEmpty() ) {
			String newPassword = e.getPassword();
			// Hash the password
			String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());	
			pst3.setString(1, hashedPassword);
			pst3.setInt(2, e.getId());
		}
		
		
		int rowsAffected = pst.executeUpdate() ;
		
		int rowsAffected2 = 1 ;
		if(e.getPhoto() != null) {
			rowsAffected2 = ppst.executeUpdate() ;
		}
		int rowsAffected3 = 1;
		if(! e.getPassword().isEmpty() ) {
			rowsAffected3 = pst3.executeUpdate() ;
		}
		 
		//si aucune ligne n'est affecté alors faire un rollback puis fermer la connection et sortir de la methode
		if(rowsAffected < 1 || rowsAffected2 < 1 || rowsAffected3 < 1) {
			//annuler la transaction
			try {
				if(conn != null)  conn.rollback();
			}catch(SQLException sqe4) {}
			
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
				if(ppst != null ) ppst.close();
				if(pst3 != null ) pst3.close();


			}catch(SQLException sqe5) {
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");					
			}
			
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
			
		}	
			
		//si tout est passé correctement alors valider la transaction, fermer la connection 
		try {
			conn.commit();
		}catch(SQLException sqe6) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}
		
	}catch(SQLException sqe7) {
		sqe7.printStackTrace();
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
			if(ppst != null ) ppst.close();
			if(pst3 != null ) pst3.close();


		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base");
		}			
	}

	
}


//retourne le nombre de masters auxquels un étudiant a été inscrit.
public int getEnrolledMastersNumber(int idEtudiant) throws EtudiantDaoException{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	int num = -1; 
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
		pst = conn.prepareStatement(" SELECT COUNT(*) FROM inscription WHERE id_etudiant = ?");
		pst.setInt(1, idEtudiant);
		
		rs = pst.executeQuery();
		
			if(rs.next()) {
				num = rs.getInt(1);
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


	return num;

}



}


