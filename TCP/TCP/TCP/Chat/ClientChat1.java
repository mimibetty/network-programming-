import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientChat1 extends JFrame {

    // Khởi tạo các biến liên quan đến đọc, ghi dữ liệu và kết nối socket
    static BufferedReader in = null; // Đọc dữ liệu từ server
    static PrintWriter out = null; // Ghi dữ liệu gửi tới server
    static Socket client = null; // Socket cho kết nối tới server
    static int port = 12345; // Cổng kết nối tới server

    // Các thành phần giao diện
    JTextField inputField;
    JTextArea outputField;

    public ClientChat1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 420);
        setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        add(panel);
        panel.setPreferredSize(new Dimension(400,400));
        panel.setLayout(new FlowLayout());

        inputField = new JTextField(26);
        outputField = new JTextArea(20,33);
        JButton submitButton = new JButton("Gửi");

        panel.add(outputField);
        panel.add(inputField);
        panel.add(submitButton);

        // Thêm sự kiện cho nút Gửi
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });

        // Thêm sự kiện cho trường nhập liệu, nhấn Enter để gửi
        inputField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submit();
                }
            }
        });

        setVisible(true);

        // Thiết lập kết nối tới server
        try {
            client = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            // Khởi tạo một luồng mới để đọc dữ liệu từ server
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            String svString = in.readLine();
                            outputField.append(svString + "\n");
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            // Đóng kết nối nếu có lỗi xảy ra
            try {
                client.close();
                in.close();
                out.close();
            } catch (Exception e2) {
            }
            e.printStackTrace();
        }
    }

    // Phương thức gửi dữ liệu tới server
    void submit() {
        try {
            String t = inputField.getText();
            outputField.append("Client: " + t + "\n");
            out.println(t);
            inputField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        new ClientChat1();
    }
}