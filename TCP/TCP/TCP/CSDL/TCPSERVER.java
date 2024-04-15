
import java.io.*;
import java.net.*;
import java.sql.*;

public class TCPSERVER {
    public static void main(String[] args) {
        try {
            // Khởi tạo kết nối tới cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu", "root", "");

            // Tạo server socket chạy trên port 9999
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server is running");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Khởi tạo input và output stream
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                // Nhận câu truy vấn SQL từ client
                String sql = in.readUTF();
                System.out.println("Received query from client: " + sql);

                try {
                    // Thực hiện câu truy vấn và gửi kết quả trở lại cho client
                    Statement sttm = conn.createStatement();
                    ResultSet resultSet = sttm.executeQuery(sql);
                    while (resultSet.next()) {
                        String result = resultSet.getString(2);
                        out.writeUTF(result);
                        out.flush();
                    }
                } catch (SQLException e) {
                    // Gửi thông báo lỗi nếu có
                    out.writeUTF("Error: " + e.getMessage());
                    out.flush();
                }

                // Đóng input, output stream và socket
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}