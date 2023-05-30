package view;

import javax.swing.JPanel;
import java.awt.Color;

public class ColorTest extends JPanel {

	/**
	 * Create the panel.
	 */
	public ColorTest() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(246, 234, 190));
		panel.setBounds(0, 0, 382, 44);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(200, 227, 212));
		panel_1.setBounds(0, 44, 382, 417);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(246, 215, 167));
		panel_2.setBounds(0, 460, 382, 44);
		add(panel_2);

	}
}
