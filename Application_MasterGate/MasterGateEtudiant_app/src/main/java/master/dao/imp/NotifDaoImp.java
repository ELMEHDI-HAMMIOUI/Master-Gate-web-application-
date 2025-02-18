package master.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import master.beans.notification.Notif;
import master.beans.notification.NotifTag;
import master.dao.exception.EtudiantDaoException;
import master.dao.exception.NotifDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.NotifDao;

public class NotifDaoImp implements NotifDao{
	public List<NotifTag> getTags() throws NotifDaoException{
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		
		List <NotifTag> lst = new ArrayList<NotifTag>();
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 001). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//function core
		try {
			st = conn.createStatement();
			String query = "SELECT * FROM tag";
			rs = st.executeQuery(query);
			while(rs.next()) {
				NotifTag tg = new NotifTag();
				tg.setIdTag(rs.getInt("ID_TAG"));
				tg.setTagName(rs.getString("TAG_NAME"));
				lst.add(tg);
			}
		}catch(SQLException e) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 001). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//close connections
		 finally {
		        // Close the ResultSet
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 001). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the PreparedStatement
		        if (st != null) {
		            try {
		                st.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 001). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the Connection
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 001). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		    }
		
		return lst;
	}
	
	//get notifications by tagId
	public List<Notif> getNotifsByTagId(int tagId, int idEtudiant) throws NotifDaoException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		List <Notif> lst = new ArrayList<Notif>();
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//function core
		try {
			pst = conn.prepareStatement("select ID_NOTIF, MSG, READED, ID_TAG, ID_MASTER, ID_ETUDIANT, TO_CHAR(notif_date, 'Day, fmDD Month YYYY HH24:MI') AS formatted_date from notif where id_tag = ? and ID_ETUDIANT = ? order by readed asc");
			pst.setInt(1, tagId);
			pst.setInt(2, idEtudiant);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Notif nt = new Notif();
				nt.setIdNotif(rs.getInt("ID_NOTIF"));
				nt.setMsg(rs.getString("MSG"));
				nt.setNotifDate(rs.getString("FORMATTED_DATE"));
				
				String readed = rs.getString("READED");
				if(readed.equals("f")) nt.setReaded(false);
				else if(readed.equals("t")) nt.setReaded(true);
				
				nt.setIdTag(rs.getInt("ID_TAG"));
				nt.setIdMaster(rs.getInt("ID_MASTER"));
				nt.setIdEtudiant(rs.getInt("ID_ETUDIANT"));

				lst.add(nt);
			}
		}catch(SQLException e) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//close connections
		 finally {
		        // Close the ResultSet
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the PreparedStatement
		        if (pst != null) {
		            try {
		                pst.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the Connection
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		    }
		
		return lst;
	}

	public void readNotif(int idEtudiant) throws NotifDaoException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//function core
		try {
			pst = conn.prepareStatement("update notif set readed = 't' where ID_ETUDIANT = ? ");
			pst.setInt(1, idEtudiant);

			int rowsAffected = pst.executeUpdate() ;
			if(rowsAffected < 1) {
				//annuler la transaction
				try {
					if(conn != null)  conn.rollback();
				}catch(SQLException sqe4) {}
				
				try {
					if(conn != null ) conn.close();
					if(pst != null ) pst.close();
					
				}catch(SQLException sqe5) {
					throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
				}
				
				throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
				
			}	
		
		}catch(SQLException e) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//close connections
		 finally {
		        // Close the ResultSet
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the PreparedStatement
		        if (pst != null) {
		            try {
		                pst.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the Connection
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		    }
		
	}

	
	public int getNotReadedNotifNum(int idEtudiant) throws NotifDaoException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
        int unreadCount = 0;
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//function core
		try {
	    	String sql = "SELECT COUNT(*) AS unreadCount FROM notif WHERE readed = 'f' and ID_ETUDIANT= ?";
	     	pst = conn.prepareStatement(sql);
	    	pst.setInt(1, idEtudiant);
	     	rs = pst.executeQuery();
	            
            if (rs.next()) {
                unreadCount = rs.getInt("unreadCount");
            }
            
		}catch(SQLException e) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//close connections
		 finally {
		        // Close the ResultSet
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the PreparedStatement
		        if (pst != null) {
		            try {
		                pst.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the Connection
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		    }	
		return unreadCount;
		
	}
	
	//get all notifs by date 
	public List<Notif> getAllNotifsByOrder(int idEtudiant) throws NotifDaoException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		List <Notif> lst = new ArrayList<Notif>();
		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//function core
		try {
			pst = conn.prepareStatement("select ID_NOTIF, MSG, READED, ID_TAG, ID_MASTER, ID_ETUDIANT, TO_CHAR(notif_date, 'Day, fmDD Month YYYY HH24:MI') AS formatted_date from notif where ID_ETUDIANT = ? order by notif_date desc, readed asc");
			pst.setInt(1, idEtudiant);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Notif nt = new Notif();
				nt.setIdNotif(rs.getInt("ID_NOTIF"));
				nt.setMsg(rs.getString("MSG"));
				nt.setNotifDate(rs.getString("FORMATTED_DATE"));
				
				String readed = rs.getString("READED");
				if(readed.equals("f")) nt.setReaded(false);
				else if(readed.equals("t")) nt.setReaded(true);
				
				nt.setIdTag(rs.getInt("ID_TAG"));
				nt.setIdMaster(rs.getInt("ID_MASTER"));
				nt.setIdEtudiant(rs.getInt("ID_ETUDIANT"));

				lst.add(nt);
			}
		}catch(SQLException e) {
			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		}
		
		//close connections
		 finally {
		        // Close the ResultSet
		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the PreparedStatement
		        if (pst != null) {
		            try {
		                pst.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		        // Close the Connection
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException sqe) {
		    			throw new NotifDaoException("Erreur de connexion à la base de données (Code d'erreur : 002). Veuillez contacter l'administrateur si le problème persiste.");
		            }
		        }
		    }
		
		return lst;
	}


	
	
}
