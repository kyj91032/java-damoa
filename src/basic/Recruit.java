package basic;

import java.awt.Color;  
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingConstants;

public class Recruit extends JPanel {
	private JTextField textField;
	private Border border;
	private App app;
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
	
	public Recruit(App app) { 
		
		this.app = app;
		
		border = BorderFactory.createLineBorder(Color.BLACK); //테두리 설정
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		KategoriaComboBox(); // 카테고리 콤보박스
		
		RegionComboBox(); // 지역 콤보박스
		
		SetTitleTextField(); // 제목 텍스트필드 설정
		
		TopPanel(app); // 맨위 판넬 설정 (Home.java 내용 가져옴)
		
		SetImagePanel(); //이미지 판넬 설정
		
		SetTextArea(); // 텍스트에리어 설정
		
		btnPanel(); // 버튼 판넬
		
		resetFields();
		
		
		JButton btnNewButton_2 = new JButton("<");
		btnNewButton_2.setBounds(257, 221, 40, 33);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(">");
		btnNewButton_3.setBounds(348, 221, 40, 33);
		add(btnNewButton_3);
		
	}

	private void SetImagePanel() {   
		Imagepanel = new JPanel();
		Imagepanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Imagepanel.setBackground(new Color(255, 255, 255));
		Imagepanel.setBounds(12, 89, 232, 161);
		Imagepanel.setLayout(null);
		
		defaultImageIcon = new ImageIcon("path/to/default/image.png"); // 초기 상태의 이미지 아이콘 경로 설정
		ImageLabel = new JLabel("이미지를 등록하세요", defaultImageIcon, JLabel.CENTER);
		ImageLabel.setBounds(0, 0, 232, 161);
		Imagepanel.add(ImageLabel);
		Imagepanel.addMouseListener(new MouseAdapter() {

			@Override
	        public void mouseClicked(MouseEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            int result = fileChooser.showOpenDialog(Imagepanel);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                selectedImagePath = selectedFile.getAbsolutePath(); // 선택한 이미지 파일의 경로 저장

	                try {
	                    // 이미지 로드
	                    BufferedImage image = ImageIO.read(selectedFile);

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
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(10, 260, 380, 234);
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
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		add(textArea);
	}

	private void TopPanel(App app) {     
		setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 400, 50);
		add(panel_1);
		
		ImageIcon daicon = new ImageIcon("image/damoa.jpeg");
		Image daimage = daicon.getImage();
		Image daimage2 = daimage.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon daicon2 = new ImageIcon(daimage2);
		panel_1.setLayout(null);
		
		JLabel dmlbl = new JLabel();
		dmlbl.setBounds(0, 0, 50, 45);
		dmlbl.setBackground(new Color(240, 240, 240));
		panel_1.add(dmlbl);
		dmlbl.setIcon(daicon2);
		
		JLabel lblDamoa = new JLabel("다모아 모집하기");
		lblDamoa.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDamoa.setBounds(124, 13, 132, 23);
		lblDamoa.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
		panel_1.add(lblDamoa);
		
		btnNewButton_4 = new JButton("완료");
		btnNewButton_4.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnNewButton_4.setForeground(new Color(0, 0, 255));
		btnNewButton_4.setBounds(327, 10, 61, 30);
		panel_1.add(btnNewButton_4);
		
		btnNewButton_4.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		String sql = "INSERT INTO posttable (title, kategorie, region, specificregion, textarea) VALUES (?, ?, ?, ?, ?)";
	   		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/damoa",
	   				 "root", "1234");
	   	             PreparedStatement statement = connection.prepareStatement(sql)) {
	   	            // 값 설정
	   	            statement.setString(1, textField.getText()); // JTextField에서 입력된 값
	   	            statement.setString(2, (String) KategorieComboBox.getSelectedItem()); // JComboBox에서 선택된 값
	   	            statement.setString(3, (String) mainComboBox.getSelectedItem()); // JComboBox에서 선택된 값
	   	            statement.setString(4, (String) subComboBox.getSelectedItem()); // JComboBox에서 선택된 값
	   	            statement.setString(5, textArea.getText()); // JTextArea에서 입력된 값

	   	            // 쿼리 실행
	   	            statement.executeUpdate();

	   	            System.out.println("값이 성공적으로 저장되었습니다.");
	   	        } catch (SQLException ex) {
	   	            System.out.println("값을 저장하는 동안 오류가 발생했습니다: " + ex.getMessage());
	   	        }
	        	app.showCard("recruitComplete"); 
	        	resetFields(); // 필드 초기화 메소드 호출
	        }
	    });
	}

	private void SetTitleTextField() {      
		textField = new JTextField(20);
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
		textField.setBounds(10, 56, 382, 26);
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

		textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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
        mainComboBox.setFont(new Font("굴림", Font.BOLD, 15));
        subComboBox.setBounds(257, 178, 133, 33);
        subComboBox.setBackground(new Color(255, 255, 255));
        subComboBox.setFont(new Font("굴림", Font.BOLD, 15));
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
		KategorieComboBox.setFont(new Font("굴림체", Font.BOLD, 15));
		KategorieComboBox.setBounds(257, 92, 133, 33);
		add(KategorieComboBox);
	}

	private void btnPanel() { 
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 500, 400, 70);
		add(panel);
		panel.setLayout(new GridLayout(1, 4));
		
		JButton btnNewButton_2 = new JButton("홈");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            app.showCard("home"); // 홈 버튼 누르면 홈 화면 보여줌
	            resetFields(); // 필드 초기화 메소드 호출
	        }
	    });

		
		JButton btnNewButton_4 = new JButton("모집하기");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            app.showCard("recruit"); // if 로그인이 안돼있다면 실행으로. 추가 예정
	            resetFields(); // 필드 초기화 메소드 호출
	        }
	    });
		
		
		JButton btnNewButton_3 = new JButton("채팅");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.showCard("chatlist");
				resetFields(); // 필드 초기화 메소드 호출
			}
		});
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("마이페이지");
		btnNewButton.setBackground(new Color(255, 255, 255));
		panel.add(btnNewButton);
		setVisible(true);
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            app.showCard("login"); // if 로그인이 안돼있다면 실행으로. 추가 예정
	            resetFields(); // 필드 초기화 메소드 호출
	        }
	    });
	}
	
	
	private void resetFields() { 
        textField.setText(" 제목 : ");
        selectedImagePath = null; // 이미지 경로 초기화
        ImageLabel.setIcon(defaultImageIcon); // 초기 상태 이미지로 설정
        ImageLabel.setText("이미지를 등록하세요"); // 텍스트 초기화
        //mainComboBox.setSelectedItem(0);
        //KategorieComboBox.setSelectedItem(null);
        textArea.setText(" 내용을 입력하세요 : ");
    }
}



