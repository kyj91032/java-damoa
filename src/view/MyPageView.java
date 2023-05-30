package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
import model.UserEntity;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class MyPageView extends JPanel {
	
	private Model model;
	private Controller controller;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public MyPageView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(221, 238, 255));
		setLayout(null);
				
		addCenterPanel();
		
		addBtnPanel(controller);

	    setVisible(true);
	}

	private void addCenterPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(170, 200, 167));
		panel.setBounds(0, 0, 400, 50);
		add(panel);
		panel.setLayout(null);
		
		String nickname = model.getNicknameById(model.getCurrentUserId()); // 닉네임 db에서 불러와서 보여줌
		JLabel lblNewLabel = new JLabel(nickname);
		System.out.println(nickname);
		lblNewLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel.setBounds(56, 6, 126, 38);
		panel.add(lblNewLabel);
		
		
		
	}

	
	private void addBtnPanel(Controller controller) { // 하단 4메뉴 생성 메소드
		JPanel panel1 = new JPanel();
	    panel1.setBackground(new Color(201, 219, 178));
	    panel1.setBounds(0, 500, 400, 70);
	    add(panel1);
	    panel1.setLayout(new GridLayout(1, 4));

	    JLabel lblHome = new JLabel();
	    lblHome.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
	    ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
	    Image imghome = homeicon.getImage();
	    Image imghome2 = imghome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgicon2 = new ImageIcon(imghome2);
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
	    lblRecruitment.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
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
	    lblChat.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
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
	    ImageIcon mypageicon = new ImageIcon("image/mypage.png");
	    Image imgmypage = mypageicon.getImage();
	    Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
		lblMypage.setIcon(iconmypage2);
	    lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
	    lblMypage.setBackground(new Color(201, 219, 178));
	    panel1.add(lblMypage);
	    
	    JPanel panel_2 = new JPanel();
	    panel_2.setLayout(null);
	    panel_2.setBackground(new Color(246, 255, 222));
	    panel_2.setBounds(0, 49, 400, 454);
	    add(panel_2);
	    
	    JLabel lblNewLabel_2_2_1_1_1 = new JLabel("로그아웃");
	    lblNewLabel_2_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2_2_1_1_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
	    lblNewLabel_2_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	    lblNewLabel_2_2_1_1_1.setBackground(Color.WHITE);
	    lblNewLabel_2_2_1_1_1.setBounds(219, 380, 140, 46);
	    panel_2.add(lblNewLabel_2_2_1_1_1);
	    
	    JLabel lblNewLabel_2_2_1_1_1_1 = new JLabel("정보 수정하기");
	    lblNewLabel_2_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2_2_1_1_1_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
	    lblNewLabel_2_2_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	    lblNewLabel_2_2_1_1_1_1.setBackground(Color.WHITE);
	    lblNewLabel_2_2_1_1_1_1.setBounds(37, 380, 140, 46);
	    panel_2.add(lblNewLabel_2_2_1_1_1_1);
	    
	    JLabel lblNewLabel_1 = new JLabel("아이디");
	    lblNewLabel_1.setBounds(37, 44, 57, 15);
	    panel_2.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
	    lblNewLabel_1_1.setBounds(37, 87, 57, 15);
	    panel_2.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("닉네임");
	    lblNewLabel_1_2.setBounds(37, 131, 57, 15);
	    panel_2.add(lblNewLabel_1_2);
	    
	    JLabel lblNewLabel_1_3 = new JLabel("전화번호");
	    lblNewLabel_1_3.setBounds(37, 176, 57, 15);
	    panel_2.add(lblNewLabel_1_3);
	    
	    JLabel lblNewLabel_1_4 = new JLabel("생년월일");
	    lblNewLabel_1_4.setBounds(37, 224, 57, 15);
	    panel_2.add(lblNewLabel_1_4);
	    
	    
	    UserEntity currentUser = model.getCurrentUser();
	    
	    textField = new JTextField(currentUser.getUsername());
	    textField.setBounds(172, 41, 116, 21);
	    panel_2.add(textField);
	    textField.setColumns(10);
	    
	    textField_1 = new JTextField(currentUser.getUserpw());
	    textField_1.setColumns(10);
	    textField_1.setBounds(172, 84, 116, 21);
	    panel_2.add(textField_1);
	    
	    textField_2 = new JTextField(currentUser.getNickname());
	    textField_2.setColumns(10);
	    textField_2.setBounds(172, 128, 116, 21);
	    panel_2.add(textField_2);
	    
	    textField_3 = new JTextField(currentUser.getPhone());
	    textField_3.setColumns(10);
	    textField_3.setBounds(172, 173, 116, 21);
	    panel_2.add(textField_3);
	    
	    textField_4 = new JTextField(currentUser.getBirth());
	    textField_4.setColumns(10);
	    textField_4.setBounds(172, 221, 116, 21);
	    panel_2.add(textField_4);
	    lblMypage.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	controller.showCard("mypage");
	        }
	    });
	}
}
