package master.dao.interfaces;

import master.beans.Responsable;
import master.beans.ResponsableCard;

public interface ResponsableDao {
		public boolean checkLogin(String email,String password);
		public Responsable getRespoByID(int id);
		int getIdByEmail(String email);
		boolean checkMasterPermition(int respo_id, int master_id);
		ResponsableCard getRespoCardbyId(int id_respo);
		boolean changePassWord(int id_respo, String new_password);
}
