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
      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
      panel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
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

   private void CenterPanel() {
      
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setBounds(0, 50, 400, 451);
      add(panel);
      panel.setLayout(null);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBounds(12, 45, 376, 156);
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

      
      
      JPanel panel_2 = new JPanel();
      panel_2.setBounds(12, 10, 376, 25);
      panel.add(panel_2);
      panel_2.setLayout(null);
      
      JLabel TitleLabel = new JLabel(currentPost.getTitle());
      TitleLabel.setFont(new Font("한컴 고딕", Font.BOLD, 20));
      TitleLabel.setBounds(0, 0, 301, 25);
      panel_2.add(TitleLabel);
      
      JLabel NicknameLabel = new JLabel(model.getNicknameById(currentPost.getUserId()) + "님의 모집");
      NicknameLabel.setBounds(302, 0, 74, 25);
      panel_2.add(NicknameLabel);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBounds(12, 211, 376, 230);
      panel.add(panel_3);
      panel_3.setLayout(null);
      
      JLabel TextAreaLabel = new JLabel(currentPost.getTextArea());
      TextAreaLabel.setVerticalAlignment(SwingConstants.TOP);
      TextAreaLabel.setBounds(0, 0, 376, 200);
      panel_3.add(TextAreaLabel);
      
      JLabel kategorieLabel = new JLabel(currentPost.getKategorie());
      kategorieLabel.setFont(new Font("한컴 고딕", Font.BOLD, 12));
      kategorieLabel.setBounds(10, 205, 70, 25);
      panel_3.add(kategorieLabel);
      
      JLabel RegoinLabel = new JLabel(currentPost.getRegion());
      RegoinLabel.setFont(new Font("한컴 고딕", Font.BOLD, 8));
      RegoinLabel.setBounds(226, 207, 70, 20);
      panel_3.add(RegoinLabel);
      
      JLabel SpecificRegionLabel = new JLabel (currentPost.getSpecificregion()); 
      SpecificRegionLabel.setFont(new Font("한컴 고딕", Font.BOLD, 8));
      SpecificRegionLabel.setBounds(300, 210, 70, 20);
      panel_3.add(SpecificRegionLabel);
      
      
      

       }

   
   private void btnPanel() {
      JPanel panel1 = new JPanel();
       panel1.setBackground(new Color(201, 219, 178));
       panel1.setBounds(0, 500, 400, 70);
       add(panel1);
       panel1.setLayout(new GridLayout(1, 4));

       JLabel lblHome = new JLabel();
       lblHome.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
       ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
       Image imghome = homeicon.getImage();
       Image imghome2 = imghome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
      ImageIcon imgicon2 = new ImageIcon(imghome2);
       lblHome.setIcon(imgicon2);
       lblHome.setHorizontalAlignment(SwingConstants.CENTER);
       lblHome.setBackground(new Color(201, 219, 178));
       panel1.add(lblHome);
       lblHome.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               controller.showCard("home"); // 라벨 클릭 시 홈 화면 보여줌
           }
       });
       
       JLabel lblRecruitment = new JLabel();
       lblRecruitment.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
       ImageIcon posticon = new ImageIcon("image/postbutton3.png");
       Image imgpost = posticon.getImage();
       Image imgpost2 = imgpost.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
      ImageIcon posticon2 = new ImageIcon(imgpost2);
      lblRecruitment.setIcon(posticon2);
       lblRecruitment.setHorizontalAlignment(SwingConstants.CENTER);
       lblRecruitment.setBackground(new Color(201, 219, 178));
       panel1.add(lblRecruitment);
       lblRecruitment.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
               controller.showCard("postformview"); // 라벨 클릭 시 채팅 화면 보여줌
           }
       });

       
       JLabel lblChat = new JLabel();
       lblChat.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
       ImageIcon chaticon = new ImageIcon("image/chatbutton.png");
       Image imgchat = chaticon.getImage();
       Image imgchat2 = imgchat.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
      ImageIcon iconchat2 = new ImageIcon(imgchat2);
      lblChat.setIcon(iconchat2);
       lblChat.setHorizontalAlignment(SwingConstants.CENTER);
       lblChat.setBackground(new Color(201, 219, 178));
       panel1.add(lblChat);
       lblChat.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               controller.showCard("chatlist"); // 라벨 클릭 시 채팅 화면 보여줌
           }
       });

       JLabel lblMypage = new JLabel();
       ImageIcon mypageicon = new ImageIcon("image/mypage.png");
       Image imgmypage = mypageicon.getImage();
       Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
      ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
      lblMypage.setIcon(iconmypage2);
       lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
       lblMypage.setBackground(new Color(201, 219, 178));
       panel1.add(lblMypage);
       lblMypage.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
              controller.showCard("mypage");       
           }
       });
   }
}