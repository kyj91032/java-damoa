package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
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
import java.awt.Graphics;
import java.awt.Graphics2D;

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
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

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
	

	public ChatView(Model model, Controller controller, List<ChatMessageEntity> chatmessages) {
		this.model = model;
		this.controller = controller;
		this.chatmessages = chatmessages;
		

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(228, 204, 255));
		setLayout(null);
		
		border = BorderFactory.createLineBorder(Color.BLACK);
		
		topPanel();
		
		bottomPanel();
		
		tf.requestFocus();
		
		
		
		
	}

	private void bottomPanel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(10, 523, 380, 45);
		panel_1.setBackground(new Color(228, 204, 255));
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
		        StyleConstants.setBold(rightAlign, true);
		        doc.setParagraphAttributes(doc.getLength(), message.length(), rightAlign, false);
		        try {
		            doc.insertString(doc.getLength(), message, rightAlign);   
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
			} else {
				String message = "[" + model.getNicknameById(chatmessage.getUserId()) + "]님 : " 
			+ chatmessage.getContent() + "\n";
		        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
		        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
                StyleConstants.setBold(leftAlign, false);
		        doc.setParagraphAttributes(doc.getLength(), message.length(), leftAlign, false);
		        try {
		            doc.insertString(doc.getLength(), message, leftAlign);
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
			}
		}
		
		tf = new JTextField() {
			class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area());
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};		
		tf.setFont(new Font("Hannotate TC", Font.PLAIN, 13));
		tf.setBounds(1, 6, 309, 32);
		
		
		panel_1.add(tf);
		tf.setColumns(10);
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		
					String message = tf.getText();
					
//			        String sender = model.getNicknameById(model.getCurrentUserId());
			        String content = message;
			        String newMessage = content + "\n";
			        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
			        StyleConstants.setFontFamily(attributeSet, "Hannotate TC");
			        StyleConstants.setFontSize(attributeSet, 15);
			        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_RIGHT);
			        StyleConstants.setBold(attributeSet, true);
			        
			        doc.setParagraphAttributes(doc.getLength(), 0, attributeSet, false);

			        
			        int length = doc.getLength();
			        try {
			        	doc.insertString(length, newMessage, attributeSet);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        ta.setCaretPosition(length + newMessage.length());
			        
			        tf.setText("");
			        tf.requestFocus();
			        
			        model.insertChatMessage(message);
				}
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("전송");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = tf.getText();
				
//		        String sender = model.getNicknameById(model.getCurrentUserId());
		        String content = message;
		        String newMessage = content + "\n";
		        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		        StyleConstants.setFontFamily(attributeSet, "Hannotate TC");
		        StyleConstants.setFontSize(attributeSet, 15);
		        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_RIGHT);
		        StyleConstants.setBold(attributeSet, true);
		        
		        doc.setParagraphAttributes(doc.getLength(), 0, attributeSet, false);
		        
		        int length = doc.getLength();
		        try {
		        	doc.insertString(length, newMessage, attributeSet);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        ta.setCaretPosition(length + newMessage.length());
		        
		        tf.setText("");
		        tf.requestFocus();
		        
		        model.insertChatMessage(message);
			}
		});
		btnNewButton_1.setBounds(320, 6, 55, 36);
		panel_1.add(btnNewButton_1);
		
		
	}

	
	private void topPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(228, 204, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton backbtn = new JButton("<") {
			class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area());
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};	
		backbtn.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 17));
		backbtn.setHorizontalAlignment(SwingConstants.LEFT);
		backbtn.setBounds(10, 10, 30, 30);
		backbtn.setBackground(new Color(228, 204, 255));
		backbtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				controller.showCard("chatlist");
				} 
			});
		panel_1.add(backbtn);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(228, 204, 255));
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

