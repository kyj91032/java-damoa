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
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ChatClientFrame extends JFrame implements ActionListener {
	
	private JLabel lblResult;
	private int sum =0;
	private JTextField tf;
	private JTextArea ta;
	private JButton btnSend;

	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	
	public ChatClientFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 350);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());          //레이아웃
		
		setPanelCenter();
		
		setPanelBottom();
		
		setResizable(false);
		
		setVisible(true);
		
		setSocket();
		
		
		tf.setFocusable(true);
		
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
		ChatClientFrame ccf =  new ChatClientFrame("ChatClient");
		ccf.setSocket();
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
			
			
			
			ta.append("[클라이언트] : " + tf.getText() + "\n");
		}
		tf.setText("");
		tf.requestFocus();
	}
	public void setSocket() {
	
		
		try {
			
			socket = new Socket("localhost", 9000);
	
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
			while(true) {  //inMsg = #홍길동#
				

				String inMsg = in.readLine();
//				String[] msg = inMsg.split("#");
//				ta.append(msg[0]+ ":" + msg[1] + "\n");
				ta.append("[서버] :" + inMsg + "\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				in.close();
				socket.close();
						} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
