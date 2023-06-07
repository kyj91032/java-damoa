package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicScrollBarUI;

import controller.Controller;
import model.ChatRoomEntity;
import model.Model;
import model.PostEntity;
import view.ChatListView.ImageLabelItem;
import view.ChatListView.ImageLabelListCellRenderer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.JToolBar;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class HomeView extends JPanel {
	
	private Controller controller; private Model model; private List<PostEntity> posts; private String selectedValue; private JComboBox<String> comboBox; private JScrollPane scrollPane; private JList<ImageLabelItem> list; private DefaultListModel<ImageLabelItem> listModel; private JTextField textField;
	
	public HomeView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(Color.white);
		setLayout(null);
		
		posts = model.getAllPosts();
		
		TopPanel();	
		
		Centerbtn();
		
		btnPanel();
		
	}

	
	private void TopPanel() { 

		class CustomComboBoxRenderer extends BasicComboBoxRenderer {
		    @Override
		    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        if (isSelected) {
		            c.setBackground(Color.WHITE);
		            c.setForeground(new Color(228,204,255));
		        } else {
		            c.setBackground(Color.WHITE);
		            c.setForeground(Color.BLACK);
		        }
		        setBorder(BorderFactory.createLineBorder(Color.gray)); // 경계선(border) 추가
		        return c;
		    }
		}
		
		JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(360, 25, 20, 20);
        ImageIcon categoryicon = new ImageIcon("image/카테고리.png");
        Image imgcategory = categoryicon.getImage();
        Image imgcategory2 = imgcategory.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconcategory2 = new ImageIcon(imgcategory2);
        lblNewLabel_2.setIcon(iconcategory2);
        add(lblNewLabel_2);
        
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	String[] items = {"전체","운동하기", "취미", "스터디", "물품 배달", "음식 배달","택시"};
                comboBox = new JComboBox<>(items);
                int comboBoxWidth = 200;
                int comboBoxHeight = 30;
                int comboBoxX = lblNewLabel_2.getX() - comboBoxWidth;
                int comboBoxY = lblNewLabel_2.getY() + (lblNewLabel_2.getHeight() - comboBoxHeight) / 2;
                comboBox.setBounds(comboBoxX, comboBoxY, comboBoxWidth, comboBoxHeight);
                comboBox.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
                comboBox.setBackground(Color.WHITE);
                comboBox.setRenderer(new CustomComboBoxRenderer());
                comboBox.setBorder(BorderFactory.createLineBorder(Color.gray));
                comboBox.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		// 콤보 박스 값 변경 시 실행되는 작업
                		selectedValue = (String) comboBox.getSelectedItem();
                		System.out.println("선택한 값: " + selectedValue);
                		
                		if (selectedValue == null  || selectedValue.equals("전체")) {
                			posts = model.getAllPosts();

                		} else {
                			posts = model.getCategoryPosts(selectedValue);

                		}
                		comboBox.setSelectedItem(selectedValue);
                		remove(scrollPane);
                		Centerbtn();
 		                revalidate();
 	                    repaint();
                	}
                });
                add(comboBox);
                comboBox.showPopup();
            }
        });



		
        textField = new JTextField();
        textField.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        textField.setBorder(null);
        textField.setBounds(100, 25, 200, 20);
        add(textField);
        textField.setColumns(10);
        
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(330, 25, 20, 20);
        ImageIcon searchicon = new ImageIcon("image/돋보기.png");
        Image imgsearch = searchicon.getImage();
        Image imgsearch2 = imgsearch.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon iconsearch2 = new ImageIcon(imgsearch2);
        lblNewLabel_1.setIcon(iconsearch2);
        add(lblNewLabel_1);		
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String searchText = textField.getText();
				posts = model.searchPost(searchText);
				
				remove(scrollPane);
                
                Centerbtn();
                
                revalidate();
                repaint();
			}
		});
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(new Color(228, 204, 255));
        lblNewLabel.setBounds(0, 0, 400, 70);
        ImageIcon damoaicon = new ImageIcon("image/상단바.jpg");
        Image imgdamoa = damoaicon.getImage();
        Image imgdamoa2 = imgdamoa.getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        ImageIcon icondamoa2 = new ImageIcon(imgdamoa2);
        lblNewLabel.setIcon(icondamoa2);
        add(lblNewLabel);
	}
	
	
	
	private void Centerbtn() {
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 70, 400, 429);
		
		
	   // 수직 스크롤바의 모양을 사용자 정의
	    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
	      private final Dimension d = new Dimension();
	      @Override
	      protected JButton createDecreaseButton(int orientation) {
	        return new JButton() {
	          @Override
	          public Dimension getPreferredSize() {
	            return d;
	          }
	        };
	      }
	      @Override
	      protected JButton createIncreaseButton(int orientation) {
	        return new JButton() {
	          @Override
	          public Dimension getPreferredSize() {
	            return d;
	          }
	        };
	      }
	      @Override
	      protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
	        // 트랙(track) 그리기 - 여기서는 그리지 않음
	      }
	      @Override
	      protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
	        // 슬라이더(thumb) 그리기
	        Graphics2D g2 = (Graphics2D)g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        Color color = null;
	        JScrollBar sb = (JScrollBar)c;
	        if(!sb.isEnabled() || r.width > r.height) {
	          return;
	        } else if(isDragging) {
	          color = new Color(200,200,100,200); // 누르고 드래그시 ( rgb값  + 투명도  )
	        } else if(isThumbRollover()) {
	          color = new Color(228,204,255,200);  // 마우스 올린경우
	        } else {
	          color = new Color(228,204,255);  // 기본값
	        }
	        g2.setPaint(color);
	        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
	        g2.setPaint(Color.WHITE);
	        g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
	        g2.dispose();
	      }
	      @Override
	      protected void setThumbBounds(int x, int y, int width, int height) {
	        super.setThumbBounds(x,y,width,height);
	        scrollbar.repaint();
	      }
	    });
		
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
		add(scrollPane);
		
		
		listModel = new DefaultListModel<>();
		
		
		for (PostEntity post : posts) {
	        ImageIcon imageIcon = new ImageIcon(post.getImage());
			Image imageIcon2 = imageIcon.getImage();
			Image imageIcon3 = imageIcon2.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			ImageIcon imageIcon4 = new ImageIcon(imageIcon3);


	        // ImageLabelItem 객체를 생성하여 ImageIcon과 추가 정보를 저장합니다.
	        ImageLabelItem item = new ImageLabelItem(imageIcon4 , post.getTitle(), model.getNicknameById(post.getUserId()),post.getKategorie(), post.getDate());

	    
	        
	        
	        // 리스트 모델에 ImageLabelItem을 추가합니다.
	        listModel.addElement(item);
	    }
	    
	    // JList를 생성하고 리스트 모델을 설정합니다.
	    list = new JList<>(listModel);
	  //  list.setBorder(null);
	    list.setCellRenderer(new ImageLabelListCellRenderer());
	    
	    
	    list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = list.getSelectedIndex();
                if (model.isLoggedin()) { // Check if the user is logged in
                    PostEntity selectedPost = posts.get(index);
                    model.setCurrentPost(selectedPost); // currentpost가 지정됨.
                    controller.showCard("post");
                } else {
                	controller.showCard("login");
                }
            }
        });
	    
	    list.setVisibleRowCount(5);
	    list.setFixedCellHeight(100);
	    
		scrollPane.setViewportView(list);
	}
	
	
	
	
	class ImageLabelItem {
	       private ImageIcon image;
	       private String title;
	       private String username;
	       private String category;
	       private LocalDateTime time;
	       
	       public ImageLabelItem(ImageIcon image, String title, String username, String category, LocalDateTime time) {
	          this.image = image;
	          this.title = title;      
	          this.username = username;
	          this.category = category;
	          this.time = time;
	          

	       }
	       
	       public String gettitleLabel() {
	          return title;
	       }
	       
	       public String getusernameLabel() {
	    	   return username;
	       }
	       public String getcategoryLabel() {
	    	   return category;
	       }
	       public LocalDateTime gettimeLabel() {
	    	   return time;
	       }
	    }
	    
	class ImageLabelListCellRenderer extends JPanel implements ListCellRenderer<ImageLabelItem> {
	    private JLabel imageLabel;
	    private JLabel titleLabel;
	    private JLabel usernameLabel;
	    private JLabel categoryLabel;
	    private JLabel timeLabel;

	    public ImageLabelListCellRenderer() {
	        setOpaque(true);
	        
	        imageLabel = new JLabel();
	        imageLabel.setBorder(BorderFactory.createLineBorder((new Color(228,204,255)), 1));
	        add(imageLabel);

	        titleLabel = new JLabel();
	        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	        titleLabel.setForeground(Color.black);
	        add(titleLabel);
	        
	        usernameLabel = new JLabel();
	        usernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
	        usernameLabel.setForeground(Color.gray);
	        add(usernameLabel);
	        
	        categoryLabel = new JLabel();
	        categoryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
	        categoryLabel.setForeground(Color.gray);
	        add(categoryLabel);
			
			timeLabel = new JLabel();
			timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
			timeLabel.setForeground(Color.gray);
	        add(timeLabel);
			
	    }

	    @Override
	    public Component getListCellRendererComponent(JList<? extends ImageLabelItem> list,
	                                                  ImageLabelItem value, int index,
	                                                  boolean isSelected, boolean cellHasFocus) {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy년 MM월 dd일");
	        timeLabel.setText(value.gettimeLabel().format(formatter));
	    	
	        imageLabel.setIcon(value.image);
	        titleLabel.setText(value.gettitleLabel());
	        usernameLabel.setText("닉네임 : " + value.getusernameLabel());
	        categoryLabel.setText("카테고리 : " + value.getcategoryLabel());


	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }
	        if (index % 2 == 0) {
	            setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
	        } else {
	            setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
	        }

	        return this;
	    }


	    @Override
	    public void doLayout() {
	        super.doLayout();
	        
	        imageLabel.setBounds(10,10,80,80);
	        titleLabel.setBounds(100,10,300,15);
	        usernameLabel.setBounds(100,30,100,15);
	        categoryLabel.setBounds(100, 45, 100, 15);
	        timeLabel.setBounds(300,80,100,15);
	    }
	}

	    
	    private void btnPanel() {
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

