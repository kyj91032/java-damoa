package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.Controller;
import model.ChatMessageEntity;
import model.ChatRoomEntity;
import model.Model;
import model.PostEntity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChatListView extends JPanel {
	private Model model;
	private Controller controller;
	
	private DefaultListModel listModel;
	private ArrayList<ChatView> chats;
	private List<ChatMessageEntity> chatmessages;
	

	public ChatListView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		TopPanel();		

		ListPanel();
		
		btnPanel();		
	}
	
	private void TopPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
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

	
	private void ListPanel() {
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(0, 50, 400, 452);
	    add(scrollPane);
	    
	    
	    DefaultListModel<ImageLabelItem> listModel = new DefaultListModel<>();

	    List<ChatRoomEntity> chatRooms = model.getChatListByUserId(model.getCurrentUserId());
	    
	    for (ChatRoomEntity chatRoom : chatRooms) {
	        ImageIcon imageIcon = new ImageIcon(chatRoom.getImage());

	        // 이미지를 원하는 크기로 조정합니다.
	        Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

	        // ImageLabelItem 객체를 생성하여 ImageIcon과 추가 정보를 저장합니다.
	        ImageLabelItem item = new ImageLabelItem(scaledImageIcon, chatRoom.getRoomName());

	        // 리스트 모델에 ImageLabelItem을 추가합니다.
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
					chatmessages = model.getCurrentChatMessageByRoomid(selectedChat.getRoomId());
					controller.showCard("chat");
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
	    lblRecruitment.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		"\uB4F1\uB85D", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
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
	    lblChat.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		"\uCC44\uD305", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
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
	    lblMypage.setBorder(new TitledBorder(new LineBorder(new Color(207, 197, 255), 3, true), 
	    		"MY", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
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
	
	}
}