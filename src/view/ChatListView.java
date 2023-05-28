package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Model;

public class ChatListView extends JPanel {
	
	private Controller controller;
	private DefaultListModel listModel;
	private Model model;

	public ChatListView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		btnPanel();

		ListPanel();

	}

	private void ListPanel() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); scrollPane.setBounds(0, 0, 400, 502);
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
				controller.showCard("chat");
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

	    JLabel lblHome = new JLabel();
	    ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
	    Image imghome = homeicon.getImage();
	    Image imghome2 = imghome.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon imgicon2 = new ImageIcon(imghome2);
	    lblHome.setIcon(imgicon2);
	    lblHome.setHorizontalAlignment(SwingConstants.CENTER);
	    lblHome.setBackground(new Color(255, 255, 255));
	    panel.add(lblHome);
	    lblHome.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            controller.showCard("home"); // 라벨 클릭 시 홈 화면 보여줌
	        }
	    });
	    
	    JLabel lblRecruitment = new JLabel();
	    ImageIcon posticon = new ImageIcon("image/postbutton3.png");
	    Image imgpost = posticon.getImage();
	    Image imgpost2 = imgpost.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon posticon2 = new ImageIcon(imgpost2);
		lblRecruitment.setIcon(posticon2);
	    lblRecruitment.setHorizontalAlignment(SwingConstants.CENTER);
	    lblRecruitment.setBackground(new Color(255, 255, 255));
	    panel.add(lblRecruitment);

	    
	    JLabel lblChat = new JLabel();
	    ImageIcon chaticon = new ImageIcon("image/chatbutton.png");
	    Image imgchat = chaticon.getImage();
	    Image imgchat2 = imgchat.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconchat2 = new ImageIcon(imgchat2);
		lblChat.setIcon(iconchat2);
	    lblChat.setHorizontalAlignment(SwingConstants.CENTER);
	    lblChat.setBackground(new Color(255, 255, 255));
	    panel.add(lblChat);
	    lblChat.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            controller.showCard("chatlist"); // 라벨 클릭 시 채팅 화면 보여줌
	        }
	    });

	    JLabel lblMypage = new JLabel();
	    ImageIcon mypageicon = new ImageIcon("image/mypage.png");
	    Image imgmypage = mypageicon.getImage();
	    Image imgmypage2 = imgmypage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
		lblMypage.setIcon(iconmypage2);
	    lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
	    lblMypage.setBackground(new Color(255, 255, 255));
	    panel.add(lblMypage);
	    lblMypage.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            controller.showCard("mypage"); // 라벨 클릭 시 마이페이지 화면 보여줌
	        }
	    });

	    setVisible(true);
	}
}
