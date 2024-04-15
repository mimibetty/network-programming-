import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class TCPCLIENT extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextArea textArea;
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dos;

    private JButton btnNewButton_1;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TCPCLIENT frame = new TCPCLIENT();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TCPCLIENT() {
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

        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectToServer();

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

        btnNewButton_1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message();

            }

        });
    }
    private void ConnectToServer()
    {
        try {
            int port = Integer.parseInt(textField.getText());

            socket = new Socket("localhost", port);
            din = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            textArea.append("Connected to server\n");
        } catch (Exception e) {
            textArea.append("Error connecting to server: " + e.getMessage() + "\n");
        }
    }
    private void Message()
    {
        try {
            String message = textField_1.getText();
            dos.writeUTF(message);
            dos.flush();

            String response = din.readUTF();
            textArea.append("Client: " + message + "\n");
            textArea.append("Server response: " + response + "\n");
            String status = din.readUTF();
            status = new String(status);
//            textArea.append("Status:" + status + "\n");
//            System.out.println(status.trim());

            if (status.trim().equals("true")) {
                btnNewButton_1.setVisible(false);
            }
            else {
            }

            textField_1.setText("");
        } catch (Exception e) {
            textArea.append("Error sending message: " + e.getMessage() + "\n");
        }
    }
}
