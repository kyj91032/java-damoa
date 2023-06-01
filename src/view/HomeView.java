package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
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
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.border.LineBorder;

public class HomeView extends JPanel {
	
	private Controller controller;
	private Model model;
	
	public HomeView(Model model, Controller controller) {
		setBorder(null);
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(207, 197, 255));
		
		
		TopPanel();
		
		Centerbtn();
		
		btnPanel();
		
	}

	
	private void TopPanel() {
		setLayout(null);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
		
		ImageIcon scicon = new ImageIcon("image/돋보기.jpeg");
		Image scimage = scicon.getImage();
		Image scimage2 = scimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon scicon2 = new ImageIcon(scimage2);
		
		ImageIcon liicon = new ImageIcon("image/목록.jpeg");
		Image liimage = liicon.getImage();
		Image liimage2 = liimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon liicon2 = new ImageIcon(liimage2);
		
		ImageIcon alicon = new ImageIcon("image/종.jpeg");
		Image alimage = alicon.getImage();
		Image alimage2 = alimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon alicon2 = new ImageIcon(alimage2);
		
		
	}
	
	private void Centerbtn() { 
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 95, 395, 420);
		scrollPane.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
	    scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
	    scrollPane.getVerticalScrollBar().setOpaque(false);

	    // JScrollPane의 레이아웃을 사용자 정의
	    scrollPane.setLayout(new ScrollPaneLayout() {
	      @Override
	      public void layoutContainer(Container parent) {
	        JScrollPane scrollPane = (JScrollPane)parent;

	        Rectangle availR = scrollPane.getBounds();
	        availR.x = availR.y = 0;

	        Insets insets = parent.getInsets();
	        availR.x = insets.left;
	        availR.y = insets.top;
	        availR.width  -= insets.left + insets.right;
	        availR.height -= insets.top  + insets.bottom;

	        Rectangle vsbR = new Rectangle();
	        vsbR.width  = 12;
	        vsbR.height = availR.height;
	        vsbR.x = availR.x + availR.width - vsbR.width;
	        vsbR.y = availR.y;

	        if(viewport != null) {
	          viewport.setBounds(availR);
	        }
	        if(vsb != null) {
	          vsb.setVisible(true);
	          vsb.setBounds(vsbR);
	        }
	      }
	    });

	 
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
	          color = new Color(255,255,100,200);
	        } else {
	          color = new Color(220,220,200,200);
	        }
	        g2.setPaint(color);
	        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
	        g2.setPaint(Color.WHITE);
	        g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
	        g2.dispose();
	      }
	      @Override
	      protected void setThumbBounds(int x, int y, int width, int height) {
	        super.setThumbBounds(x, y, width, height);
	        scrollbar.repaint();
	      }
	    });
	    

	    add(scrollPane);
		
		
		
		DefaultListModel<ImageLabelItem> listModel = new DefaultListModel<>();

		List<PostEntity> posts = model.getAllPosts();
	    
		for (PostEntity post : posts) {
	        ImageIcon imageIcon = new ImageIcon(post.getImage());

	        // 이미지를 원하는 크기로 조정합니다.
	        Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

	        // ImageLabelItem 객체를 생성하여 ImageIcon과 추가 정보를 저장합니다.
	        ImageLabelItem item = new ImageLabelItem(scaledImageIcon, post.getTitle());

	        // 리스트 모델에 ImageLabelItem을 추가합니다.
	        listModel.addElement(item);
	    }
	    
	    // JList를 생성하고 리스트 모델을 설정합니다.
	    JList<ImageLabelItem> list = new JList<>(listModel);
	    list.setBackground(new Color(207, 197, 255));
	    list.setBorder(null);
	    list.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
	    list.setCellRenderer(new ImageLabelListCellRenderer());
	    
	    
	    list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = list.getSelectedIndex();
                if (model.isLoggedin()) { // Check if the user is logged in
                    PostEntity selectedPost = posts.get(index);
                    model.setCurrentPost(selectedPost);
                    controller.showCard("post");
                } else {
                	controller.showCard("login");
                }
            }
        });

		
		
		scrollPane.setViewportView(list);
		
		
	}
	
	class ImageLabelItem {
	    private ImageIcon image;
	    private String label;

	    public ImageLabelItem(ImageIcon image, String label) {
	        this.image = image;
	        this.label = label;	   
	    }

	    public String getLabel() {
	        return label;
	    }
	}

	class ImageLabelListCellRenderer extends JPanel implements ListCellRenderer<ImageLabelItem> {
	    private JLabel imageLabel;
	    private JLabel textLabel;
	
	    public ImageLabelListCellRenderer() {
	        setLayout(new BorderLayout());
	        setOpaque(true);
	
	        imageLabel = new JLabel();
	        textLabel = new JLabel();
	
	        add(imageLabel, BorderLayout.WEST);
	        add(textLabel, BorderLayout.CENTER);
	    }

	    @Override
	    public Component getListCellRendererComponent(JList<? extends ImageLabelItem> list, ImageLabelItem value, int index,
	                                                  boolean isSelected, boolean cellHasFocus) {
	        imageLabel.setIcon(value.image);
	        textLabel.setText(value.getLabel());

	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	        }

	        return this;
	    }
	}

	
	private void btnPanel() {  
		JPanel panel1 = new JPanel();
		panel1.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(192, 192, 192)));
	    panel1.setBackground(new Color(207, 197, 255));
	    panel1.setBounds(0, 507, 400, 63);
	    add(panel1);

	    JLabel lblHome = new JLabel();
	    lblHome.setBounds(10, 1, 80, 60);
	    lblHome.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		" home ", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblHome.getBorder()).setTitleFont(titleFont);
	    ImageIcon homeicon = new ImageIcon("image/homebutton2.png");
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
	    lblRecruitment.setBounds(110, 1, 80, 60);
	    lblRecruitment.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), "\uB4F1\uB85D", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont1 = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblRecruitment.getBorder()).setTitleFont(titleFont1);
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
	            controller.showCard("postform"); // 라벨 클릭 시 채팅 화면 보여줌
	        }
	    });

	    
	    JLabel lblChat = new JLabel();
	    lblChat.setBounds(210, 1, 80, 60);
	    lblChat.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), "\uCC44\uD305", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont2 = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblChat.getBorder()).setTitleFont(titleFont2);
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
	    lblMypage.setBounds(310, 1, 80, 60);
	    lblMypage.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), "MY", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
	    Font titleFont3 = new Font("한컴 말랑말랑 Regular", Font.BOLD, 12);
        ((TitledBorder) lblMypage.getBorder()).setTitleFont(titleFont3);
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

	    
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBackground(new Color(207, 197, 255));
	    panel_1.setBorder(null);
	    panel_1.setBounds(0, 0, 400, 53);
	    add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblNewLabel_6 = new JLabel(" 다 모 아");
	    lblNewLabel_6.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
	    lblNewLabel_6.setBounds(80, 0, 105, 50);
	    panel_1.add(lblNewLabel_6);
	    
	    JLabel damoaIcon = new JLabel();
	    damoaIcon.setBounds(3, 3, 80, 50);
	    ImageIcon imageIcon = new ImageIcon("image/newdamoaicon.png");
	    Image imgdaomoa = imageIcon.getImage();
	    Image imgdaomoa2 = imgdaomoa.getScaledInstance(80, 50, Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(imgdaomoa2);
	    // ImageIcon을 JLabel에 설정
	    damoaIcon.setIcon(imageIcon2);
	    panel_1.add(damoaIcon);
	    
	    JLabel lblNewLabel = new JLabel("최신글 목록");
	    lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 15));
	    lblNewLabel.setBounds(10, 70, 95, 20);
	    add(lblNewLabel);
	    
	    
	}
}

