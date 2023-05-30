package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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

public class CategoryView extends JPanel {
	private Model model;
	private Controller controller;
	private Border border;
	
	public CategoryView(Model model, Controller controller) {
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
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(0, 49, 400, 521);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("카테고리 1");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(24, 23, 85, 85);
		lblNewLabel_1.setBorder(border);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("카테고리 2");
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setBounds(152, 23, 85, 85);
		lblNewLabel_1_1.setBorder(border);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("카테고리 3");
		lblNewLabel_1_2.setEnabled(false);
		lblNewLabel_1_2.setBounds(288, 23, 85, 85);
		lblNewLabel_1_2.setBorder(border);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("카테고리 4");
		lblNewLabel_1_3.setEnabled(false);
		lblNewLabel_1_3.setBounds(24, 155, 85, 85);
		lblNewLabel_1_3.setBorder(border);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("카테고리 5");
		lblNewLabel_1_4.setEnabled(false);
		lblNewLabel_1_4.setBounds(152, 155, 85, 85);
		lblNewLabel_1_4.setBorder(border);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("카테고리 6");
		lblNewLabel_1_5.setEnabled(false);
		lblNewLabel_1_5.setBounds(288, 155, 85, 85);
		lblNewLabel_1_5.setBorder(border);
		panel_1.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("카테고리 7");
		lblNewLabel_1_3_1.setEnabled(false);
		lblNewLabel_1_3_1.setBounds(24, 288, 85, 85);
		lblNewLabel_1_3.setBorder(border);
		panel_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("카테고리 8");
		lblNewLabel_1_3_2.setEnabled(false);
		lblNewLabel_1_3_2.setBounds(152, 288, 85, 85);
		lblNewLabel_1_3.setBorder(border);
		panel_1.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_3_3 = new JLabel("카테고리 9");
		lblNewLabel_1_3_3.setEnabled(false);
		lblNewLabel_1_3_3.setBounds(288, 288, 85, 85);
		lblNewLabel_1_3.setBorder(border);
		panel_1.add(lblNewLabel_1_3_3);
	}
	
	
	private void topPanel() {
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(0, 0, 400, 52);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("카테고리");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2.setBounds(50, 10, 100, 30);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("   <");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.showCard("home");
			}
		});
		lblNewLabel.setBounds(10, 10, 35, 35);
		panel_2.add(lblNewLabel);
		
	}
}