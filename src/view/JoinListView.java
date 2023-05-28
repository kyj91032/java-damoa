package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.Controller;
import model.Model;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoinListView extends JPanel {
	private Model model;
	private Controller controller;
	private Border border;
	
	public JoinListView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		
		
		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		topPanel();
		
		centerpanel();
		
		setVisible(true);
	}

	private void centerpanel() {
		
	}
	
	
	private void topPanel() {
	
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(0, 49, 400, 521);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 400, 521);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(0, 0, 400, 52);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("참여내역");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2.setBounds(50, 10, 100, 30);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("   <");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.showCard("mypage");
			}
		});
		lblNewLabel.setBounds(10, 10, 35, 35);
		panel_2.add(lblNewLabel);
		
	}
}