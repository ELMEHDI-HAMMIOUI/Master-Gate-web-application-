package master.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import master.beans.MComment;
import master.beans.MasterCard;
import master.dao.exception.EtudiantDaoException;
import master.dao.exception.MCommentDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.MCommentDao;

public class MCommentDaoImp implements MCommentDao{
	public List<MComment> getAllComments(int idMaster) throws MCommentDaoException {
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;		
		ResultSet rs = null;
		ResultSet rs2 = null;
	    List<MComment> commentList =new ArrayList<MComment>();
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe1) {
			throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");
		}
		
		//core
		try {
			//query to get comment things
			pst = conn.prepareStatement("SELECT id_comment, id_master, id_etudiant, to_char(created_at, 'DD Mon YYYY') \"created_at\" , txt, nom || ' ' || prenom \"full_name\" FROM  m_comment join etudiant using(id_etudiant) WHERE id_master = ?");
	        pst.setInt(1, idMaster);
			rs=pst.executeQuery();
	        //retrieve data
	        while(rs.next()) {
	    	    MComment comment =new MComment();
	        	comment.setIdComment(rs.getString("id_comment"));
	        	comment.setIdMaster(rs.getInt("id_master"));
	        	comment.setIdEtd(rs.getInt("id_etudiant"));
	        	comment.setCreatedAt(rs.getString("created_at"));
	        	comment.setTxt(rs.getString("txt"));
	        	comment.setEtudiantName(rs.getString("full_name"));
	        	//add to list
	        	System.out.println(comment.getTxt());
	        	commentList.add(comment);
	        }
		}catch(SQLException sqe7) {
			sqe7.printStackTrace();
			throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");
		}
		
		finally {
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
			}catch(SQLException sqe8) {
				throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");
			}			
		}
		
	return commentList;
	}
	
	
	public void addComment(MComment com) throws MCommentDaoException{
		Connection conn = null;
		PreparedStatement pst = null;
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			sqe.printStackTrace();
			throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");	
		}
		
		//function core
		try {
			//Query things
			pst = conn.prepareStatement("INSERT INTO m_comment (id_comment, id_master, id_etudiant, txt ) VALUES(?, ?, ?, ?)");
			
			pst.setString(1, UUID.randomUUID().toString());
			pst.setFloat(2, com.getIdMaster());
			pst.setFloat(3, com.getIdEtd());
			pst.setString(4, com.getTxt());

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
					sqe5.printStackTrace();
					throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");					
				}
				
				throw new MCommentDaoException("Echec de l'inscription, Un problème est survenu lors de la connection avec la Base");
				
			}	
				
			//si tout est passé correctement alors valider la transaction, fermer la connection 
			try {
				conn.commit();
			}catch(SQLException sqe6) {
				sqe6.printStackTrace();
				throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");
			}

		}catch(SQLException sqe) {
			sqe.printStackTrace();
			throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");
		}
		
		//close connections
		finally {
			try {
				if(conn != null ) conn.close();
				if(pst != null ) pst.close();
			}catch(SQLException sqe) {
				sqe.printStackTrace();
				throw new MCommentDaoException("Un problème est survenu lors de la connection avec la Base");
			}			
		}


	}


}
