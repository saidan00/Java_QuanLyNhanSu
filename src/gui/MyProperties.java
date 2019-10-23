package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MyProperties {
	static final int DEFAULT_WIDTH = 1280;
	static final int DEFAULT_HEIGHT = 720;
	static final Font DEFAULT_FONT = new Font("Verdana", Font.PLAIN, 20);
	static final String Color_Teal = "#00796b";
	static final String Color_Teal_Dark = "#004c40";
	static final String Color_Teal_Light = "#48a999";
	
	
	public static void DEFAULT_BUTTON(JButton btn, String background, String foreground, Rectangle rec) {
		
	}
	
	public static void BtnFlat(JButton btn) {
		// flat style
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
	}
	
	public static void BtnHover(JButton btn) {
		Color originColor = btn.getBackground(); 
		
		btn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setBackground(Color.GRAY);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(originColor);
		    }
		});
	}
	
	public static void BtnHover(JButton btn, Color hoverColor) {
		Color originColor = btn.getBackground(); 
		
		btn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(originColor);
		    }
		});
	}
}
