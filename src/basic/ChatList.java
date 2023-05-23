package basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChatList extends JPanel {
	private Statement stmt;
	private App app;
	private DefaultListModel listModel;

	public ChatList(Statement stmt, App app) {
		this.stmt = stmt;
		this.app = app;

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		btnPanel();

		ListPanel();
		
		TopPanel();

	}
	
	private void TopPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
		
		JLabel dmlbl = new JLabel();
		dmlbl.setBackground(new Color(240, 240, 240));
		dmlbl.setBounds(12, 2, 45, 45);
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

	private void ListPanel() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); scrollPane.setBounds(0, 50, 400, 452);
		add(scrollPane);
		  
		listModel = new DefaultListModel<>(); 
		  	listModel.addElement("항목 1");
		  	listModel.addElement("항목 2"); 
		  	listModel.addElement("항목 3");
		  
		JList list = new JList(listModel); 
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2)
				app.showCard("chat");
			}
		});
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
				app.showCard("home"); // 홈 버튼 누르면 홈 화면 보여줌
			}
		});

		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.showCard("chatlist");
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
				app.showCard("login"); // if 로그인이 안돼있다면 실행으로. 추가 예정
			}
		});
	}
}
