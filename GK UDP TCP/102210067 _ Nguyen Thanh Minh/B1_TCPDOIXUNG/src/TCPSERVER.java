import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSERVER extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;

    private ServerSocket serverSocket;
    private Thread serverThread;

    public static String toBin(Long k) {
        String bin = "";
        while (k > 0) {
            bin = (((k & 1) == 1) ? "1" : "0") + bin;
            k >>= 1;
        }
        while (bin.length() < 8) {
            bin = '0' + bin;
        }
        return bin;
    }

    public static boolean isBinPalindrome(Long k) {
        String bin = toBin(k);
        int n = (int)(bin.length());
        for (int i=0; i<n/2; i++) {
            if (bin.charAt(i) != bin.charAt(n-i-1)) return false;
        }
        return true;
    }


    public TCPSERVER() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 926, 548);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("PORT");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 10, 50, 38);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(99, 10, 226, 38);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("START");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(439, 10, 191, 38);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel_1 = new JLabel("SERVER LOG");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 79, 143, 38);
        contentPane.add(lblNewLabel_1);

        textArea = new JTextArea();
        textArea.setBounds(10, 127, 889, 355);
        contentPane.add(textArea);

        btnNewButton.addActionListener(e -> {

            int port = Integer.parseInt(textField.getText());

            try {
                serverSocket = new ServerSocket(port);

                serverThread = new Thread(() -> {
                    try {
                        while (true) {
                            Socket clientSocket = serverSocket.accept();
                            updateTextArea("Client connected");

                            Thread clientThread = new Thread(() -> {
                                try {
                                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                                    DataInputStream din = new DataInputStream(clientSocket.getInputStream());

                                    while (true) {
                                        String clientMessage = din.readUTF();
                                        String msg = "So nhi phan la: " + toBin(Long.parseLong(clientMessage)) +"\n"
                                                + "Ket qua : " + isBinPalindrome(Long.parseLong(clientMessage)) +"\n";
                                        String ok;
                                        if (isBinPalindrome(Long.parseLong(clientMessage)) == true) {
                                            msg = msg + " day la so doi xung, dung nhap!  " + "\n";
                                            ok = "true";
                                        }
                                        else {
                                            ok = "false";
                                        }
                                        updateTextArea("Client: " + clientMessage);
                                        updateTextArea("Server: " + msg);

                                        dos.writeUTF(msg);
                                        dos.writeUTF(ok);
                                        dos.flush();
                                    }
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            });

                            clientThread.start();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });

                serverThread.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void updateTextArea(String message) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(message + "\n");
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TCPSERVER frame = new TCPSERVER();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
