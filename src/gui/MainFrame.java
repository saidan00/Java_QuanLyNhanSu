package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	HeaderPanel header;
	JPanel nav;
	JPanel navContainer;
	ArrayList<JButton> navLinks;
	
	public MainFrame() {
		init();
	}
	
	public void init() {
		this.setLayout(null);
		
		this.setSize(MyProperties.DEFAULT_WIDTH, MyProperties.DEFAULT_HEIGHT);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		
		initHeader();
		centeredFrame();
	}
	
	public void initHeader() {
		header = new HeaderPanel();
		this.add(header);
		header.setBounds(0, 0, MyProperties.DEFAULT_WIDTH, header.HEIGHT);
		
		header.minimizeAction(this);
	}
	
	public void centeredFrame() {
		// centered frame
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		int xLocation = (screenWidth - MyProperties.DEFAULT_WIDTH) / 2;
		int yLocation = ((screenHeight - MyProperties.DEFAULT_HEIGHT) / 2 - 25);
		this.setLocation(xLocation,yLocation);
	}
}
