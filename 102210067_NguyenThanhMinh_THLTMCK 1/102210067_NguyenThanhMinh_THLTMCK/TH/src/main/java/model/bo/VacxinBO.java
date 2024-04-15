package model.bo;

import java.util.List;

import model.dao.DAO;
import model.bean.Vacxin;

public class VacxinBO {
	private DAO vacxinDAO;
	
	public VacxinBO() {
		vacxinDAO = new DAO();
	}
	
	public List<Vacxin> getAllVacxin() {
		return vacxinDAO.getAllVacxin();
	}
	
	public Vacxin getVacxinByID(String id) {
		return vacxinDAO.getVacxinById(id);
	}
	
	public boolean addVacxin(Vacxin v) {
		return vacxinDAO.addVacxin(v);
	}
	
	public boolean updateVacxin(Vacxin v) {
		return vacxinDAO.updateVacxin(v);
	}
	
	public boolean deleteVacxin(String id) {
		return vacxinDAO.deleteVacxin(id);
	}
}
