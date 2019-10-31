package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {

	public static final int WIDTH = MyProperties.DEFAULT_WIDTH - NavPanel.WIDTH;
	public static final int HEIGHT = MyProperties.DEFAULT_HEIGHT - HeaderPanel.HEIGHT;
	
	public ContentPanel() {
		this.setLayout(new BorderLayout());
		this.setBounds(NavPanel.WIDTH, HeaderPanel.HEIGHT, WIDTH, HEIGHT);
		this.setBackground(Color.BLACK);
	}
}
