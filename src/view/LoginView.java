package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.Model;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginView extends JPanel {
	
	private JTextField textField;
	private JPasswordField passwordField;
	
	private Model model;
	private Controller controller;

	
	public LoginView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		showTopPanel();
		
		showLoginView();
		
		showBtnPanel();
	
	}

	private void showTopPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
		
		ImageIcon scicon = new ImageIcon("image/돋보기.jpeg");
		Image scimage = scicon.getImage();
		Image scimage2 = scimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon scicon2 = new ImageIcon(scimage2);
		
		ImageIcon liicon = new ImageIcon("image/목록.jpeg");
		Image liimage = liicon.getImage();
		Image liimage2 = liimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon liicon2 = new ImageIcon(liimage2);
		
		ImageIcon alicon = new ImageIcon("image/종.jpeg");
		Image alimage = alicon.getImage();
		Image alimage2 = alimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon alicon2 = new ImageIcon(alimage2);	
		
		JLabel dmlbl = new JLabel();
		dmlbl.setBackground(new Color(240, 240, 240));
		dmlbl.setBounds(12, 0, 50, 50);
		panel_1.add(dmlbl);
		dmlbl.setIcon(daicon2);
		
		JLabel lblNewLabel = new JLabel("세상 사람");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(74, 0, 58, 23);
		panel_1.add(lblNewLabel);
		
		JLabel lblDamoa = new JLabel("damoa");
		lblDamoa.setFont(new Font("Franklin Gothic Book", Font.BOLD | Font.ITALIC, 18));
		lblDamoa.setBounds(124, 17, 78, 23);
		panel_1.add(lblDamoa);
		
		
	}

	private void showLoginView() {
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(49, 160, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(49, 250, 57, 15);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(49, 185, 290, 30);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(49, 275, 290, 30);
		add(passwordField);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBounds(242, 334, 97, 23);
		add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() { // 로그인 버튼 누를 시 로그인 시도
		    public void actionPerformed(ActionEvent e) {
		        String username = textField.getText(); // 아이디 저장
		        String password = new String(passwordField.getPassword()); // 비번 저장
		        if (model.checkLogin(username, password)) {
		            System.out.println("로그인 성공!");
		            controller.showCard("home");
		            textField.setText("");
		            passwordField.setText("");
		        } else {
		            System.out.println("로그인 실패!");
		            textField.setText("");
		            passwordField.setText("");
		        }
		    }
		});
		
		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.setBounds(242, 367, 97, 23);
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCard("signup");
			}
		});
	}
	
	private void showBtnPanel() {
		JPanel panel1 = new JPanel();
		panel1.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(192, 192, 192)));
	    panel1.setBackground(new Color(207, 197, 255));
	    panel1.setBounds(0, 507, 400, 63);
	    add(panel1);

	    JLabel lblHome = new JLabel();
	    lblHome.setBounds(10, 1, 80, 60);
	    lblHome.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		" home ", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblHome.getBorder()).setTitleFont(titleFont);
	    ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
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
	    lblRecruitment.setBounds(110, 1, 80, 60);
	    lblRecruitment.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		"\uB4F1\uB85D", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont1 = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblRecruitment.getBorder()).setTitleFont(titleFont1);
	    ImageIcon posticon = new ImageIcon("image/postbutton3.png");
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
	    lblChat.setBounds(210, 1, 80, 60);
	    lblChat.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		"\uCC44\uD305", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont2 = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblChat.getBorder()).setTitleFont(titleFont2);
	    ImageIcon chaticon = new ImageIcon("image/chatbutton.png");
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

	    JLabel lblMypage = new JLabel();
	    lblMypage.setBounds(310, 1, 80, 60);
	    lblMypage.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		"MY", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont3 = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblMypage.getBorder()).setTitleFont(titleFont3);
	    ImageIcon mypageicon = new ImageIcon("image/mypage.png");
	    Image imgmypage = mypageicon.getImage();
	    Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
		lblMypage.setIcon(iconmypage2);
	    lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
	    lblMypage.setBackground(new Color(201, 219, 178));
	    panel1.add(lblMypage);
	    lblMypage.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		controller.showCard("mypage");       
	    	}
	    });
	}
}
