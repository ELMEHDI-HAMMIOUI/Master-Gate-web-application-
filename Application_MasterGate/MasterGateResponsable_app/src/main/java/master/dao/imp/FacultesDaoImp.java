package master.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import master.beans.Facultes;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FacultesDao;

public class FacultesDaoImp implements FacultesDao{
	public List<Facultes> getAllFacultes(){

		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		List<Facultes> facultes = new ArrayList<Facultes>();

		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {}
		
		//function core
		try {
			//Query things
			String getFacultesQuery = "SELECT * FROM Faculte";
			st = conn.createStatement();
			rs = st.executeQuery(getFacultesQuery);
			//storing data
			while(rs.next()) {
				Facultes fa = new Facultes();
				fa.setId(rs.getInt(1));
				fa.setNom(rs.getString(2));
				fa.setSurnom(rs.getString(3));
				fa.setLogo(rs.getString(4));
				//fa.setUni(UniDao.getUni(rs.getInt(5))
				facultes.add(fa);
			}

		}catch(SQLException sqe) {}
		
		//close connections
		try {
			if(conn != null ) conn.close();
			if(st != null ) st.close();
		}catch(SQLException sqe) {}
		
		//return the list 
		return facultes;
	}
	}


