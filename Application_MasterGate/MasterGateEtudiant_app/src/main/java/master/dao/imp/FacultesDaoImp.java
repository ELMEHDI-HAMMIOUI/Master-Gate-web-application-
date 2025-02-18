package master.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import master.beans.Facultes;
import master.beans.MasterCard;
import master.beans.Universite;
import master.dao.exception.EtudiantDaoException;
import master.dao.factory.OraFactory;
import master.dao.interfaces.FacultesDao;

public class FacultesDaoImp implements FacultesDao{
	
	public List<Facultes> getAllFacultes() throws EtudiantDaoException{

		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		List<Facultes> facultes = new ArrayList<Facultes>();

		//connect to DB
		try {
			conn = OraFactory.getConnection();
			conn.setAutoCommit(false);
		}catch(SQLException sqe) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
		}
		
		//function core
		try {
			//Query things
			String getFacultesQuery = "SELECT * FROM faculte";
			st = conn.createStatement();
			rs = st.executeQuery(getFacultesQuery);
			//storing data
			while(rs.next()) {
				Facultes fa = new Facultes();
				fa.setId(rs.getInt(1));
				fa.setNom(rs.getString(2));
				fa.setSurnom(rs.getString(3));
				fa.setLogo(rs.getString(4));
				fa.setUniName( getUniByFacId( fa.getId() ).getNom() );
				fa.setUniId(getUniByFacId( fa.getId() ).getId());
				facultes.add(fa);
			}

		}catch(SQLException sqe) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
		}
		
		//close connections
		finally {
			try {
				if(conn != null ) conn.close();
				if(st != null ) st.close();
			}catch(SQLException sqe) {
				throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
			}
		}
		
		//return the list 
		return facultes;
	}
	

public Universite getUniByFacId(int facId) throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Universite u = new Universite();
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	try {
		pst = conn.prepareStatement("select * from universite where id_universite = ( select id_universite from faculte where id_faculte = ? )");
		pst.setInt(1, facId);
		//get the result
		rs = pst.executeQuery();
		while( rs.next() ) {
			u.setId(rs.getInt("ID_UNIVERSITE"));
			u.setLogo(rs.getString("LOGO_UNI"));
			u.setNom(rs.getString("NOM_UNI"));
			u.setSurnom(rs.getString("SURNOM_UNI"));
			u.setVille(rs.getString("VILLE"));
			u.setWeb_site(rs.getString("WEB_SITE"));
						
		}

		
	}catch(SQLException sqe7) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
		}			
	}
	
	return u;
	
}
	
public Facultes getFaculteById(int idFac) throws EtudiantDaoException{

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Facultes fa = new Facultes();
	fa.setId(0);

	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
	}
	
	//function core
	try {
		//Query things
		pst = conn.prepareStatement("SELECT * FROM faculte where id_faculte = ?");
		pst.setInt(1, idFac);
		rs = pst.executeQuery();
		
		if(rs.next()) {
			fa.setId(rs.getInt(1));
			fa.setNom(rs.getString(2));
			fa.setSurnom(rs.getString(3));
			fa.setLogo(rs.getString(4));
			fa.setUniName( getUniByFacId( fa.getId() ).getNom() );
			fa.setUniId(getUniByFacId( fa.getId() ).getId());
		}
		else {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
		}

	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
	}
	
	//close connections
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
		}
	}
	
	return fa;
}


//count number of facs
public int getTotalFacsRecords() {
  int totalRecords = 0;
  String sql = "SELECT COUNT(*) FROM faculte";

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
	

//count number of facs having at least a master
public int getTotalFacsRecordsHavingMaster() {
int totalRecords = 0;
String sql = "select count( DISTINCT id_faculte ) FROM faculte JOIN responsable USING(id_faculte) JOIN master USING(id_responsable)";

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



public List<Facultes> getFacsByPage(int pageNumber) throws EtudiantDaoException {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
	Facultes fa = new Facultes();
	List<Facultes> facultes = new ArrayList<Facultes>();
    int offset = (pageNumber - 1) * FACULTES_CARDS_PAGE_SIZE;
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "SELECT id_faculte FROM ("
        		+ "    SELECT a.*, ROWNUM rnum FROM ("
        		+ "        SELECT * FROM faculte ORDER BY id_faculte"
        		+ "    ) a WHERE ROWNUM <= ?"
        		+ ") WHERE rnum > ?" ;
        
        ps = connection.prepareStatement(sql);
        ps.setInt(1, offset + FACULTES_CARDS_PAGE_SIZE);
        ps.setInt(2, offset);
        
        rs=ps.executeQuery();
        while(rs.next()) {
        	fa=getFaculteById(rs.getInt("id_faculte"));
        	facultes.add(fa);
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

	return facultes;
}


public List<Facultes> searchFacs(String keyword) throws EtudiantDaoException{

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps=null;
	List<Facultes> facultes = new ArrayList<Facultes>();

	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
	}
	
	//function core
	try {
		//Query things
		String sql = "select id_faculte from faculte join universite using(id_universite) "
				+ "where lower(nom_fac) like ? "
				+ "OR lower(SURNOM_FAC) LIKE ? "
				+ "OR lower(NOM_UNI) LIKE ? "
				+ "OR lower(VILLE) LIKE ? "
				+ "OR lower(NOM_UNI) LIKE ? "
				+ "OR lower(SURNOM_UNI) LIKE ? ";
		
		  keyword = "%" + keyword.toLowerCase() + "%"; 

		  ps = conn.prepareStatement(sql);
		  ps.setString(1, keyword);
		  ps.setString(2, keyword);
		  ps.setString(3, keyword);
		  ps.setString(4, keyword);
		  ps.setString(5, keyword);
		  ps.setString(6, keyword);
		  
		  rs = ps.executeQuery();

		while(rs.next()) {
			Facultes fa = getFaculteById(rs.getInt("ID_FACULTE"));
			facultes.add(fa);
		}

	}catch(SQLException sqe) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
	}
	
	//close connections
	finally {
		try {
			if(conn != null ) conn.close();
			if(ps != null ) ps.close();
		}catch(SQLException sqe) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base de donné");
		}
	}
	
	//return the list 
	return facultes;
}


//get just faculties having master
public List<Facultes> getFacsHavingMasterByPage(int pageNumber) throws EtudiantDaoException {
	Connection connection = null;
	PreparedStatement ps=null;
    ResultSet rs=null;    	
	Facultes fa = new Facultes();
	List<Facultes> facultes = new ArrayList<Facultes>();
    int offset = (pageNumber - 1) * FACULTES_CARDS_PAGE_SIZE;
    
	    try {
        connection = OraFactory.getConnection();
        String sql = "SELECT id_faculte FROM ("
        		+ "    SELECT a.*, ROWNUM rnum FROM ("
        		+ "         select distinct ID_faculte, NOM_FAC, SURNOM_FAC, LOGO_FAC, ID_UNIVERSITE "
        		+ "    		from master join responsable using(id_responsable) "
        		+ "    		join faculte using(id_faculte) "
        		+ "    ) a WHERE ROWNUM <= ?"
        		+ ") WHERE rnum > ?" ;
        
        ps = connection.prepareStatement(sql);
        ps.setInt(1, offset + FACULTES_CARDS_PAGE_SIZE);
        ps.setInt(2, offset);
        
        rs=ps.executeQuery();
        while(rs.next()) {
        	if( !OraFactory.getMasterDao().getMasterCardsByFacId( rs.getInt("id_faculte") ).isEmpty() ) {
            	fa=getFaculteById(rs.getInt("id_faculte"));
            	facultes.add(fa);
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

	return facultes;
}



public List<Universite> getAllUnis() throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<Universite> listUni = new ArrayList<Universite>();
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	try {
		pst = conn.prepareStatement("select * from universite ");
		//get the result
		rs = pst.executeQuery();
		while( rs.next() ) {
			Universite u = new Universite();
			u.setId(rs.getInt("ID_UNIVERSITE"));
			u.setLogo(rs.getString("LOGO_UNI"));
			u.setNom(rs.getString("NOM_UNI"));
			u.setSurnom(rs.getString("SURNOM_UNI"));
			u.setVille(rs.getString("VILLE"));
			u.setWeb_site(rs.getString("WEB_SITE"));
			listUni.add(u);
		}

		
	}catch(SQLException sqe7) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
		}			
	}
	
	return listUni;
	
}

public List<String> getAllCities() throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<String> cities = new ArrayList<String>();
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	try {
		pst = conn.prepareStatement("select distinct ville from master_card_v ");
		//get the result
		rs = pst.executeQuery();
		while( rs.next() ) {
			cities.add(rs.getString("ville"));
		}

		
	}catch(SQLException sqe7) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
		}			
	}
	
	return cities;

}


public List<String> getAllSpecialities() throws EtudiantDaoException{
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<String> specialities = new ArrayList<String>();
	//connect to DB
	try {
		conn = OraFactory.getConnection();
		conn.setAutoCommit(false);
	}catch(SQLException sqe1) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	try {
		pst = conn.prepareStatement("select distinct specialite from master_card_v ");
		//get the result
		rs = pst.executeQuery();
		while( rs.next() ) {
			specialities.add(rs.getString("SPECIALITE"));
		}

		
	}catch(SQLException sqe7) {
		throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
	}
	
	finally {
		try {
			if(conn != null ) conn.close();
			if(pst != null ) pst.close();
		}catch(SQLException sqe8) {
			throw new EtudiantDaoException("Un problème est survenu lors de la connection avec la Base ");
		}			
	}
	
	return specialities;

}
	}


