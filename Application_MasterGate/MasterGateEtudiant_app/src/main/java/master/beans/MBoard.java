package master.beans;

public class MBoard {
	private String masterName;
	private String dateInsc;
	private boolean isPs;//isPreselectioné?
	private boolean isLf;//isInListeFinale?
	private boolean isLa;//isInListeAttente?
	private int idMaster;
	private int insc_time_left;//temps restants avant la fin d'inscription
	private int pres_time_left;//temps restants avant l'affichage de preselection
	private int conc_time_left;//temps restants avant l'affichage de concours
	

	public MBoard(String masterName, String dateInsc, boolean isPs, boolean isLf, boolean isLa, int idMaster,
			int insc_time_left, int pres_time_left, int conc_time_left) {
		this.masterName = masterName;
		this.dateInsc = dateInsc;
		this.isPs = isPs;
		this.isLf = isLf;
		this.isLa = isLa;
		this.idMaster = idMaster;
		this.insc_time_left = insc_time_left;
		this.pres_time_left = pres_time_left;
		this.conc_time_left = conc_time_left;
	}

	public MBoard() {
		
	}
	
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getDateInsc() {
		return dateInsc;
	}
	public void setDateInsc(String dateInsc) {
		this.dateInsc = dateInsc;
	}
	public boolean isPs() {
		return isPs;
	}
	public void setPs(boolean isPs) {
		this.isPs = isPs;
	}
	public boolean isLf() {
		return isLf;
	}
	public void setLf(boolean isLf) {
		this.isLf = isLf;
	}
	public boolean isLa() {
		return isLa;
	}
	public void setLa(boolean isLa) {
		this.isLa = isLa;
	}
	public int getIdMaster() {
		return idMaster;
	}
	public void setIdMaster(int idMaster) {
		this.idMaster = idMaster;
	}
	public int getInsc_time_left() {
		return insc_time_left;
	}

	public void setInsc_time_left(int insc_time_left) {
		this.insc_time_left = insc_time_left;
	}

	public int getPres_time_left() {
		return pres_time_left;
	}

	public void setPres_time_left(int pres_time_left) {
		this.pres_time_left = pres_time_left;
	}

	public int getConc_time_left() {
		return conc_time_left;
	}

	public void setConc_time_left(int conc_time_left) {
		this.conc_time_left = conc_time_left;
	}

}
