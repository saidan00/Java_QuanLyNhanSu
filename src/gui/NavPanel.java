package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavPanel extends JPanel {
	ArrayList<JButton> navLink;
	MyProps myProps = new MyProps();

	public static final int WIDTH = MyProps.DEFAULT_WIDTH * 20 / 100;
	public static final int HEIGHT = MyProps.DEFAULT_HEIGHT - HeaderPanel.HEIGHT;

//	private final String HOME = "Home";
	private final String NHAN_VIEN = "Nhân viên";
	private final String PHONG_BAN = "Phòng ban";
	private final String CHAM_CONG = "Chấm công";
	private final String LUONG = "Lương";
	private final String KHEN_THUONG = "Khen thưởng";
	private final String THONG_KE = "Thống kê";

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
		lbl.add(NHAN_VIEN);
		lbl.add(PHONG_BAN);
		lbl.add(LUONG);
		lbl.add(CHAM_CONG);
		lbl.add(KHEN_THUONG);
		lbl.add(THONG_KE);

		JButton btnTemp;

		int arrSize = lbl.size();
		for (int i = 0; i < arrSize; i++) {
			btnTemp = new JButton();
			navLink.add(btnTemp);
			initBtn(navLink.get(i), lbl.get(i), i);
		}

		// add to panel
		for (int i = 0; i < arrSize; i++) {
			this.add(navLink.get(i));
		}
	}

	// Khởi tạo các button
	private void initBtn(JButton btn, String lbl, int index) {
		btn.setText(lbl);
		btn.setBackground(Color.decode(myProps.Color_Teal));
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial Nova", Font.BOLD, 20));

		int marginTop = MyProps.DEFAULT_HEIGHT - HEIGHT + 10 + index * 75;
		Rectangle rec = new Rectangle(0, marginTop, WIDTH, 75);
		btn.setBounds(rec);

		btn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(myProps.Color_Teal_Light)));

		myProps.BtnFlat(btn);

		btn.setBorderPainted(true);

		myProps.BtnHover(btn, Color.decode(myProps.Color_Teal_Light));
	}

	public void addSwitchPanel(JPanel contentPanel) {
		JButton btnTemp = new JButton();
		int navSize = navLink.size();
		for (int i = 0; i < navSize; i++) {
			btnTemp = navLink.get(i);
			addBtnClicked(contentPanel, btnTemp);
		}
	}

	private void addBtnClicked(JPanel contentPanel, JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String lbl = btn.getText();
				switch (lbl) {
				case NHAN_VIEN:
					NhanVienGUI nvGUI = new NhanVienGUI();
					switchPanel(contentPanel, nvGUI);
					break;
				case PHONG_BAN:
					PhongBanGUI pbGUI = new PhongBanGUI();
					switchPanel(contentPanel, pbGUI);
					break;
				case CHAM_CONG:
					ChamCongGUI ccGUI = new ChamCongGUI();
					switchPanel(contentPanel, ccGUI);
					break;
				case LUONG:
					LuongGUI luongGUI = new LuongGUI();
					switchPanel(contentPanel, luongGUI);
					break;	
				case KHEN_THUONG:
					KhenThuongGUI ktGUI = new KhenThuongGUI();
					switchPanel(contentPanel, ktGUI);
					break;	
				case THONG_KE:
					ThongKeGUI tkGUI = new ThongKeGUI();
					switchPanel(contentPanel, tkGUI);
					break;
				default:
					break;
				}
				setActiveBackground(btn);
			}
		});
	}

	private void switchPanel(JPanel parentPanel, JPanel childPanel) {
		// removing panel
		parentPanel.removeAll();
		parentPanel.repaint();
		parentPanel.revalidate();

		// adding panel
		parentPanel.add(childPanel);
		parentPanel.repaint();
		parentPanel.revalidate();
	}

	private void setActiveBackground(JButton btn) {
		int navSize = navLink.size();
		for (int i = 0; i < navSize; i++) {
			navLink.get(i).setBackground(Color.decode(myProps.Color_Teal));
			myProps.BtnHover(navLink.get(i), Color.decode(myProps.Color_Teal_Light));
		}

		btn.setBackground(Color.decode(myProps.Color_Teal_Light));
		myProps.BtnHover(btn, Color.decode(myProps.Color_Teal_Light));
	}
}
