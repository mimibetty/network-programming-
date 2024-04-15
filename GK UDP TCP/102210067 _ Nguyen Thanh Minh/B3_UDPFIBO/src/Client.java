import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextArea textArea;
    private JButton btnNewButton_1;
    public DatagramSocket clientSocket = null;
    public Client() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 936, 495);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("PORT");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(24, 26, 45, 13);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(92, 13, 160, 34);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("CONNECT");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(325, 15, 170, 34);
        contentPane.add(btnNewButton);
        clientSocket = new DatagramSocket();
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientSocket = new DatagramSocket(6789);
                } catch (SocketException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        textArea = new JTextArea();
        textArea.setBounds(24, 57, 888, 253);
        contentPane.add(textArea);

        JLabel lblNewLabel_1 = new JLabel("Nhập tin nhắn");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(24, 320, 125, 22);
        contentPane.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(24, 343, 768, 105);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        btnNewButton_1 = new JButton("SEND");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_1.setBounds(802, 343, 110, 105);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InetAddress IPAddress = null;
                        try {
                            IPAddress = InetAddress.getByName("localhost");
                        } catch (UnknownHostException e1) {
                            e1.printStackTrace();
                        }
//                        while (true) {
                        byte[] sendData = new byte[65507];
                        byte[] receiveData = new byte[65507];
                        String sentence = textField_1.getText();
//                            if (sentence.compareTo("quit") == 0)
//                                break;
                        sendData = sentence.getBytes();
                        int port = Integer.parseInt(textField.getText());
                        DatagramPacket sendPacket =
                                new DatagramPacket(sendData, sendData.length, IPAddress, port);

                        try {
                            clientSocket.send(sendPacket);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        DatagramPacket receivePacket =
                                new DatagramPacket(receiveData, receiveData.length);

                        try {
                            clientSocket.receive(receivePacket);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }


                        String modifiedSentence = new String(receivePacket.getData());
                        String ff = modifiedSentence.trim();
                        int lenn = modifiedSentence.length();
                        textArea.append("FROM SERVER:" + "\n" + modifiedSentence);

//                        System.out.println( ff);
//
//                        System.out.println( ff.charAt(ff.length()-1));
//                        System.out.println(lenn + "  " + ff.charAt(lenn-1));
//                        System.out.println(ff.charAt(lenn-2));
                        if ( ff.charAt(ff.length()-1) == 't') {
                            btnNewButton_1.setVisible(false);
                        }
                        //                        }
//                        clientSocket.close();
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