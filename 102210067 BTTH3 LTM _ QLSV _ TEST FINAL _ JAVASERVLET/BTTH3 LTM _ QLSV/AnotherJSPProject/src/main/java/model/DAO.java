package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAO {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "jdbc:mysql://localhost";
	final private String user = "root";
	final private String passwd = "";
	final private String option = "&autoReconnect=true&useSSL=true&verifyServerCertificate=false";
	Connection conn = null;
	  
	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(host+"?user="+user+"&password="+passwd+option);
		} catch (Exception e) {
			System.err.println("DAO is null");
			e.printStackTrace();
		}
	}
	
	final private String userTable = "dulieu.admin";
	public boolean credentialValidate(String username, String password) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
				"SELECT * FROM " +userTable+ " " +
				"WHERE username='"+username+"' AND password='"+password+"';"
			);
			if (rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
//	final private String svTable = "dulieu";

	final private String svTable = "dulieu.sinhvien";
	public boolean themSV(String mssv, String hoten, int age, String university) {
		try {
			Statement st = conn.createStatement();
			String cmd = ""+
				"INSERT INTO " +svTable+ " VALUES ("+mssv+", '"+hoten+"', '"+age+"', '"+university+"');";
			System.out.println(cmd);
			return (st.executeUpdate(cmd) > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public ArrayList<SinhVien> getListOfUser() {
		ArrayList<SinhVien> arr = new ArrayList<>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(""+
				"SELECT * FROM "+svTable+";"
			);
			while (rs.next()) {
				SinhVien u = new SinhVien(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				arr.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public SinhVien	getSinhVien(String mssv) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(""+
				"SELECT * FROM "+svTable+" "+
				"WHERE MSSV="+mssv+";"
			);
			System.out.println("SELECT * FROM "+svTable+" "+"WHERE MSSV="+mssv+";");
			if (rs.next()) {
				SinhVien u = new SinhVien(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				System.out.println(u.getMSSV());
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public SinhVien	getSinhVienAtKhoa(String _khoa) {
//		try {
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(""+
//				"SELECT * FROM "+svTable+" "+
//				"WHERE khoa="+_khoa+";"
//			);
//			System.out.println("SELECT * FROM "+svTable+" "+"WHERE khoa="+_khoa+";");
//			if (rs.next()) {
//				SinhVien u = new SinhVien(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
//				System.out.println(u.getMSSV());
//				return u;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
	public boolean updateSV(String mssv, String hoten, int age, String university) {
		try {
			Statement st = conn.createStatement();
			String cmd = ""+
				"UPDATE "+svTable+" SET hoten='"+hoten+"', age='"+age+"', university='"+university+"' "+
				"WHERE mssv="+mssv+";";
			System.out.println(cmd);
			return (st.executeUpdate(cmd) > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteSV(String mssv, String hoten, int age, String university) {
		try {
			Statement st = conn.createStatement();
			String cmd = ""+
				"DELETE FROM "+svTable+" "+"WHERE mssv="+mssv+";";
			System.out.println(cmd);
			return (st.executeUpdate(cmd) > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// --------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// testing
		DAO dao = new DAO();
		if (dao.conn != null) {
			System.out.println("Success.");
			
		}
	}
}
