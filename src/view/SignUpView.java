package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import controller.Controller;
import model.Model;
import javax.swing.JRadioButton;


public class SignUpView extends JPanel implements MouseListener {

   private Controller controller;
   private Model model;
   private JTextField idField, nicknameField, PhoneField, birthField;
   private JPasswordField passwordField;
   private JPasswordField passwordField_1;
   private JLabel lblNewLabel_3;
   
   public SignUpView(Model model, Controller controller) {
      
      this.model = model;
      this.controller = controller;
      
      setPreferredSize(new Dimension(400, 570));
      setBackground(new Color(255, 255, 255));
      setLayout(null);
      
      
      showSignupPanel();
      
   }
   
   private void showSignupPanel() {
      
      Border roundedBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
      Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
      Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, emptyBorder);
      
      Border roundedBorder2 = BorderFactory.createLineBorder(new Color(228, 204, 255), 3, true);
      Border emptyBorder2 = BorderFactory.createEmptyBorder(5, 10, 5, 10);
      Border compoundBorder2 = BorderFactory.createCompoundBorder(roundedBorder2, emptyBorder2);
    
      JPanel panel = new JPanel();
      panel.setBackground(new Color(255, 255, 255));
      panel.setLayout(null);
      panel.setPreferredSize(new Dimension(400, 700));
      panel.setBounds(-12, 0, 400, 570);
      add(panel);
      
      JLabel lblNewLabel_2_1 = new JLabel("");
      ImageIcon phoneicon = new ImageIcon("image/전화기.png");
     Image phoneimage = phoneicon.getImage();
     Image phoneimage2 = phoneimage.getScaledInstance(33, 33, Image.SCALE_SMOOTH);
     ImageIcon phoneicon2 = new ImageIcon(phoneimage2);
     lblNewLabel_2_1.setIcon(phoneicon2);
      lblNewLabel_2_1.setBounds(70, 349, 35, 35);
      panel.add(lblNewLabel_2_1);
      
      PhoneField = new JTextField();
      PhoneField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      PhoneField.setBorder(compoundBorder);
      PhoneField.setText("전화번호");
      PhoneField.setColumns(10);
      PhoneField.setBounds(105, 354, 250, 30);
      String initialText5 = "전화번호";
      if (!PhoneField.hasFocus() && PhoneField.getText().equals(initialText5)) {
         PhoneField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
      }
      PhoneField.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent e) {
            if (PhoneField.getText().equals(initialText5)) {
               PhoneField.setText("");
               PhoneField.setBorder(compoundBorder2);
               PhoneField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
            }
         }
         
         public void focusLost(FocusEvent e) {
            if (PhoneField.getText().isEmpty()) {
               PhoneField.setText(initialText5);
               PhoneField.setBorder(compoundBorder);
               PhoneField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
            }
         }
      });
      panel.add(PhoneField);
      
      nicknameField = new JTextField();
      nicknameField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      nicknameField.setBorder(compoundBorder);
      nicknameField.setText("닉네임");
      nicknameField.setColumns(10);
      nicknameField.setBounds(105, 304, 250, 30);
      String initialText4 = "닉네임";
      if (!nicknameField.hasFocus() && nicknameField.getText().equals(initialText4)) {
         nicknameField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
      }
      nicknameField.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent e) {
            if (nicknameField.getText().equals(initialText4)) {
               nicknameField.setText("");
               nicknameField.setBorder(compoundBorder2);
               nicknameField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
            }
         }
         
         public void focusLost(FocusEvent e) {
            if (nicknameField.getText().isEmpty()) {
               nicknameField.setText(initialText4);
               nicknameField.setBorder(compoundBorder);
               nicknameField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
            }
         }
      });
      panel.add(nicknameField);
      
      JLabel lblNewLabel_2 = new JLabel("");
      ImageIcon loginicon = new ImageIcon("image/사람.png");
     Image loginimage = loginicon.getImage();
     Image loginimage2 = loginimage.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
     ImageIcon loginicon2 = new ImageIcon(loginimage2);
     lblNewLabel_2.setIcon(loginicon2);
      lblNewLabel_2.setBounds(70, 299, 35, 35);
      panel.add(lblNewLabel_2);
      
      passwordField = new JPasswordField();
      passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      passwordField.setBorder(compoundBorder);
      String initialText3 = "비밀번호 확인";
      passwordField.setText(initialText3);
      passwordField.setBounds(105, 215, 250, 30);
      if (new String(passwordField.getPassword()).equals(initialText3)) {
          passwordField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
          passwordField.setEchoChar((char) 0); // 점으로 표시하지 않음
      }
      passwordField.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
              if (new String(passwordField.getPassword()).equals(initialText3)) {
                 passwordField.setEchoChar('\u2022'); // 비밀번호 입력 시 점으로 표시
                  passwordField.setText(""); 
                  passwordField.setBorder(compoundBorder2);
                  passwordField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
              }
          }

          public void focusLost(FocusEvent e) {
              if (passwordField.getPassword().length == 0) {
                 passwordField.setEchoChar((char) 0); // 점으로 표시하지 않음
                  passwordField.setText(initialText3);
                  passwordField.setBorder(compoundBorder);
                  passwordField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
              }
          }
      });
      panel.add(passwordField);
      
      JLabel lblNewLabel_1_1 = new JLabel("");
      ImageIcon passwordicon = new ImageIcon("image/자물쇠.png");
     Image passwordimage = passwordicon.getImage();
     Image passwordimage2 = passwordimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
     ImageIcon passwordicon2 = new ImageIcon(passwordimage2);
      lblNewLabel_1_1.setIcon(passwordicon2);
      lblNewLabel_1_1.setBounds(70, 210, 35, 35);
      panel.add(lblNewLabel_1_1);
      
      passwordField_1 = new JPasswordField();
      passwordField_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      passwordField_1.setBorder(compoundBorder);
      String initialText2 = "비밀번호";
      passwordField_1.setText(initialText2);
      passwordField_1.setBounds(105, 165, 250, 30);
      if (new String(passwordField_1.getPassword()).equals(initialText2)) {
          passwordField_1.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
          passwordField_1.setEchoChar((char) 0); // 점으로 표시하지 않음
      }
      passwordField_1.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
              if (new String(passwordField_1.getPassword()).equals(initialText2)) {
                 passwordField_1.setEchoChar('\u2022'); // 비밀번호 입력 시 점으로 표시
                  passwordField_1.setText(""); 
                  passwordField_1.setBorder(compoundBorder2);
                  passwordField_1.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
              }
          }

          public void focusLost(FocusEvent e) {
              if (passwordField_1.getPassword().length == 0) {
                 passwordField_1.setEchoChar((char) 0); // 점으로 표시하지 않음
                  passwordField_1.setText(initialText2);
                  passwordField_1.setBorder(compoundBorder);
                  passwordField_1.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
              }
          }
      });
      panel.add(passwordField_1);

      
      JLabel lblNewLabel_1 = new JLabel("");
      lblNewLabel_1.setBounds(70, 160, 35, 35);
     lblNewLabel_1.setIcon(passwordicon2);
      panel.add(lblNewLabel_1);
      
      idField = new JTextField();
      idField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      idField.setBorder(compoundBorder);
      idField.setText("아이디");
      idField.setColumns(10);
      idField.setBounds(105, 115, 250, 30);
      String initialText = "아이디";
      if (!idField.hasFocus() && idField.getText().equals(initialText)) {
         idField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
      }
      idField.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent e) {
            if (idField.getText().equals(initialText)) {
               idField.setText("");
               idField.setBorder(compoundBorder2);
               idField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
            }
         }
         
         public void focusLost(FocusEvent e) {
            if (idField.getText().isEmpty()) {
               idField.setText(initialText);
               idField.setBorder(compoundBorder);
               idField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
            }
         }
      });
      panel.add(idField);
      
      JLabel lblNewLabel = new JLabel("");
     lblNewLabel.setIcon(loginicon2);
      lblNewLabel.setBounds(70, 110, 35, 35);
      panel.add(lblNewLabel);
      
      birthField = new JTextField();
      birthField.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      birthField.setBorder(compoundBorder);
      birthField.setText("생년월일(6자리)");
      birthField.setColumns(10);
      birthField.setBounds(105, 404, 250, 30);
      String initialText6 = "생년월일(6자리)";
      if (!birthField.hasFocus() && birthField.getText().equals(initialText6)) {
         birthField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
      }
      birthField.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent e) {
            if (birthField.getText().equals(initialText6)) {
               birthField.setText("");
               birthField.setBorder(compoundBorder2);
               birthField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
            }
         }
         
         public void focusLost(FocusEvent e) {
            if (birthField.getText().isEmpty()) {
               birthField.setText(initialText6);
               birthField.setBorder(compoundBorder);
               birthField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
            }
         }
      });
      panel.add(birthField);
      
      JLabel lblNewLabel_2_1_3 = new JLabel("");
      ImageIcon birthicon = new ImageIcon("image/달력.png");
     Image birthimage = birthicon.getImage();
     Image birthimage2 = birthimage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
     ImageIcon birthicon2 = new ImageIcon(birthimage2);
     lblNewLabel_2_1_3.setIcon(birthicon2);
      lblNewLabel_2_1_3.setBounds(70, 399, 35, 35);
      panel.add(lblNewLabel_2_1_3);
      
      lblNewLabel_3 = new JLabel("");
      ImageIcon singupicon = new ImageIcon("image/회원가입.jpg");
     Image singupimage = singupicon.getImage();
     Image singupimage2 = singupimage.getScaledInstance(165, 50, Image.SCALE_SMOOTH);
     ImageIcon singupicon2 = new ImageIcon(singupimage2);
     lblNewLabel_3.setIcon(singupicon2);
      lblNewLabel_3.setBounds(187, 466, 168, 53);
      panel.add(lblNewLabel_3);
      lblNewLabel_3.addMouseListener(this);
      
      
      JLabel lblNewLabel_4 = new JLabel("");
      ImageIcon damoaicon = new ImageIcon("image/다모아.jpg");
     Image damoaimage = damoaicon.getImage();
     Image damoaimage2 = damoaimage.getScaledInstance(160, 80, Image.SCALE_SMOOTH);
     ImageIcon damoaicon2 = new ImageIcon(damoaimage2);
     lblNewLabel_4.setIcon(damoaicon2);
      lblNewLabel_4.setBounds(70, 5, 200, 100);
      panel.add(lblNewLabel_4);
      
      JLabel lblNewLabel_5 = new JLabel("");
      ImageIcon backicon = new ImageIcon("image/뒤로가기.png");
      Image backimage = backicon.getImage();
      Image backimage2 = backimage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
      ImageIcon backicon2 = new ImageIcon(backimage2);
      lblNewLabel_5.setIcon(backicon2);
      lblNewLabel_5.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		controller.showCard("login");
      	}
      });
      lblNewLabel_5.setBounds(15, 15, 35, 35);
      panel.add(lblNewLabel_5);
      
      
   }
   
   

	public JTextField getIdField() {
		return idField;
	}
	
	public void setIdField(JTextField idField) {
		this.idField = idField;
	}
	
	public JTextField getNicknameField() {
		return nicknameField;
	}
	
	public void setNicknameField(JTextField nicknameField) {
		this.nicknameField = nicknameField;
	}
	
	public JTextField getPhoneField() {
		return PhoneField;
	}
	
	public void setPhoneField(JTextField phoneField) {
		PhoneField = phoneField;
	}
	
	public JTextField getBirthField() {
		return birthField;
	}
	
	public void setBirthField(JTextField birthField) {
		this.birthField = birthField;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	
	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}
	
	public void setPasswordField_1(JPasswordField passwordField_1) {
		this.passwordField_1 = passwordField_1;
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj == lblNewLabel_3) {
			model.registerUser(this);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
