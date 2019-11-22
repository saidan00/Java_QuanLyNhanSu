package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {

	MyProps myProps = new MyProps();
	public static final int WIDTH = MyProps.DEFAULT_WIDTH - NavPanel.WIDTH;
	public static final int HEIGHT = MyProps.DEFAULT_HEIGHT - HeaderPanel.HEIGHT;

	public ContentPanel() {
		this.setLayout(new BorderLayout());
		this.setBounds(NavPanel.WIDTH, HeaderPanel.HEIGHT, WIDTH, HEIGHT);
		this.setBackground(Color.BLACK);
	}

	public void initMainContent(JPanel comp) {
		this.add(BorderLayout.CENTER, comp);
	}
}
