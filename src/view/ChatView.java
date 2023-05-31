package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import controller.Controller;
import model.ChatMessageEntity;
import model.ChatRoomEntity;
import model.Model;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.ScrollPaneConstants;

public class ChatView extends JPanel {
	private Model model;
	private Controller controller;
	private Border border;
	private JTextField textField;
	private JTextArea ta;
	private JTextField tf;
	private JButton backbtn;
	private JPanel card;
	private ArrayList<ChatView> chats;
	private List<ChatMessageEntity> chatmessages;
	private ChatRoomEntity chatroom;
	private JLabel lblNewLabel;

	public ChatView(Model model, Controller controller, List<ChatMessageEntity> chatmessages) {
		this.model = model;
		this.controller = controller;
		this.chatmessages = chatmessages;

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		border = BorderFactory.createLineBorder(Color.BLACK);
		card = new JPanel();
		
		topPanel();
		
		bottomPanel();
		
		tf.requestFocus();
		
	}

	private void bottomPanel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 523, 400, 47);
		panel_1.setBackground(new Color(255,255,255));
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 175, 400, 345);
		add(scrollPane);
		
		ta = new JTextArea();
		ta.setEditable(false);
		ta.setLineWrap(true);
		scrollPane.setViewportView(ta);
		
		for (ChatMessageEntity chatmessage : chatmessages) {
			ta.append(chatmessage.getContent() + "\n");
		}
		
		
		tf = new JTextField();
		tf.setBounds(12, 6, 309, 32);
		panel_1.add(tf);
		tf.setColumns(10);
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!tf.getText().equals("")) {
						ta.append("["+ model.getNicknameById(model.getCurrentUserId()) +" ]님 : " + tf.getText() + "\n");
					}
					tf.setText("");
					tf.requestFocus();
				}
			}
			
		});
		JButton btnNewButton_1 = new JButton("전송");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tf.getText().equals("")) {
					ta.append("["+ model.getNicknameById(model.getCurrentUserId()) +" ]님 : " + tf.getText() + "\n");
				}
				tf.setText("");
				tf.requestFocus();
			}
		});
		btnNewButton_1.setBounds(333, 6, 55, 36);
		panel_1.add(btnNewButton_1);
		
		
	}

	
	private void topPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton backbtn = new JButton("<");
		backbtn.setBounds(12, 2, 45, 45);
		backbtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.showCard("chatlist");
				} 
			});
		panel_1.add(backbtn);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 51, 400, 122);
		panel.setBorder(border);
		add(panel);
		panel.setLayout(null);
		
		chatroom = model.getCurrentChatRoom();
		
		JLabel ImageLabel = new JLabel();
        byte[] imgData = chatroom.getImage();
        if (imgData != null) {
            ImageIcon imageIcon = new ImageIcon(imgData);
            Image image = imageIcon.getImage();
            int labelWidth = 106; // 라벨의 너비 값을 직접 설정
            int imageHeight = 100;
            Image scaledImage = image.getScaledInstance(labelWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            ImageLabel.setIcon(scaledIcon);
        }
        ImageLabel.setBounds(12, 10, 106, 100);
        panel.add(ImageLabel);
		
        	
		JLabel lblNewLabel_1 = new JLabel(chatroom.getRoomName());
		lblNewLabel_1.setBounds(130, 10, 258, 100);
		panel.add(lblNewLabel_1);
		
	}

}

