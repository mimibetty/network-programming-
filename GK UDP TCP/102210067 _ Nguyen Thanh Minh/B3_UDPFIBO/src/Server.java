
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

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

    public static HashMap<Long, Integer> fibonacciMap = new HashMap<>();
    public static long f1=0, f2=1;
    public static int fIdx=1;

    public static int getFiboIndex(Long num) {
        while (f2 <= num) {
            fibonacciMap.put(f2, fIdx);
            long tmp = f1 + f2;
            f1 = f2;
            f2 = tmp;
            fIdx++;
        }
        return fibonacciMap.getOrDefault(num, -1);
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

                                String responses = new String(request);
                                Long k = Long.parseLong(request);
                                String message = "Ket qua:  ";
                                int FiboTh = getFiboIndex(k);
                                if (k == 1) {
                                    message = message + k + " la so fibo o vi tri 1 va 2 \n";
                                    message = message + "Yeu cau ngung nhap \n";
                                    message = message + "Quitt";
                                }
                                else {
                                    if (FiboTh == -1) {
                                        message = message + k + " khong phai la so fibo \n";

                                    }
                                    else {
                                        message = message + k + " la so fibo o vi tri  " + FiboTh +"\n" ;
                                        message = message + "Yeu cau ngung nhap \n";
                                        message = message + "Quitt";
                                    }
                                }
//                                message += '\n';
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

        JLabel lblNewLabel_1 = new JLabel("SERVER LOG");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 79, 143, 38);
        frame.contentPane.add(lblNewLabel_1);

        frame.textArea = new JTextArea();
        frame.textArea.setBounds(10, 127, 889, 355);
        frame.contentPane.add(frame.textArea);

        frame.setVisible(true);


    }
}