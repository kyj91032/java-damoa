package basic;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class HomeView extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomeView() {
		setLayout(null);
		setPreferredSize(new Dimension(400, 570));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 178, 128));
		panel.setBounds(0, 0, 400, 51);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 222, 134));
		panel_1.setBounds(0, 51, 400, 452);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(185, 239, 134));
		panel_2.setBounds(0, 503, 400, 67);
		add(panel_2);
		
		
	}
}
