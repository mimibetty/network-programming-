package UDP_Bai1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {
    private JPanel contentPane;
    private static JTextField textField;
    private JTextArea textArea;

    public static DatagramSocket serverSocket;
    public static String Hoa(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z')
                c = (char) (c + 32);
            res += c;
        }
        return res;
    }

    public static String Thuong(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') c = (char)(c - 32);
            res += c;
        }
        return res;
    }

    public static String Nguoc(String s) {
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            res += c;
        }
        return res;
    }

    public static String HoaThuong(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') c = (char)(c - 32);
            else if (c >= 'A' && c <= 'Z') c =(char)(c + 32);
            res += c;
        }
        return res;
    }
    public static int DemTu(String s)
    {
        s = ' ' + s;
        int cnt = 0;
        for (int i = 1; i < s.length(); i++)
        {
            char c = s.charAt(i);
            char c1 = s.charAt(i - 1);
            if (((c >= 'A' && c <= 'Z')  || (c >= 'a' && c <='z')) && (c1 == ' ')) cnt++;
        }
        return cnt;
    }
    public static int NguyenAm(String s)
    {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if ((c == 'u') || (c == 'e') || (c == 'o') || (c == 'a') || (c == 'i') || (c == 'U') || (c == 'E') || (c == 'O') || (c == 'A') || (c == 'I')) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        Server frame = new Server();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 926, 548);
        frame.contentPane = new JPanel();
        frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(frame.contentPane);
        frame.contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("PORT");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 10, 50, 38);
        frame.contentPane.add(lblNewLabel);

        frame.textField = new JTextField();
        frame.textField.setBounds(99, 10, 226, 38);
        frame.contentPane.add(frame.textField);
        frame.textField.setColumns(10);

        JButton btnNewButton = new JButton("START");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(439, 10, 191, 38);
        frame.contentPane.add(btnNewButton);
        serverSocket = new DatagramSocket();
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = Integer.parseInt(textField.getText());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
                            frame.textArea.append("Server is started + \n");
                            while (true) {
                                byte[] receiveData = new byte[65507];
                                byte[] sendData = new byte[65507];
                                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                                serverSocket.receive(receivePacket);
                                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

                                InetAddress clientIPAddress = receivePacket.getAddress();
                                int clientPort = receivePacket.getPort();

                                String[] responses = new String[6];
                                responses[0] = Hoa(request);
                                responses[1] = Thuong(request);
                                responses[2] = HoaThuong(request);
                                responses[3] = Nguoc(request);
                                responses[4] = "So luong tu la " + DemTu(request);
                                responses[5] = "So luong nguyen am la " + NguyenAm(request);
                                String message = responses[0] + "\n" + responses[1] + "\n" + responses[2] + "\n" + responses[3] + "\n" + responses[4] + "\n" + responses[5] + "\n";
                                sendData = message.getBytes();
                                frame.textArea.append(message);
//                                System.out.println(message);
                                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
                                serverSocket.send(sendPacket);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        JLabel lblNewLabel_1 = new JLabel("SERVER Diag");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 79, 143, 38);
        frame.contentPane.add(lblNewLabel_1);

        frame.textArea = new JTextArea();
        frame.textArea.setBounds(10, 127, 889, 355);
        frame.contentPane.add(frame.textArea);

        frame.setVisible(true);


    }
}