package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class Chat extends JPanel {
	private Statement stmt;
	private App app;
	private Border border;
	private JTextField textField;
	private JTextArea ta;
	private JTextField tf;

	public Chat(Statement stmt, App app) {
		this.stmt = stmt;
		this.app = app;

		setPreferredSize(new Dimension(400, 570));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		border = BorderFactory.createLineBorder(Color.BLACK);
		
		topPanel();
		
		bottomPanel();
		
		tf.requestFocus();
		
	}

	private void bottomPanel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 523, 400, 47);
		panel_1.setBackground(new Color(255,255,255));
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("전송");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tf.getText().equals("")) {
					ta.append("[나] : " + tf.getText() + "\n");
				}
				tf.setText("");
				tf.requestFocus();
			}
		});
		btnNewButton_1.setBounds(333, 6, 55, 36);
		panel_1.add(btnNewButton_1);
		
		tf = new JTextField();
		tf.setBounds(12, 6, 309, 32);
		panel_1.add(tf);
		tf.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 175, 388, 345);
		add(scrollPane);
		
		ta = new JTextArea();
		ta.setEditable(false);
		ta.setLineWrap(true);
		scrollPane.setViewportView(ta);
	}

	private void topPanel() {
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.showCard("chatlist");
			}
		});
		btnNewButton.setBounds(0, 0, 41, 29);
		
		add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 31, 400, 142);
		panel.setBorder(border);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Image");
		lblNewLabel.setBounds(12, 21, 106, 100);
		lblNewLabel.setBorder(border);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(130, 21, 258, 100);
		panel.add(lblNewLabel_1);
		
	}
}
