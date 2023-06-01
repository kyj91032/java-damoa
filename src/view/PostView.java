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
	      panel.setBounds(0, 0, 400, 570);
	      add(panel);
	      panel.setLayout(null);
	      
	      JPanel panel_1 = new JPanel();
	      panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
	      panel_1.setBackground(new Color(255, 255, 255));
	      panel_1.setBounds(10, 10, 380, 245);
	      panel.add(panel_1);
	      panel_1.setLayout(null);
	      
	      
	      if (currentPost != null) {
	          JLabel ImageLabel = new JLabel("New label");
	          byte[] imgData = currentPost.getImage();
	          if (imgData != null) {
	              ImageIcon imageIcon = new ImageIcon(imgData);
	              Image image = imageIcon.getImage();
	              int labelWidth = 380; // 라벨의 너비 값을 직접 설정
	              int imageHeight = 245;
	              Image scaledImage = image.getScaledInstance(labelWidth, imageHeight, Image.SCALE_SMOOTH);
	              ImageIcon scaledIcon = new ImageIcon(scaledImage);
	              ImageLabel.setIcon(scaledIcon);
	          }
	          ImageLabel.setBounds(0, 0, 380, 245);
	          panel_1.add(ImageLabel);
	      }

	      String postusername = model.getNicknameById(currentPost.getUserId());
	      
	      JPanel panel_2 = new JPanel();
	      panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
	      panel_2.setBackground(new Color(255, 255, 255));
	      panel_2.setBounds(10, 260, 380, 55);
	      panel.add(panel_2);
	      panel_2.setLayout(null);
	      
	      JLabel lblNewLabel_2 = new JLabel("New label");
	      lblNewLabel_2.setBounds(10, 0, 70, 55);
	      panel_2.add(lblNewLabel_2);
	      
	      JLabel NicknameLabel = new JLabel(postusername + "님의 모집");
	      NicknameLabel.setBounds(85, 5, 125, 25);
	      panel_2.add(NicknameLabel);
	      NicknameLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 13));
	      
	      JLabel RegoinLabel = new JLabel(currentPost.getRegion());
	      RegoinLabel.setBounds(85, 28, 70, 25);
	      panel_2.add(RegoinLabel);
	      RegoinLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 12));
	      
	      JLabel SpecificRegionLabel = new JLabel (currentPost.getSpecificRegion());
	      SpecificRegionLabel.setBounds(155, 28, 70, 25);
	      panel_2.add(SpecificRegionLabel);
	      SpecificRegionLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 12));
	      
	      JPanel panel_3 = new JPanel();
	      panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
	      panel_3.setBackground(new Color(255, 255, 255));
	      panel_3.setBounds(10, 320, 380, 200);
	      panel.add(panel_3);
	      panel_3.setLayout(null);
	      
	      JLabel TextAreaLabel = new JLabel(currentPost.getTextarea());
	      TextAreaLabel.setBounds(10, 75, 370, 125);
	      panel_3.add(TextAreaLabel);
	      TextAreaLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 12));
	      TextAreaLabel.setVerticalAlignment(SwingConstants.TOP);
	      
	      JLabel TitleLabel = new JLabel(currentPost.getTitle());
	      TitleLabel.setBounds(15, 10, 353, 30);
	      panel_3.add(TitleLabel);
	      TitleLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 16));
	      
	      JLabel kategorieLabel = new JLabel(currentPost.getKategorie());
	      kategorieLabel.setBounds(15, 45, 100, 25);
	      panel_3.add(kategorieLabel);
	      kategorieLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 13));
	      
	      JLabel lblNewLabel = new JLabel("삭제하기");
	      lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	      lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	      lblNewLabel.setBounds(16, 526, 165, 38);
	      panel.add(lblNewLabel);
	      
	      JLabel lblNewLabel_1 = new JLabel("채팅방 참여하기");
	      lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	      lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	      lblNewLabel_1.setBounds(215, 526, 165, 38);
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
