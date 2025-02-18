package master.dao.imp;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import master.beans.Facultes;
import master.beans.Responsable;
import master.beans.ResponsableCard;
import master.beans.Universite;
import master.dao.factory.OraFactory;
import master.dao.interfaces.ResponsableDao;

public class ResponsableDaoImp implements  ResponsableDao{

	@Override
	public boolean checkLogin(String email, String password) {
	  PreparedStatement ps = null;
	  ResultSet resultat = null;
	  Connection connexion=null;
	  boolean login=false;
	  try {
		  connexion = OraFactory.getConnection();
		  ps = connexion.prepareStatement("SELECT * FROM RESPONSABLE WHERE EMAIL = ? AND PASSWORD = ? AND ID_RESPONSABLE not  in (select ID_RESPO from BLOCKEDRESPO )" );
		  ps.setString(1, email);
		  ps.setString(2, password);
	      resultat = ps.executeQuery();
	      while(resultat.next()){login=true;}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    try {if (resultat != null) resultat.close();} catch (SQLException e) { e.printStackTrace(); }
	    try {if (ps != null) resultat.close();}		  catch (SQLException e) { e.printStackTrace(); }
	    try {if (connexion != null) resultat.close();}catch (SQLException e) { e.printStackTrace(); }
	    }
	  return login ;
	}

	@Override
	public Responsable getRespoByID(int id) {
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  Connection connexion=null;
		  Responsable responsable=null;
		  Facultes fac=null;
		  Universite uni=null;
		  try {
			  connexion=OraFactory.getConnection();
			  String sql = "SELECT * from RESPONSABLE_FACULTE_UNIVERSITE_v where ID_RESPONSABLE=? ";
			  ps = connexion.prepareStatement(sql);
			  ps.setInt(1, id);
		      rs = ps.executeQuery();
		      if(rs.next()) {
		    	  uni=new Universite();
		    	  fac=new Facultes();
		    	  responsable=new Responsable();
		    	  uni.setId(rs.getInt("ID_UNIVERSITE"));
		    	  uni.setNom(rs.getString("NOM_UNI"));
		    	  uni.setVille(rs.getString("VILLE"));
		    	  uni.setSurnom(rs.getString("SURNOM_UNI"));
		    	  uni.setWeb_site(rs.getString("WEB_SITE"));
		    	  uni.setLogo(rs.getString("LOGO_UNI"));
		    	  fac.setId(rs.getInt("ID_FACULTE"));
		    	  fac.setNom(rs.getString("NOM_FAC"));
		    	  fac.setSurnom(rs.getString("SURNOM_FAC"));
		    	  fac.setLogo(rs.getString("LOGO_FAC"));
		    	  fac.setUni(uni);
		    	  responsable.setId(rs.getInt("ID_RESPONSABLE"));
		    	  responsable.setEmail(rs.getString("EMAIL"));
		    	  responsable.setPassword(null);
		    	  responsable.setFaculte(fac);
		      }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
	    	  if (rs != null) {try { rs.close();} catch (SQLException e) { e.printStackTrace();  }}
	    	  if (ps != null) {try {ps.close();} catch (SQLException e) { e.printStackTrace();} }
	    	  if (connexion != null) {try {connexion.close();} catch (SQLException e) {e.printStackTrace();}}
		    }
		return responsable;
	}
	@Override 
	public int getIdByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connexion=null;
		int id=-1;
		try {
			connexion=OraFactory.getConnection();
			String sql = "SELECT ID_RESPONSABLE from RESPONSABLE_FACULTE_UNIVERSITE_v where EMAIL=? ";
			ps = connexion.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
			id=rs.getInt("ID_RESPONSABLE");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {try { rs.close();} catch (SQLException e) { e.printStackTrace();  }}
			if (ps != null) {try {ps.close();} catch (SQLException e) { e.printStackTrace();} }
			if (connexion != null) {try {connexion.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return id;
	}
	@Override
	public boolean checkMasterPermition(int respo_id,int master_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connexion=null;
		boolean Permition =false;
		try {
			connexion=OraFactory.getConnection();
			String sql = "select id_master,id_responsable  from MASTER_CONDITION_V  where id_master=? and id_responsable=?";
			ps = connexion.prepareStatement(sql);
			ps.setInt(1, master_id);
			ps.setInt(2, respo_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Permition=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {try { rs.close();} catch (SQLException e) { e.printStackTrace();  }}
			if (ps != null) {try {ps.close();} catch (SQLException e) { e.printStackTrace();} }
			if (connexion != null) {try {connexion.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return Permition;
	}
		
	@Override
	public ResponsableCard getRespoCardbyId(int id_respo) {
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  Connection connexion=null;
		  ResponsableCard responsable=null;
		  try {
			  connexion=OraFactory.getConnection();
			  String sql = "SELECT * from RESPONSABLE_FACULTE_UNIVERSITE_v where ID_RESPONSABLE=? ";
			  ps = connexion.prepareStatement(sql);
			  ps.setInt(1, id_respo);
		      rs = ps.executeQuery();
		      if(rs.next()) {
		    	  responsable=new ResponsableCard();
		    	  responsable.setId_uni(rs.getInt("ID_UNIVERSITE"));
		    	  responsable.setNom_uni(rs.getString("NOM_UNI"));
		    	  responsable.setVille(rs.getString("VILLE"));
		    	  responsable.setSurnom_uni(rs.getString("SURNOM_UNI"));
		    	  responsable.setWeb_site(rs.getString("WEB_SITE"));
		    	  responsable.setLogo_uni(rs.getString("LOGO_UNI"));
		    	  responsable.setId_fac(rs.getInt("ID_FACULTE"));
		    	  responsable.setNom_fac(rs.getString("NOM_FAC"));
		    	  responsable.setSurnom_fac(rs.getString("SURNOM_FAC"));
		    	  responsable.setLogo_fac(rs.getString("LOGO_FAC"));

		    	  responsable.setId_respo(rs.getInt("ID_RESPONSABLE"));
		    	  responsable.setEmail(rs.getString("EMAIL"));
		    	  responsable.setPassword(null);
		      }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
	    	  if (rs != null) {try { rs.close();} catch (SQLException e) { e.printStackTrace();  }}
	    	  if (ps != null) {try {ps.close();} catch (SQLException e) { e.printStackTrace();} }
	    	  if (connexion != null) {try {connexion.close();} catch (SQLException e) {e.printStackTrace();}}
		    }
		return responsable;
	}
	@Override
	public boolean changePassWord(int id_respo,String new_password) {
		PreparedStatement ps = null;
		  boolean isUpdated = false;
		  int rowAffected;
		  Connection connexion=null;
		  try {
			  connexion=OraFactory.getConnection();
			  String sql = "update  RESPONSABLE set PASSWORD=? where id_responsable=?";
			  ps = connexion.prepareStatement(sql);
			  ps.setString(1, new_password);
			  ps.setInt(2, id_respo);
			  rowAffected = ps.executeUpdate();
			  if(rowAffected>0) {
				  isUpdated=true;
			  }
		  } catch (SQLException e) {
		        e.printStackTrace();
		  } finally {
	    	  if (ps != null) {try {ps.close();} catch (SQLException e) { e.printStackTrace();} }
	    	  if (connexion != null) {try {connexion.close();} catch (SQLException e) {e.printStackTrace();}}
		  }
		  return isUpdated;
	}

	
	
}
