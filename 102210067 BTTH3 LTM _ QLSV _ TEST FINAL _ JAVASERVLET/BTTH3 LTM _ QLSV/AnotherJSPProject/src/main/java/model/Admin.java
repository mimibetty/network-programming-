package model;
import model.DAO;

public class Admin {
	private DAO dao = new DAO();
	
	private String username, password;
	public Admin(String un, String pw) {
		username=un; password=pw;
	}
	
	public void setUsername(String un) {
		username=un;
	}
	public void setPassword(String pw) {
		password=pw;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public boolean validate() {
		return dao.credentialValidate(username, password);
	}
}
