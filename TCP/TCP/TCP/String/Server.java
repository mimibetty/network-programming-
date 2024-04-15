package StringTCP;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Server extends JFrame{
	static ServerSocket sv = null;
	static BufferedReader in = null;
	static PrintWriter out = null;
	static Socket client= null;
	static int port = 12345;
	JTextArea p;
	public Server() {
		setName("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 420);
		setLayout(new FlowLayout());
		p = new JTextArea(20,30);
		JPanel pan = new JPanel();
		pan.setSize(400,420);
		pan.add(p);
		add(pan);
		setVisible(true);
		mainpr();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();	
	}
	void mainpr() {
		try {
			sv = new ServerSocket(port);
			p.append("Server listening...\n");
			while (true) {
				client = sv.accept();
				p.append("Client connected\n");
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(),true);
				while(true) {
					String t = in.readLine();
					p.append("Client sent: "+t+"\n");
					String tmp = Solve(t);
					out.println(tmp);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			try {
				
			sv.close();
			in.close();
			out.close();
			}catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	static String Solve(String s) {
		String res = "";
		// đảo ngược
		String tmp = "";
		for (int i = s.length()-1; i>=0;i--) {
			tmp+=s.charAt(i);
		}
		res+= "Chuỗi đảo ngược: "+tmp+"\n";
		//
		tmp = s.toUpperCase();
		res+= "Chuỗi in hoa: "+tmp+"\n";
		tmp = s.toLowerCase();
		res+= "Chuỗi in thường: "+tmp+"\n";
		String[] arr = s.split(" ");
		tmp = "";
		int cnt=0;
		for (String p:arr) {
			tmp+= p.substring(0,1).toUpperCase()+p.substring(1)+" ";
			for (int i = 0; i < p.length(); i++) {
	            char kyTu = Character.toLowerCase(p.charAt(i));
	            if (kyTu == 'a' || kyTu == 'e' || kyTu == 'i' || kyTu == 'o' || kyTu == 'u') {
	                cnt++;
	            }
	        }
		}
		res+="Chuỗi vừa hoa vừa thường: "+tmp+"\n";
		res+="Có "+arr.length+" từ, có: "+Integer.toString(cnt)+" nguyên âm.";
		
		return res;
	}

}
