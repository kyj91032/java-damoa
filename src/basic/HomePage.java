package basic;

import java.awt.Color;

import javax.swing.JFrame;

public class HomePage extends JFrame {
	public HomePage(String title) {
//	setBackground(new Color(255, 255, 255));
//	getContentPane().setForeground(new Color(255, 255, 255));
	setTitle(title);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(400,570);
	setLocationRelativeTo(null);
	setLayout(null);
	
	
	
	
	
	
	
	
	setVisible(true);
	}

	public static void main(String[] args) {
		new HomePage("다모아 홈페이지");
	}

}
