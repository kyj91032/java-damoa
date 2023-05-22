package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ChatList extends JPanel {
	private Statement stmt;
	private App app;
	
	public ChatList(Statement stmt, App app) {
		this.stmt = stmt;
		this.app = app;
		

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		btnPanel();
		
		
		ListPanel();
		
	}



	private void ListPanel() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 400, 502);
		add(scrollPane);
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
	            app.showCard("Card2"); // 홈 버튼 누르면 홈 화면 보여줌
	        }
	    });

		
		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.showCard("Card5");
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
	            app.showCard("Card3"); // if 로그인이 안돼있다면 실행으로. 추가 예정
	        }
	    });
}
	}
