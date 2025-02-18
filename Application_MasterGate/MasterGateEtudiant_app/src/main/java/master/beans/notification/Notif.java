package master.beans.notification;

public class Notif {
	private int idNotif;
	private String msg;
	private String notifDate;
	private boolean readed; 
	private int idTag;
	private int idMaster;
	private int idEtudiant;
	
	public int getIdNotif() {
		return idNotif;
	}
	public void setIdNotif(int idNotif) {
		this.idNotif = idNotif;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNotifDate() {
		return notifDate;
	}
	public void setNotifDate(String notifDate) {
		this.notifDate = notifDate;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	public int getIdTag() {
		return idTag;
	}
	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}
	public int getIdMaster() {
		return idMaster;
	}
	public void setIdMaster(int idMaster) {
		this.idMaster = idMaster;
	}
	public int getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

}
