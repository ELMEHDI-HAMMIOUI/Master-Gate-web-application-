package master.dao.interfaces;

import java.util.List;

import master.beans.notification.Notif;
import master.beans.notification.NotifTag;
import master.dao.exception.NotifDaoException;

public interface NotifDao {
	public List<NotifTag> getTags() throws NotifDaoException;
	public List<Notif> getNotifsByTagId(int tagId, int idEtudiant) throws NotifDaoException;
	public void readNotif(int idEtudiant) throws NotifDaoException;
	//get the number of notifications not readed
	public int getNotReadedNotifNum(int idEtudiant) throws NotifDaoException;
	public List<Notif> getAllNotifsByOrder(int idEtudiant) throws NotifDaoException;
}
