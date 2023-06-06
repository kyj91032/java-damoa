package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.Controller;
import model.Model;
import model.PostEntity;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PostView extends JPanel {
	
	private Controller controller;
	private Model model;
	private PostEntity currentPost;
	
	public PostView(Model model, Controller controller, PostEntity currentPost) {
		this.controller = controller;
		this.model = model;
		this.currentPost = currentPost;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
				
		TopPanel();
		
		CenterPanel();
		
		setLayout(null);
	}
	
	private void TopPanel() { 
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(228, 204, 255));
		panel.setBounds(0, 0, 400, 70);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(currentPost.getTitle());
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 23, 340, 40);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.showCard("home");
			}
		});
		ImageIcon backicon = new ImageIcon("image/홈3.png");
		Image backimage = backicon.getImage();
		Image	backimage2 = backimage.getScaledInstance(35	, 35, Image.SCALE_SMOOTH);
		ImageIcon backicon2 = new ImageIcon(backimage2);
		lblNewLabel_2.setIcon(backicon2);
		lblNewLabel_2.setBounds(340, 20, 35, 35);
		panel.add(lblNewLabel_2);
	}

	private void CenterPanel() {
		JPanel panel = new JPanel();
	      panel.setBackground(new Color(255, 255, 255));
	      panel.setBounds(0, 0, 400, 570);
	      add(panel);
	      panel.setLayout(null);
	      
	      String postusername = model.getNicknameById(currentPost.getUserId());
	      
	      JPanel panel_3 = new JPanel();
	      panel_3.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(228, 204, 255)));
	      panel_3.setBackground(new Color(255, 255, 255));
	      panel_3.setBounds(10, 310, 380, 180);
	      panel.add(panel_3);
	      panel_3.setLayout(null);
	      
	      JLabel usernameLabel = new JLabel("닉네임 : " +  postusername);
	      usernameLabel.setBounds(10, 10, 300, 25);
	      usernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
	      panel_3.add(usernameLabel);
	      
	      JLabel kategorieLabel = new JLabel("카테고리 : " + currentPost.getKategorie());
	      kategorieLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
	      kategorieLabel.setBounds(10, 35, 300, 25);
	      panel_3.add(kategorieLabel);
	      
	      JLabel textLabel = new JLabel(currentPost.getTextarea());
	      textLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
	      textLabel.setBounds(10, 70, 360, 100);
	      textLabel.setHorizontalAlignment(SwingConstants.LEFT);
	      textLabel.setVerticalAlignment(SwingConstants.TOP);
	      panel_3.add(textLabel);
	      
	      JLabel lblNewLabel = new JLabel("");
	      ImageIcon delicon = new ImageIcon("image/삭제.jpg");
		  Image delimage = delicon.getImage();
		  Image	delimage2 = delimage.getScaledInstance(150, 40, Image.SCALE_SMOOTH);
		  ImageIcon delicon2 = new ImageIcon(delimage2);
		  lblNewLabel.setIcon(delicon2);
	      lblNewLabel.setBounds(35, 500, 150, 40);
	      panel.add(lblNewLabel);
	      lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(model.getCurrentUserId() == currentPost.getUserId()) {
						model.deletePost(currentPost.getPostId());
						controller.showCard("home");
					} else {
						JOptionPane.showMessageDialog(null, "글 작성자가 아닙니다.", "글 삭제 실패", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
	      });
	      
	      
	      JLabel lblNewLabel_1 = new JLabel("");
	      ImageIcon joinicon = new ImageIcon("image/채팅참여.jpg");
		  Image joinimage = joinicon.getImage();
		  Image	joinimage2 = joinimage.getScaledInstance(150, 40, Image.SCALE_SMOOTH);
		  ImageIcon joinicon2 = new ImageIcon(joinimage2);
		  lblNewLabel_1.setIcon(joinicon2);
	      lblNewLabel_1.setBounds(215, 500, 150, 40);
	      panel.add(lblNewLabel_1);
	      lblNewLabel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(model.isjoinuser(model.getCurrentUserId(), currentPost.getPostId())) {
						JOptionPane.showMessageDialog(null, "이미 참여중인 채팅방입니다.", "채팅방 참여 실패", JOptionPane.ERROR_MESSAGE);
					} else {
						model.userJoinChat(currentPost.getPostId());
						controller.showCard("chatlist");
					}
					
					
				}
	      });
	      
	      JLabel lblNewLabel_3 = new JLabel("");
	      ImageIcon icon = new ImageIcon(currentPost.getImage());
		  Image image = icon.getImage();
		  Image	image2 = image.getScaledInstance(322, 224, Image.SCALE_SMOOTH);
		  ImageIcon icon2 = new ImageIcon(image2);
		  lblNewLabel_3.setIcon(icon2);
	      lblNewLabel_3.setBounds(39, 80, 322, 224);
	      panel.add(lblNewLabel_3);
		
		
	}
}
