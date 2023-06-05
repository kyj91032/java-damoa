package chat;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class ChatClientFrame extends JFrame implements ActionListener {
	
	private JCheckBox chk1;
	private JCheckBox chk2;
	private JCheckBox chk3;
	private JLabel lblResult;
	public int sum = 0;
	private JTextField tf;
	private JTextArea ta;
	private JButton btnSend;
	
	Socket socket = null;
	BufferedReader in = null;
	BufferedWriter out = null;
	
	public ChatClientFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 350);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		setPanelCenter(); // 메소드로 뽑아서 작업하는 것
		
		setPanelBottom();
		
		setResizable(false);
		
		tf.setFocusable(true);
		
		setVisible(true);
		tf.requestFocus();
	}
	private void setPanelCenter() {
		JPanel panCenter = new JPanel(); // panel은 flowlayout이 기본 설정
		panCenter.setLayout(new BorderLayout());
		panCenter.setBackground(Color.RED);	
		
		ta = new JTextArea(10, 20);
		ta.setLineWrap(true); // 글자가 벽에 닿으면 다음줄로 넘어오는 작업
		ta.setEditable(false); // 글자가 나온것을 수정하지 못하도록 하는 작업
		
		JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 스크롤을 설정하는 방법
		
		panCenter.add(sp); // 스크롤 설정한것을 add해야 스크롤이 보인다
		
		
		add(panCenter, BorderLayout.CENTER); //레이아웃을 센터로 보이게 설정
	}
	private void setPanelBottom() {
		JPanel panBottom = new JPanel();
		
		
		tf = new JTextField(12);
		tf.addActionListener(this);
		panBottom.add(tf);
		
		btnSend = new JButton("보내기");
		btnSend.setPreferredSize(new Dimension(100, 30)); // 버튼의 길이를 조절할 수 있다
		btnSend.addActionListener(this);
		panBottom.add(btnSend);
		
		add(panBottom, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		ChatClientFrame ccf = new ChatClientFrame("ChatClient");
		ccf.setSocket();		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!tf.getText().equals("")) {
			
			String outMsg = tf.getText();
			try {
				out.write(outMsg + "\n");
				out.flush();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			
			//ta.append("[클라이언트] : " + tf.getText() + "\n");
		}
		tf.setText("");		
		tf.requestFocus();
	}
	public void setSocket() {
				
		try {
			
			socket = new Socket("localhost", 9000);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {				
				String inMsg = in.readLine();  
				ta.append("[서버] : " + inMsg + "\n");
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
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
