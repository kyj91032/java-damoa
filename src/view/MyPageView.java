package view;

import java.awt.Color; 
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.Controller;
import model.Model;
import model.UserEntity;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MyPageView extends JPanel {
	
	private Model model; private Controller controller; private JTextField PhoneField; private JTextField nicknameField; private JTextField idField; private JTextField birthField; private JLabel lblNewLabel_3; private JLabel lblMypage; private JTextField passwordField_1;


	
	public MyPageView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(221, 238, 255));
		setLayout(null);
				
		addCenterPanel();
		
		btnPanel(controller);
		
		addBtnPanel(controller);

	    setVisible(true);
	}

	private void btnPanel(Controller controller) {
		JPanel panel1 = new JPanel();
		   panel1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		   panel1.setBackground(new Color(255, 255, 255));
		   panel1.setBounds(0, 500, 400, 70);
		   add(panel1);
		
		   JLabel lblHome = new JLabel();
		   lblHome.setBounds(0, 0, 100, 70);
		   lblHome.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		   ImageIcon homeicon = new ImageIcon("image/home.png");
		   Image imghome = homeicon.getImage();
		   Image imghome2 = imghome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		   ImageIcon imgicon2 = new ImageIcon(imghome2);
		   panel1.setLayout(null);
		   lblHome.setIcon(imgicon2);
		   lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		   lblHome.setBackground(new Color(201, 219, 178));
		   panel1.add(lblHome);
		   lblHome.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent e) {
		           controller.showCard("home"); // 라벨 클릭 시 홈 화면 보여줌
		       }
		   });
		   
		   JLabel lblRecruitment = new JLabel();
		   lblRecruitment.setBounds(100, 0, 100, 70);
		   lblRecruitment.setBorder(null);
		   ImageIcon posticon = new ImageIcon("image/post.png");
		   Image imgpost = posticon.getImage();
		   Image imgpost2 = imgpost.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		  ImageIcon posticon2 = new ImageIcon(imgpost2);
		  lblRecruitment.setIcon(posticon2);
		   lblRecruitment.setHorizontalAlignment(SwingConstants.CENTER);
		   lblRecruitment.setBackground(new Color(201, 219, 178));
		   panel1.add(lblRecruitment);
		   lblRecruitment.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent e) {
		           controller.showCard("postform"); // 라벨 클릭 시 채팅 화면 보여줌
		       }
		   });
		
		   
		   JLabel lblChat = new JLabel();
		   lblChat.setBounds(200, 0, 100, 70);
		   lblChat.setBorder(new LineBorder(new Color(192, 192, 192)));
		   ImageIcon chaticon = new ImageIcon("image/chat.png");
		   Image imgchat = chaticon.getImage();
		   Image imgchat2 = imgchat.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		  ImageIcon iconchat2 = new ImageIcon(imgchat2);
		  lblChat.setIcon(iconchat2);
		   lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		   lblChat.setBackground(new Color(201, 219, 178));
		   panel1.add(lblChat);
		   lblChat.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent e) {
		           controller.showCard("chatlist"); // 라벨 클릭 시 채팅 화면 보여줌
		       }
		   });
		
		   lblMypage = new JLabel();
		   lblMypage.setBounds(300, 0, 100, 70);
		   ImageIcon mypageicon = new ImageIcon("image/mypage.png");
		   Image imgmypage = mypageicon.getImage();
		   Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		  ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
		  lblMypage.setIcon(iconmypage2);
		   lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
		   lblMypage.setBackground(new Color(192, 192, 192));
		   panel1.add(lblMypage);
		   lblMypage.addMouseListener(new MouseAdapter() {
		      public void mouseClicked(MouseEvent e) {
		         controller.showCard("mypage");  
		       }
		   });
	}

	private void addCenterPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(228, 204, 255));
		panel.setBounds(0, 0, 400, 70);
		add(panel);
		panel.setLayout(null);
		
		String nickname = model.getNicknameById(model.getCurrentUserId()); // 닉네임 db에서 불러와서 보여줌
		JLabel lblNewLabel = new JLabel(model.getNicknameById(model.getCurrentUserId()) + "님 어서오세요!");
		System.out.println(nickname);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 20, 370, 40);
		panel.add(lblNewLabel);
		
		
	
	}

	
	
	
	
	private void addBtnPanel(Controller controller) { // 하단 4메뉴 생성 메소드
	    JPanel panel_2 = new JPanel();
	    panel_2.setBorder(null);
	    panel_2.setLayout(null);
	    panel_2.setBackground(new Color(255, 255, 255));
	    panel_2.setBounds(0, 70, 400, 430);
	    add(panel_2);
	    
	    Border roundedBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
	     Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
	      Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, emptyBorder);
	      
	      Border roundedBorder2 = BorderFactory.createLineBorder(new Color(228, 204, 255), 3, true);
	      Border emptyBorder2 = BorderFactory.createEmptyBorder(5, 10, 5, 10);
	      Border compoundBorder2 = BorderFactory.createCompoundBorder(roundedBorder2, emptyBorder2);
	      
	      JLabel lblNewLabel_2_1 = new JLabel("");
	      ImageIcon phoneicon = new ImageIcon("image/전화기.png");
	     Image phoneimage = phoneicon.getImage();
	     Image phoneimage2 = phoneimage.getScaledInstance(33, 33, Image.SCALE_SMOOTH);
	     ImageIcon phoneicon2 = new ImageIcon(phoneimage2);
	     lblNewLabel_2_1.setIcon(phoneicon2);
	      lblNewLabel_2_1.setBounds(50, 200, 35, 35);
	      panel_2.add(lblNewLabel_2_1);
	      
	      PhoneField = new JTextField();
	      PhoneField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
	      PhoneField.setBorder(compoundBorder);
	      PhoneField.setText(model.getCurrentUser().getPhone());
	      PhoneField.setColumns(10);
	      PhoneField.setBounds(95, 205, 250, 30);
	      String initialText5 = model.getCurrentUser().getPhone();
	      if (!PhoneField.hasFocus() && PhoneField.getText().equals(initialText5)) {
	         PhoneField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	      }
	      PhoneField.addFocusListener(new FocusAdapter() {
	         public void focusGained(FocusEvent e) {
	            if (PhoneField.getText().equals(initialText5)) {
	               PhoneField.setText("");
	               PhoneField.setBorder(compoundBorder2);
	               PhoneField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	         }
	         
	         public void focusLost(FocusEvent e) {
	            if (PhoneField.getText().isEmpty()) {
	               PhoneField.setText(initialText5);
	               PhoneField.setBorder(compoundBorder);
	               PhoneField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	         }
	      });
	      panel_2.add(PhoneField);
	      
	      nicknameField = new JTextField();
	      nicknameField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
	      nicknameField.setBorder(compoundBorder);
	      nicknameField.setText(model.getCurrentUser().getNickname());
	      nicknameField.setColumns(10);
	      nicknameField.setBounds(95, 155, 250, 30);
	      String initialText4 = model.getCurrentUser().getNickname();
	      if (!nicknameField.hasFocus() && nicknameField.getText().equals(initialText4)) {
	         nicknameField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	      }
	      nicknameField.addFocusListener(new FocusAdapter() {
	         public void focusGained(FocusEvent e) {
	            if (nicknameField.getText().equals(initialText4)) {
	               nicknameField.setText("");
	               nicknameField.setBorder(compoundBorder2);
	               nicknameField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	         }
	         
	         public void focusLost(FocusEvent e) {
	            if (nicknameField.getText().isEmpty()) {
	               nicknameField.setText(initialText4);
	               nicknameField.setBorder(compoundBorder);
	               nicknameField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	         }
	      });
	      panel_2.add(nicknameField);
	      
	      JLabel lblNewLabel_2 = new JLabel("");
	      ImageIcon loginicon = new ImageIcon("image/사람.png");
	     Image loginimage = loginicon.getImage();
	     Image loginimage2 = loginimage.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
	     ImageIcon loginicon2 = new ImageIcon(loginimage2);
	     lblNewLabel_2.setIcon(loginicon2);
	      lblNewLabel_2.setBounds(50, 150, 35, 35);
	      panel_2.add(lblNewLabel_2);
	      
	      
	      
	      
	      passwordField_1= new JTextField();
	      String initialText3 = model.getCurrentUser().getUserpw();
	      passwordField_1.setBorder(compoundBorder);
	      passwordField_1.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
	      passwordField_1.setText(model.getCurrentUser().getUserpw());
	      passwordField_1.setBounds(95, 105, 250, 30);
	      if (!passwordField_1.hasFocus() && passwordField_1.getText().equals(initialText3)) {
	    	  passwordField_1.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	      }
	      passwordField_1.addFocusListener(new FocusAdapter() {
	         public void focusGained(FocusEvent e) {
	            if (passwordField_1.getText().equals(initialText3)) {
	            	passwordField_1.setText("");
	            	passwordField_1.setBorder(compoundBorder2);
	               passwordField_1.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	         }
	         
	         public void focusLost(FocusEvent e) {
	            if (passwordField_1.getText().isEmpty()) {
	            	passwordField_1.setText(initialText3);
	            	passwordField_1.setBorder(compoundBorder);
	            	passwordField_1.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	         }
	      });
	      panel_2.add(passwordField_1);

	      
	     JLabel lblNewLabel_1 = new JLabel("");
	     lblNewLabel_1.setBounds(50, 100, 35, 35);
	     ImageIcon passwordicon = new ImageIcon("image/자물쇠.png");
		 Image passwordimage = passwordicon.getImage();
		 Image passwordimage2 = passwordimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		 ImageIcon passwordicon2 = new ImageIcon(passwordimage2);
	     lblNewLabel_1.setIcon(passwordicon2);
	     panel_2.add(lblNewLabel_1);
	      
	      idField = new JTextField();
	      idField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
	      idField.setBorder(compoundBorder);
	      idField.setText(  model.getCurrentUser().getUsername());
	      idField.setColumns(10);
	      idField.setBounds(95, 55, 250, 30);
	      String initialText = model.getCurrentUser().getUsername();
	      if (!idField.hasFocus() && idField.getText().equals(initialText)) {
	         idField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	      }
	      idField.addFocusListener(new FocusAdapter() {
	         public void focusGained(FocusEvent e) {
	            if (idField.getText().equals(initialText)) {
	               idField.setText("");
	               idField.setBorder(compoundBorder2);
	               idField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	         }
	         
	         public void focusLost(FocusEvent e) {
	            if (idField.getText().isEmpty()) {
	               idField.setText(initialText);
	               idField.setBorder(compoundBorder);
	               idField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	         }
	      });
	      panel_2.add(idField);
	      
	      JLabel lblNewLabel = new JLabel("");
	     lblNewLabel.setIcon(loginicon2);
	      lblNewLabel.setBounds(50, 50, 35, 35);
	      panel_2.add(lblNewLabel);
	      
	      birthField = new JTextField();
	      birthField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
	      birthField.setBorder(compoundBorder);
	      birthField.setText( model.getCurrentUser().getBirth());
	      birthField.setColumns(10);
	      birthField.setBounds(95, 255, 250, 30);
	      String initialText6 = model.getCurrentUser().getBirth();
	      if (!birthField.hasFocus() && birthField.getText().equals(initialText6)) {
	         birthField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	      }
	      birthField.addFocusListener(new FocusAdapter() {
	         public void focusGained(FocusEvent e) {
	            if (birthField.getText().equals(initialText6)) {
	               birthField.setText("");
	               birthField.setBorder(compoundBorder2);
	               birthField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	         }
	         
	         public void focusLost(FocusEvent e) {
	            if (birthField.getText().isEmpty()) {
	               birthField.setText(initialText6);
	               birthField.setBorder(compoundBorder);
	               birthField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	         }
	      });
	      panel_2.add(birthField);
	      
	      JLabel lblNewLabel_2_1_3 = new JLabel("");
	      ImageIcon birthicon = new ImageIcon("image/달력.png");
	     Image birthimage = birthicon.getImage();
	     Image birthimage2 = birthimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	     ImageIcon birthicon2 = new ImageIcon(birthimage2);
	     lblNewLabel_2_1_3.setIcon(birthicon2);
	      lblNewLabel_2_1_3.setBounds(50, 250, 35, 35);
	      panel_2.add(lblNewLabel_2_1_3);

	     
	    JLabel modifylbl = new JLabel(""); 
	    modifylbl.setBounds(50, 320, 120, 40);
	    ImageIcon modifyicon = new ImageIcon("image/정보수정.jpg");
	    Image modifyimage = modifyicon.getImage();
	    Image modifyimage2 = modifyimage.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
	    ImageIcon modifyicon2 = new ImageIcon(modifyimage2);
	    modifylbl.setIcon(modifyicon2);
	    panel_2.add(modifylbl);
	    
	    modifylbl.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		UserEntity updateUserEntity = new UserEntity(model.getCurrentUserId(), 
	    				idField.getText(), passwordField_1.getText(), nicknameField.getText(), 
	    				PhoneField.getText(), birthField.getText());
	    		model.updateUserData(updateUserEntity);
	    		JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.", "회원정보수정", 
	    				JOptionPane.INFORMATION_MESSAGE);
	    		controller.showCard("mypage");
	    	}
		});
	    UserEntity currentUser = model.getCurrentUser();
	    lblMypage.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		controller.showCard("mypage");
	    	}
	    });
	    
	    JLabel logoutlbl = new JLabel(""); 
		logoutlbl.setBounds(225, 320, 120, 40);
		ImageIcon logouticon = new ImageIcon("image/로그아웃.jpg");
		Image logoutimage = logouticon.getImage();
		Image logoutimage2 = logoutimage.getScaledInstance(120, 40, Image.SCALE_SMOOTH);
		ImageIcon logouticon2 = new ImageIcon(logoutimage2);
		logoutlbl.setIcon(logouticon2);
		panel_2.add(logoutlbl);
	    
	    logoutlbl.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		model.logout();
	    		controller.showCard("login");
	    		JOptionPane.showMessageDialog(null, "로그아웃되었습니다.", "로그아웃", 
	    				JOptionPane.INFORMATION_MESSAGE);
	    	}
		});
	}
}
