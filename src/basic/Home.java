package basic;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;
import javax.swing.JToolBar;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;

public class Home extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 570);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 386, 48);
		getContentPane().add(panel_1);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\hhee4\\OneDrive\\바탕 화면\\학교\\2-1\\JAVA\\java code\\java_Damoa\\java-damoa\\image\\damoa.jpg");
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(50,50 , Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(newImage);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(304, 10, 29, 30);
		panel_1.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(12, 0, 50, 50);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(resizedIcon);
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setEnabled(false);
		btnNewButton_1_1.setBounds(345, 10, 29, 30);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("New button");
		btnNewButton_1_2.setEnabled(false);
		btnNewButton_1_2.setBounds(263, 10, 29, 30);
		panel_1.add(btnNewButton_1_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 48, 386, 422);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 5, 386, 417);
		panel_2.add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"ㅁ", "ㅁ", "ㅁ", "", "ㅁ", "ㅁ", "ㅁ", "ㅁㅁ", "ㅁ", "ㅁㅁ", "ㅁ", "ㅁㅁ", "", "ㅁㅁ", "ㅁㅁ", "", "ㅁ", "ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ", "", "", "ㅁ", "ㅁ", "ㅁ", "ㅁ", "ㅁ", "ㅁ", "ㅁ", "", "ㅁ", "ㅁ", "ㅁ", "ㅁ", "ㅁㅁ", "", "ㅁ", "ㅁ", "ㅁ", "", "", "ㅁ", "ㅁ", "ㅁ", "ㅁ", "ㅁ"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 471, 386, 62);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		setVisible(true);
		
		
		
	}
}
