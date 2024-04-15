package model;

public class SinhVien {
	private String Mssv;
	private String HoTen;
	private int Age;
	private String University;
	public SinhVien(String m, String ht, int gt, String k) {
		try {
			Mssv = m;
			HoTen = ht;
			Age = gt;
			University = k;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getMSSV() {return Mssv;}
	public String getHoTen() {return HoTen;}
	public int getAge() {return Age;}
	public String getUniversity() {return University;}
}
