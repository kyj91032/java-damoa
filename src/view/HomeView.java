package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class HomeView extends JPanel {
	
	private Controller controller;
	private Model model;
	private List<PostEntity> posts;
	private String selectedValue;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private JList<ImageLabelItem> list;
	private DefaultListModel<ImageLabelItem> listModel;
	private JTextField textField;
	
	public HomeView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(Color.white);
		
		posts = model.getAllPosts();
		
		TopPanel();	
		
		Centerbtn();
		
		btnPanel();
		
	}

	
	private void TopPanel() {
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		panel_1.setLayout(null);
		
		ImageIcon liicon = new ImageIcon("image/목록.jpeg");
		Image liimage = liicon.getImage();
		Image liimage2 = liimage.getScaledInstance(30,30 , Image.SCALE_SMOOTH);
		ImageIcon liicon2 = new ImageIcon(liimage2);
				
		JLabel dmlbl = new JLabel();
		dmlbl.setBackground(new Color(240, 240, 240));
		dmlbl.setBounds(12, 0, 50, 50);
		panel_1.add(dmlbl);
		
		JButton categorytbtn = new JButton();
	    categorytbtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	JFrame newFrame = new JFrame("카테고리");
	            newFrame.setSize(300, 200);
	            newFrame.setLocationRelativeTo(null);
	            newFrame.getContentPane().setLayout(null);

	        	String[] items = {"운동하기", "공동구매", "택시"};
	        	comboBox = new JComboBox<>(items);
		        comboBox.setBounds(50, 50, 200, 30);
		        
		        comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		                // 콤보 박스 값 변경 시 실행되는 작업
		                selectedValue = (String) comboBox.getSelectedItem();
		            }
		        });
		        newFrame.getContentPane().add(comboBox);
		
		        JButton confirmButton = new JButton("확인");
		        confirmButton.setBounds(100, 100, 100, 30);
		        confirmButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                // 확인 버튼 클릭 시 실행되는 작업
		                selectedValue = (String) comboBox.getSelectedItem();
		                System.out.println("선택한 값: " + selectedValue);
		
		                newFrame.dispose(); // 프레임 닫기
		                
		                if (selectedValue == null || selectedValue.equals("전체")) {
		        	        posts = model.getAllPosts();
		        	    } else {
		        	        posts = model.getCategoryPosts(selectedValue);
		        	    }
		                
		             	// 기존의 중앙 버튼을 제거
	                    remove(scrollPane);
		                
		                Centerbtn();
		                
		                revalidate();
	                    repaint();
		                
		            }
		        });
		        newFrame.getContentPane().add(confirmButton);
		
		        newFrame.setVisible(true);
		    }
		});
		
	    
	    
	    
		categorytbtn.setForeground(new Color(255, 255, 255));
		categorytbtn.setBounds(348, 6, 30, 30);
		categorytbtn.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(categorytbtn);
		categorytbtn.setIcon(liicon2);
		
		textField = new JTextField();
		textField.setBounds(12, 10, 253, 26);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("검색");
		lblNewLabel.setBounds(273, 15, 35, 16);
		panel_1.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String searchText = textField.getText();
				posts = model.searchPost(searchText);
				
				remove(scrollPane);
                
                Centerbtn();
                
                revalidate();
                repaint();
			}
		});
	}
	
	
	
	private void Centerbtn() {
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 50, 400, 453);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		listModel = new DefaultListModel<>();
		
		
		for (PostEntity post : posts) {
	        ImageIcon imageIcon = new ImageIcon(post.getImage());

	        Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

	        // ImageLabelItem 객체를 생성하여 ImageIcon과 추가 정보를 저장합니다.
	        ImageLabelItem item = new ImageLabelItem(scaledImageIcon, post.getTitle());

	        // 리스트 모델에 ImageLabelItem을 추가합니다.
	        listModel.addElement(item);
	    }
	    
	    // JList를 생성하고 리스트 모델을 설정합니다.
	    list = new JList<>(listModel);
	    list.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
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
	    panel1.setBackground(new Color(201, 219, 178));
	    panel1.setBounds(0, 502, 400, 68);
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
	            controller.showCard("postform"); // 라벨 클릭 시 채팅 화면 보여줌
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

