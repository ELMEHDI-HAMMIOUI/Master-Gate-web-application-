package master.dao.imp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import master.beans.DocsBean;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.DocsDao;
import java.util.UUID;
public class DocsDaoImp implements DocsDao{

	public void addDocs(DocsBean doc) throws EtudiantDaoException {
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

			pst = conn.prepareStatement("INSERT INTO doc (ID_DOC, ID_MASTER, ID_ETUDIANT, DOC) VALUES (?, ?, ?, ?) ");
			pst.setString(1, String.valueOf( UUID.randomUUID() ));
			pst.setInt(2, doc.getIdMaster());
			pst.setInt(3, doc.getIdEtudiant());
			
			//insertion des fichiers
			try {
		        pst.setBinaryStream(4, doc.getDocs().getInputStream(), (int)  doc.getDocs().getSize());
			}catch(IOException io) {
				throw new EtudiantDaoException("Un problème est survenu lors de la lecture du document");
			}


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

}
