import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.ArrayList;
	import java.util.List;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;

	public class ServerChat extends JFrame {
		public static List<Socket> clients = new ArrayList<>();
		static BufferedReader in = null;
		static BufferedReader UserIn =null;
		static int port = 12345;
		static JTextField inputField;
		static JTextArea outputField;
		public ServerChat() {
			// TODO Auto-generated constructor stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(400, 420);
			setLayout(new FlowLayout());
			JPanel panel = new JPanel();
			add(panel);
			panel.setPreferredSize(new Dimension(400,400));
			panel.setLayout(new FlowLayout());
			inputField = new JTextField(26);
			outputField = new JTextArea(20,33);
	//        outputField.
			JButton submitButton = new JButton("Gửi");

			panel.add(outputField);
			panel.add(inputField);
			panel.add(submitButton);

			//
			submitButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					submit();
				}
			});
			inputField.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						submit();
					}
				}
			});
			setVisible(true);
			predo();
		}
		void submit() {
			try {
				String t;
				t = inputField.getText();
				inputField.setText("");
				outputField.append("Server: "+t+"\n");
				for (Socket client: ServerChat.clients) {
					PrintWriter out = new PrintWriter(client.getOutputStream(), true);
					out.println("Server: "+t);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		void predo() {
			ServerSocket serverSocket = null;
			int port = 12345;
			try {
				serverSocket = new ServerSocket(port);
				outputField.append("Server đang lắng nghe cổng "+port+"\n");
				int cnt=0;
				while (true) {
					Socket clinSocket = serverSocket.accept();
					cnt++;
					outputField.append("Client "+ cnt+ " đã kết nối\n");
					clients.add(clinSocket);
					ClientHandler hd = new ClientHandler(clinSocket,cnt);
					new Thread(hd).start();
				}

			}catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					serverSocket.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new ServerChat();
		}

	}
