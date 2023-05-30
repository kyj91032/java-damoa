package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
		btnPanel();
	}
	
	private void TopPanel() { 
		setLayout(null);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
	}

	private void CenterPanel() {
		JPanel panel = new JPanel();
	      panel.setBackground(new Color(207, 197, 255));
	      panel.setBounds(0, 0, 400, 564);
	      add(panel);
	      panel.setLayout(null);
	      
	      JPanel panel_1 = new JPanel();
	      panel_1.setBackground(new Color(255, 255, 255));
	      panel_1.setBounds(6, 5, 388, 197);
	      panel.add(panel_1);
	      panel_1.setLayout(null);
	      
	      
	      if (currentPost != null) {
	          JLabel ImageLabel = new JLabel("New label");
	          byte[] imgData = currentPost.getImage();
	          if (imgData != null) {
	              ImageIcon imageIcon = new ImageIcon(imgData);
	              Image image = imageIcon.getImage();
	              int labelWidth = 376; // 라벨의 너비 값을 직접 설정
	              int imageHeight = 156; 
	              Image scaledImage = image.getScaledInstance(labelWidth, imageHeight, Image.SCALE_SMOOTH);
	              ImageIcon scaledIcon = new ImageIcon(scaledImage);
	              ImageLabel.setIcon(scaledIcon);
	          }
	          ImageLabel.setBounds(0, 0, 376, 156);
	          panel_1.add(ImageLabel);
	      }

	      String postusername = model.getNicknameById(currentPost.getUserId());
	      
	      JPanel panel_2 = new JPanel();
	      panel_2.setBackground(new Color(255, 255, 255));
	      panel_2.setBounds(6, 203, 388, 38);
	      panel.add(panel_2);
	      panel_2.setLayout(null);
	      
	      JLabel TitleLabel = new JLabel(currentPost.getTitle());
	      TitleLabel.setFont(new Font("HY그래픽M", Font.BOLD, 16));
	      TitleLabel.setBounds(6, 6, 230, 32);
	      panel_2.add(TitleLabel);
	      
	      JLabel NicknameLabel = new JLabel(postusername + "님의 모집");
	      NicknameLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 12));
	      NicknameLabel.setBounds(255, 7, 127, 32);
	      panel_2.add(NicknameLabel);
	      
	      JPanel panel_3 = new JPanel();
	      panel_3.setBackground(new Color(255, 255, 255));
	      panel_3.setBounds(6, 243, 388, 254);
	      panel.add(panel_3);
	      panel_3.setLayout(null);
	      
	      JLabel TextAreaLabel = new JLabel(currentPost.getTextarea());
	      TextAreaLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 12));
	      TextAreaLabel.setVerticalAlignment(SwingConstants.TOP);
	      TextAreaLabel.setBounds(12, 21, 364, 179);
	      panel_3.add(TextAreaLabel);
	      
	      JLabel kategorieLabel = new JLabel(currentPost.getKategorie());
	      kategorieLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 14));
	      kategorieLabel.setBounds(12, 175, 70, 25);
	      panel_3.add(kategorieLabel);
	      
	      JLabel RegoinLabel = new JLabel(currentPost.getRegion());
	      RegoinLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 12));
	      RegoinLabel.setBounds(228, 175, 70, 22);
	      panel_3.add(RegoinLabel);
	      
	      JLabel SpecificRegionLabel = new JLabel (currentPost.getSpecificRegion()); 
	      SpecificRegionLabel.setFont(new Font("HY그래픽M", Font.PLAIN, 12));
	      SpecificRegionLabel.setBounds(302, 175, 70, 22);
	      panel_3.add(SpecificRegionLabel);
	      
	      JLabel lblNewLabel = new JLabel("삭제하기");
	      lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	      lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	      lblNewLabel.setBounds(16, 509, 165, 38);
	      panel.add(lblNewLabel);
	      
	      JLabel lblNewLabel_1 = new JLabel("채팅방 참여하기");
	      lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	      lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	      lblNewLabel_1.setBounds(215, 509, 165, 38);
	      panel.add(lblNewLabel_1);
		
		
	}
	
	private void btnPanel() {
	    ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
	    Image imghome = homeicon.getImage();
	    Image imghome2 = imghome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgicon2 = new ImageIcon(imghome2);
	    ImageIcon posticon = new ImageIcon("image/postbutton3.png");
	    Image imgpost = posticon.getImage();
	    Image imgpost2 = imgpost.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon posticon2 = new ImageIcon(imgpost2);
	    ImageIcon chaticon = new ImageIcon("image/chatbutton.png");
	    Image imgchat = chaticon.getImage();
	    Image imgchat2 = imgchat.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconchat2 = new ImageIcon(imgchat2);
	    ImageIcon mypageicon = new ImageIcon("image/mypage.png");
	    Image imgmypage = mypageicon.getImage();
	    Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
	}
}
