package UDP_Bai3;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SERVER extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	public static DatagramSocket serverSocket;
	public static Map<Integer, InetAddress> clients = new HashMap<>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SERVER frame = new SERVER();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SocketException 
	 */
	public SERVER() throws SocketException {
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
		btnNewButton.setBounds(386, 15, 170, 34);
		contentPane.add(btnNewButton);
		textArea = new JTextArea();
		textArea.setBounds(24, 57, 888, 372);
		contentPane.add(textArea);
		serverSocket = new DatagramSocket();
		btnNewButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int port = Integer.parseInt(textField.getText());
		        new Thread(new Runnable() {

		            @Override
		            public void run() {
		                try (DatagramSocket serverSocket = new DatagramSocket(port)) {
		                    textArea.append("Server is started \n");
		                    while (true) {
		                        byte[] receiveData = new byte[65507];
		                        byte[] sendData = new byte[65507];
		                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		                        serverSocket.receive(receivePacket);
		                        String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

		                        InetAddress clientIPAddress = receivePacket.getAddress();
		                        int clientPort = receivePacket.getPort();

		                        if (!clients.containsKey(clientPort)) {
		                            clients.put(clientPort, clientIPAddress);
		                        }
		                        textArea.append(request + "\n");

		                        for (Map.Entry<Integer, InetAddress> entry : clients.entrySet()) {
		                            InetAddress clientAddress = entry.getValue();
		                            int clientPortO = entry.getKey();
		                            if (clientPortO != clientPort) {
		                                sendData = request.getBytes();
		                                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPortO);
		                                serverSocket.send(sendPacket);
		                            }
		                        }
		                    }
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }).start();
		    }

		});
		
	}

}
