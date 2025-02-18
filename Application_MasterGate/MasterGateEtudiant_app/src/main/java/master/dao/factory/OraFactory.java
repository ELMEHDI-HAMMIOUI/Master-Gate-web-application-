package master.dao.factory;
import master.dao.interfaces.DocsDao;
import master.dao.interfaces.EtudiantDao;
import master.dao.interfaces.FacultesDao;
import master.dao.interfaces.FilieresLicenseDao;
import master.dao.interfaces.MCommentDao;
import master.dao.interfaces.MasterDao;
import master.dao.interfaces.NotifDao;
import master.dao.interfaces.ResponsableDao;
import master.dao.imp.DocsDaoImp;
import master.dao.imp.EtudiantDaoImp;
import master.dao.imp.FacultesDaoImp;
import master.dao.imp.FilieresLicenseDaoImp;
import master.dao.imp.MCommentDaoImp;
import master.dao.imp.MasterDaoImp;
import master.dao.imp.NotifDaoImp;
import master.dao.imp.ResponsableDaoImp;

import java.sql.*;

public class OraFactory {
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

public static NotifDao getNotifDao() {
	return new NotifDaoImp();
}

public static DocsDao getDocsDao() {
	return new DocsDaoImp();
}

public static MCommentDao getMCommentDao() {
	return new MCommentDaoImp();
}
}