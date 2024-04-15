import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {

    // Define GUI components
    private JPanel contentPane;
    private JTextField textField; // TextField for entering PORT
    private JTextField textField_1; // TextField for entering Message
    private JTextArea textArea; // TextArea for displaying messages

    // DatagramSocket for UDP communication
    public DatagramSocket clientSocket = null;

    public Client() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 936, 495);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create and position JLabel and JTextField for PORT
        JLabel lblNewLabel = new JLabel("PORT");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(24, 26, 45, 13);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(92, 13, 160, 34);
        contentPane.add(textField);
        textField.setColumns(10);

        // Create and position JButton for CONNECT
        JButton btnNewButton = new JButton("CONNECT");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(325, 15, 170, 34);
        contentPane.add(btnNewButton);

        // Create DatagramSocket at port 6789 when CONNECT button is clicked
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientSocket = new DatagramSocket(6789);
                } catch (SocketException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Create and position JTextArea for displaying messages
        textArea = new JTextArea();
        textArea.setBounds(24, 57, 888, 253);
        contentPane.add(textArea);

        // Create and position JLabel and JTextField for entering Message
        JLabel lblNewLabel_1 = new JLabel("Nhập tin nhắn");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(24, 320, 125, 22);
        contentPane.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(24, 343, 768, 105);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        // Create and position JButton for SEND
        JButton btnNewButton_1 = new JButton("SEND");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.setBounds(802, 343, 110, 105);
        contentPane.add(btnNewButton_1);

        // Create new thread to send message and receive response when SEND button is clicked
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InetAddress IPAddress = InetAddress.getByName("localhost");
                            byte[] sendData = new byte[1024];
                            byte[] receiveData = new byte[1024];

                            // Get message from textField
                            String sentence = textField_1.getText();
                            sendData = sentence.getBytes();

                            // Get port from textField
                            int port = Integer.parseInt(textField.getText());

                            // Create and send DatagramPacket
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                            clientSocket.send(sendPacket);

                            // Receive DatagramPacket
                            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            clientSocket.receive(receivePacket);

                            // Display received message
                            String modifiedSentence = new String(receivePacket.getData());
                            textArea.append("FROM SERVER: " + modifiedSentence);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}