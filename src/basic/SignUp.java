package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class SignUp extends JPanel {

	private Statement stmt;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public SignUp(Statement stmt) {
		
		this.stmt = stmt;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.setBounds(50, 502, 300, 29);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(50, 42, 57, 15);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(50, 67, 290, 30);
		add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(50, 109, 57, 15);
		add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(50, 136, 290, 30);
		add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("비밀번호 확인");
		lblNewLabel_1_1.setBounds(50, 178, 75, 15);
		add(lblNewLabel_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(50, 205, 290, 30);
		add(passwordField_1);
		
		JLabel lblNewLabel_2 = new JLabel("닉네임");
		lblNewLabel_2.setBounds(50, 247, 57, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("전화번호");
		lblNewLabel_2_1.setBounds(50, 316, 57, 15);
		add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(50, 274, 290, 30);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(50, 343, 290, 30);
		add(textField_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("주소");
		lblNewLabel_2_1_1.setBounds(50, 385, 57, 15);
		add(lblNewLabel_2_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(50, 412, 75, 27);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(137, 412, 75, 27);
		add(comboBox_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(224, 412, 75, 27);
		add(comboBox_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 0, 388, 564);
		add(scrollPane);
		
		
		
	}
}
