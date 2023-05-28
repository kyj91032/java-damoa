package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.JToolBar;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeView extends JPanel {
	
	private Controller controller;
	
	public HomeView(Controller controller) {
		
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(Color.white);
		
		
		TopPanel();
		
		Centerbtn();
		
		btnPanel();
		
	}

	
	
	private void TopPanel() {
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
		
		JButton listbtn = new JButton();
		listbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("리스트 창으로 이동");
			}
		});
		listbtn.setForeground(new Color(255, 255, 255));
		listbtn.setBounds(317, 10, 30, 30);
		listbtn.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(listbtn);
		listbtn.setIcon(liicon2);
		
		JButton alrimbtn = new JButton();
		alrimbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("알림 창으로 이동");
			}
		});
		alrimbtn.setBounds(358, 10, 30, 30);
		alrimbtn.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(alrimbtn);
		alrimbtn.setIcon(alicon2);
		
		JButton searchbtn = new JButton();
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("검색 창으로 연결");
			}
		});
		searchbtn.setBounds(276, 10, 30, 30);
		searchbtn.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(searchbtn);
		searchbtn.setIcon(scicon2);
		
		JLabel lblNewLabel = new JLabel("세상 사람");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(74, 0, 58, 23);
		panel_1.add(lblNewLabel);
		
		JLabel lblDamoa = new JLabel("damoa");
		lblDamoa.setFont(new Font("Franklin Gothic Book", Font.BOLD | Font.ITALIC, 18));
		lblDamoa.setBounds(124, 17, 78, 23);
		panel_1.add(lblDamoa);
		
		
	}

	
	
	
	private void Centerbtn() {
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 50, 400, 450);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 400, 450);
		panel_2.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JList list = new JList();
		
		scrollPane.setViewportView(list);
	}
	

	private void btnPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 500, 400, 70);
		add(panel);
		panel.setLayout(new GridLayout(1, 4));
		
		JButton btnNewButton_2 = new JButton("홈");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            controller.showCard("home"); // 홈 버튼 누르면 홈 화면 보여줌
	        }
	    });

		
		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showCard("chatlist");
			}
		});
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("마이페이지");
		btnNewButton.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton);
		setVisible(true);
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            controller.showCard("login"); // if 로그인이 안돼있다면 실행으로. 추가 예정
	        }
	    });
	}
}
