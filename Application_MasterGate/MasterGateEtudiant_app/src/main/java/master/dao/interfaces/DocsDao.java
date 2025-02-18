package master.dao.interfaces;

import master.beans.DocsBean;
import master.dao.exception.EtudiantDaoException;

public interface DocsDao {
	public void addDocs(DocsBean doc) throws EtudiantDaoException; 
}
