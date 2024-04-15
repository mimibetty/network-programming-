
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.FileWriter;
import java.io.IOException;


public class ConnectGoogle  {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        byte[] ipAddr = new byte[] {127, 0, 0, 1};
        addr = InetAddress.getByAddress(ipAddr);
        String hostname =  addr.getHostName(); // Get the host name
        //Get canonicall host name
        String hostnameCanonical = addr.getCanonicalHostName();
        System.out.print(hostnameCanonical);
        try (Socket socket = new Socket("www.google.com", 80);
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             FileWriter fileWriter = new FileWriter("index.html")) {
            // gui yeu cau GET HTTP
            socketOut.println("GET / HTTP/1.1");
            socketOut.println("Host:   " + "www.google.com");
            socketOut.println("Connection: is close");
            socketOut.println(); // dong trong the hien ket thuc header cua yeu cau
            String inputLine;
            boolean headerEnded = false; // Biến cờ để kiểm tra xem phần header đã kết thúc hay chưa
            // Đọc và ghi từng dòng từ phản hồi vào file
            while ((inputLine = socketIn.readLine()) != null) {
                if (headerEnded) {
                    // Ghi nội dung vào file index.html
                    fileWriter.write(inputLine + "\n");
                } else if (inputLine.isEmpty()) {
                    // Nếu  dòng trống => kết thúc của header và nội dung sắp bắt đầu
                    headerEnded = true;
                }
            }
            // Đảm bảo tất cả dữ liệu đã được ghi xuống file
            fileWriter.flush();
            View();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void View() throws IOException{
        File htmlFile = new File("index.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}
