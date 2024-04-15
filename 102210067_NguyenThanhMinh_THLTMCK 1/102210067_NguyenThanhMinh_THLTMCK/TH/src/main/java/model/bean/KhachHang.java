package model.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KhachHang {
	private String makh;
	private String hotenkh;
	private String sdt;
	private String diachi;
	private Date ngaysinh;
	private boolean gioitinh;
	
	public KhachHang(String m, String h, String s, String d, Date n, boolean g) {
		makh = m;
		hotenkh = h;
		sdt = s;
		diachi = d;
		ngaysinh = n;
		gioitinh = g;
	}
	
	public String getMaKH() {
		return makh;
	}
	
	public String getHotenKH() {
		return hotenkh;
	}
	
	public String getSDT() {
		return sdt;
	}
	
	public String getDC() {
		return diachi;
	}
	
	public Date getDOB() {
		return ngaysinh;
	}
	
	public boolean isMale() {
		return gioitinh;
	}
	
	public void setMaKH(String a) {
		makh = a;
	}
	
	public void setHotenKH(String a) {
		hotenkh = a;
	}
	
	public void setSDT(String a) {
		sdt = a;
	}
	
	public void setDC(String a) {
		diachi = a;
	}
	
	public void setDOB(String a) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ngaysinh = sdf.parse(a);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setMale(boolean a) {
		gioitinh = a;
	}
}
