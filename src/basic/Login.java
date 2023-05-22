package basic;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridLayout;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login() {
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
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
		
		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.setBounds(242, 367, 97, 23);
		add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 500, 400, 70);
		add(panel);
		panel.setLayout(new GridLayout(1, 4));
		
		JButton btnNewButton_2 = new JButton("홈");
		btnNewButton_2.setBackground(Color.WHITE);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(Color.WHITE);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.setBackground(Color.WHITE);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("마이페이지");
		btnNewButton_5.setBackground(Color.WHITE);
		panel.add(btnNewButton_5);
		
		
	}
}