package view;

import java.awt.Color; 
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
import model.UserEntity;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;

public class MyPageView extends JPanel {
	
	private Model model;
	private Controller controller;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public MyPageView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(221, 238, 255));
		setLayout(null);
				
		addCenterPanel();
		
		addBtnPanel(controller);

	    setVisible(true);
	}

	private void addCenterPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(128, 128, 128)));
		panel.setBackground(new Color(228, 204, 255));
		panel.setBounds(0, 0, 400, 50);
		add(panel);
		panel.setLayout(null);
		
		String nickname = model.getNicknameById(model.getCurrentUserId()); // 닉네임 db에서 불러와서 보여줌
		JLabel lblNewLabel = new JLabel("<dynamic>");
		System.out.println(nickname);
		lblNewLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 20));
		lblNewLabel.setBounds(20, 5, 216, 40);
		panel.add(lblNewLabel);
		
		
		
	}

	
	private void addBtnPanel(Controller controller) { // 하단 4메뉴 생성 메소드
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
	    
	    JPanel panel_2 = new JPanel();
	    panel_2.setLayout(null);
	    panel_2.setBackground(new Color(228, 204, 255));
	    panel_2.setBounds(0, 49, 400, 454);
	    add(panel_2);
	    
	    
	    
	    JLabel lblNewLabel_2_2_1_1_1 = new JLabel("로그아웃") {
	    	class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area());
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};	
	    lblNewLabel_2_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2_2_1_1_1.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 17));
	    lblNewLabel_2_2_1_1_1.setBackground(Color.WHITE);
	    lblNewLabel_2_2_1_1_1.setBounds(219, 380, 140, 46);
	    panel_2.add(lblNewLabel_2_2_1_1_1);
	    lblNewLabel_2_2_1_1_1.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		model.logout();
	    		controller.showCard("login");
	    		JOptionPane.showMessageDialog(null, "로그아웃되었습니다.", "로그아웃", 
	    				JOptionPane.INFORMATION_MESSAGE);
	    	}
		});
	    
	    JLabel lblNewLabel_2_2_1_1_1_1 = new JLabel("정보 수정하기") {
	    	class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area());
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};
	    lblNewLabel_2_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2_2_1_1_1_1.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 17));
	    lblNewLabel_2_2_1_1_1_1.setBackground(Color.WHITE);
	    lblNewLabel_2_2_1_1_1_1.setBounds(37, 380, 140, 46);
	    panel_2.add(lblNewLabel_2_2_1_1_1_1);
	    lblNewLabel_2_2_1_1_1_1.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		UserEntity updateUserEntity = new UserEntity(model.getCurrentUserId(), 
	    				textField.getText(), textField_1.getText(), textField_2.getText(), 
	    				textField_3.getText(), textField_4.getText());
	    		model.updateUserData(updateUserEntity);
	    		JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.", "회원정보수정", 
	    				JOptionPane.INFORMATION_MESSAGE);
	    	}
		});
	    
	    
	    JLabel lblNewLabel_1 = new JLabel("아이디");
	    lblNewLabel_1.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    lblNewLabel_1.setBounds(37, 44, 60, 20);
	    panel_2.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
	    lblNewLabel_1_1.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    lblNewLabel_1_1.setBounds(37, 87, 60, 20);
	    panel_2.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("닉네임");
	    lblNewLabel_1_2.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    lblNewLabel_1_2.setBounds(37, 131, 60, 20);
	    panel_2.add(lblNewLabel_1_2);
	    
	    JLabel lblNewLabel_1_3 = new JLabel("전화번호");
	    lblNewLabel_1_3.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    lblNewLabel_1_3.setBounds(37, 176, 60, 20);
	    panel_2.add(lblNewLabel_1_3);
	    
	    JLabel lblNewLabel_1_4 = new JLabel("생년월일");
	    lblNewLabel_1_4.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    lblNewLabel_1_4.setBounds(37, 224, 60, 20);
	    panel_2.add(lblNewLabel_1_4);
	    
	    
	    UserEntity currentUser = model.getCurrentUser();
	    textField = new JTextField(currentUser.getUsername()) {
    	class RoundedCornerBorder extends AbstractBorder {
	    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
	    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	    	    Graphics2D g2 = (Graphics2D) g.create();
	    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
	    	    g2.setPaint(ALPHA_ZERO);
	    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
	    	    corner.subtract(new Area(border));
	    	    g2.fill(corner);
	    	    g2.setPaint(Color.GRAY);
	    	    g2.draw(border);
	    	    g2.dispose();
	    	  }
	    	  public Shape getBorderShape(int x, int y, int w, int h) {
	    	    int r = h; //h / 2;
	    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
	    	  }
	    	  public Insets getBorderInsets(Component c) {
	    	    return new Insets(4, 8, 4, 8);
	    	  }
	    	  public Insets getBorderInsets(Component c, Insets insets) {
	    	    insets.set(4, 8, 4, 8);
	    	    return insets;
	    	  }
	    	}
			 protected void paintComponent(Graphics g) {
			    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
			      Graphics2D g2 = (Graphics2D) g.create();
			      g2.setPaint(getBackground());
			      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
			          0, 0, getWidth() - 1, getHeight() - 1));
			      g2.dispose();
			    }
			    super.paintComponent(g);
			  }
			  public void updateUI() {
			    super.updateUI();
			    setOpaque(false);
			    setBorder(new RoundedCornerBorder());
			  }
			};
	  	textField.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	  	textField.setBounds(172, 41, 120, 25);
	    panel_2.add(textField);


	    textField_1 = new JTextField(currentUser.getUserpw()) {
	    	class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area(border));
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};
	    textField_1.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    textField_1.setColumns(10);
	    textField_1.setBounds(172, 84, 120, 25);
	    panel_2.add(textField_1);
	    
	    textField_2 = new JTextField(currentUser.getNickname()) {
	    	class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area(border));
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};	
	    textField_2.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    textField_2.setColumns(10);
	    textField_2.setBounds(172, 128, 120, 25);
	    panel_2.add(textField_2);
	    
	    textField_3 = new JTextField(currentUser.getPhone()) {
	    	class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area(border));
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};	
	    textField_3.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    textField_3.setColumns(10);
	    textField_3.setBounds(172, 173, 120, 25);
	    panel_2.add(textField_3);
	    
	    textField_4 = new JTextField(currentUser.getBirth()) {
	    	class RoundedCornerBorder extends AbstractBorder {
		    	  private static final Color ALPHA_ZERO = new Color(0x0, true);
		    	  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		    	    Graphics2D g2 = (Graphics2D) g.create();
		    	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    	    Shape border = getBorderShape(x, y, width - 1, height - 1);
		    	    g2.setPaint(ALPHA_ZERO);
		    	    Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		    	    corner.subtract(new Area(border));
		    	    g2.fill(corner);
		    	    g2.setPaint(Color.GRAY);
		    	    g2.draw(border);
		    	    g2.dispose();
		    	  }
		    	  public Shape getBorderShape(int x, int y, int w, int h) {
		    	    int r = h; //h / 2;
		    	    return new RoundRectangle2D.Double(x, y, w, h, r, r);
		    	  }
		    	  public Insets getBorderInsets(Component c) {
		    	    return new Insets(4, 8, 4, 8);
		    	  }
		    	  public Insets getBorderInsets(Component c, Insets insets) {
		    	    insets.set(4, 8, 4, 8);
		    	    return insets;
		    	  }
		    	}
				 protected void paintComponent(Graphics g) {
				    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
				      Graphics2D g2 = (Graphics2D) g.create();
				      g2.setPaint(getBackground());
				      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
				          0, 0, getWidth() - 1, getHeight() - 1));
				      g2.dispose();
				    }
				    super.paintComponent(g);
				  }
				  public void updateUI() {
				    super.updateUI();
				    setOpaque(false);
				    setBorder(new RoundedCornerBorder());
				  }
				};
	    textField_4.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 15));
	    textField_4.setColumns(10);
	    textField_4.setBounds(172, 221, 120, 25);
	    panel_2.add(textField_4);
	    lblMypage.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	controller.showCard("mypage");
	        }
	    });
	}
}
