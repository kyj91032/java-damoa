package chat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ChatServerFrame extends JFrame implements ActionListener {
	
	private int sum =0;
	private JTextField tf;
	private JTextArea ta;
	private JButton btnSend;
	
	private ServerSocket listener = null;
	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private ArrayList<ServerThread> threadList = new ArrayList<>();

	public ChatServerFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 350);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());          //레이아웃
		
		setPanelCenter();
		
		setPanelBottom();
		
		setResizable(false);
		
		tf.setFocusable(true);
		
		setVisible(true);
		setServer();
		
		
		tf.requestFocus();
	}

	private void setPanelCenter() {
		JPanel panCenter = new JPanel();
		
		panCenter.setLayout(new BorderLayout());
		ta = new JTextArea();
		ta.setLineWrap(true);
		ta.setEditable(false);
		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panCenter.add(sp);
		
		add(panCenter, BorderLayout.CENTER);
	}

	private void setPanelBottom() {
		JPanel panBottom = new JPanel();
		tf = new JTextField(17);
		tf.addActionListener(this);
		panBottom.add(tf);
		btnSend = new JButton("보내기");
		btnSend.addActionListener(this);
		btnSend.setPreferredSize(new Dimension(80,30));
		panBottom.add(btnSend);
		add(panBottom, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		ChatServerFrame csf = new ChatServerFrame("ChatServer");
		csf.setServer();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!tf.getText().equals("")) {
		
			String outMsg = tf.getText();  //next는 띄어쓰기 nextLine은 엔터
			try {
				out.write(outMsg + "\n");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			ta.append("[서버] : " + tf.getText() + "\n");
		}
		tf.setText("");
		tf.requestFocus();
	}
	public void setServer() {
		
		
		try {
			listener = new ServerSocket(9000);
			ta.append("연결을 기다립니다..." + "\n");
			
			
			while(true) {
				socket = listener.accept();
				ta.append("연결되었습니다." + "\n");
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String inMsg = in.readLine();
				ta.append("[클라이언트] :" + inMsg + "\n");
				
				ServerThread st = new ServerThread(socket, threadList);
				threadList.add(st);
				st.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				in.close();
				listener.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
