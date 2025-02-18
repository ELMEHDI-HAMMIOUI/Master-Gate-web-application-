package master.dao.factory;
import master.dao.interfaces.EtudiantDao;
import master.dao.interfaces.FacultesDao;
import master.dao.interfaces.FilieresLicenseDao;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.ResponsableDao;
import master.dao.imp.EtudiantDaoImp;
import master.dao.imp.FacultesDaoImp;
import master.dao.imp.FilieresLicenseDaoImp;
import master.dao.imp.MasterDaoImp;
import master.dao.imp.ResponsableDaoImp;

import java.sql.*;

public class OraFactory {
	/*public static final String driver="oracle.jdbc.driver.OracleDriver";
	public static final String dbUrl="jdbc:oracle:thin:pfe/pfe123@//localhost:1521/xepdb1";


	public static Connection getConnection() throws SQLException{
	try {
		Class.forName(driver);
	}catch(ClassNotFoundException ec) {}
	
	return DriverManager.getConnection(dbUrl);

	}*/
	//just set my connection method as comment and use yours 
	//from here
	public  static final String driver="oracle.jdbc.driver.OracleDriver";
	public static final String dbUrl="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
	public static final String username="mehdi";
	public static final String password="12369";

	public static Connection getConnection() throws SQLException{
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException ec) {ec.printStackTrace();}
		
		return DriverManager.getConnection(dbUrl,username,password);
	
	}

	//to here */
	
	public static EtudiantDao getUserDao() {
		return new EtudiantDaoImp();
	}
	
	public static FilieresLicenseDao getFilieresLicenseDao() {
		return new FilieresLicenseDaoImp();
	}
	
	public static FacultesDao getFacultesDao() {
		return new FacultesDaoImp();
	}
	

	public static ResponsableDao getResponsableDao() {
		return new ResponsableDaoImp();
	}
	public static MasterDao getMasterDao() {
		return new MasterDaoImp();
	}

}