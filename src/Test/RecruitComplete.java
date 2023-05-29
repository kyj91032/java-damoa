package Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RecruitComplete extends JPanel {
	private App app;
	
	public RecruitComplete(App app) {
		this.app = app;
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0, 255, 255));
		titlePanel.setBounds(10, 55, 378, 37);
		add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 378, 37);
		titlePanel.add(lblNewLabel_1);
		
		JPanel TextAreaPanel = new JPanel();
		TextAreaPanel.setBackground(new Color(255, 0, 128));
		TextAreaPanel.setBounds(10, 274, 378, 236);
		add(TextAreaPanel);
		
		JPanel KategoriePanel = new JPanel();
		KategoriePanel.setBackground(new Color(0, 128, 128));
		KategoriePanel.setBounds(10, 247, 126, 25);
		add(KategoriePanel);
		KategoriePanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 0, 126, 25);
		KategoriePanel.add(lblNewLabel_2);
		
		JPanel RegionPanel = new JPanel();
		RegionPanel.setLayout(null);
		RegionPanel.setBackground(new Color(255, 128, 64));
		RegionPanel.setBounds(136, 247, 126, 25);
		add(RegionPanel);
		
		JPanel SpecificRegionPanel = new JPanel();
		SpecificRegionPanel.setLayout(null);
		SpecificRegionPanel.setBackground(new Color(0, 128, 128));
		SpecificRegionPanel.setBounds(262, 247, 126, 25);
		add(SpecificRegionPanel);
		
		TopPanel(); // 맨위 판넬 설정 (Home.java 내용 가져옴)
		
		RecruitBt();
		
		HomeBt(app);
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

	private void RecruitBt() {
		JButton btnNewButton_1 = new JButton("채팅 참여");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(255, 523, 133, 37);
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 0));
		panel.setBounds(10, 99, 378, 141);
		add(panel);
		panel.setLayout(null);
	}

	private void HomeBt(App app) {
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(12, 523, 119, 37);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.showCard("home");
			}
		});
		add(btnNewButton);
	}
}
