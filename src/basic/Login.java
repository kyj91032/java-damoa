package basic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 570);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 128, 128));
		panel_1.setBounds(0, 0, 386, 43);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 64, 0));
		panel_2.setBounds(0, 42, 386, 428);
		getContentPane().add(panel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setBounds(0, 471, 386, 62);
		getContentPane().add(panel);
		setVisible(true);
		
	}
}
