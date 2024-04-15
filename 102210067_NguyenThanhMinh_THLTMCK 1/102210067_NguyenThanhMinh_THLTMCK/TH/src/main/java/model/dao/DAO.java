package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.bean.LichSuTiemPhong;
import model.bean.Vacxin;

public class DAO {
	private static String urlDB = "jdbc:mysql://localhost:3306/dulieu";
	private static String userDB = "root";
	private static String passDB = "";
	
	public List<Vacxin> getAllVacxin() {
		try {
			ResultSet rs = execute("select * from vacxin");
			List<Vacxin> result = new ArrayList<>();
			while (rs.next()) {
				result.add(new Vacxin(
						rs.getString("mavacxin"),
						rs.getString("tenvacxin"),
						rs.getInt("somui"),
						rs.getString("mota"),
						rs.getInt("giavacxin"),
						rs.getString("tenhangsx")));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<LichSuTiemPhong> getAllDistinctLichSuTiem() {
		try {
			ResultSet rs = execute("SELECT * FROM lichsutiemphong GROUP BY maKH, mavacxin");
			List<LichSuTiemPhong> result = new ArrayList<>();
			while (rs.next()) {
				result.add(new LichSuTiemPhong(
						rs.getString("maKH"),
						rs.getString("mavacxin"),
						rs.getInt("sttmui"),
						rs.getDate("ngaytiemphong"),
						rs.getDate("ngayhentieptheo")
				));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Map<String, Integer> countLichSuTiem() {
		try {
			ResultSet rs = execute("SELECT maKH, mavacxin, COUNT(*) as count FROM lichsutiemphong GROUP BY maKH, mavacxin");
			Map<String, Integer> result = new HashMap<>();
			while (rs.next()) {
				// Tạo một key duy nhất từ maKH và mavacxin
				String key = rs.getString("maKH") + "_" + rs.getString("mavacxin");
				result.put(key, rs.getInt("count"));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	int CostVatxin(String s) {
		Vacxin vac = getVacxinById(s);
		return vac.getGiaVacxin();
	}
//	public List<Vacxin> getAllVacxin() {
//		try {
//			ResultSet rs = execute("select * from vacxin");
//			List<Vacxin> result = new ArrayList<>();
//			while (rs.next()) {
//				result.add(new Vacxin(
//						rs.getString("mavacxin"),
//						rs.getString("tenvacxin"),
//						rs.getInt("somui"),
//						rs.getString("mota"),
//						rs.getInt("giavacxin"),
//						rs.getString("tenhangsx")));
//			}
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	
	public Vacxin getVacxinById(String id) {
		try {
			ResultSet rs = execute("select * from vacxin where mavacxin = '"+id+"'");

			while (rs.next()) {
				Vacxin result = new Vacxin(
						rs.getString("mavacxin"),
						rs.getString("tenvacxin"),
						rs.getInt("somui"),
						rs.getString("mota"),
						rs.getInt("giavacxin"),
						rs.getString("tenhangsx"));
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateVacxin(Vacxin v) {
		try {
			String id = v.getMaVacxin();
			String ffString = "update vacxin " 
					+ "set tenvacxin = '" + v.getTenVacxin()
					+ "',set somui = '" + v.getSoMui()
					+ "',set mota = '" + v.getMoTa()
					+ "',set giavacxin = '" + v.getGiaVacxin()
					+ "',set tenhangsx = '" + v.getTenHangSX()
					+ "' where mavacxin = '" + id + "'";
			System.out.println(ffString);
			
//			int res = executeU("update vacxin " 
//					+ "set tenvacxin = '" + v.getTenVacxin()
//					+ "',set somui = '" + v.getSoMui()
//					+ "',set mota = '" + v.getMoTa()
//					+ "',set giavacxin = '" + v.getGiaVacxin()
//					+ "',set tenhangsx = '" + v.getTenHangSX()
//					+ "' where mavacxin = '" + id + "'");
			int res = executeU("UPDATE vacxin " 
					+ "SET tenvacxin = '" + v.getTenVacxin()
					+ "', somui = '" + v.getSoMui()
					+ "', mota = '" + v.getMoTa()
					+ "', giavacxin = '" + v.getGiaVacxin()
					+ "', tenhangsx = '" + v.getTenHangSX()
					+ "' WHERE mavacxin = '" + id + "'");
			return (res>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addVacxin(Vacxin v) {
		try {
			int res = executeU("insert into vacxin(mavacxin,tenvacxin,somui,mota,giavacxin,tenhangsx) values "
					+ "('" + v.getMaVacxin() 
					+ "','"+ v.getTenVacxin()
					+ "','"+ v.getSoMui()
					+ "','"+ v.getMoTa()
					+ "','"+ v.getGiaVacxin()
					+ "','"+ v.getTenHangSX()+"')");
			return (res>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteVacxin(String id) {
		try {
			int res = executeU("delete from vacxin where mavacxin = '"+ id +"'");
			return (res>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ResultSet execute(String query) {
		Connection conn = getConnection(urlDB, userDB, passDB);
		Statement stmt;
		try {
			stmt = conn.createStatement();
	        // get data from table 'student'
	        ResultSet rs = stmt.executeQuery(query);
	        return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int executeU(String query) {
		Connection conn = getConnection(urlDB, userDB, passDB);
		Statement stmt;
		try {
			stmt = conn.createStatement();
	        // get data from table 'student'
	        int rs = stmt.executeUpdate(query);
	        return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}

