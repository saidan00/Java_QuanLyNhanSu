package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavPanel extends JPanel {
	ArrayList<JButton> navLink;
	MyProps myProps = new MyProps();
	
	public static final int WIDTH = MyProps.DEFAULT_WIDTH * 20 / 100;
	public static final int HEIGHT = MyProps.DEFAULT_HEIGHT - HeaderPanel.HEIGHT;
	
	public NavPanel() {
		this.setLayout(null);
		this.setBounds(0, HeaderPanel.HEIGHT, WIDTH, HEIGHT);
		this.setBackground(Color.decode(myProps.Color_Teal));
		
		initNavLink();
	}
	
	// Khởi tạo các link của navigations
	private void initNavLink() {
		navLink = new ArrayList<JButton>();
		
		ArrayList<String> lbl = new ArrayList<String>();
		lbl.add("Home");
		lbl.add("Nhân viên");
		lbl.add("Thống kê");
		
		JButton btnTemp;
		
		int arrSize = lbl.size();
		for (int i = 0; i < arrSize; i++) {
			btnTemp = new JButton();
			navLink.add(btnTemp);
			initBtn(navLink.get(i), lbl.get(i), i);
		}
	}
	
	// Khởi tạo các button
	private void initBtn(JButton btn, String lbl, int index) {
		btn.setText(lbl);
		btn.setBackground(Color.decode(myProps.Color_Teal));
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Verdana", Font.BOLD, 20));

		int marginTop = MyProps.DEFAULT_HEIGHT - HEIGHT + 10 + index * 75;
		Rectangle rec = new Rectangle(0, marginTop, WIDTH, 75);
		btn.setBounds(rec);
		
		btn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(myProps.Color_Teal_Light)));
		
		myProps.BtnFlat(btn);
		
		btn.setBorderPainted(true);
		
		myProps.BtnHover(btn, Color.decode(myProps.Color_Teal_Light));
		
		this.add(btn);
	}
}
