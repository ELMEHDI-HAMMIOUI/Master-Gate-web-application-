package master.beans;

public class MComment {
    
    private String idComment;
    private int idMaster;
    private int idEtd;
    private String createdAt;
    private String txt;
    private String etudiantName;
    
	// Default constructor
    public MComment() {
    }

    // Parameterized constructor
    public MComment(String idComment, int idMaster, int idEtd, String createdAt, String txt, String etudiantName) {
        this.idComment = idComment;
        this.idMaster = idMaster;
        this.idEtd = idEtd;
        this.createdAt = createdAt;
        this.txt = txt;
        this.etudiantName = etudiantName;
    }

    // Getters and Setters
    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public int getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(int idMaster) {
        this.idMaster = idMaster;
    }

    public int getIdEtd() {
        return idEtd;
    }

    public void setIdEtd(int idEtd) {
        this.idEtd = idEtd;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    

    public String getEtudiantName() {
		return etudiantName;
	}

	public void setEtudiantName(String etudiantName) {
		this.etudiantName = etudiantName;
	}

}
