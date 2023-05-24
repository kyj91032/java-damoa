package Test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChatList extends JPanel {
	private Statement stmt;
	private App app;
	private DefaultListModel listModel;
	private Chat chat;
	private String label;
	private Chat[] chats;

	public ChatList(Statement stmt, App app, Chat chat) {
		this.stmt = stmt;
		this.app = app;
		this.chat = chat;
		
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		btnPanel();

		ListPanel();
		
		TopPanel();
		
		createChats(); 

	}
	
	
	public Chat getChat() {
	    return chat;
	}
	
	public String getLabel() {
        return label;
    }
	
	private void createChats() {
	    int numChats = 3; // Chat 객체의 수

	    chats = new Chat[numChats]; // Chat 객체 배열 생성

	    for (int i = 0; i < numChats; i++) {
	        chats[i] = new Chat(stmt, app); // Chat 객체 생성 후 배열 요소에 할당
	    }
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
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(0, 50, 400, 452);
	    add(scrollPane);

	    ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
	    Image daimage = daicon.getImage();
	    Image daimage2 = daimage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    ImageIcon daicon2 = new ImageIcon(daimage2);

	    ImageIcon scicon = new ImageIcon("image/돋보기.jpeg");
	    Image scimage = scicon.getImage();
	    Image scimage2 = scimage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    ImageIcon scicon2 = new ImageIcon(scimage2);

	    ImageIcon liicon = new ImageIcon("image/목록.jpeg");
	    Image liimage = liicon.getImage();
	    Image liimage2 = liimage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    ImageIcon liicon2 = new ImageIcon(liimage2);

	    ImageIcon alicon = new ImageIcon("image/종.jpeg");
	    Image alimage = alicon.getImage();
	    Image alimage2 = alimage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    ImageIcon alicon2 = new ImageIcon(alimage2);

	    listModel = new DefaultListModel<>();
	    listModel.addElement(new ImageLabelItem(alicon2, "항목 1", chats[0].getChat())); // Chat 객체 전달
	    listModel.addElement(new ImageLabelItem(liicon2, "항목 2", chats[1].getChat())); // Chat 객체 전달
	    listModel.addElement(new ImageLabelItem(scicon2, "항목 3", chats[2].getChat())); // Chat 객체 전달

	    JList<ImageLabelItem> list = new JList<>(listModel);
	    list.setCellRenderer(new ImageLabelListCellRenderer());
	    list.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                ImageLabelItem selectedItem = list.getSelectedValue();
	                Chat selectedChat = selectedItem.getChat();
	                // Chat 객체 사용
	                app.showCard("chat");
	            }
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

		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_3);

		JButton btnNewButton = new JButton("마이페이지");
		btnNewButton.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton);
		setVisible(true);
	}
}
