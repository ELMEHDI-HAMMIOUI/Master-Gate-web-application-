package master.beans;

//ce bean va contenir les masters qui sont proches de leur dates limites
public class CloseMaster {
	
	private int idMaster;
	private String masterName;
	private String dateFin;
	private int timeLeft;
	
	public CloseMaster() {
	}
	
	public CloseMaster(int idMaster, String masterName, String dateFin, int timeLeft) {
		this.idMaster = idMaster;
		this.masterName = masterName;
		this.dateFin = dateFin;
		this.timeLeft = timeLeft;
	}

	
	public int getIdMaster() {
		return idMaster;
	}
	public void setIdMaster(int idMaster) {
		this.idMaster = idMaster;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

}
