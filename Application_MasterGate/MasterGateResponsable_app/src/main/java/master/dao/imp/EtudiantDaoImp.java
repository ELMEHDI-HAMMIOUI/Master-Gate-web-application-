package master.dao.imp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import master.beans.Etudiant;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.EtudiantDao;

public class EtudiantDaoImp implements EtudiantDao {
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
				pst = conn.prepareStatement("SELECT * FROM PROFILE WHERE id_etudiant = ?");
		
			pst.setInt(1, etudiantId);
			//get the result
			rs = pst.executeQuery();
			if(rs.next()) {
			
				e.setId(rs.getInt("ID_ETUDIANT"));
				e.setNom(rs.getString("NOM"));
				e.setPrenom(rs.getString("PRENOM"));
				e.setDateNaissance(rs.getString("D_NAISS"));
				e.setSexe(rs.getString("SEXE"));
				e.setTel(rs.getInt("TELE"));//tel in database should be int(number)
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
				e.setDatePremiereInscription(rs.getString("d_pr_inscription"));
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
}
	




