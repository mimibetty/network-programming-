

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class ServerMath extends JFrame{
	static ServerSocket sv = null;
	static BufferedReader in = null;
	static PrintWriter out = null;
	static Socket client= null;
	static int port = 12345;
	JTextArea p;
	public ServerMath() {
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
		new ServerMath();
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
					System.out.print(tmp+"\n");
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
		String res="";
		s = s.replaceAll(" ","");
		try {
			SolveEquation n = new SolveEquation(s);
			res = Integer.toString(n.res);
		}
		catch (Exception e) {
			e.printStackTrace();
			res = "Something wrong with your equation";
		}

		return res;
	}

};

class SolveEquation{
	public int res=0;

	public SolveEquation(String s) throws Exception {
//		s = "(1412-3421)*1";
		res = sol(s);
	}
	public int sol(String s) throws Exception {
		int res=0;
		String tmp = "";
		boolean in=false;
		int cntbrake = 0;
		ArrayList<token> arr = new ArrayList<>();
		int cursig = 0;
		boolean am =false;
		for (int i =0; i<s.length(); i++) {
			if (in==true) {
				if (s.charAt(i)=='(') cntbrake++;
				else if (s.charAt(i)==')') cntbrake--;;
				if (cntbrake!=0) {
					tmp+=s.charAt(i);
				}
				else {
					arr.add(new token(cursig,am?-sol(tmp):sol(tmp)));
					am = false;
					in = false;
					cursig = 0;
					tmp = "";
				}
				continue;
			}
			if (s.charAt(i)==')') {
				cntbrake--;
			}
			else if (s.charAt(i)=='(') {
				cntbrake++;
				tmp = "";
				in = true;
			}
			else if (s.charAt(i)=='+') {

			}
			else if (s.charAt(i)=='-') {
				am = !am;
			}
			else if (s.charAt(i)=='*') {
				if (i==0 || !(Character.isDigit(s.charAt(i-1)) || s.charAt(i)!=')')) throw new Exception("Fool");
				cursig = 1;
			}
			else if (s.charAt(i)=='/') {
				if (i==0 || !(Character.isDigit(s.charAt(i-1)) || s.charAt(i)!=')')) throw new Exception("Fool");
				cursig = 2;
			}
			else if (s.charAt(i)>='0'&& s.charAt(i)<='9') {
				int val =0;
				while(i<s.length() && s.charAt(i)>='0'&& s.charAt(i)<='9') {
					val*=10;
					val+=s.charAt(i)-'0';
					i++;
				}
				if (am) val = -val;
				am = false;
				arr.add(new token(cursig,val));
				cursig = 0;
				i--;
				continue;
			}
			else throw new Exception("Fool! Unexpected characters!");
		}
		for (int i = 1; i<arr.size(); i++) {
			if(arr.get(i).op == 1) {
				arr.get(i).val*= arr.get(i-1).val;
				arr.get(i-1).val=0;
				arr.get(i).op = 0;
			}
			else if (arr.get(i).op==2) {
				arr.get(i).val = arr.get(i-1).val/arr.get(i).val;
				arr.get(i-1).val=0;
				arr.get(i).op = 0;
			}

		}
		for (token x: arr) {
			res+=x.val;
		}
		if (cntbrake!=0) throw new Exception("Fool");
		return res;
	}
}
class token{
	public Integer op=0;
	public Integer val=0;
	public token(Integer opp, Integer valInteger) {
		op = opp;
		val = valInteger;
	}
}








