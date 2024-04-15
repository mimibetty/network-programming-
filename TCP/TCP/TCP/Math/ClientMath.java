

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ClientMath extends JFrame{
    static BufferedReader in = null;
    static BufferedReader UserIn =null;
    static PrintWriter out = null;
    static Socket client= null;
    static int port = 12345;
    JTextField inputField;
    JTextArea outputField;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ClientMath();
    }
    public ClientMath() {
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
        JButton submitButton = new JButton("Gửi");

        panel.add(outputField);
        panel.add(inputField);
        panel.add(submitButton);

        //
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        try {
            client = new Socket("localhost",port);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
        }catch (Exception e) {
            // TODO: handle exception
            try {
                client.close();
                in.close();
                out.close();
            }catch (Exception e2) {
                // TODO: handle exception
            }
            e.printStackTrace();
        }
    }
    void submit() {
        try {
            String t;
            t = inputField.getText();
            inputField.setText("");
            outputField.setText("Client: "+t+"\n");
            outputField.append("Server: \n");
            out.println(t);
            String tmpString="";
            int cnt = 1;
            while(cnt--!=0) {
                String tmp = in.readLine();
                outputField.append(tmp+"\n");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
