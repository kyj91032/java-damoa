package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
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

public class MyPageView extends JPanel {
	
	private Model model;
	private Controller controller;
	private JLabel lblNewLabel_1;
	
	public MyPageView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(221, 238, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(170, 200, 167));
		panel.setBounds(0, 0, 400, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("nickname");
		lblNewLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel.setBounds(56, 6, 126, 38);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("프로필 보기");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(219, 223, 234));
		btnNewButton.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		btnNewButton.setBounds(256, 12, 117, 29);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(201, 219, 178));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 50, 400, 450);
		add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(246, 255, 222));
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel("나의 참여");
		lblNewLabel_1.setBackground(new Color(201, 219, 178));
		lblNewLabel_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_1.setBounds(0, 0, 381, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("참여내역");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 25, 381, 49);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("관심목록");
		lblNewLabel_2_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_2_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(0, 75, 381, 49);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("내 모집");
		lblNewLabel_2_1_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_2_1_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setBounds(0, 125, 381, 49);
		panel_2.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("기타");
		lblNewLabel_1_1.setBackground(new Color(201, 219, 178));
		lblNewLabel_1_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_1_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_1_1.setBounds(0, 175, 381, 25);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("알림설정");
		lblNewLabel_2_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2_2.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_2_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setBounds(0, 200, 381, 49);
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("고객센터");
		lblNewLabel_2_2_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_2_2_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_2_2_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setBounds(0, 250, 381, 49);
		panel_2.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("신고내역");
		lblNewLabel_2_2_1_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_2_2_1_1.setFont(new Font("HY그래픽M", Font.PLAIN, 13));
		lblNewLabel_2_2_1_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setBounds(0, 300, 381, 49);
		panel_2.add(lblNewLabel_2_2_1_1);
		
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
	    lblMypage.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            controller.showCard("mypage"); // 라벨 클릭 시 마이페이지 화면 보여줌
	        }
	    });

	    setVisible(true);
	}
}
