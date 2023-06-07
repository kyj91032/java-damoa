package view;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import controller.Controller;
import model.ChatMessageEntity;
import model.ChatRoomEntity;
import model.Model;
import model.PostEntity;
import view.ChatServerThread;
import view.HomeView.ImageLabelItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChatListView extends JPanel {
	private Model model; private Controller controller;private DefaultListModel listModel; private ArrayList<ChatView> chats; private List<ChatMessageEntity> chatmessages;private ServerSocket listener = null; private Socket socket = null; private JScrollPane scrollPane;

	public ChatListView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		TopPanel();

		ListPanel();
		
		btnPanel();
		
		setLayout(null);
		
	}
	
	
    	
	private void TopPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(228, 204, 255));
		panel.setBounds(0, 0, 400, 70);
		add(panel);
		panel.setLayout(null);
		
		String nickname = model.getNicknameById(model.getCurrentUserId()); // 닉네임 db에서 불러와서 보여줌
		JLabel lblNewLabel = new JLabel(model.getNicknameById(model.getCurrentUserId()) + "님의 채팅방");
		System.out.println(nickname);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 20, 370, 40);
		panel.add(lblNewLabel);
	}

	
	private void ListPanel() {
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
	    
	    
	    DefaultListModel<ImageLabelItem> listModel = new DefaultListModel<>();

	    List<ChatRoomEntity> chatRooms = model.getChatListByUserId(model.getCurrentUserId());
	    
	    for (ChatRoomEntity chatRoom : chatRooms) {
	    	
	        ImageIcon imageIcon = new ImageIcon(chatRoom.getImage());
	        Image scaledImage = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
	        int roomid = chatRoom.getRoomId();
	        

	        ImageLabelItem item = new ImageLabelItem(scaledImageIcon ,chatRoom.getRoomName(),
	        		model.getNicknameById(model.getPostBypostid(model.getPostidbyRoomid(roomid)).getUserId()) ,
	        		model.getPostBypostid(model.getPostidbyRoomid(roomid)).getKategorie(),
	        		model.getPostBypostid(model.getPostidbyRoomid(roomid)).getDate());
	        
	        
	        listModel.addElement(item);
	    }
	    
	    // JList를 생성하고 리스트 모델을 설정합니다.
	    JList<ImageLabelItem> list = new JList<>(listModel);
	    list.setCellRenderer(new ImageLabelListCellRenderer());
	    
	    list.addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				if (model.isLoggedin()) {
					ChatRoomEntity selectedChat = chatRooms.get(index);
					model.setCurrentChatRoom(selectedChat);
					int roomid = selectedChat.getRoomId();
					
					String chatViewName = "chat" + roomid;
					controller.showCard(chatViewName);
		            
                } else {
                	controller.showCard("login");
                }
	        }
	    });

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
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        timeLabel.setText(value.gettimeLabel().format(formatter));
	   	    	
	        imageLabel.setIcon(value.image);
	        titleLabel.setText(value.gettitleLabel());
	        usernameLabel.setText("방장 : " + value.getusernameLabel());
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
	        
	        imageLabel.setBounds(10,7,80,80);
	        titleLabel.setBounds(100,10,300,15);
	        usernameLabel.setBounds(100,30,100,15);
	        categoryLabel.setBounds(100, 45, 100, 15);
	        timeLabel.setBounds(350,70,100,15);
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