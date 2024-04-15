package model;

import java.sql.SQLException;
import java.sql.Statement;

import model.DAO;
public class BO {
	DAO dao = new DAO();
	public void themSV(String mssv, String hoten, int age, String khoa) {
		dao.themSV(mssv, hoten, age, khoa);
	}
	
	public boolean validate(String username, String password) {
		return (boolean) dao.credentialValidate(username, password);
	}
	
	public void deleteSV(String mssv, String hoten, int age, String khoa)
	{
		dao.deleteSV(mssv, hoten, age, khoa);
	}
	
	public void updateSV(String mssv, String hoten, int age, String khoa)
	{
		dao.updateSV(mssv, hoten, age, khoa);
	}

	public static void main(String[] args) {
		
	}
	
}
