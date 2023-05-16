package basic;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App extends JFrame{
	
		public App(String title) {
			setBackground(new Color(255, 255, 255));
			getContentPane().setForeground(new Color(255, 255, 255));
			setTitle(title);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(400,570);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			
			
			
			// contentpane의 배경 흰색으로 변경 
			getContentPane().setBackground(Color.white);
			
			ImageIcon image = new ImageIcon("image/damoa.jpg");
			Image img = image.getImage();
	        
	        // 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
	    	Image updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	        
	        // 새로운 Image 객체로 ImageIcon 객체 생성
	        ImageIcon updateIcon = new ImageIcon(updateImg);
	        
	        JLabel imgLabel = new JLabel(updateIcon);
			getContentPane().add(imgLabel, BorderLayout.CENTER);
			
			JLabel damoaTitle = new JLabel("d a m o a",(int) CENTER_ALIGNMENT);
			damoaTitle.setBorder(BorderFactory.createEmptyBorder(25, 0, 0 , 0));
			Font damoaTitleFont = new Font("궁서",Font.ITALIC,40);
			damoaTitle.setFont(damoaTitleFont);
			getContentPane().add(damoaTitle, BorderLayout.NORTH);
			
			
			setVisible(true);
		}
		
	
	public static void main(String[] args) {
		new App("다모아 시작화면");
	}

}
