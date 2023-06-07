package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import controller.Controller;
import model.Model;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class LoginView extends JPanel {
   
	private JTextField textField; private JPasswordField passwordField; private Model model; private Controller controller; private JTextField idField;



   
   public LoginView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
      
      setPreferredSize(new Dimension(400, 570));
      setBackground(new Color(228, 204, 255));
      setLayout(null);
      
      showLoginView();
      
      showBtnPanel();
   
   }

   private void showLoginView() {
      
        Border roundedBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, emptyBorder);
         
        Border roundedBorder2 = BorderFactory.createLineBorder(new Color(228, 204, 255), 3, true);
        Border emptyBorder2 = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border compoundBorder2 = BorderFactory.createCompoundBorder(roundedBorder2, emptyBorder2);
      
         
         
        textField = new JTextField();
        textField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
        textField.setBorder(compoundBorder);
         textField.setText("아이디");
         textField.setColumns(10);
         textField.setBounds(115, 190, 200, 30);
         String initialText = "아이디";
         if (!textField.hasFocus() && textField.getText().equals(initialText)) {
            textField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
         }
         textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
               if (textField.getText().equals(initialText)) {
                  textField.setText("");
                  textField.setBorder(compoundBorder2);
                  textField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
               }
            }
            
            public void focusLost(FocusEvent e) {
               if (textField.getText().isEmpty()) {
                  textField.setText(initialText);
                  textField.setBorder(compoundBorder);
                  textField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
               }
            }
         });
         add(textField);
      
         JLabel lblNewLabel_1 = new JLabel("");
         ImageIcon passwordicon = new ImageIcon("image/자물쇠.png");
        Image passwordimage = passwordicon.getImage();
        Image passwordimage2 = passwordimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon passwordicon2 = new ImageIcon(passwordimage2);
         lblNewLabel_1.setIcon(passwordicon2);
         lblNewLabel_1.setBounds(80, 245, 35, 35);
         add(lblNewLabel_1);
      
      passwordField = new JPasswordField();
      passwordField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            String username = textField.getText(); // 아이디 
            String password = new String(passwordField.getPassword()); // 비번 
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
               if (model.checkLogin(username, password)) {
            	   System.out.println("로그인 성공!");
            	   controller.showCard("home");
            	   textField.setText("");
            	   passwordField.setText("");
               }else {
            	   System.out.println("로그인 실패!");
            	   textField.setText("");
            	   passwordField.setText("");
              }
            }
         }
      });
      
      JLabel lblNewLabel_2 = new JLabel("");
         ImageIcon loginicon = new ImageIcon("image/사람.png");
        Image loginimage = loginicon.getImage();
        Image loginimage2 = loginimage.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        ImageIcon loginicon2 = new ImageIcon(loginimage2);
        lblNewLabel_2.setIcon(loginicon2);
         lblNewLabel_2.setBounds(80, 185, 35, 35);
         add(lblNewLabel_2);
      
      
      
         passwordField = new JPasswordField();
         passwordField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 13));
         passwordField.setBorder(compoundBorder);
         String initialText2 = "비밀번호";
         passwordField.setText(initialText2);
         passwordField.setBounds(115, 250, 200, 30);
         if (new String(passwordField.getPassword()).equals(initialText2)) {
             passwordField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
             passwordField.setEchoChar((char) 0); // 점으로 표시하지 않음
         }
         passwordField.addFocusListener(new FocusAdapter() {
             public void focusGained(FocusEvent e) {
                 if (new String(passwordField.getPassword()).equals(initialText2)) {
                    passwordField.setEchoChar('\u2022'); // 비밀번호 입력 시 점으로 표시
                    passwordField.setText(""); 
                    passwordField.setBorder(compoundBorder2);
                    passwordField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
                 }
             }

             public void focusLost(FocusEvent e) {
                 if (passwordField.getPassword().length == 0) {
                    passwordField.setEchoChar((char) 0); // 점으로 표시하지 않음
                    passwordField.setText(initialText2);
                    passwordField.setBorder(compoundBorder);
                    passwordField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
                 }
             }
         });
         add(passwordField);
      
      
      
      JButton btnNewButton = new JButton("");
      btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      btnNewButton.setBounds(75, 315, 100, 30);
      btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
       ImageIcon logbtnicon = new ImageIcon("image/로그인버튼.jpg");
       Image logbtnimg = logbtnicon.getImage();
       Image logbtnimg2 = logbtnimg.getScaledInstance(100, 30, Image.SCALE_SMOOTH);
      ImageIcon logbtnicon2 = new ImageIcon(logbtnimg2);
       btnNewButton.setIcon(logbtnicon2);
      add(btnNewButton);
      btnNewButton.addActionListener(new ActionListener() { // 로그인 버튼 누를 시 로그인 시도
          public void actionPerformed(ActionEvent e) {
              String username = textField.getText(); // 아이디 저장
              String password = new String(passwordField.getPassword()); // 비번 저장
              if (model.checkLogin(username, password)) {
                  System.out.println("로그인 성공!");
                  controller.showCard("home");
                  textField.setText("");
                  passwordField.setText("");
              } else {
                  System.out.println("로그인 실패!");
                  textField.setText("");
                  passwordField.setText("");
              }
          }
      });
      
      JButton btnNewButton_1 = new JButton("");
      btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      btnNewButton_1.setBounds(219, 315, 100, 30);
      btnNewButton_1.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
       ImageIcon pswbtnicon = new ImageIcon("image/회원가입버튼.jpg");
       Image pswbtnimg = pswbtnicon.getImage();
       Image pswbtnimg2 = pswbtnimg.getScaledInstance(100, 30, Image.SCALE_SMOOTH);
      ImageIcon pswbtnicon2 = new ImageIcon(pswbtnimg2);
      btnNewButton_1.setIcon(pswbtnicon2);
      add(btnNewButton_1);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            controller.showCard("signup");
         }
      });
      
      JLabel lblNewLabel = new JLabel("");
       lblNewLabel.setBounds(75, 50, 250, 80);
       ImageIcon daicon = new ImageIcon("image/다모아2.jpg");
       Image daimg = daicon.getImage();
       Image daimg2 = daimg.getScaledInstance(250, 80, Image.SCALE_SMOOTH);
       ImageIcon daicon2 = new ImageIcon(daimg2);
       lblNewLabel.setIcon(daicon2);
       add(lblNewLabel);
       
       JLabel lblNewLabel_3 = new JLabel("");
       lblNewLabel_3.setBounds(30, 140, 340, 250);
       ImageIcon logbkicon = new ImageIcon("image/로그인.jpg");
       Image logbkimg = logbkicon.getImage();
       Image logbkimg2 = logbkimg.getScaledInstance(340, 250, Image.SCALE_SMOOTH);
       ImageIcon logbkicon2 = new ImageIcon(logbkimg2);
       lblNewLabel_3.setIcon(logbkicon2);
       add(lblNewLabel_3);
   }
   
   private void showBtnPanel() {
	   JPanel panel1 = new JPanel();
	   panel1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	   panel1.setBackground(new Color(255, 255, 255));
	   panel1.setBounds(0, 500, 400, 70);
	   add(panel1);
	
	   JLabel lblHome = new JLabel();
	   lblHome.setBounds(0, 0, 100, 70);
	   lblHome.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	   ImageIcon homeicon = new ImageIcon("image/home.png");
	   Image imghome = homeicon.getImage();
	   Image imghome2 = imghome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	   ImageIcon imgicon2 = new ImageIcon(imghome2);
	   panel1.setLayout(null);
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
	   lblRecruitment.setBounds(100, 0, 100, 70);
	   lblRecruitment.setBorder(null);
	   ImageIcon posticon = new ImageIcon("image/post.png");
	   Image imgpost = posticon.getImage();
	   Image imgpost2 = imgpost.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	  ImageIcon posticon2 = new ImageIcon(imgpost2);
	  lblRecruitment.setIcon(posticon2);
	   lblRecruitment.setHorizontalAlignment(SwingConstants.CENTER);
	   lblRecruitment.setBackground(new Color(201, 219, 178));
	   panel1.add(lblRecruitment);
	   lblRecruitment.addMouseListener(new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	           controller.showCard("postform"); // 라벨 클릭 시 채팅 화면 보여줌
	       }
	   });
	
	   
	   JLabel lblChat = new JLabel();
	   lblChat.setBounds(200, 0, 100, 70);
	   lblChat.setBorder(new LineBorder(new Color(192, 192, 192)));
	   ImageIcon chaticon = new ImageIcon("image/chat.png");
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
	   lblMypage.setBounds(300, 0, 100, 70);
	   ImageIcon mypageicon = new ImageIcon("image/mypage.png");
	   Image imgmypage = mypageicon.getImage();
	   Image imgmypage2 = imgmypage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	  ImageIcon iconmypage2 = new ImageIcon(imgmypage2);
	  lblMypage.setIcon(iconmypage2);
	   lblMypage.setHorizontalAlignment(SwingConstants.CENTER);
	   lblMypage.setBackground(new Color(192, 192, 192));
	   panel1.add(lblMypage);
	   lblMypage.addMouseListener(new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	         controller.showCard("mypage");  
	       }
	   });
   }
}