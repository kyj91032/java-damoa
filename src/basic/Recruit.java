package basic;

import java.awt.Color; 
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class Recruit extends JPanel {
	private JTextField textField;
	private Border border;
	private App app;
	private JComboBox<String> mainComboBox;
    private JComboBox<String> subComboBox;

	public Recruit(App app) { 
		
		this.app = app;
		
		border = BorderFactory.createLineBorder(Color.BLACK); //테두리 설정
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		KategoriaComboBox(); // 카테고리 콤보박스
		
		RegionComboBox(); // 지역 콤보박스
		
		SetTitleTextField(); // 제목 텍스트필드 설정
		
		TopPanel(); // 맨위 판넬 설정 (Home.java 내용 가져옴)
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(12, 54, 378, 82);
		add(panel_1);
		panel_1.setLayout(null);
		
		SetTextArea(); //텍스트에리어 설정
		
		RecruitmentBt(app); //모집하기 버튼
		
		ReturnBt(app); //뒤로가기 버튼
		
	}

	private void RecruitmentBt(App app) { 
		JButton btnNewButton = new JButton("모집하기");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(255, 523, 133, 37);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            app.showCard("recruitComplete"); 
	        }
	    });
	}

	private void SetTextArea() {
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(10, 260, 378, 253);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		add(textArea);
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

	private void ReturnBt(App app) {  
		JButton btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(12, 523, 119, 37);
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            app.showCard("home"); // 홈 버튼 누르면 홈 화면 보여줌
	        }
	    });
	}

	private void SetTitleTextField() { 
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
		textField.setBounds(12, 143, 378, 33);
		textField.setColumns(10);
		textField.setText(" 제목 : ");
		textField.setCaretPosition(6);
		textField.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (textField.getCaretPosition() <= 6) {
		            textField.setCaretPosition(6); // 커서 위치를 고정시킴
		      }
		    }
		  }
		);
		textField.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (textField.getCaretPosition() <= 6) {
		            textField.setCaretPosition(6); // 커서 위치를 고정시킴
		        }
		    }
		});
		textField.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		add(textField);
	}

	private void RegionComboBox() {  
        String[] Region = {"지역","Seoul","Incheon", "Busan", "Daegu",
				"Gwangju","Daejeon","Ulsan","Sejong"};
        mainComboBox = new JComboBox<>(Region);
        
        String[] NullItems = {""};
        String[] SeoulRegion = {"1","2","3", "4", "5"};
        String[] IncheonRegion = {"11","22","33", "44", "55"};
        String[] BusanRegion = {"12","23","34", "45", "56"};
        
        subComboBox = new JComboBox<>(NullItems);

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
                } 
            }
        });
        mainComboBox.setBounds(12, 217, 170, 33);
        mainComboBox.setBackground(new Color(255, 255, 255));
        mainComboBox.setFont(new Font("굴림", Font.BOLD, 15));
        subComboBox.setBounds(208, 217, 182, 33);
        subComboBox.setBackground(new Color(255, 255, 255));
        subComboBox.setFont(new Font("굴림", Font.BOLD, 15));
        add(mainComboBox);
        add(subComboBox); 
 }

	private void KategoriaComboBox() {
		String[] Kategorie={"카테고리","운동하기", "공동구매", "?", "??", "???","????"};
		
		JComboBox comboBox = new JComboBox(Kategorie);
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setFont(new Font("굴림체", Font.BOLD, 15));
		comboBox.setBounds(12, 183, 378, 24);
		add(comboBox);
	}
}
