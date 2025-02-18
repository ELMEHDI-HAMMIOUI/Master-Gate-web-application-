package master;

import bean.*;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Imp {
    public static final String driver = "oracle.jdbc.driver.OracleDriver";
    public static final String dbUrl = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    public static final String username = "mehdi";
    public static final String password = "12369";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static boolean checkLogin(String userEmail, String userPassword) {
        String query = "SELECT id FROM Admin WHERE email = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, userEmail);
            stmt.setString(2, userPassword);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Responsable getRespobyId(int id_respo) {
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  Connection connexion=null;
		  Responsable responsable=null;
		  try {
			  connexion=getConnection();
			  String sql = "SELECT * from RESPONSABLE_FACULTE_UNIVERSITE_v where ID_RESPONSABLE=? ";
			  ps = connexion.prepareStatement(sql);
			  ps.setInt(1, id_respo);
		      rs = ps.executeQuery();
		      if(rs.next()) {
		    	  responsable=new Responsable();
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
    public List<Responsable> getAllReso() {
        List<Responsable> responsables = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connexion = null;
        try {
            connexion = getConnection();
            String sql = "SELECT ID_RESPONSABLE from RESPONSABLE_FACULTE_UNIVERSITE_v where ID_RESPONSABLE not  in (select ID_RESPO from BLOCKEDRESPO )";
            ps = connexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id_respo = rs.getInt("ID_RESPONSABLE");
                Responsable responsable = getRespobyId(id_respo);
                responsables.add(responsable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return responsables;
    }
	public List<Facultes> getAllFacultes(){

		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		List<Facultes> facultes = new ArrayList<Facultes>();

		//connect to DB
		try {
			conn = getConnection();
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
				facultes.add(fa);
			}

		}catch(SQLException sqe) {}
		
		try {
			if(conn != null ) conn.close();
			if(st != null ) st.close();
		}catch(SQLException sqe) {}
		
		return facultes;
	}
    public boolean deleteResponsable(int id_responsable) {
        Connection connection = null;
        PreparedStatement insertStmt = null;
        boolean success = false;

        try {
            connection = getConnection();
            connection.setAutoCommit(false); 
            String insertSql = "INSERT INTO blockedRespo (id_respo) VALUES (?)";
            insertStmt = connection.prepareStatement(insertSql);
            insertStmt.setInt(1, id_responsable);
            int rowsInserted = insertStmt.executeUpdate();
            if(rowsInserted>0) {
            	success=true;
            }
        }catch(SQLException sqe) {}
        finally {
            // Close resources
            try {
                if (insertStmt != null) insertStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return success;
    }
    
    public boolean insertResponsable(String email, String password, int id_faculte) {
        Connection connection = null;
        PreparedStatement insertStmt = null;
        boolean success = false;

        try {
            connection = getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Insert into RESPONSABLE table
            String insertSql = "INSERT INTO RESPONSABLE VALUES (SQC_RESPONSABLE.nextval, ?, ?, ?)";
            insertStmt = connection.prepareStatement(insertSql);
            insertStmt.setString(1, email);
            insertStmt.setString(2, password);
            insertStmt.setInt(3, id_faculte);
            
            int rowsInserted = insertStmt.executeUpdate();

            // Check if insertion was successful
            if (rowsInserted > 0) {
                // Commit transaction
                connection.commit();
                success = true;
            } 
        } catch (SQLException e) {
                // Handle exceptions
                e.printStackTrace();
        }finally {
            // Close resources
            try {
                if (insertStmt != null) insertStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return success;
    }
    public boolean ModifierProfile(Responsable respo) {
        String sql = "UPDATE RESPONSABLE SET EMAIL = ?, PASSWORD = ? WHERE ID_RESPONSABLE = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, respo.getEmail());
            ps.setString(2, respo.getPassword());

            ps.setInt(3, respo.getId_respo());
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteFromBlockedRespo(int id_respo) {
        String sql = "DELETE FROM blockedRespo WHERE ID_RESPO = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, id_respo);
            
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<Responsable> getAllDeletedRespo() {
        List<Responsable> responsables = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connexion = null;
        try {
            connexion = getConnection();
            String sql = "SELECT ID_RESPONSABLE from RESPONSABLE_FACULTE_UNIVERSITE_v where ID_RESPONSABLE   in (select ID_RESPO from BLOCKEDRESPO )";
            ps = connexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id_respo = rs.getInt("ID_RESPONSABLE");
                Responsable responsable = getRespobyId(id_respo);
                responsables.add(responsable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return responsables;
    }

}

