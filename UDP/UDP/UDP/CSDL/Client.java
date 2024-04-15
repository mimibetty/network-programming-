package Client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Client extends JFrame {
    private JButton getNhanVienButton;
    private JButton getPhongBanButton;
    private JTextArea textArea;
    private final int serverPort = 7000; // Port which server is listening on.
    private final String serverAddress = "localhost"; // Server IP address.
    public Client() {
        initializeUI();
        setupActions();
    }
    private void initializeUI() {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getNhanVienButton = new JButton("Get NhanVien");
        getPhongBanButton = new JButton("Get PhongBan");
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false); // Make the text area non-editable
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(getNhanVienButton);
        add(getPhongBanButton);
        add(scrollPane);
    }
    private void setupActions() {
        getNhanVienButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequestToServer("getNhanVien");
            }
        });
        getPhongBanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequestToServer("getPhongBan");
            }
        });
    }
    private void sendRequestToServer(String request) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverIPAddress = InetAddress.getByName(serverAddress);
            byte[] sendData = request.getBytes();
            byte[] receiveData = new byte[1024];
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPort);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String response = new String(receivePacket.getData()).trim();
            textArea.setText(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error communicating with the server.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
}
