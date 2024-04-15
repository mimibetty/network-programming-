package model.bean;

public class Vacxin {

	private String maVacxin;

	private String tenVacxin;

	private int soMui;

	private String moTa;

	private int giaVacxin;

	private String tenHangSX;

	public Vacxin() {

	}

	public Vacxin(String maVacxin, String tenVacxin, int soMui, String moTa, int giaVacxin, String tenHangSX) {

		this.maVacxin = maVacxin;

		this.tenVacxin = tenVacxin;

		this.soMui = soMui;

		this.moTa = moTa;

		this.giaVacxin = giaVacxin;

		this.tenHangSX = tenHangSX;

	}

	// Getter và setter cho các thuộc tính

	public String getMaVacxin() {

		return maVacxin;

	}

	public void setMaVacxin(String maVacxin) {

		this.maVacxin = maVacxin;

	}

	public String getTenVacxin() {

		return tenVacxin;

	}

	public void setTenVacxin(String tenVacxin) {

		this.tenVacxin = tenVacxin;

	}

	public int getSoMui() {

		return soMui;

	}

	public void setSoMui(int soMui) {

		this.soMui = soMui;

	}

	public String getMoTa() {

		return moTa;

	}

	public void setMoTa(String moTa) {

		this.moTa = moTa;

	}

	public int getGiaVacxin() {

		return giaVacxin;

	}

	public void setGiaVacxin(int giaVacxin) {

		this.giaVacxin = giaVacxin;

	}

	public String getTenHangSX() {

		return tenHangSX;

	}

	public void setTenHangSX(String tenHangSX) {

		this.tenHangSX = tenHangSX;

	}

}