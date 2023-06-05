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
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ScrollPaneConstants;

public class ChatView extends JPanel {
	private Model model;
	private Controller controller;
	private Border border;
	private JTextField textField;
	private JTextPane ta;
	private JTextField tf;
	private JButton backbtn;
	private ArrayList<ChatView> chats;
	private List<ChatMessageEntity> chatmessages;
	private ChatRoomEntity chatroom;
	private JLabel lblNewLabel;
	
	private Socket socket = null;
	private BufferedReader reader = null;
    private BufferedWriter writer = null;
	private int roomid;
	private int portNumber;
	
		

	public ChatView(Model model, Controller controller, int roomid) {
		this.model = model;
		this.controller = controller;
		this.roomid = roomid;
		
		
		portNumber = model.getPortNumberByRoomId(roomid);
        chatmessages = model.getCurrentChatMessageByRoomid(roomid);
		
		
		

		
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		border = BorderFactory.createLineBorder(Color.BLACK);
		
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
		
		ta = new JTextPane();
		ta.setFont(new Font("Hannotate TC", Font.PLAIN, 15));
		ta.setEditable(false);
		StyledDocument doc = ta.getStyledDocument();
		DefaultCaret caret = (DefaultCaret) ta.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(ta);
		
		for (ChatMessageEntity chatmessage : chatmessages) {
			if(model.getCurrentUserId() == chatmessage.getUserId()) {
				String message = chatmessage.getContent() + "\n";
		        SimpleAttributeSet rightAlign = new SimpleAttributeSet();
		        StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
		        doc.setParagraphAttributes(doc.getLength(), message.length(), rightAlign, false);
		        try {
		            doc.insertString(doc.getLength(), message, rightAlign);   
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
			} else {
				String message = "[" + model.getNicknameById(chatmessage.getUserId()) + "]님 : " + chatmessage.getContent() + "\n";
		        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
		        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
		        doc.setParagraphAttributes(doc.getLength(), message.length(), leftAlign, false);
		        try {
		            doc.insertString(doc.getLength(), message, leftAlign);
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
			}
		}
		
		
		
		tf = new JTextField();
		tf.setFont(new Font("Hannotate TC", Font.PLAIN, 13));
		tf.setBounds(12, 6, 309, 32);
		panel_1.add(tf);
		tf.setColumns(10);
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String message = tf.getText();
//			        String content = message + "\n";
//			        			        
//			        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
//			        StyleConstants.setFontFamily(attributeSet, "Hannotate TC");
//			        StyleConstants.setFontSize(attributeSet, 15);
//			        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_RIGHT);
//			        
//			        doc.setParagraphAttributes(doc.getLength(), 0, attributeSet, false);
//			        
//			        int length = doc.getLength();
//			        try {
//			        	doc.insertString(length, content, attributeSet);
//					} catch (BadLocationException e1) {
//						e1.printStackTrace();
//					}
//			        ta.setCaretPosition(length + content.length());
//			        
//			        tf.setText("");
//			        tf.requestFocus();
					
					
					
					sendMessage(message);

				
			        
					
					
				}
			}
		});
		JButton btnNewButton_1 = new JButton("전송");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = tf.getText();
//		        String content = message + "\n";
//		        
//		        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
//		        StyleConstants.setFontFamily(attributeSet, "Hannotate TC");
//		        StyleConstants.setFontSize(attributeSet, 15);
//		        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_RIGHT);
//		        
//		        doc.setParagraphAttributes(doc.getLength(), 0, attributeSet, false);
//		        
//		        int length = doc.getLength();
//		        try {
//		        	doc.insertString(length, content, attributeSet);
//				} catch (BadLocationException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//		        ta.setCaretPosition(length + content.length());
//		        
//		        tf.setText("");
//		        tf.requestFocus();
//		        
//		        model.insertChatMessage(message);
				
				
				sendMessage(message);
				
			}
		});
		btnNewButton_1.setBounds(333, 6, 55, 36);
		panel_1.add(btnNewButton_1);
		
		
	}

	
	private void sendMessage(String message) {

		try {
	        writer.write(message);    // 메시지를 버퍼에 작성
	        System.out.println("chatview의 sendmessage 실행 " + message);
	        writer.newLine();         // 줄 바꿈 문자 추가
	        writer.flush();           // 버퍼의 내용을 서버로 전송
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    tf.setText("");              // 입력 필드 초기화
	    tf.requestFocus();            // 입력 필드에 포커스 설정
	}

	public void appendMessage(String message) {
        
		String content = message + "\n";
		
		StyledDocument doc = ta.getStyledDocument();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attributeSet, "Hannotate TC");
        StyleConstants.setFontSize(attributeSet, 15);
        
        doc.setParagraphAttributes(doc.getLength(), 0, attributeSet, false);

        int length = doc.getLength();
        
        try {
            doc.insertString(doc.getLength(), content, attributeSet);
            System.out.println("appendmessage의 실행" + message);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        ta.setCaretPosition(length + content.length());
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
				try {
		            if (socket != null) {
		                socket.close();
		            }
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }

				
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
		
		chatroom = model.getCurrentChatRoomByRoomId(roomid);
		
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





	public void openclient(ChatManager chatmanager)) {
	    Thread openClientThread = new Thread(() -> {
	        socket = null;
	        try {
	            socket = new Socket("localhost", portNumber);
	            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            
	            chatmanager.addChatThread(null);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	        try {
	            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        
	        // chatserverThread 생성
	        Thread chatserverThread = new Thread(() -> {
	            String message;
	            try {
	                while ((message = reader.readLine()) != null) {
	                    appendMessage(message);  // 받은 메시지를 화면에 표시
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        });

	        chatserverThread.start();
	    });

	    openClientThread.start();
	}



}

