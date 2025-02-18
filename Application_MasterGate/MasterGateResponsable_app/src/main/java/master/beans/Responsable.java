package master.beans;

public class Responsable {
	private int id ;
	private String email ;
	private String password ;
	private Facultes faculte ;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	public Facultes getFaculte() {
		return faculte;
	}
	public void setFaculte(Facultes faculte) {
		this.faculte = faculte;
	}
}
