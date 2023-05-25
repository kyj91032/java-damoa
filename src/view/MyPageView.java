package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
		panel.setBounds(0, 0, 400, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("nickname");
		lblNewLabel.setBounds(56, 6, 126, 38);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("프로필 보기");
		btnNewButton.setBounds(256, 12, 117, 29);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 500, 400, 70);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 4));
		
		JButton btnNewButton_2 = new JButton("홈");
		btnNewButton_2.setBackground(Color.WHITE);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(Color.WHITE);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.setBackground(Color.WHITE);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("마이페이지");
		btnNewButton_1.setBackground(Color.WHITE);
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 50, 400, 450);
		add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel("나의 참여");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		lblNewLabel_1.setBounds(0, 0, 381, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("참여내역");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 25, 381, 49);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("관심목록");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(0, 75, 381, 49);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("내 모집");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setBounds(0, 125, 381, 49);
		panel_2.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("기타");
		lblNewLabel_1_1.setBounds(0, 175, 381, 25);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("알림설정");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setBounds(0, 200, 381, 49);
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("고객센터");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setBounds(0, 250, 381, 49);
		panel_2.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("신고내역");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setBounds(0, 300, 381, 49);
		panel_2.add(lblNewLabel_2_2_1_1);
		
	}
}
