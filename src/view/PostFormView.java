package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

import controller.Controller;
import model.Model;

import javax.swing.SwingConstants;
import java.io.ByteArrayOutputStream;


public class PostFormView extends JPanel implements ActionListener {
	
	private JTextField textField;
	private Border border;
	private JComboBox<String> mainComboBox;
    private JComboBox<String> subComboBox;
	private String userInput;
	private JComboBox<String> KategorieComboBox;
	private JTextArea textArea;
	private JButton btnNewButton_4;
	private JPanel Imagepanel;
	private JLabel ImageLabel;
	private ImageIcon defaultImageIcon;
	private String selectedImagePath;
	private Model model;
	private Controller controller;
	private byte[] imageData;
	
	
	public PostFormView(Model model, Controller controller) {
		
		this.model = model;
		this.controller = controller;
		
		border = BorderFactory.createLineBorder(Color.BLACK); // 테두리 설정
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(228, 204, 255));
		setLayout(null);
		
		KategoriaComboBox(); // 카테고리 콤보박스
		
		RegionComboBox(); // 지역 콤보박스
		
		SetTitleTextField(); // 제목 텍스트필드 설정
		
		TopPanel(controller); // 맨위 판넬 설정 (Home.java 내용 가져옴)
		
		SetImagePanel(); //이미지 판넬 설정
		
		SetTextArea(); // 텍스트에리어 설정
		
		btnPanel(); // 버튼 판넬	
		
		resetFields();// 내용초기화
	}

	private void SetImagePanel() {   
		Imagepanel = new JPanel();
		Imagepanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Imagepanel.setBackground(new Color(255, 255, 255));
		Imagepanel.setBounds(12, 89, 232, 161);
		Imagepanel.setLayout(null);
		
		defaultImageIcon = new ImageIcon("path/to/default/image.png"); // 초기 상태의 이미지 아이콘 경로 설정
		ImageLabel = new JLabel("이미지를 등록하세요", defaultImageIcon, JLabel.CENTER);
		ImageLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.PLAIN, 13));
		ImageLabel.setBounds(10, 0, 232, 160);
		Imagepanel.add(ImageLabel);
		Imagepanel.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            int result = fileChooser.showOpenDialog(Imagepanel);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                selectedImagePath = selectedFile.getAbsolutePath(); // 선택한 이미지 파일의 경로 저장
	                try {
	                    // 이미지 로드
	                    BufferedImage image = ImageIO.read(selectedFile);
	                    
	                    // 이미지 db에 저장하기 위해 byte[]로 변환
	                    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	                    ImageIO.write(image, "png", baos);
	                    baos.flush();
	                    imageData = baos.toByteArray();
	                    baos.close();

	                    // 패널 크기에 맞게 이미지 조정
	                    Image scaledImage = image.getScaledInstance(Imagepanel.getWidth(),
	                    		Imagepanel.getHeight(), Image.SCALE_SMOOTH);

	                    // 조정된 이미지를 JLabel에 표시
	                    ImageIcon imageIcon = new ImageIcon(scaledImage);
	                    ImageLabel.setIcon(imageIcon);
	                    ImageLabel.setText(""); // 텍스트 비우기

	                    Imagepanel.revalidate();
	                    Imagepanel.repaint();
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	    });
		
	    ImageLabel.setBounds(0, 0, 232, 161);
	    Imagepanel.add(ImageLabel);
		add(Imagepanel);
	}

	private void SetTextArea() {     
		textArea = new JTextArea();
		textArea.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(10, 260, 380, 235);
		String initialText = " 내용을 입력하세요 : ";
		textArea.setText(initialText);
	    if (!textArea.hasFocus() && textArea.getText().equals(initialText)) {
	    	textArea.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	    }

	    // FocusListener 추가
	    textArea.addFocusListener(new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	            if (textArea.getText().equals(initialText)) {
	            	textArea.setText("");
	            	textArea.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	        }

	        public void focusLost(FocusEvent e) {
	            if (textArea.getText().isEmpty()) {
	            	textArea.setText(initialText);
	            	textArea.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	        }
	    });
		textArea.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(128, 128, 128)));
		add(textArea);
	}

	private void TopPanel(Controller controller) {     
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(128, 128, 128)));
		panel_1.setBackground(new Color(228, 204, 255));
		panel_1.setBounds(0, 0, 400, 41);
		add(panel_1);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
		panel_1.setLayout(null);
		
		JLabel lblDamoa = new JLabel("모집하기");
		lblDamoa.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDamoa.setBounds(165, 10, 70, 25);
		lblDamoa.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 19));
		panel_1.add(lblDamoa);
		
		btnNewButton_4 = new JButton("완료") {
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
		btnNewButton_4.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 12));
		btnNewButton_4.setForeground(new Color(0, 0, 255));
		btnNewButton_4.setBounds(330, 10, 50, 25);
		btnNewButton_4.setBackground(new Color(228, 204, 255));
		panel_1.add(btnNewButton_4);
		btnNewButton_4.addActionListener(this);
		
	}

	private void SetTitleTextField() {      
		textField = new JTextField(20);
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 17));
		textField.setBounds(10, 50, 380, 30);
		textField.setColumns(10);
		String initialText = " 제목 : ";
	    textField.setText(initialText);
	    if (!textField.hasFocus() && textField.getText().equals(initialText)) {
	        textField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	    }

	    // FocusListener 추가
	    textField.addFocusListener(new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	            if (textField.getText().equals(initialText)) {
	                textField.setText("");
	                textField.setForeground(Color.BLACK); // 원래 텍스트 색상으로 변경
	            }
	        }

	        public void focusLost(FocusEvent e) {
	            if (textField.getText().isEmpty()) {
	                textField.setText(initialText);
	                textField.setForeground(Color.GRAY); // 회색으로 텍스트 색상 변경
	            }
	        }
	    });

		textField.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(128, 128, 128)));
		add(textField);
	}

	private void RegionComboBox() {      
        String[] Region = {"지역","Seoul","Incheon", "Busan", "Daegu",
						   "Gwangju","Daejeon","Ulsan","Sejong"};
        mainComboBox = new JComboBox(Region);
        
        String[] NullItems = {""};
        
        String[] SeoulRegion = {"강서구","양천구","구로구","영등포구","금천구","동작구","관악구","노원구","서초구","마포구",
        						"서대문구","은평구","종로구","중구","용산구","성동구","중량구","광진구","강동구","송파구",
        						"강남구","서초구","관악구","동작구","금천구"};
        Comparator<String> koreanComparator = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(SeoulRegion, koreanComparator);

        String[] IncheonRegion = {"중구","서구","강화군","옹진군","동구","연수구","남동구","부평구","계양구","남구",};
        Comparator<String> koreanComparator1 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(IncheonRegion, koreanComparator1);
        
        String[] BusanRegion = {"강서구","사하구","사상구", "북구", "금정구","동래구","연제구","부산진구","동구","서구","중구",
        		"영도구","남구","수영구","연제구","해운대구","기장군"};
        Comparator<String> koreanComparator2 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(BusanRegion, koreanComparator2);
        
        String[] DaeguRegion = {"달성군","달서구","서구","중구","남구","수성구","동구","북구"};
        Comparator<String> koreanComparator3 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(DaeguRegion, koreanComparator2);
        
        String[] GwangjuRegion = {"광산구","서구","남구","동구","북구"};
        Comparator<String> koreanComparator4 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(GwangjuRegion, koreanComparator2);
        
        String[] DaejeonRegion = {"유성구","대덕구","서구","중구","동구"};
        Comparator<String> koreanComparator5 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(DaejeonRegion, koreanComparator2);
        
        String[] UlsanRegion = {"을주군","중구","남구","북구","동구"};
        Comparator<String> koreanComparator6 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(UlsanRegion, koreanComparator2);
        
        String[] SejongRegion = {"소정면","전의면","전동면","연서면","조치원읍","장군면","연동면","부강면","금남면","한솔동",
        		"연기면","도담동","아름동"};
        Comparator<String> koreanComparator7 = (s1, s2) -> s1.compareTo(s2); //오름차순 정렬
        Arrays.sort(SejongRegion, koreanComparator2);
        
        subComboBox = new JComboBox(NullItems);

        mainComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) mainComboBox.getSelectedItem();
                if (selectedCategory.equals("지역")) {
                    subComboBox.removeAllItems();
                    for (String 지역 : NullItems) {
                        subComboBox.addItem(지역);
                    }
                } else if (selectedCategory.equals("Seoul")) {
                    subComboBox.removeAllItems();
                    for (String Seoul : SeoulRegion) {
                        subComboBox.addItem(Seoul);
                    }
                } else if (selectedCategory.equals("Incheon")) {
                    subComboBox.removeAllItems();
                    for (String Incheon : IncheonRegion) {
                        subComboBox.addItem(Incheon);
                    }
                } else if (selectedCategory.equals("Busan")) {
                    subComboBox.removeAllItems();
                    for (String Busan : BusanRegion) {
                        subComboBox.addItem(Busan);
                    }
                } else if (selectedCategory.equals("Daegu")) {
                    subComboBox.removeAllItems();
                    for (String Daegu : DaeguRegion) {
                        subComboBox.addItem(Daegu);
                    }
                } else if (selectedCategory.equals("Gwangju")) {
                    subComboBox.removeAllItems();
                    for (String Gwangju : GwangjuRegion) {
                        subComboBox.addItem(Gwangju);
                    }
                } else if (selectedCategory.equals("Daejeon")) {
                    subComboBox.removeAllItems();
                    for (String Daejeon : DaejeonRegion) {
                        subComboBox.addItem(Daejeon);
                    }
                } else if (selectedCategory.equals("Ulsan")) {
                    subComboBox.removeAllItems();
                    for (String Ulsan : UlsanRegion) {
                        subComboBox.addItem(Ulsan);
                    }
                } else if (selectedCategory.equals("Sejong")) {
                    subComboBox.removeAllItems();
                    for (String Sejong : SejongRegion) {
                        subComboBox.addItem(Sejong);
                    }
                } 
            }
        }); 
        mainComboBox.setBounds(257, 135, 133, 33);
        mainComboBox.setBackground(new Color(255, 255, 255));
        mainComboBox.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
        subComboBox.setBounds(257, 178, 133, 33);
        subComboBox.setBackground(new Color(255, 255, 255));
        subComboBox.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
        add(mainComboBox);
        add(subComboBox);
 }

	private void KategoriaComboBox() {       
		String[] Kategorie={"카테고리","운동하기", "공동구매", "?", "??", "???","택시"};
		String[] partialArray = Arrays.copyOfRange(Kategorie, 1, Kategorie.length);
		Comparator<String> koreanComparator = (s1, s2) -> s1.compareTo(s2);
		Arrays.sort(partialArray, koreanComparator);
		System.arraycopy(partialArray, 0, Kategorie, 1, partialArray.length);
		KategorieComboBox = new JComboBox(Kategorie);
		KategorieComboBox.setBackground(new Color(255, 255, 255));
		KategorieComboBox.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 15));
		KategorieComboBox.setBounds(257, 92, 133, 33);
		add(KategorieComboBox);
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
		    	   resetFields();
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
		    	  resetFields();
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
		    	   resetFields();
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
		    	  resetFields();
		         controller.showCard("mypage");  
		       }
		   });
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnNewButton_4) {
			resetFields();
			model.postForm(this);
			controller.showCard("home");
		}
		
	}
	

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JComboBox<String> getMainComboBox() {
		return mainComboBox;
	}

	public void setMainComboBox(JComboBox<String> mainComboBox) {
		this.mainComboBox = mainComboBox;
	}

	public JComboBox<String> getSubComboBox() {
		return subComboBox;
	}

	public void setSubComboBox(JComboBox<String> subComboBox) {
		this.subComboBox = subComboBox;
	}

	public JComboBox<String> getKategorieComboBox() {
		return KategorieComboBox;
	}

	public void setKategorieComboBox(JComboBox<String> kategorieComboBox) {
		KategorieComboBox = kategorieComboBox;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	private void resetFields() { 
        textField.setText(" 제목 : ");
        selectedImagePath = null; // 이미지 경로 초기화
        ImageLabel.setIcon(defaultImageIcon); // 초기 상태 이미지로 설정
        ImageLabel.setText("이미지를 등록하세요"); // 텍스트 초기화
        mainComboBox.setSelectedIndex(0);
        KategorieComboBox.setSelectedIndex(0);
        textArea.setText(" 내용을 입력하세요 : ");
    }
}



