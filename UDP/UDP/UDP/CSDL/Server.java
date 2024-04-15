import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Server {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/nhanvien";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        DatagramSocket serverSocket;
        try {
            serverSocket = new DatagramSocket(7000);
            System.out.println("Server is started");
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String request = new String(receivePacket.getData()).trim();
                String response;
                if (request.equals("getNhanVien")) {
                    response = getNhanVien();
                }
                else if (request.equals("getPhongBan")) {
                    response = getDepartmentData();
                } else {
                    response = "Invalid command";
                }
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getNhanVien() {
        StringBuilder result = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM nhanvien")) {
                while (rs.next()) {
                    String id = rs.getString("IDNV");
                    String name = rs.getString("Hoten");
                    String position = rs.getString("Diachi");
                    result.append("IDNV: ").append(id).append(", Name: ").append(name).append(", Position: ").append(position).append("\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error accessing database";
        }
        return result.toString();
    }
        
    private static String getDepartmentData() {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM phongban")) {
                while (rs.next()) {
                    String id = rs.getString("IDPB");
                    String name = rs.getString("Tenpb");
                    String describe = rs.getString("Mota");
                    result.append("IDPB: ").append(id).append(", Name: ").append(name).append(", Describe: ").append(describe).append("\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error accessing database";
        }
        return result.toString();
    }
}
