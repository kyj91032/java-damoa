package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class SignUp extends JPanel {

	private Statement stmt;
	private App app;
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public SignUp(Statement stmt, App app) {
		
		this.stmt = stmt;
		this.app = app;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		showTopPanel();
		
		
		showSignupPanel();
		
				
		
		
	}
	
	private void showTopPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
		
		ImageIcon scicon = new ImageIcon("image/돋보기.jpg");
		Image scimage = scicon.getImage();
		Image scimage2 = scimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon scicon2 = new ImageIcon(scimage2);
		
		ImageIcon liicon = new ImageIcon("image/목록.jpg");
		Image liimage = liicon.getImage();
		Image liimage2 = liimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon liicon2 = new ImageIcon(liimage2);
		
		ImageIcon alicon = new ImageIcon("image/종.jpg");
		Image alimage = alicon.getImage();
		Image alimage2 = alimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon alicon2 = new ImageIcon(alimage2);
		
		JLabel lblNewLabel = new JLabel("세상 사람");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(74, 0, 58, 23);
		panel_1.add(lblNewLabel);
		
		JLabel lblDamoa = new JLabel("damoa");
		lblDamoa.setFont(new Font("Franklin Gothic Book", Font.BOLD | Font.ITALIC, 18));
		lblDamoa.setBounds(124, 17, 78, 23);
		panel_1.add(lblDamoa);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(6, -2, 56, 52);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.showCard("Card3");
			}
		});
		
	}
	
	private void showSignupPanel() {
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.setBounds(50, 516, 300, 29);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(50, 80, 57, 15);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(50, 107, 290, 30);
		add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(50, 149, 57, 15);
		add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(50, 176, 290, 30);
		add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("비밀번호 확인");
		lblNewLabel_1_1.setBounds(50, 218, 75, 15);
		add(lblNewLabel_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(50, 245, 290, 30);
		add(passwordField_1);
		
		JLabel lblNewLabel_2 = new JLabel("닉네임");
		lblNewLabel_2.setBounds(50, 287, 57, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("전화번호");
		lblNewLabel_2_1.setBounds(50, 356, 57, 15);
		add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(50, 314, 290, 30);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(50, 383, 290, 30);
		add(textField_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("주소");
		lblNewLabel_2_1_1.setBounds(50, 425, 57, 15);
		add(lblNewLabel_2_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(50, 452, 75, 27);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(137, 452, 75, 27);
		add(comboBox_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(224, 452, 75, 27);
		add(comboBox_1_1);
	}

	
}
