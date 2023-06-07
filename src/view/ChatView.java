package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.Document;
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
import java.util.Vector;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.geom.RoundRectangle2D;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ScrollPaneConstants;

public class ChatView extends JPanel {
	
	private Model model; private Controller controller; private Border border; private JTextField textField; private JTextPane ta; private JTextField tf; private JButton backbtn; private ArrayList<ChatView> chats; private List<ChatMessageEntity> chatmessages; private ChatRoomEntity chatroom; private JLabel lblNewLabel;private Socket socket = null; private BufferedReader reader = null; private BufferedWriter writer = null; private int roomid; private int portNumber; private int senduserid;



	public ChatView(Model model, Controller controller, int roomid) {
		this.model = model;
		this.controller = controller;
		this.roomid = roomid;
		
		senduserid = model.getCurrentUserId();
		
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
	

	// 생성자 또는 초기화 메서드에서 doc 초기화
	private void bottomPanel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(0, 520, 400, 50);
		panel_1.setBackground(new Color(228, 204, 255));
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 169, 400, 351);
		 scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		      private final Dimension d = new Dimension();
		      @Override
		      protected JButton createDecreaseButton(int orientation) {
		        return new JButton() {
		          @Override
		          public Dimension getPreferredSize() {
		            return d;
		          }
		        };
		      }
		      @Override
		      protected JButton createIncreaseButton(int orientation) {
		        return new JButton() {
		          @Override
		          public Dimension getPreferredSize() {
		            return d;
		          }
		        };
		      }
		      @Override
		      protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		        // 트랙(track) 그리기 - 여기서는 그리지 않음
		      }
		      @Override
		      protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		        // 슬라이더(thumb) 그리기
		        Graphics2D g2 = (Graphics2D)g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                            RenderingHints.VALUE_ANTIALIAS_ON);
		        Color color = null;
		        JScrollBar sb = (JScrollBar)c;
		        if(!sb.isEnabled() || r.width > r.height) {
		          return;
		        } else if(isDragging) {
		          color = new Color(200,200,100,200); // 누르고 드래그시 ( rgb값  + 투명도  )
		        } else if(isThumbRollover()) {
		          color = new Color(228,204,255,200);  // 마우스 올린경우
		        } else {
		          color = new Color(228,204,255);  // 기본값
		        }
		        g2.setPaint(color);
		        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
		        g2.setPaint(Color.WHITE);
		        g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
		        g2.dispose();
		      }
		      @Override
		      protected void setThumbBounds(int x, int y, int width, int height) {
		        super.setThumbBounds(x,y,width,height);
		        scrollbar.repaint();
		      }
		    });
			
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setBounds(0, 169, 400, 351);
		add(scrollPane);
		
		
		ta = new JTextPane();
		ta.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		ta.setEditable(false);
		StyledDocument doc = ta.getStyledDocument();
		DefaultCaret caret = (DefaultCaret) ta.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(ta);

		for (ChatMessageEntity chatmessage : chatmessages) {
		    if (model.getCurrentUserId() == chatmessage.getUserId()) {
		        String message = chatmessage.getContent() + "\n";
		        SimpleAttributeSet rightAlign = new SimpleAttributeSet();
		        StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
		        StyleConstants.setBackground(rightAlign, new Color(230, 230, 230)); // 말풍선 배경색 설정
		        StyleConstants.setSpaceBelow(rightAlign, 5); // 말풍선 아래 여백 설정

		        int length = doc.getLength();
		        doc.setParagraphAttributes(length, message.length(), rightAlign, false);
		        try {
		            doc.insertString(length, message, rightAlign);
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
		        
		    } else {
		        String nickname = model.getNicknameById(chatmessage.getUserId());
		        String message = "[" + nickname + "]님 : " + chatmessage.getContent() + "\n";
		        SimpleAttributeSet leftAlign = new SimpleAttributeSet();

		        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
		        StyleConstants.setBackground(leftAlign, new Color(228, 204, 255,200)); // 말풍선 배경색 설정
		        StyleConstants.setSpaceBelow(leftAlign, 5); // 말풍선 아래 여백 설정

		        int length = doc.getLength();
		        doc.setParagraphAttributes(length, message.length(), leftAlign, false);
		        try {
		            doc.insertString(length, message, leftAlign);
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
		    }
		}

		
		tf = new JTextField();
		tf.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf.setBounds(25, 13, 274, 23);
		tf.setBorder(null);
		panel_1.add(tf);
		tf.setColumns(10);
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					String message = tf.getText();
				        					
					sendMessage(message, senduserid);
										
					model.insertChatMessage(message);
					
					tf.setText("");              
				    tf.requestFocus();
				}
			}
			
		});


		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = tf.getText();
								
				sendMessage(message, senduserid);
				
				model.insertChatMessage(message);
				
				tf.setText("");
			    tf.requestFocus();
				
			}
		});
		btnNewButton_1.setBounds(330, 5, 55, 35);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon chatIcon = new ImageIcon("image/채팅.jpg");
        Image chatimage = chatIcon.getImage();
        Image chatimage2 = chatimage.getScaledInstance(390, 40, Image.SCALE_SMOOTH);
        ImageIcon chaticon2 = new ImageIcon(chatimage2);
        lblNewLabel_2.setIcon(chaticon2);
        lblNewLabel_2.setBounds(10, 5, 390, 40);
		panel_1.add(lblNewLabel_2);
		
	}

	
	public void openclient() {
	    Thread openClientThread = new Thread(() -> {
	        socket = null;
	        try {
	            socket = new Socket("localhost", portNumber);

	            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	            
	            while(true) {				
					String inMsg = reader.readLine();  
					appendMessage(inMsg);
				}
	            
	        } catch (IOException ex) {
	        }
	        

	    });

	    openClientThread.start();
	}

	
	
	private void sendMessage(String message, int senduserid) {

		try {
	        writer.write(senduserid + ":" + message);
	        writer.newLine();
	        writer.flush();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public void appendMessage(String message) {
		String[] parts = message.split(":");
		int senduserid = Integer.parseInt(parts[0]);
		String content = parts[1] + "\n";

		StyledDocument doc = ta.getStyledDocument();
		int length = doc.getLength();

		if (model.getCurrentUserId() == senduserid) {
		    SimpleAttributeSet rightAlign = new SimpleAttributeSet();
		    StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
		    StyleConstants.setBackground(rightAlign, new Color(230, 230, 230)); // 말풍선 배경색 설정
		    doc.setParagraphAttributes(length, content.length(), rightAlign, false);

		    try {
		        doc.insertString(length, content, rightAlign);
		    } catch (BadLocationException e) {
		        e.printStackTrace();
		    }
		} else {
		    String contents = "[" + model.getNicknameById(senduserid) + "]님 : " + content;

		    SimpleAttributeSet leftAlign = new SimpleAttributeSet();
		    StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
		    StyleConstants.setBackground(leftAlign, new Color(228, 204, 255,200)); // 말풍선 배경색 설정

		    doc.setParagraphAttributes(length, contents.length(), leftAlign, false);

		    try {
		        doc.insertString(length, contents, leftAlign);
		    } catch (BadLocationException e) {
		        e.printStackTrace();
		    }
		    
		}

		ta.setCaretPosition(length + content.length());


	}
	
	private void topPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(228, 204, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton backbtn = new JButton("");
		ImageIcon backIcon = new ImageIcon("image/뒤로가기2.png");
        Image backimage = backIcon.getImage();
        Image backimage2 = backimage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon backicon2 = new ImageIcon(backimage2);
        backbtn.setIcon(backicon2);
        backbtn.setBorder(null);
        backbtn.setBounds(10, 15, 20, 20);
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
		panel.setBounds(0, 50, 400, 120);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		add(panel);
		panel.setLayout(null);
		
		chatroom = model.getCurrentChatRoomByRoomId(roomid);
		JLabel lblNewLabel_1_3 = new JLabel(chatroom.getRoomName());
		lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(40, 10, 260, 30);
		panel_1.add(lblNewLabel_1_3);
		
		
		JLabel ImageLabel = new JLabel();
        byte[] imgData = chatroom.getImage();
        if (imgData != null) {
            ImageIcon imageIcon = new ImageIcon(imgData);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            ImageLabel.setIcon(scaledIcon);
        }
        ImageLabel.setBounds(10, 10, 101, 101);
        ImageLabel.setBorder(BorderFactory.createLineBorder((new Color(228,204,255)), 2));
        panel.add(ImageLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("방장 : " + model.getNicknameById(model.getPostBypostid(model.getPostidbyRoomid(roomid)).getUserId()));
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		lblNewLabel_1_1.setBounds(123, 10, 260, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("카테고리 : " + (model.getPostBypostid(model.getPostidbyRoomid(roomid)).getKategorie()));
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		lblNewLabel_1_2.setBounds(123, 30, 260, 25);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel((model.getPostBypostid(model.getPostidbyRoomid(roomid)).getTextarea()));
		lblNewLabel_1_2_1.setForeground(Color.GRAY);
		lblNewLabel_1_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 9));
		lblNewLabel_1_2_1.setHorizontalAlignment(JLabel.LEFT);
		lblNewLabel_1_2_1.setVerticalAlignment(JLabel.TOP);
		lblNewLabel_1_2_1.setBounds(123, 54, 260, 53);
		panel.add(lblNewLabel_1_2_1);
	}
}

