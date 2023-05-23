package basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JPanel {

	/**
	 * Create the panel.
	 */
	public Start() {
		
		setPreferredSize(new Dimension(400, 570));
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		ImageIcon image = new ImageIcon("image/damoa.jpeg");
		Image img = image.getImage();
        
        // 추출된 Image의 크기 조절하여 새로운 Image 객체 생성
    	Image updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        
        // 새로운 Image 객체로 ImageIcon 객체 생성
        ImageIcon updateIcon = new ImageIcon(updateImg);
        
        JLabel imgLabel = new JLabel(updateIcon);
        
		add(imgLabel);

	}
}