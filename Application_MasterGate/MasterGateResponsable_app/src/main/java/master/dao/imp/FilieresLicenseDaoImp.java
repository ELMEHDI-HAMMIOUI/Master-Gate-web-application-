package master.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import master.beans.FilieresLicense;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FilieresLicenseDao;

public class FilieresLicenseDaoImp implements FilieresLicenseDao{
	
	public List<FilieresLicense> getFilieresLicense(){
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		List<FilieresLicense> filieres = new ArrayList<FilieresLicense>();

		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {}
		
		//function core
		try {
			//Query things
			String sql = "select ID_FIL_LICENCE from  FILIERE_LICENCE";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			//storing data
			while(rs.next()) {
				FilieresLicense fl = getFilierById(rs.getInt("ID_FIL_LICENCE"));
				filieres.add(fl);
			}
		}catch(SQLException sqe) {}
		
		//close connections
		finally {
			if(conn != null ) try{conn.close();}catch(SQLException sqe) {}
			if(st != null )try{st.close();}catch(SQLException sqe) {}
			if(rs != null )try{rs.close();}catch(SQLException sqe) {}
		}
		
		//return the list 
		return filieres;
	}
	@Override
	public FilieresLicense getFilierById(int id){
		Connection connexion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		FilieresLicense filiere =new FilieresLicense();
		try {
			connexion = OraFactory.getConnection();
			String sql="select * from Filiere_LICENCE where ID_FIL_LICENCE=? ";
			ps=connexion.prepareStatement(sql);
			ps.setInt(1, id); 
			rs = ps.executeQuery();
			if(rs.next()) {
				filiere.setId_filiere(rs.getInt("ID_FIL_LICENCE"));
				filiere.setNom(rs.getString("NOM_FIL"));
				filiere.setSurnom(rs.getString("SURNOM_FIL"));
			}
		}catch(SQLException e) {e.printStackTrace();}
		
		finally {
			if(connexion != null ) try{connexion.close();}catch(SQLException sqe) {}
			if(ps != null )try{ps.close();}catch(SQLException e) {e.printStackTrace();}
			if(rs != null )try{rs.close();}catch(SQLException e) {e.printStackTrace();}
		}
		return filiere;
	}
	@Override 
	public List<FilieresLicense> getFiliereAdmetByMasterId(int id_master){
		Connection connexion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<FilieresLicense> filieresAdmet = new ArrayList<FilieresLicense>();
		try {
			connexion = OraFactory.getConnection();
			String sql="select * from MASTER_FILIER_ADMET_V where ID_MASTER=? ";
			ps=connexion.prepareStatement(sql);
			ps.setInt(1, id_master); 
			rs = ps.executeQuery();
			while(rs.next()) {
				filieresAdmet.add(getFilierById(rs.getInt("ID_FIL_LICENCE")));
			}
		}catch(SQLException e) {e.printStackTrace();}
		
		finally {
			if(connexion != null ) try{connexion.close();}catch(SQLException sqe) {}
			if(ps != null )try{ps.close();}catch(SQLException e) {e.printStackTrace();}
			if(rs != null )try{rs.close();}catch(SQLException e) {e.printStackTrace();}
		}
		return filieresAdmet;

	}
}
