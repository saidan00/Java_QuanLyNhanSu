package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class MyProps {
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
	
	public static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public static GridBagConstraints MyGridBagConstraints(int x, int y, int width, int height, boolean isFillWidth, boolean isFillHeight) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = width;
		cons.gridheight = height;
		
		if (isFillWidth == true && isFillHeight == false) {
			cons.fill = GridBagConstraints.HORIZONTAL;
		} else if (isFillWidth == false && isFillHeight == true) {
			cons.fill = GridBagConstraints.VERTICAL;
		} else if (isFillWidth == true && isFillHeight == true) {
			cons.fill = GridBagConstraints.BOTH;
		}
		
		return cons;
	}
}
