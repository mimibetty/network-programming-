package UDP_Bai3;

import java.awt.EventQueue;
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
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CLIENT extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JTextField textField_2;
	private JTextField textField_3;

	public DatagramSocket clientSocket = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLIENT frame = new CLIENT();
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
	public CLIENT() throws SocketException {
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
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(278, 13, 160, 34);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(462, 13, 160, 34);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("CONNECT");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(663, 15, 170, 34);
		contentPane.add(btnNewButton);
		clientSocket = new DatagramSocket();
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int port = Integer.parseInt(textField_2.getText());
				 try {
						clientSocket = new DatagramSocket(port);
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
		

		JButton btnNewButton_1 = new JButton("SEND");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(802, 343, 110, 105);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable()
								{

									@Override
									public void run() {
										int portSV = Integer.parseInt(textField.getText());
										InetAddress IPAddress = null;
										try {
											IPAddress = InetAddress.getByName("localhost");
										} catch (UnknownHostException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										String name = textField_3.getText();
										
								            byte[] sendData = new byte[65507]; 
								            byte[] receiveData = new byte[65507]; 
								            
								            String message = textField_1.getText();
								            String sentence = name + ": " + message;
								            sendData = sentence.getBytes();

								            DatagramPacket sendPacket = 
								                    new DatagramPacket(sendData, sendData.length, IPAddress, portSV); 
								            
								            try {
												clientSocket.send(sendPacket);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} 

								            DatagramPacket receivePacket = 
								                new DatagramPacket(receiveData, receiveData.length); 

								            try {
												clientSocket.receive(receivePacket);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} 
								            
								            
								            String modified_Sentence = new String(receivePacket.getData());
								            textArea.append(modified_Sentence + "\n"); 
								        
								        
										
									}
									
							
								}).start();
						
					}
			
				});
	}

}
