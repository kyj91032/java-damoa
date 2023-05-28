package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

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
		        
		        // 여기에서 DB와 비교하여 로그인 처리를 수행하는 로직을 구현
		        if (model.checkLogin(username, password)) {
		            // 로그인 성공 시 처리할 코드를 작성 예정
		            System.out.println("로그인 성공!");
		            controller.showCard("home");
		        } else {
		            // 로그인 실패 시 처리할 코드를 작성 예정
		            System.out.println("로그인 실패!");
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
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 500, 400, 70);
		add(panel);
		panel.setLayout(new GridLayout(1, 4));
		
		JButton btnNewButton_2 = new JButton("홈");
		btnNewButton_2.setBackground(Color.WHITE);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            controller.showCard("home"); // 홈 버튼 누르면 홈 화면 보여줌
	        }
	    });
		
		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(Color.WHITE);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCard("chatlist");
			}
		});
		btnNewButton_3.setBackground(Color.WHITE);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("마이페이지");
		btnNewButton_5.setBackground(Color.WHITE);
		panel.add(btnNewButton_5);
	}

	
	
	

}