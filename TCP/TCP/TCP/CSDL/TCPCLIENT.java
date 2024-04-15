
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class TCPCLIENT {
    public static void main(String[] args) {
        try {
            // Tạo socket kết nối tới server trên port 9999
            Socket socket = new Socket("localhost", 9999);

            // Khởi tạo input và output stream
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Gửi câu truy vấn SQL tới server
            String sql = "SELECT * FROM SINHVIEN";
            out.writeUTF(sql);
            out.flush();

            // Nhận kết quả từ server
            String result;
            while (true) {
                try {
                    result = in.readUTF();
                    if (result.isEmpty()) {
                        break;
                    }
                    System.out.println(result);
                } catch (EOFException e) {
                    break;
                }
            }

            // Đóng input, output stream và socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}