package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import util.RoundedCornerBorder;

public class MyProps {
	public static final int DEFAULT_WIDTH = 1280;
	public static final int DEFAULT_HEIGHT = 720;
	public final Font DEFAULT_FONT = new Font("Arial Nova", Font.PLAIN, 20);
	public final Font DEFAULT_FONT_MEDIUM = new Font("Arial Nova", Font.PLAIN, 16);
	public final Font DEFAULT_FONT_SMALL = new Font("Arial Nova", Font.PLAIN, 12);
	public final Font DEFAULT_FONT_SMALL_BOLD = new Font("Arial Nova", Font.BOLD, 12);
	public final String Color_Teal = "#00796b";
	public final String Color_Teal_Dark = "#004c40";
	public final String Color_Teal_Light = "#48a999";

	public void DEFAULT_BUTTON(JButton btn, String background, String foreground, Rectangle rec) {

	}

	public String currentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String currentDate = dtf.format(now);
		return currentDate;
	}

	public void BtnFlat(JButton btn) {
		// flat style
//		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
	}

	public void BtnHover(JButton btn) {
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

	public void BtnHover(JButton btn, Color hoverColor) {
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

	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	public GridBagConstraints MyGridBagConstraints(int x, int y, int width, int height, boolean isFillWidth,
			boolean isFillHeight) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(10, 10, 10, 10);
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

	public JTextField RoundedTextField(int columns) {
		JTextField txt = new JTextField(columns) {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};

		return txt;
	}
}
