package master.dao.interfaces;
import java.util.List;  

import master.beans.*;
public interface FilieresLicenseDao {
	public List<FilieresLicense> getFilieresLicense();
	public FilieresLicense getFilierById(int id);
	List<FilieresLicense> getFiliereAdmetByMasterId(int id_master);
}
